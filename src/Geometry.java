

import org.la4j.vector.*;
import org.la4j.vector.dense.*;

public class Geometry {
    
    public static Vector[] getRayByPixel(double i, double j,
            double iMax, double jMax, double fovDeg) {
        
        double iMin = 0;
        double jMin = 0;
        
        double imgWidth = iMax - iMin;
        double imgHeight = jMax - jMin;
        
        double aspectRatio = imgWidth / imgHeight;
        
        // lookAt is assumed to be (0, 0, 0).
        // lookFrom is assumed to be on z axis.
        
        double fovRad = fovDeg * (Math.PI / 180);
        
        double windowWidth = 2 * Math.tan(fovRad / 2) * Config.getLookFromZ();
        double windowHeight = windowWidth * (1 / aspectRatio);
        
        double uMax = windowWidth / 2;
        double uMin = -windowWidth / 2;
        
        double vMax = windowHeight / 2;
        double vMin = -windowHeight / 2;
        
        double u = (i - iMin) * ((uMax - uMin) / (iMax - iMin)) + uMin;
        double v = (j - jMin) * ((vMax - vMin) / (jMax - jMin)) + vMin;
        double w = 0;
        
        // Window is already on xy plane and centered, so we can omit the
        // window to world transformation.
        
        Vector o = Config.getLookFrom();
        
        Vector d = new BasicVector(
                new double[] {u, v, w - Config.getLookFromZ()});
        
        return new Vector[] {o, d};
        
    }
    
    public static Vector getSphereInters(Vector[] ray, Sphere sph) {
        
        Vector o = ray[0];
        Vector d = ray[1];
        
        //o = o.add(d.multiply(0.001));
        
        double a = d.innerProduct(d);
        //System.out.println("a:" + a);
        double b =
                o.subtract(sph.getCenter()).multiply(2).innerProduct(d);
        //System.out.println("b:" + b);
        double c =
                (o.subtract(sph.getCenter()).
                innerProduct(o.subtract(sph.getCenter())) -
                Math.pow(sph.getRadius(), 2));
        //System.out.println("c:" + c);
        
        double discr = Math.pow(b, 2) - 4 * a * c;
        //System.out.println("Discr:" + discr);
        if (discr < 0) {
            return null;
        }
        
        double t0 = (-b - Math.sqrt(discr)) / (2 * a);
        double t1 = (-b + Math.sqrt(discr)) / (2 * a);
        
        //System.out.println("t0:" + t0);
        //System.out.println("Intersection 1:" + o.add(d.multiply(t0)));
        //System.out.println("t1:" + t1);
        //System.out.println("Intersection 2:" + o.add(d.multiply(t1)));
        
        double nearerPointParam;
        if (t0 <= 0 && t1 <= 0) {
            return null;
        }
        else if (t0 > 0 && t1 <= 0) {
            nearerPointParam = t0;
        }
        else if (t1 > 0 && t0 <= 0) {
            nearerPointParam = t1;
        }
        else {
            nearerPointParam = Math.min(t0, t1);
        }
        
        Vector result = o.add(d.multiply(nearerPointParam));
        
        return result;
        
    }
    
    public static Vector getSphereNormal(Sphere sph, Vector intersP) {
        
        Vector result = null;
            
        double[] intersCps = ((BasicVector)intersP).toArray();
        double x = intersCps[0];
        double y = intersCps[1];
        double z = intersCps[2];

        double[] sphereCenterCps =
                ((BasicVector)sph.getCenter()).toArray();
        double cx = sphereCenterCps[0];
        double cy = sphereCenterCps[1];
        double cz = sphereCenterCps[2];

        result = new BasicVector(new double[] {x - cx, y - cy, z - cz});
        result = result.normalize();
        
        return result;
        
    }
    
    public static Vector getReflection(Vector directionToSource,
            Vector intersN) {
        
        return intersN.multiply(2).multiply(intersN.innerProduct(
                directionToSource)).subtract(directionToSource).normalize();
        
    }
    
    public static Vector getTransmission(Intersection inters,
            double refractionIndex) {
        
        Vector i = inters.getRayDirection();
        Vector n = inters.getNormal();
        
        double n1divN2;
        if (inters.isExitingTransparent()) {            
            n1divN2 = 1 / refractionIndex;
            n = n.multiply(-1);
            if (Config.customDebug) {
                System.out.println("Exiting transparent object");
                System.out.println("Normal for transmission is:" + n);
            }
        }
        else {                        
            n1divN2 = refractionIndex / 1;
            if (Config.customDebug) {
                System.out.println("Entering transparent object");
                System.out.println("Normal for transmission is:" + n);
            }
        }
               
        Vector directionToSource = inters.getRayDirection().multiply(-1);
        double cosTheta_i = directionToSource.innerProduct(n);
        if (cosTheta_i > 1) {
            cosTheta_i = 1;
        }
        double theta_i = Math.acos(cosTheta_i);
        
        double sin_squared_theta_t = Math.pow(n1divN2, 2) *
                (Math.pow(Math.sin(theta_i), 2));
        if (sin_squared_theta_t > 1) {
            sin_squared_theta_t = 1;
        }
        
        if (Config.customDebug) {
            System.out.println("i dot n:" + directionToSource.innerProduct(n));
            System.out.println("theta_i degrees:" + theta_i * (180 / Math.PI));
        }
        
        Vector t = i.multiply(n1divN2).add(
                n.multiply(
                        (n1divN2 * Math.cos(theta_i) -
                                Math.sqrt(1 - sin_squared_theta_t))
                        )
                );
        
        if (Double.isNaN(t.get(0))) {
            System.out.println("Transmission ray has NaN components");
            System.out.println(inters);
        }
        
        return t.normalize();
             
        //return inters.getRayDirection();
        
    }
    
    public static double distance(Vector p1, Vector p2) {
        
        double[] p1c = ((BasicVector)p1).toArray();
        double[] p2c = ((BasicVector)p2).toArray();
        
        return Math.sqrt(
                Math.pow(p2c[0] - p1c[0], 2) +
                Math.pow(p2c[1] - p1c[1], 2) +
                Math.pow(p2c[2] - p1c[2], 2));
        
    }
    
    public static Vector crossProduct(Vector u, Vector v) {
        
        // i  j  k 
        // u1 u2 u3
        // v1 v2 v3
        
        double u1 = u.get(0);
        double u2 = u.get(1);
        double u3 = u.get(2);
        
        double v1 = v.get(0);
        double v2 = v.get(1);
        double v3 = v.get(2);
        
        double[] components = new double[3];
        components[0] = u2 * v3 - u3 * v2;
        components[1] = u3 * v1 - u1 * v3;
        components[2] = u1 * v2 - u2 * v1;
        
        return new BasicVector(components);
        
    }
    
    public static Vector getPolygonInters(Vector[] ray, Polygon poly) {
        
        if (Config.pi == 7 && Config.pj == 3) {
            //System.out.println("i:" + Config.pi + " j:" + Config.pj);
        }
        
        Vector planeInters = getPlaneInters(ray, poly);
        //System.out.println("planeInters:" + planeInters);
        if (planeInters == null) {
            return null;
        }
        Vector inters = new BasicVector(new double[] {
                planeInters.get(0),
                planeInters.get(1),
                planeInters.get(2)});
        
        double dominantComp = Math.max(
                Math.max(Math.abs(inters.get(0)), Math.abs(inters.get(1))),
                Math.abs(inters.get(2)));
        int dropInd;
        int uInd;
        int vInd;
        //if (Geometry.doubleEquals(dominantComp, Math.abs(inters.get(0)))) {
        if (true) {
            dropInd = 0;
            uInd = 1;
            vInd = 2;
        }
        /*
        else if (Geometry.doubleEquals(dominantComp, Math.abs(inters.get(1)))) {
            dropInd = 1;
            uInd = 0;
            vInd = 2;
        }
        else {
            dropInd = 2;
            uInd = 1;
            vInd = 2;
        }
        */
        //System.out.println("dropInd:" + dropInd);
        //System.out.println("uInd:" + uInd);
        //System.out.println("vInd:" + vInd);
        
        Vector[] origVertices = poly.getVertices();
        Vector[] vs = new Vector[origVertices.length];
        for (int i = 0; i < vs.length; i++) {
            
            vs[i] = new BasicVector(new double[] {
                    origVertices[i].get(0),
                    origVertices[i].get(1),
                    origVertices[i].get(2)});
            
            vs[i].set(dropInd, 0);
            
            vs[i].set(uInd, vs[i].get(uInd) - inters.get(uInd));
            vs[i].set(vInd, vs[i].get(vInd) - inters.get(vInd));
            
            //System.out.println("Projected v " + i + ":" + vs[i]);
            
        }
        inters.set(dropInd, 0);
        //System.out.println("Projected inters:" + intersTemp);
        
        int numCross = 0;
        int sh;
        if (vs[0].get(vInd) < 0.001) {
            sh = -1;
        }
        else {
            sh = 1;
        }
        int nsh;
        
        for (int i = 0; i < vs.length; i++) {
            
            int nextInd = i + 1;
            if (nextInd == vs.length) {
                nextInd = 0;
            }
            
            Vector curr = vs[i];
            Vector next = vs[nextInd];
            
            if (next.get(vInd) < 0.001) {
                nsh = -1;
            }
            else {
                nsh = 1;
            }
            
            if (sh != nsh) {
                
                if (curr.get(uInd) > -0.001 && next.get(uInd) > -0.001) {
                    numCross++;
                }
                else if (curr.get(uInd) > -0.001 || next.get(uInd) > -0.001) {
                    
                    double uCross = curr.get(uInd) -
                            curr.get(vInd) *
                            (next.get(uInd) - curr.get(uInd)) /
                            (next.get(vInd) - curr.get(vInd));
                    if (uCross > -0.001) {
                        numCross++;
                    }
                            
                }
                
            }
            
            sh = nsh;
            
        }
        
        //System.out.println("numCross:" + numCross);
        if (numCross % 2 == 1) {
            return planeInters;
        }
        else {
            return null;
        }
        
    }

    public static Vector getPlaneInters(Vector[] ray, Polygon poly) {
        
        Vector rayO = ray[0];
        Vector rayD = ray[1];
        
        Vector n = getPolygonNormal(poly);
        double dist = getDistOfPlaneFromOrigin(poly);
        
        double v_d = n.innerProduct(rayD);
        if (doubleEquals(v_d, 0)) {
            return null;
        }
        
        double v_o = -(n.innerProduct(rayO) + dist);
        
        double t = v_o / v_d;
        if (t < 0) {
            return null;
        }
        
        if (v_d > 0) {
            n = n.multiply(-1);
        }
        poly.setNormal(n);
        
        Vector inters = new BasicVector(new double[] {
                rayO.get(0) + t * rayD.get(0),
                rayO.get(1) + t * rayD.get(1),
                rayO.get(2) + t * rayD.get(2)});
        
        return inters;
        
    }
    
    public static double getDistOfPlaneFromOrigin(Polygon poly) {
        
        Vector origin = new BasicVector(new double[] {0, 0, 0});
        Vector planePoint = poly.getVertices()[0];
        Vector normal = getPolygonNormal(poly);
        
        double dist = origin.subtract(planePoint).innerProduct(normal);
        
        return dist;
        
    }
    
    public static Vector getPolygonNormal(Polygon poly) {
        
        Vector[] vertices = poly.getVertices();
        
        Vector normal = Geometry.crossProduct(
                vertices[1].subtract(vertices[0]),
                vertices[2].subtract(vertices[0]));
        
        /*
        Vector normal = Geometry.crossProduct(
                vertices[0].subtract(vertices[1]),
                vertices[2].subtract(vertices[1]));
        
        /*
        Vector normal = Geometry.crossProduct(
                vertices[0].subtract(vertices[2]),
                vertices[1].subtract(vertices[2]));
        */
        return normal.normalize();
        
    }
    
    public static boolean doubleEquals(double d1, double d2) {
        
        return Math.abs(d2 - d1) < 0.001;
        
    }

}
