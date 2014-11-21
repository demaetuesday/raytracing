

import static org.junit.Assert.*;
import org.junit.Test;
import org.la4j.vector.Vector;
import org.la4j.vector.dense.BasicVector;

public class GeometryTest {

    @Test
    public void getSphereIntersTest() {
        
        Vector o = new BasicVector(new double[] {0, 0, 1});
        Vector d = new BasicVector(new double[] {0, 0, -1});
        Vector[] ray1 = new Vector[] {o, d};
        
        o = new BasicVector(new double[] {0, 0, 1});
        d = new BasicVector(new double[] {0, 0.2, -1});
        Vector[] ray2 = new Vector[] {o, d};
        
        o = new BasicVector(new double[] {0, 0, 1});
        d = new BasicVector(new double[] {0, 2, -1});
        Vector[] ray3 = new Vector[] {o, d};
        
        Sphere sph = new Sphere();
        sph.setCenter(new BasicVector(new double[] {0, 0, -1}));
        sph.setRadius(1);
        
        //System.out.println(Geometry.getSphereInters(ray1, sph));
        //System.out.println(Geometry.getSphereInters(ray2, sph));
        //System.out.println(Geometry.getSphereInters(ray3, sph));
        
    }
    
    @Test
    public void getSphereNormalTest() {
        
        Sphere sph = new Sphere();
        sph.setCenter(new BasicVector(new double[] {0, 0, -1}));
        sph.setRadius(1);
        
        Vector o = new BasicVector(new double[] {0, 0, 1});
        Vector d = new BasicVector(new double[] {0, 0, -1});
        Vector[] ray = new Vector[] {o, d};
        Vector intersP = Geometry.getSphereInters(ray, sph);
        //System.out.println(Geometry.getSphereNormal(sph, intersP));
        
        o = new BasicVector(new double[] {0, 0, 1});
        d = new BasicVector(new double[] {0, 0.2, -1});
        ray = new Vector[] {o, d};
        intersP = Geometry.getSphereInters(ray, sph);
        //System.out.println(Geometry.getSphereNormal(sph, intersP));
        
        o = new BasicVector(new double[] {0, 0, 1});
        d = new BasicVector(new double[] {0, 0.3, -1});
        ray = new Vector[] {o, d};
        intersP = Geometry.getSphereInters(ray, sph);
        //System.out.println(Geometry.getSphereNormal(sph, intersP));
        
        o = new BasicVector(new double[] {0, 0, 1});
        d = new BasicVector(new double[] {0, 0.57, -1});
        ray = new Vector[] {o, d};
        intersP = Geometry.getSphereInters(ray, sph);
        //System.out.println(Geometry.getSphereNormal(sph, intersP));
        
        o = new BasicVector(new double[] {0, 0, 1});
        d = new BasicVector(new double[] {0.3, 0.3, -1});
        ray = new Vector[] {o, d};
        intersP = Geometry.getSphereInters(ray, sph);
        //System.out.println(Geometry.getSphereNormal(sph, intersP));
        
        sph = new Sphere();
        sph.setCenter(new BasicVector(new double[] {1, 0, 0}));
        sph.setRadius(1);
        
        o = new BasicVector(new double[] {0, 0, 1});
        d = new BasicVector(new double[] {0.5, 0, -1});
        ray = new Vector[] {o, d};
        intersP = Geometry.getSphereInters(ray, sph);
        //System.out.println("intersP:" + intersP);
        //System.out.println(Geometry.getSphereNormal(sph, intersP));
        
    }
    
    @Test
    public void getPolygonIntersTest() {
        
        //System.out.println("getPolygonIntersTest()");
        
        Vector o = new BasicVector(new double[] {0, 0, 0});
        Vector d = new BasicVector(new double[] {1, 0.2, 0.2});
        Vector[] ray = new Vector[] {o, d};
        Polygon p = new Polygon();
        p.setVertices(new Vector[] {
                new BasicVector(new double[] {1, 0, 0}),
                new BasicVector(new double[] {1, 1, 0}),
                new BasicVector(new double[] {1, 0, 1})});
        Vector inters = Geometry.getPolygonInters(ray, p);
        //System.out.println("Intersection:" + inters);
        
        o = new BasicVector(new double[] {0, 0, 0});
        d = new BasicVector(new double[] {0, 1, 0});
        ray = new Vector[] {o, d};
        p = new Polygon();
        p.setVertices(new Vector[] {
                new BasicVector(new double[] {0, 3, 0}),
                new BasicVector(new double[] {1, 3, 0}),
                new BasicVector(new double[] {0, 3, 1})});
        inters = Geometry.getPolygonInters(ray, p);
        //System.out.println("Intersection:" + inters);
        
    }
    
    @Test
    public void getPlaneIntersTest() {
        
        //System.out.println("getPlaneIntersTest()");
        
        Vector o = new BasicVector(new double[] {0, 0, 1});
        Vector d = new BasicVector(new double[] {0.1, 0, -1});
        Vector[] ray = new Vector[] {o, d};
        Polygon p = new Polygon();
        p.setVertices(new Vector[] {
                new BasicVector(new double[] {1, 0, 0}),
                new BasicVector(new double[] {1, 1, 0}),
                new BasicVector(new double[] {1, 0, 1})});          
        Vector inters = Geometry.getPlaneInters(ray, p);
        //System.out.println(inters);
        
        o = new BasicVector(new double[] {1, 0, 0});
        d = new BasicVector(new double[] {0, 0, -1});
        ray = new Vector[] {o, d};
        p = new Polygon();
        p.setVertices(new Vector[] {
                new BasicVector(new double[] {2, 0, -3}),
                new BasicVector(new double[] {-2, 0, -3}),
                new BasicVector(new double[] {0, 2, -3})});    
        inters = Geometry.getPlaneInters(ray, p);
        //System.out.println(inters);
        
        o = new BasicVector(new double[] {0, 0, 1});
        d = new BasicVector(new double[] {0, 0, -1});
        ray = new Vector[] {o, d};
        p = new Polygon();
        p.setVertices(new Vector[] {
                new BasicVector(new double[] {0.3, -0.3, -0.4}),
                new BasicVector(new double[] {0, 0.3, -0.1}),
                new BasicVector(new double[] {-0.3, -0.3, 0.2})});      
        inters = Geometry.getPlaneInters(ray, p);
        //System.out.println(inters);
        
    }
    
    @Test
    public void getDistFromPlaneToOriginTest() {
        
        //System.out.println("getDistFromPlaneToOriginTest()");
        
        Polygon p = new Polygon();
        p.setVertices(new Vector[] {
                new BasicVector(new double[] {1, 1000, 0}),
                new BasicVector(new double[] {1, 1000, 1}),
                new BasicVector(new double[] {1, 999, 0})});
        assertTrue(Geometry.doubleEquals(1,
                Geometry.getDistOfPlaneFromOrigin(p)));
        
        p = new Polygon();
        p.setVertices(new Vector[] {
                new BasicVector(new double[] {1, 500, 0}),
                new BasicVector(new double[] {2, 1000, 0}),
                new BasicVector(new double[] {2, 1000, 1})});
        assertTrue(Geometry.doubleEquals(0,
                Geometry.getDistOfPlaneFromOrigin(p)));
        
        p.setVertices(new Vector[] {
                new BasicVector(new double[] {2, 0, 0}),
                new BasicVector(new double[] {0, 2, 0}),
                new BasicVector(new double[] {2, 0, 2})});      
        assertTrue(Geometry.doubleEquals(Math.sqrt(2),
                Geometry.getDistOfPlaneFromOrigin(p)));
        
        p.setVertices(new Vector[] {
                new BasicVector(new double[] {1, 0, 0}),
                new BasicVector(new double[] {1, 0, 1}),
                new BasicVector(new double[] {0, -2, 0})});      
        //System.out.println(Geometry.getDistOfPlaneFromOrigin(p));                
        
    }
    
    @Test
    public void getPolygonNormalTest() {
        
        //System.out.println("getPolygonNormalTest()");
        
        Polygon p = new Polygon();
        p.setVertices(new Vector[] {
                new BasicVector(new double[] {1, 0, 0}),
                new BasicVector(new double[] {1, 1, 0}),
                new BasicVector(new double[] {1, 0, 1})});
        //System.out.println(Geometry.getPolygonNormal(p));
        
        p.setVertices(new Vector[] {
                new BasicVector(new double[] {0, 3, 0}),
                new BasicVector(new double[] {1, 3, 0}),
                new BasicVector(new double[] {0, 3, 1})}); 
        //System.out.println(Geometry.getPolygonNormal(p));
        
        p.setVertices(new Vector[] {
                new BasicVector(new double[] {2, 0, 0}),
                new BasicVector(new double[] {0, 2, 0}),
                new BasicVector(new double[] {2, 0, 2})}); 
        //System.out.println(Geometry.getPolygonNormal(p));
        
        p.setVertices(new Vector[] {
                new BasicVector(new double[] {0, 1000, 0}),
                new BasicVector(new double[] {2, 1001, 0}),
                new BasicVector(new double[] {2, 1001, 1})}); 
        //System.out.println(Geometry.getPolygonNormal(p));
        
    }
    
    @Test
    public void crossProductTest() {
        
        Vector v1 = new BasicVector(new double[] {3, 6, 2});
        Vector v2 = new BasicVector(new double[] {5, 1, 9});
        Vector ex = new BasicVector(new double[] {52, -17, -27});
        assertEquals(ex, Geometry.crossProduct(v1, v2));
        
        v1 = new BasicVector(new double[] {-4, -6, -8});
        v2 = new BasicVector(new double[] {13, 12, 11});
        ex = new BasicVector(new double[] {30, -60, 30});
        assertEquals(ex, Geometry.crossProduct(v1, v2));
        
        v1 = new BasicVector(new double[] {-4, -6, -8});
        v2 = new BasicVector(new double[] {-13, -12, -11});
        ex = new BasicVector(new double[] {-30, 60, -30});
        assertEquals(ex, Geometry.crossProduct(v1, v2));
        
        v1 = new BasicVector(new double[] {0.123, 0.456, 0.789});
        v2 = new BasicVector(new double[] {1.987, 1.654, 1.321});
        ex = new BasicVector(new double[] {-0.70263, 1.40526, -0.70263});
        assertEquals(ex, Geometry.crossProduct(v1, v2));
        
    }

}
