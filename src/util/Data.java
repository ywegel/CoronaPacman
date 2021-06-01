package util;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import java.net.URL;

public class Data {

    //public static InputStream //TODO load images from resources

    //URL url = getClass().getResource("/path/to/image.jpg");
    //Image image = new ImageIcon(url).getImage();

    public static Image loadImage(String path){
        URL url = getClass().getResource("/path/to/image.jpg"); //TODO load outside classpath
        return new ImageIcon(url).getImage();
    }

}