package util;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class Data {

    public static Image loadImageFromRes(String path) {
        URL url = ClassLoader.getSystemClassLoader().getResource(path);
        return new ImageIcon(url).getImage();
    }

    public static InputStream loadFileFromResAsStream(String filename) {
        return Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResourceAsStream(filename));
    }

    public static Font setPacFont() {
        Font pacFont = null;
        try {
            InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("crackman.ttf");
            pacFont = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(70f);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        return pacFont;
    }

    public final static int MAP_CHUNK_SIZE = 323;
    private final static int LINE_OFFSET = 5;

    public static HashMap<Coord, MapChunkValues> loadMapDataFromFile() {
        int currentChunk = 0;
        HashMap<Coord, MapChunkValues> map = new HashMap<>();
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("map.txt");
        try (Scanner scanner = new Scanner(is)) {
            scanner.nextLine();

            for (int i = 0; i < 20; i++) {

                for (int j = 0; j < 17; j++) {
                    String currentLine = scanner.nextLine();

                    MapChunkValues mapChunkValues = new MapChunkValues(
                            Character.digit(currentLine.charAt(LINE_OFFSET), 10) != 0,
                            Character.digit(currentLine.charAt(LINE_OFFSET + 1), 10) != 0,
                            Character.digit(currentLine.charAt(LINE_OFFSET + 2), 10) != 0,
                            new PlayerMovableDir(
                                    Character.digit(currentLine.charAt(LINE_OFFSET + 4), 10) != 0,
                                    Character.digit(currentLine.charAt(LINE_OFFSET + 5), 10) != 0,
                                    Character.digit(currentLine.charAt(LINE_OFFSET + 6), 10) != 0,
                                    Character.digit(currentLine.charAt(LINE_OFFSET + 7), 10) != 0
                            )
                    );

                    map.put(
                            new Coord(j, i),
                            mapChunkValues
                    );
                    currentChunk++;
                }
            }

        }
        System.out.println("Chunks loaded: " + currentChunk);
        return map;
    }
    /*                    System.out.println("-----");
                    System.out.println("X: " + j);
                    System.out.println("Y: " + i);
                    System.out.println("Has Dot: " + (Character.digit(currentLine.charAt(LINE_OFFSET), 10) != 0));
                    System.out.println("Has TP: " + (Character.digit(currentLine.charAt(LINE_OFFSET + 1), 10) != 0));
                    System.out.println("Has VAC: " + (Character.digit(currentLine.charAt(LINE_OFFSET + 2), 10) != 0));
                    System.out.println("Has COV: " + (Character.digit(currentLine.charAt(LINE_OFFSET + 3), 10) != 0));
                    System.out.println("--");
                    System.out.println("Has UP: " + (Character.digit(currentLine.charAt(LINE_OFFSET + 5), 10) != 0));
                    System.out.println("Has DOWN: " + (Character.digit(currentLine.charAt(LINE_OFFSET + 6), 10) != 0));
                    System.out.println("Has LEFT: " + (Character.digit(currentLine.charAt(LINE_OFFSET + 7), 10) != 0));
                    System.out.println("Has RIGHT: " + (Character.digit(currentLine.charAt(LINE_OFFSET + 8), 10) != 0));*/

}