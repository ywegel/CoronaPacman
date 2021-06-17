package util;

import java.awt.*;

public class Dimensions {

    public static Pair<Integer, Integer> getScreenResolution() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        return new Pair(dim.height, dim.width);
    }
}