package util;

public class MapChunkValues {
    private boolean hasDot;
    private boolean hasToiletPaper;
    private boolean hasVac;
    private boolean hasCorona;
    private PlayerMovableDir playerMovableDir;

    public MapChunkValues(boolean hasDot, boolean hasToiletPaper, boolean hasVac, boolean hasCorona, PlayerMovableDir playerMovableDir) {
        this.hasDot = hasDot;
        this.hasToiletPaper = hasToiletPaper;
        this.hasVac = hasVac;
        this.hasCorona = hasCorona;
        this.playerMovableDir = playerMovableDir;
    }
}
