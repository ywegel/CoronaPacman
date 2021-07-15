package de.dickeLunten.coronaPacman.models.entities;

import util.Coord;
import util.Data;
import util.Dimensions;

import java.awt.*;

/**
 * Model welches alle Spielerdaten enthölt
 *
 * @author Colin Clauss
 * @author Yannick Wegel
 * @author Daniel Bund
 * @author Felix Rosner
 * @author Jake Finch
 * @version 2021-07-15
 */
public class Player extends EntityModel {

    //Hoehe, Weite, X-Koordinate, Y-Koordinate und Leben des Spielers
    private int height, width, x, y, lives;

    //Momentane und Geplante Richtung des Spielers
    private PlayerDirection currentDirection = PlayerDirection.RIGHT;
    private PlayerDirection plannedDirection;

    //Koordinaten des Spielers im Gitter des Spielfelds
    private Coord cords;

    //Bild des Spielers
    private Image[] img;

    /**
     * Konstruktor des Players.
     * Erzeugt ein Player-Objekt mit Standartwerten
     */
    public Player() {
        cords = new Coord(0, 0);
        x = Dimensions.PIXEL_PER_CHUNK_X / 2;
        y = Dimensions.PIXEL_PER_CHUNK_X / 2;
        lives = 3;
        width = 64;
        height = 65;
        plannedDirection = currentDirection;
        img = new Image[8];

        //Player size = 641 653
        for (int i = 0; i < 8; i++) {
            img[i] = Data.loadImageFromRes("img/SpielerAnsicht" + i + ".png").getScaledInstance(width, height, Image.SCALE_FAST);
        }
    }

    /**
     * Entscheidet welche move-Methode ausgeführt wird je nach dem in welche Richtung sich der Spieler bewegen soll.
     */
    public void move() {
        switch (currentDirection) {
            case UP -> moveUp();
            case DOWN -> moveDown();
            case RIGHT -> moveRight();
            case LEFT -> moveLeft();
        }
    }

    /**
     * Move-Methode die den Spieler nach oben bewegt
     */
    private void moveUp() {
        y = y - 2;
    }

    /**
     * Move-Methode die den Spieler nach unten bewegt
     */
    private void moveDown() {
        y = y + 2;
    }

    /**
     * Move-Methode die den Spieler nach rechts bewegt
     */
    private void moveRight() {
        x = x + 2;
    }

    /**
     * Move-Methode die den Spieler nach links bewegt
     */
    private void moveLeft() {
        x = x - 2;
    }

    public void setCords(Coord cords) {
        this.cords = cords;
    }

    public Coord getCords() {
        return cords;
    }

    public PlayerDirection getPlannedDirection() {
        return plannedDirection;
    }

    public void setPlannedDirection(PlayerDirection plannedDirection) {
        this.plannedDirection = plannedDirection;
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

    public PlayerDirection getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(PlayerDirection currentDirection) {
        this.currentDirection = currentDirection;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int a) {
        lives = a;
    }

    public Image getImg(boolean isSwitch) {
        return switch (currentDirection) {
            case UP -> img[isSwitch ? 1 : 0];
            case DOWN -> img[2 + (isSwitch ? 1 : 0)];
            case RIGHT -> img[4 + (isSwitch ? 1 : 0)];
            case LEFT -> img[6 + (isSwitch ? 1 : 0)];
        };
    }
}
