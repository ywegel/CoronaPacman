package de.dickeLunten.coronaPacman.models.entities;

import util.Coord;
import util.PlayerMovableDir;

public class Corona extends EntityModel {

    private int length, width, x, y;
    private Coord cords;
    private PlayerDirection currentDirection;

    public Corona(){
        x = 0;
        y = 0;
        length = 20;
        width = 10;
        cords = new Coord(0,0);
    }

    public void move() {
        switch (currentDirection) {
            case UP -> moveUp();
            case DOWN -> moveDown();
            case RIGHT -> moveRight();
            case LEFT -> moveLeft();
        }
    }

    private void moveUp() {
        y = y - 1;
        //cords.setY(cords.getY() - 1);
        //update();
    }

    private void moveDown() {
        y = y + 1;
        //cords.setY(cords.getY() + 1);
        //update();
    }

    private void moveRight() {
        x = x + 1;
        //cords.setX(cords.getX() + 1);
        //update();
    }

    private void moveLeft() {
        x = x - 1;
        //cords.setX(cords.getX() - 1);
        //update();
    }

    public void moveChunk() {
        switch (currentDirection) {
            case UP -> moveChunkUp();
            case DOWN -> moveChunkDown();
            case RIGHT -> moveChunkRight();
            case LEFT -> moveChunkLeft();
        }
    }

    private void moveChunkUp() {
        cords.setY(cords.getY() - 1);
    }

    private void moveChunkDown() {
        cords.setY(cords.getY() + 1);
    }

    private void moveChunkRight() {
        cords.setX(cords.getX() + 1);
    }

    private void moveChunkLeft() {
        cords.setX(cords.getX() - 1);
    }

    public void setCurrentDirection(PlayerDirection dir){
        currentDirection = dir;
    }

    public Coord getCords() {
        return cords;
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