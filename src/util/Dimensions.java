package util;

import java.awt.*;

public class Dimensions {
    private static Pair<Integer, Integer> screenRes;

    public static Pair<Integer, Integer> getScreenResolution() {
        if(screenRes == null) {
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            screenRes =  new Pair(dim.height, dim.width);
        }
        return screenRes;
    }
}