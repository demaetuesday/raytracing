

import java.util.*;

import org.la4j.vector.Vector;
import org.la4j.vector.dense.BasicVector;

public class Renderer {
        
    private final int IMAGE_WIDTH = 2048;
    private final int IMAGE_HEIGHT = 1536;
    
    private static final double BACKGROUND_DISTANCE = 100;
    
    private static final int MAX_RAY_DEPTH = 12;
    
    private static final double DIFFUSE_REFLECTION_FIDELITY = 0.85;
    
    private static final double TRANSPARENT_REFLECTION_FIDELITY = 0.5;
    private static final double TRANSMISSION_FIDELITY = 0.95;
    
    private static final double TRANSPARENT_REFRACTION_INDEX = 1.01;
        
    public int iTest;
    public int jTest;
    public Vector[] rayTest;
    
    private Scene scene;
    private List<Obj> objs;
    
    private Vector[][] image;

    public Renderer(Scene scene) {
        
        this.scene = scene;
        objs = scene.getObjs();
        
    }
    
    public Vector[][] render() {
        
        image = new Vector[IMAGE_HEIGHT][IMAGE_WIDTH];
        
        for (double j = IMAGE_HEIGHT - 1; j >= 0; j--) {
            
            Config.pj = (int)j;
            
            for (double i = 0; i < IMAGE_WIDTH; i++) {
                
                Config.pi = (int)i;                
                
                Vector[] ray = Geometry.getRayByPixel(
                        i + 0.5f, j + 0.5f,
                        IMAGE_WIDTH, IMAGE_HEIGHT, scene.getFOVDeg());
                rayTest = ray;
                
                Intersection inters = getClosestIntersection(ray, objs);
                
                Vector color = scene.getBackgroundColor();
                
                if (inters != null) {
                    
                    color = getColorR(inters, 1);
                                        
                }
            
                image[(int)j][(int)i] = color;
                
            }
            
            System.out.println("Finished row " + j);
            
        }
        
        return image;
        
    }
    
    private Vector getColorR(Intersection inters, int currentRayDepth) {
        
        Vector color = null;
        
        if (inters.getObj().getMaterial() == Material.TRANSPARENT) {
         
            Vector transmission = Geometry.getTransmission(
                    inters, TRANSPARENT_REFRACTION_INDEX);
            inters.setTransmission(transmission);
            
            Vector[] nextRay = new Vector[] {inters.getPoint(), transmission};
            Intersection nextInters = getClosestIntersection(nextRay, objs);
            
            if (nextInters == null) {
                
                color = getColor(inters).
                        multiply(TRANSPARENT_REFLECTION_FIDELITY).
                        add(
                                (scene.getBackgroundColor().
                                        multiply(TRANSMISSION_FIDELITY)));
                
                capComponents(color);
                
            }
            else {
                
                nextInters.setExitingTransparent(
                        !inters.isExitingTransparent());
                
                color = getColor(inters).
                        multiply(TRANSPARENT_REFLECTION_FIDELITY).
                        add(
                                (getColorR(nextInters, currentRayDepth).
                                        multiply(TRANSMISSION_FIDELITY)));
                
                capComponents(color);
                
            }
            
        }
        else if (inters.getObj().getMaterial() == Material.REFLECTIVE &&
                currentRayDepth <= MAX_RAY_DEPTH) {
            
            Vector[] nextRay = new Vector[] {
                    inters.getPoint(), inters.getReflection()};
            Intersection nextInters = getClosestIntersection(nextRay, objs);
            
            if (nextInters == null) {
                color = getColor(inters);
            }
            else {
                
                color = getColor(inters).add(
                        getColorR(nextInters, currentRayDepth + 1).
                        multiply(DIFFUSE_REFLECTION_FIDELITY));
                
                capComponents(color);
                
            }
            
        }
        else {
            color = getColor(inters);            
        }
        
        return color;
        
    }

    private Vector getColor(Intersection inters) {
        
        Obj intersObj = inters.getObj();
        Vector intersN = inters.getNormal();
        
        double[] c_r = ((BasicVector)intersObj.getColor()).toArray();
        double[] c_a = ((BasicVector)scene.getAmbientLightColor()).toArray();
        double[] c_l = ((BasicVector)scene.getLightColor()).toArray();
        double[] c_p = ((BasicVector)intersObj.getSpecularHighlight()).
                toArray();
        double p = intersObj.getPhongConstant();
        Vector l = scene.getDirectionToLight();
        Vector e = inters.getDirectionToCamera();
        //System.out.println("e:" + e);
        Vector r = Geometry.getReflection(l, intersN);
        //System.out.println("r:" + r);
        
        double[] colorCps = new double[3];
        for (int i = 0; i < 3; i++) {
                
            double ambientTerm = c_r[i] * c_a[i];
            
            double diffuseTerm = c_r[i] *
                    c_l[i] * (Math.max(0, intersN.innerProduct(l)));           
            
            double specularTerm = c_l[i] *
                    c_p[i] * Math.pow(Math.max(0, e.innerProduct(r)), p); 
            
            colorCps[i] = ambientTerm;
            if (!isInShadow(inters)) {
                colorCps[i] += diffuseTerm + specularTerm;
            }
            
            if (colorCps[i] > 1) {
                colorCps[i] = 1;
            }
        
        }
        
        if (Config.customDebug) {
            
            System.out.println(inters);
            
        }
        
        return new BasicVector(colorCps);
        
    }

    private Intersection getClosestIntersection(Vector ray[], List<Obj> objs) {
        
        Intersection result = null;
        
        double distance = BACKGROUND_DISTANCE;
        Obj intersObj = null;                
        Vector intersP = null;
        Vector intersN = null;
        Vector directionToCamera = null;
        Vector reflection = null;
        
        for (Obj obj : objs) {
            
            Vector intersResult = getInters(ray, obj);
            if (intersResult != null) {
                
                double distanceResult = Geometry.distance(
                        Config.getLookFrom(), intersResult);
                if (distanceResult < distance) {
                    
                    distance = distanceResult;
                    
                    intersObj = obj;
                    intersP = intersResult;
                    intersN = getNormal(intersObj, intersP);
                    
                    directionToCamera = ray[0].subtract(intersP).normalize();
                    
                    Vector directionToSource = ray[0].subtract(intersP).
                            normalize();
                    reflection =
                            Geometry.getReflection(directionToSource, intersN);
                    
                    result = new Intersection(ray[1], intersObj, intersP,
                            intersN, directionToCamera, reflection);
                    
                }                    
                
            }                    
            
        }
        
        return result;
        
    }
    
    private Vector getInters(Vector[] ray, Obj obj) {        
        
        // Moves the origin of the ray toward the direction by a small
        // epsilon value to prevent intersection with the object from which
        // the ray is emitted.
        ray[0] = ray[0].add(ray[1].multiply(0.001));
        
        Vector result = null;
        
        if (obj instanceof Sphere) {
            
            result = Geometry.getSphereInters(ray, (Sphere)obj);
            
        }
        else if (obj instanceof Polygon) {
            
            result = Geometry.getPolygonInters(ray, (Polygon)obj);
            //System.out.println("Inters:" + result);
            
        }
        
        return result;
        
    }
    
    private Vector getNormal(Obj obj, Vector intersP) {
        
        Vector result = null;
        
        if (obj instanceof Sphere) {
            
            result = Geometry.getSphereNormal((Sphere)obj, intersP);
            
        }
        else if (obj instanceof Polygon) {
            
            result = ((Polygon)obj).getNormal();
            
        }
        
        return result;
        
    }

    private boolean isInShadow(Intersection inters) {
        
        Vector[] shadowRay = new Vector[2];
        Vector intersP = inters.getPoint();                
        shadowRay[0] = intersP;
        /*
        shadowRay[1] = 
                scene.getDirectionToLight().subtract(intersP);
        */
        shadowRay[1] = scene.getDirectionToLight();
        boolean result = false;
        for (Obj obj : objs) {
            
            if (getInters(shadowRay, obj) != null) {
                
                result = true;
                break;
            
            }
            
        }
        
        //return false;
        return result;        
        
    }
    
    private void capComponents(Vector v) {
        
        if (v.get(0) > 1) {
            v.set(0, 1);
        }
        if (v.get(1) > 1) {
            v.set(1, 1);
        }
        if (v.get(2) > 1) {
            v.set(2, 1);
        }
        
    }
    
}
