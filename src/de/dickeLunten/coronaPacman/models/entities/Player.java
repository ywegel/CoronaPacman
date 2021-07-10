package de.dickeLunten.coronaPacman.models.entities;

import util.Coord;
import util.Data;
import util.Dimensions;

import java.awt.*;

public class Player extends EntityModel {

    private int height, width, x, y, chunkOffsetX, chunkOffsetY;
    private PlayerDirection currentDirection = PlayerDirection.RIGHT;
    private Coord cords;
    private int lives;
    private Image[] img;

    public Player() {
        cords = new Coord(0, 0);
        x = Dimensions.PIXEL_PER_CHUNK_X / 2;
        y = Dimensions.PIXEL_PER_CHUNK_X / 2;
        chunkOffsetX = x;
        chunkOffsetY = y;
        lives = 3;
        width = 64;
        height = 65;
        img = new Image[8];

        //Player size = 641 653
        for (int i = 0; i < 8; i++) {
            img[i] = Data.loadImageFromRes("img/SpielerAnsicht" + i + ".png").getScaledInstance(width,height,Image.SCALE_FAST);
        }
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
        y--;
        //cords.setY(cords.getY() - 1);
        //update();
    }

    private void moveDown() {
        y++;
        //cords.setY(cords.getY() + 1);
        //update();
    }

    private void moveRight() {
        x++;
        //cords.setX(cords.getX() + 1);
        //update();
    }

    private void moveLeft() {
        x--;
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

    public void setCords(Coord cords) {
        this.cords = cords;
    }

    public Coord getCords() {
        return cords;
//        return new Coord(cords.getX() + 1,cords.getY() + 1 );
    }

    public int getHeight() {
        return height;
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

    public void setHeight(int height) {
        this.height = height;
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

    public int getChunkOffsetX() {
        return chunkOffsetX;
    }

    public void setChunkOffsetX(int chunkOffsetX) {
        this.chunkOffsetX = chunkOffsetX;
    }

    public int getChunkOffsetY() {
        return chunkOffsetY;
    }

    public void setChunkOffsetY(int chunkOffsetY) {
        this.chunkOffsetY = chunkOffsetY;
    }

    public void increaseChunkOffsetX() {
        chunkOffsetX++;
    }

    public void increaseChunkOffsetY() {
        chunkOffsetY++;
    }
}
