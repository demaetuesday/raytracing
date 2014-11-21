

import org.la4j.vector.Vector;

public class Ray {

    private Vector origin;
    private Vector direction;
    private double param;
    
    public Ray(Vector origin, Vector direction, double param) {
        
        this.origin = origin;
        this.direction = direction;
        this.param = param;
        
    }

    public Vector getOrigin() {
        return origin;
    }

    public void setOrigin(Vector origin) {
        this.origin = origin;
    }

    public Vector getDirection() {
        return direction;
    }

    public void setDirection(Vector direction) {
        this.direction = direction;
    }

    public double getParam() {
        return param;
    }

    public void setParam(double param) {
        this.param = param;
    }
    
    

}
