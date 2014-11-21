

import java.io.*;
import org.la4j.vector.Vector;
import org.la4j.vector.dense.BasicVector;

public class IO {
    
    private static final String PPM_MAGIC_NUMBER = "P3";
    private static final int COLOR_MAX_VALU = 255;
    
    public static void writePPM(Vector[][] image, File file)
            throws IOException{
        
        PrintWriter writer = null;
        
        writer = new PrintWriter(file);
        
        writer.println(PPM_MAGIC_NUMBER + "\n" +
                image[0].length + "\n" +
                image.length + "\n" +
                COLOR_MAX_VALU);
        
        for (int j = image.length - 1; j >= 0; j--) {
            
            for (int i = 0; i < image[0].length; i++) {
                
                double[] components = ((BasicVector)image[j][i]).toArray();
                
                writer.println((int)(components[0] * COLOR_MAX_VALU));
                writer.println((int)(components[1] * COLOR_MAX_VALU));
                writer.println((int)(components[2] * COLOR_MAX_VALU));
                
            }
            
        }
        
        writer.close();
        
    }

}
