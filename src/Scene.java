

import java.util.*;
import org.la4j.vector.Vector;

public interface Scene {

    public double getFOVDeg();
    
    public Vector getDirectionToLight();
    public Vector getLightColor();
    public Vector getAmbientLightColor();
    public Vector getBackgroundColor();
    
    public List<Obj> getObjs();
    
}
