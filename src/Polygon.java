

import org.la4j.vector.Vector;

public class Polygon extends Obj {

    private Vector[] vertices;
    private Vector normal;

    public Vector[] getVertices() {
        return vertices;
    }

    public void setVertices(Vector[] vertices) {
        this.vertices = vertices;
    }

    public Vector getNormal() {
        return normal;
    }

    public void setNormal(Vector normal) {
        this.normal = normal;
    }   
    
}
