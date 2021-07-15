package de.dickeLunten.coronaPacman.models.entities;

import util.Coord;
import util.Data;
import util.Dimensions;

import java.awt.*;

public class TPaper {

    private int length, width, x, y;
    private Image image;
    private Coord coords;

    public TPaper(Coord coord){
        x = coord.getX() * Dimensions.PIXEL_PER_CHUNK_X;
        y = coord.getY() * Dimensions.PIXEL_PER_CHUNK_Y;
        this.coords = coord;
        length = 50;
        width = 50;
        image = Data.loadImageFromRes("img/toilettenpapier.png").getScaledInstance(width, length , Image.SCALE_DEFAULT);
    }



    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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

    public Coord getCoords() {
        return coords;
    }
}
