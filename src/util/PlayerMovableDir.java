package util;

import de.dickeLunten.coronaPacman.models.entities.PlayerDirection;

public class PlayerMovableDir {

    private final boolean up, down, left, right;


    public PlayerMovableDir(boolean up, boolean down, boolean left, boolean right) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }
}
