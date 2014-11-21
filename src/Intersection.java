

import org.la4j.vector.Vector;

public class Intersection {
    
    private Vector rayDirection;
    private Obj obj;
    private Vector point;
    private Vector normal;
    private Vector directionToCamera;
    private Vector reflection;
    private Vector transmission;
    private boolean exitingTransparent;
    
    public Intersection(Vector rayDirection, Obj obj, Vector point,
            Vector normal, Vector directionToCamera, Vector reflection) {
        
        this.rayDirection = rayDirection;
        this.obj = obj;
        this.point = point;
        this.normal = normal;
        this.directionToCamera = directionToCamera;
        this.reflection = reflection;
        
        transmission = null;
        exitingTransparent = false;
        
    }

    public Vector getRayDirection() {
        return rayDirection;
    }

    public void setRayDirection(Vector rayDirection) {
        this.rayDirection = rayDirection;
    }

    public Obj getObj() {
        return obj;
    }

    public void setObj(Obj obj) {
        this.obj = obj;
    }

    public Vector getPoint() {
        return point;
    }

    public void setPoint(Vector point) {
        this.point = point;
    }

    public Vector getNormal() {
        return normal;
    }

    public void setNormal(Vector normal) {
        this.normal = normal;
    }

    public Vector getDirectionToCamera() {
        return directionToCamera;
    }

    public void setDirectionToCamera(Vector directionToCamera) {
        this.directionToCamera = directionToCamera;
    }

    public Vector getReflection() {
        return reflection;
    }

    public void setReflection(Vector reflection) {
        this.reflection = reflection;
    }
    
    public Vector getTransmission() {
        return transmission;
    }

    public void setTransmission(Vector transmission) {
        this.transmission = transmission;
    }
    
    public boolean isExitingTransparent() {
        return exitingTransparent;
    }
    
    public void setExitingTransparent(boolean b) {
        exitingTransparent = b;
    }

    @Override
    public String toString() {
        
        return "Intersection:\n"
                + "rayDirection:" + rayDirection + "\n"
                + "obj:" + obj.getId() + "\n"
                + "point:" + point + "\n"
                + "normal:" + normal + "\n"
                + "reflection:" + reflection + "\n"
                + "transmission:" + transmission + "\n";
        
    }

}
