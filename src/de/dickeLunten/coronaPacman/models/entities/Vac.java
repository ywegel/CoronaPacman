package de.dickeLunten.coronaPacman.models.entities;

import util.Coord;
import util.Data;

import java.awt.*;

public class Vac {

    private int length, width, x, y;
    private Coord cords;

    private Image image;

    public Vac(Coord cords, int x, int y){
        this.cords = cords;
        this.x = x;
        this.y = y;
        length = 70;
        width = 70;
        image = Data.loadImageFromRes("img/Impfung.png").getScaledInstance(width, length , Image.SCALE_DEFAULT);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Coord getCords() {
        return cords;
    }

    public Image getImage() {
        return image;
    }
}
