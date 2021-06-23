package util;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Data {

    //kein / vor path !!
    public static Image loadImage(String path){
        URL url = ClassLoader.getSystemClassLoader().getResource(path);
        return new ImageIcon(url).getImage();
    }

    public static Font setPacFont(){
        Font pacFont = null;
        try {
            //create the font to use. Specify the size!
            pacFont = Font.createFont(Font.TRUETYPE_FONT, new File("crackman.ttf")).deriveFont(12f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(pacFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        return pacFont;

    }

}