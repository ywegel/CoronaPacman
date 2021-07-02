package util;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class Data {
    private static HashMap<Coord, MapChunkValues> gameMap;

    //kein / vor path !!
    public static Image loadImage(String path){
        URL url = ClassLoader.getSystemClassLoader().getResource(path);
        return new ImageIcon(url).getImage();
    }

    public static Font setPacFont(){
        Font pacFont = null;
        try {
            //create input stream
            InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("crackman.ttf");
            //create the font to use. Specify the size!
            pacFont = Font.createFont(Font.TRUETYPE_FONT,stream).deriveFont(70f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("crackman.ttf")));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        return pacFont;

    }

    public final static int MAP_CHUNK_SIZE = 323;
    private final static int LINE_OFFSET = 4;

    public static HashMap<Coord, MapChunkValues> getGameHashMap() {
        return Objects.requireNonNullElseGet(gameMap, Data::loadMapDataFromFile);
    }

    private static HashMap<Coord, MapChunkValues> loadMapDataFromFile() {
        int currentChunk = 0;
        HashMap<Coord, MapChunkValues> map = new HashMap<>();
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("map.txt");
        //File mapFile = new File("map.txt");
        try (Scanner scanner = new Scanner(is)) {
            scanner.nextLine();

            for (int i = 0; i < 19; i++) {

                for (int j = 0; j < 17; j++) {
                    String currentLine = scanner.nextLine();

                    map.put(
                            new Coord(j, i),
                            new MapChunkValues(
                                    currentLine.charAt(LINE_OFFSET) != 0,
                                    currentLine.charAt(LINE_OFFSET + 1) != 0,
                                    currentLine.charAt(LINE_OFFSET + 2) != 0,
                                    currentLine.charAt(LINE_OFFSET + 3) != 0,
                                    new PlayerMovableDir(
                                            currentLine.charAt(LINE_OFFSET + 5) != 0,
                                            currentLine.charAt(LINE_OFFSET + 6) != 0,
                                            currentLine.charAt(LINE_OFFSET + 7) != 0,
                                            currentLine.charAt(LINE_OFFSET + 8) != 0
                                    )
                            ));
                    System.out.println(i + " + " + j);
                    currentChunk++;
                }
            }

        }
        System.out.println("Chunks loaded: " + currentChunk);
        return map;
    }

}