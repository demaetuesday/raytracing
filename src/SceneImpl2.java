

import java.util.*;

import org.la4j.vector.Vector;
import org.la4j.vector.dense.BasicVector;

public class SceneImpl2 implements Scene {
    
    private double fovDeg;
    
    private Vector directionToLight;
    
    private Vector lightColor;
    
    private Vector ambientLightColor;
    
    private Vector backgroundColor;
    
    private List<Obj> objs;
    
    public SceneImpl2() {
        
        fovDeg = 45;
        
        directionToLight = new BasicVector(new double[] {0.5, 0, 1});
        directionToLight = directionToLight.normalize();
        
        lightColor = new BasicVector(new double[] {1, 1, 1});
        
        ambientLightColor = new BasicVector(new double[] {0.1, 0.1, 0.1});
        
        backgroundColor = new BasicVector(new double[] {0.05, 0.05, 0.05});
        
        objs = new LinkedList<Obj>();
        
        Sphere s4 = new Sphere();
        s4.setCenter(new BasicVector(new double[] {-0.35, 0.23, -0.3}));
        s4.setRadius(0.15);
        s4.setMaterial(Material.REFLECTIVE);
        s4.setColor(new BasicVector(new double[] {
                237.0/255, 202.0/255, 142.0/255}));
        s4.setSpecularHighlight(new BasicVector(new double[] {1, 1, 1}));
        s4.setPhongConstant(16);
        
        Sphere blueS = new Sphere();
        blueS.setCenter(new BasicVector(new double[] {0.37, 0, 0.08}));
        blueS.setRadius(0.15);
        blueS.setMaterial(Material.REFLECTIVE);
        blueS.setColor(new BasicVector(new double[] {
                106.0/255, 106.0/255, 250.0/255}));
        blueS.setSpecularHighlight(new BasicVector(new double[] {1, 1, 1}));
        blueS.setPhongConstant(16);
        
        Sphere glass2 = new Sphere();
        glass2.setCenter(new BasicVector(new double[] {0.01, 0.06, 0.3}));
        glass2.setRadius(0.03);
        glass2.setMaterial(Material.TRANSPARENT);
        glass2.setColor(new BasicVector(new double[] {0, 0, 0}));
        glass2.setSpecularHighlight(new BasicVector(new double[] {0.5, 1, 0.5}));
        glass2.setPhongConstant(16);
        glass2.setId("glass1");
        
        Sphere glass1 = new Sphere();
        glass1.setCenter(new BasicVector(new double[] {-0.04, -0.02, 0.3}));
        glass1.setRadius(0.055);
        glass1.setMaterial(Material.TRANSPARENT);
        glass1.setColor(new BasicVector(new double[] {0, 0, 0.5}));
        glass1.setSpecularHighlight(new BasicVector(new double[] {0.5, 1, 0.5}));
        glass1.setPhongConstant(16);
        glass1.setId("glass2");
        
        Polygon p1 = new Polygon();
        Vector[] vertices = new Vector[4];
        vertices[0] = new BasicVector(new double[] {0.2, 0.2, 0.1});
        vertices[1] = new BasicVector(new double[] {0.2, 0, 0.1});
        vertices[2] = new BasicVector(new double[] {0, 0, 0});
        vertices[3] = new BasicVector(new double[] {0, 0.2, 0});
        p1.setMaterial(Material.REFLECTIVE);
        p1.setColor(new BasicVector(new double[] {1, 1, 0}));
        p1.setSpecularHighlight(new BasicVector(new double[] {1, 1, 1}));
        p1.setPhongConstant(8);
        p1.setVertices(vertices);
        
        Polygon p2 = new Polygon();
        vertices = new Vector[4];
        vertices[0] = new BasicVector(new double[] {0, 0, 0});
        vertices[1] = new BasicVector(new double[] {0, -0.2, 0});
        vertices[2] = new BasicVector(new double[] {-0.2, -0.2, -0.1});
        vertices[3] = new BasicVector(new double[] {-0.2, 0, -0.1});
        p2.setMaterial(Material.REFLECTIVE);
        p2.setColor(new BasicVector(new double[] {1, 1, 0}));
        p2.setSpecularHighlight(new BasicVector(new double[] {1, 1, 1}));
        p2.setPhongConstant(8);
        p2.setVertices(vertices);
        
        Polygon p3 = new Polygon();
        vertices = new Vector[4];
        vertices[0] = new BasicVector(new double[] {0, 0.2, 0});
        vertices[1] = new BasicVector(new double[] {0, 0, 0});
        vertices[2] = new BasicVector(new double[] {-0.2, 0, -0.1});
        vertices[3] = new BasicVector(new double[] {-0.2, 0.2, -0.1});
        p3.setMaterial(Material.REFLECTIVE);
        p3.setColor(new BasicVector(new double[] {1, 0.2, 0}));
        p3.setSpecularHighlight(new BasicVector(new double[] {1, 1, 1}));
        p3.setPhongConstant(8);
        p3.setVertices(vertices);
        
        Polygon p4 = new Polygon();
        vertices = new Vector[4];
        vertices[0] = new BasicVector(new double[] {0.2, 0, 0.1});
        vertices[1] = new BasicVector(new double[] {0.2, -0.2, 0.1});
        vertices[2] = new BasicVector(new double[] {0, -0.2, 0});
        vertices[3] = new BasicVector(new double[] {0, 0, 0});
        p4.setMaterial(Material.REFLECTIVE);
        p4.setColor(new BasicVector(new double[] {1, 0.2, 0}));
        p4.setSpecularHighlight(new BasicVector(new double[] {1, 1, 1}));
        p4.setPhongConstant(8);
        p4.setVertices(vertices);
        
        Polygon p5 = new Polygon();
        vertices = new Vector[4];
        vertices = new Vector[4];
        vertices[0] = new BasicVector(new double[] {-0.2, 0.05, -0.3});
        vertices[1] = new BasicVector(new double[] {-0.2, -0.3, -0.3});
        vertices[2] = new BasicVector(new double[] {-0.35, -0.3, 0.1});
        vertices[3] = new BasicVector(new double[] {-0.35, 0.05, 0.1});
        p5.setMaterial(Material.REFLECTIVE);
        p5.setColor(new BasicVector(new double[] {0.2, 0.2, 0.2}));
        p5.setSpecularHighlight(new BasicVector(new double[] {1, 1, 1}));
        p5.setPhongConstant(8);
        p5.setVertices(vertices);
        
        objs.add(s4);
        objs.add(blueS);
        objs.add(glass2);
        objs.add(glass1);
        
        objs.add(p1);
        objs.add(p2);
        objs.add(p3);
        objs.add(p4);
        
        objs.add(p5);
        
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
