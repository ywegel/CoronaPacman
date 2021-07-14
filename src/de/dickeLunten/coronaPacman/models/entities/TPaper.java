package de.dickeLunten.coronaPacman.models.entities;

import util.Data;

import java.awt.*;

public class TPaper {

    private int length, width, x, y;
    private Image image;

    public TPaper(){
        x = 0;
        y = 0;
        length = 50;
        width = 50;
        image = Data.loadImageFromRes("img/toilettenpapier.png");
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
