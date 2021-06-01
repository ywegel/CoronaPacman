package util;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import java.net.URL;

public class Data {

    //kein / vor path !!
    public static Image loadImage(String path){
        URL url = ClassLoader.getSystemClassLoader().getResource(path);
        return new ImageIcon(url).getImage();
    }

    //TODO static extra threaded data loader

}