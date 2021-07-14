package util;

public class MapChunkValues {
    private boolean hasDot;
    private boolean hasToiletPaper;
    private boolean hasVac;
    private PlayerMovableDir playerMovableDir;

    public MapChunkValues(boolean hasDot, boolean hasToiletPaper, boolean hasVac, PlayerMovableDir playerMovableDir) {
        this.hasDot = hasDot;
        this.hasToiletPaper = hasToiletPaper;
        this.hasVac = hasVac;
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

    public MapChunkValues setHasToiletPaper(boolean hasToiletPaper) {
        this.hasToiletPaper = hasToiletPaper;
        return this;
    }

    public boolean isHasVac() {
        return hasVac;
    }

    public PlayerMovableDir getPlayerMovableDir() {
        return playerMovableDir;
    }

    public void setPlayerMovableDir(PlayerMovableDir playerMovableDir) {
        this.playerMovableDir = playerMovableDir;
    }
}
