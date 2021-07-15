package util;

import java.awt.*;

public class Dimensions {

    public static final int MAP_PIXEL_WIDTH = 800;
    public static final int MAP_PIXEL_HEIGHT = 1048;

    public static final int MAP_WIDTH = 17;
    public static final int MAP_HEIGHT = 20;

    public static final Pair<Integer, Integer> MAP_DIMENSION = new Pair<>(MAP_WIDTH, MAP_HEIGHT);

    public static final int PIXEL_PER_CHUNK_X = 45;
    public static final int PIXEL_PER_CHUNK_Y = 49;

    public static final int TICKS_PER_ANIMATION_SWITCH = 60;
    public static final int TICKS_PER_PLAYER_ANIMATION_SWITCH = 15;
    public static final int TICKS_PER_FAST_ANIMATION_SWITCH = 30;

    public static final int MAP_OFFSET_X = 33;
    public static final int MAP_OFFSET_Y = 45;

    private static Pair<Integer, Integer> screenRes;

    public static Pair<Integer, Integer> getScreenResolution() {
        if(screenRes == null) {
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            screenRes =  new Pair<>(dim.height, dim.width);
        }
        return screenRes;
    }
}