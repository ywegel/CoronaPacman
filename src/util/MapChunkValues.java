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

    public boolean isHasDot() {
        return hasDot;
    }

    public MapChunkValues setHasDot(boolean hasDot) {
        this.hasDot = hasDot;
        return this;
    }

    public MapChunkValues setHasVac(boolean hasVac){
        this.hasVac = hasVac;
        return this;
    }

    public boolean isHasToiletPaper() {
        return hasToiletPaper;
    }

    public void setHasToiletPaper(boolean hasToiletPaper) {
        this.hasToiletPaper = hasToiletPaper;
    }

    public boolean isHasVac() {
        return hasVac;
    }

    public boolean isHasCorona() {
        return hasCorona;
    }

    public void setHasCorona(boolean hasCorona) {
        this.hasCorona = hasCorona;
    }

    public PlayerMovableDir getPlayerMovableDir() {
        return playerMovableDir;
    }

    public void setPlayerMovableDir(PlayerMovableDir playerMovableDir) {
        this.playerMovableDir = playerMovableDir;
    }
}
