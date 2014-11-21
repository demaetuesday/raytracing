

import org.la4j.vector.Vector;
import org.la4j.vector.dense.BasicVector;

public class Config {
    
    private static Vector lookFrom;
    
    // For use in testing
    public static int pi;
    public static int pj;
    public static boolean customDebug;
    
    static {
        
        lookFrom = new BasicVector(new double[] {0, 0, 1});
    
    }
    
    public static Vector getLookFrom() {
        return lookFrom;
    }

    public static double getLookFromZ() {
        return ((BasicVector)lookFrom).toArray()[2];
    }
    
}
