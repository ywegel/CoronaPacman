package de.dickeLunten.coronaPacman.models.entities;

public class Vac {

    private int length, width, x, y;

    public Vac(int x, int y){
        this.x = x;
        this.y = y;
        length = 20;
        width = 10;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

}
