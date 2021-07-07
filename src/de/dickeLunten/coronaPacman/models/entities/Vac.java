package de.dickeLunten.coronaPacman.models.entities;

import util.Data;

import java.awt.*;

public class Vac {

    private int length, width, x, y;

    private Image image;

    public Vac(int x, int y){
        this.x = x;
        this.y = y;
        length = 50;
        width = 50;
        image = Data.loadImageFromRes("img/Impfung.png");
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

    public Image getImage() {
        return image;
    }
}
