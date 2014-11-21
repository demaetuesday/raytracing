

import org.la4j.vector.Vector;

public abstract class Obj {

    protected Vector color;
    protected Material material;
    
    protected Vector SpecularHighlight;
    protected double PhongConstant;
    
    protected String id;
    
    public Vector getColor() {
        return color;
    }
    
    public void setColor(Vector color) {
        this.color = color;
    }
    
    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Vector getSpecularHighlight() {
        return SpecularHighlight;
    }
    
    public void setSpecularHighlight(Vector specularHighlight) {
        SpecularHighlight = specularHighlight;
    }
    
    public double getPhongConstant() {
        return PhongConstant;
    }
    
    public void setPhongConstant(int phongConstant) {
        PhongConstant = phongConstant;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
}
