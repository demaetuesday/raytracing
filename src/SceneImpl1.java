

import java.util.*;

import org.la4j.vector.Vector;
import org.la4j.vector.dense.BasicVector;

public class SceneImpl1 implements Scene {
    
    private double fovDeg;
    
    private Vector directionToLight;
    
    private Vector lightColor;
    
    private Vector ambientLightColor;
    
    private Vector backgroundColor;
    
    private List<Obj> objs;
    
    public SceneImpl1() {
        
        fovDeg = 45;
        
        directionToLight = new BasicVector(new double[] {1, 0, 0});
        directionToLight = directionToLight.normalize();
        
        lightColor = new BasicVector(new double[] {1, 1, 1});
        
        ambientLightColor = new BasicVector(new double[] {0.1, 0.1, 0.1});
        
        backgroundColor = new BasicVector(new double[] {0.2, 0.2, 0.2});
        
        objs = new LinkedList<Obj>();
        
        Sphere s1 = new Sphere();
        s1.setCenter(new BasicVector(new double[] {0.25, -0.1, -0.2}));
        s1.setRadius(0.07);
        s1.setMaterial(Material.REFLECTIVE);
        s1.setColor(new BasicVector(new double[] {1, 1, 1}));
        s1.setSpecularHighlight(new BasicVector(new double[] {1, 1, 1}));
        s1.setPhongConstant(4);
        
        Sphere s2 = new Sphere();
        s2.setCenter(new BasicVector(new double[] {0, 0, -1.5}));
        s2.setRadius(0.3);
        s2.setMaterial(Material.DIFFUSE);
        s2.setColor(new BasicVector(new double[] {1, 0, 0}));
        s2.setSpecularHighlight(new BasicVector(new double[] {0.5, 1, 0.5}));
        s2.setPhongConstant(32);
        s2.setId("s2");
        
        Sphere s3 = new Sphere();
        s3.setCenter(new BasicVector(new double[] {-0.4, 0, -0.2}));
        s3.setRadius(0.2);
        s3.setMaterial(Material.REFLECTIVE);
        s3.setColor(new BasicVector(new double[] {0, 1, 0}));
        s3.setSpecularHighlight(new BasicVector(new double[] {0.5, 1, 0.3}));
        s3.setPhongConstant(32);
        
        Sphere s4 = new Sphere();
        s4.setCenter(new BasicVector(new double[] {0.65, 0.3, -1.5}));
        s4.setRadius(0.3);
        s4.setMaterial(Material.REFLECTIVE);
        s4.setColor(new BasicVector(new double[] {0.7, 0.7, 0}));
        s4.setSpecularHighlight(new BasicVector(new double[] {0.5, 1, 0.5}));
        s4.setPhongConstant(16);
        
        Sphere s5 = new Sphere();
        s5.setCenter(new BasicVector(new double[] {0.023, 0.015, 0.8}));
        s5.setRadius(0.03);
        s5.setMaterial(Material.TRANSPARENT);
        s5.setColor(new BasicVector(new double[] {0.3, 0.0, 0.3}));
        s5.setSpecularHighlight(new BasicVector(new double[] {0.5, 1, 0.5}));
        s5.setPhongConstant(16);
        s5.setId("s5");
        
        Sphere s6 = new Sphere();
        s6.setCenter(new BasicVector(new double[] {-0.06, -0.04, 0.6}));
        s6.setRadius(0.055);
        s6.setMaterial(Material.TRANSPARENT);
        s6.setColor(new BasicVector(new double[] {0, 0, 0.5}));
        s6.setSpecularHighlight(new BasicVector(new double[] {0.5, 1, 0.5}));
        s6.setPhongConstant(16);
        s6.setId("s6");
        
        Sphere s7 = new Sphere();
        s7.setCenter(new BasicVector(new double[] {-0.17, 0.2, -0.15}));
        s7.setRadius(0.07);
        s7.setMaterial(Material.REFLECTIVE);
        s7.setColor(new BasicVector(new double[] {0, 0, 0}));
        s7.setSpecularHighlight(new BasicVector(new double[] {1, 1, 1}));
        s7.setPhongConstant(2);
        
        Polygon t1 = new Polygon();
        Vector[] vertices = new Vector[3];
        vertices[0] = new BasicVector(new double[] {0.3, -0.3, -0.4});
        vertices[1] = new BasicVector(new double[] {0, 0.3, -0.1});
        vertices[2] = new BasicVector(new double[] {-0.3, -0.3, 0.2});
        t1.setMaterial(Material.DIFFUSE);
        t1.setColor(new BasicVector(new double[] {0, 0, 1}));
        t1.setSpecularHighlight(new BasicVector(new double[] {1, 1, 1}));
        t1.setPhongConstant(32);
        t1.setVertices(vertices);
        
        Polygon t2 = new Polygon();
        vertices = new Vector[3];
        vertices[0] = new BasicVector(new double[] {-0.2, 0.1, 0.1});
        vertices[1] = new BasicVector(new double[] {-0.2, -0.5, 0.2});
        vertices[2] = new BasicVector(new double[] {-0.2, 0.1, -0.3});
        t2.setMaterial(Material.DIFFUSE);
        t2.setColor(new BasicVector(new double[] {1, 1, 0}));
        t2.setSpecularHighlight(new BasicVector(new double[] {1, 1, 1}));
        t2.setPhongConstant(4);
        t2.setVertices(vertices);
        
        objs.add(s1);
        objs.add(s2);
        objs.add(s3);
        objs.add(s4);
        objs.add(s5);
        objs.add(s6);
        objs.add(s7);
        
        //objs.add(t1);
        //objs.add(t2);
        
    }

    @Override
    public double getFOVDeg() {
        return fovDeg;
    }

    @Override
    public Vector getDirectionToLight() {
        return directionToLight;
    }

    @Override
    public Vector getLightColor() {
        return lightColor;
    }

    @Override
    public Vector getAmbientLightColor() {
        return ambientLightColor;
    }

    @Override
    public Vector getBackgroundColor() {
        return backgroundColor;
    }

    @Override
    public List<Obj> getObjs() {
        return objs;
    }

}
