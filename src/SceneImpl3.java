

import java.util.*;

import org.la4j.vector.Vector;
import org.la4j.vector.dense.BasicVector;

public class SceneImpl3 implements Scene {
    
    private double fovDeg;
    
    private Vector directionToLight;
    
    private Vector lightColor;
    
    private Vector ambientLightColor;
    
    private Vector backgroundColor;
    
    private List<Obj> objs;
    
    public SceneImpl3() {
        
        fovDeg = 45;
        
        directionToLight = new BasicVector(new double[] {0.5, 0, 1});
        directionToLight = directionToLight.normalize();
        
        lightColor = new BasicVector(new double[] {1, 1, 1});
        
        ambientLightColor = new BasicVector(new double[] {0, 0.2, 0});
        
        backgroundColor = new BasicVector(new double[] {0.05, 0.05, 0.05});
        
        objs = new LinkedList<Obj>();
        
        Sphere s4 = new Sphere();
        s4.setCenter(new BasicVector(new double[] {0, 0, 0}));
        s4.setRadius(0.2);
        s4.setMaterial(Material.REFLECTIVE);
        s4.setColor(new BasicVector(new double[] {1, 1, 0}));
        s4.setSpecularHighlight(new BasicVector(new double[] {1, 1, 1}));
        s4.setPhongConstant(64);
        
        Sphere blueS = new Sphere();
        blueS.setCenter(new BasicVector(new double[] {0.2, 0.3, 0.1}));
        blueS.setRadius(0.15);
        blueS.setMaterial(Material.DIFFUSE);
        blueS.setColor(new BasicVector(new double[] {
                1, 1, 1}));
        blueS.setSpecularHighlight(new BasicVector(new double[] {1, 1, 1}));
        blueS.setPhongConstant(2);
        
        objs.add(s4);
        objs.add(blueS);
        
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
