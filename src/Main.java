import java.io.*;

import org.la4j.vector.Vector;

public class Main {

    public static void main(String[] args) throws Exception {
        
        Scene scene = new SceneImpl2();
        
        Renderer r = new Renderer(scene);
        
        Vector[][] image = r.render();

        File file = new File(System.getProperty("user.home") +
            File.separator + "Desktop" + File.separator + "image.ppm");
        IO.writePPM(image, file);
        
    }

}
