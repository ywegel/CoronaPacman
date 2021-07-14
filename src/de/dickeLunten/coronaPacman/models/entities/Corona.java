package de.dickeLunten.coronaPacman.models.entities;

import util.Coord;
import util.Data;
import util.Dimensions;

import javax.swing.*;
import java.awt.*;

public class Corona extends EntityModel {

    private int width, height, x, y;
    private Coord coords;
    private PlayerDirection currentDirection;
    private Image coronaImg0, coronaImg1, edibleImg0, edibleImg1;
    private boolean isImageSwitched = false;

    public Corona(int x, int y, PlayerDirection startDir) {
        this.x = x;
        this.y = y;
        this.width = 80;
        this.height = 80;
        this.coords = new Coord(x * Dimensions.PIXEL_PER_CHUNK_X, y * Dimensions.PIXEL_PER_CHUNK_Y);
        this.currentDirection = startDir;
        loadImg(isImageSwitched);
    }

    public Corona(Coord chunkPos, PlayerDirection startDir) {
        this.x = chunkPos.getX() * Dimensions.PIXEL_PER_CHUNK_X;
        this.y = chunkPos.getY() * Dimensions.PIXEL_PER_CHUNK_Y;
        this.width = 80;
        this.height = 80;
        this.coords = chunkPos;
        this.currentDirection = startDir;
        loadImg(isImageSwitched);
    }

    public Corona() {
        this(0, 0, PlayerDirection.RIGHT);
    }

    public Corona(Coord chunkPos, PlayerDirection startDir, boolean isFirstImg) {
        this(chunkPos, startDir);
        isImageSwitched = !isFirstImg;
        loadImg(isFirstImg);
    }

    public Corona(int x, int y, PlayerDirection startDir, boolean isFirstImg) {
        this(x, y, startDir);
        isImageSwitched = !isFirstImg;
        loadImg(isFirstImg);
    }

    private void loadImg(boolean firstImg) {
        if (firstImg) {
            coronaImg0 = Data.loadImageFromRes("img/virus1.png");
            coronaImg1 = Data.loadImageFromRes("img/virus2.png");
            edibleImg0 = Data.loadImageFromRes("img/ediblevirus1.png");
            edibleImg1 = Data.loadImageFromRes("img/ediblevirus2.png");

        } else {
            coronaImg0 = Data.loadImageFromRes("img/virus2.png");
            coronaImg1 = Data.loadImageFromRes("img/virus1.png");
            edibleImg0 = Data.loadImageFromRes("img/ediblevirus2.png");
            edibleImg1 = Data.loadImageFromRes("img/ediblevirus1.png");
        }
    }

    public void move(int s) {
        switch (currentDirection) {
            case UP -> moveUp(s);
            case DOWN -> moveDown(s);
            case RIGHT -> moveRight(s);
            case LEFT -> moveLeft(s);
        }
    }

    private void moveUp(int speed) {
        y = y - speed;
        //cords.setY(cords.getY() - 1);
        //update();
    }

    private void moveDown(int speed) {
        y = y + speed;
        //cords.setY(cords.getY() + 1);
        //update();
    }

    private void moveRight(int speed){
        x = x + speed;
        //cords.setX(cords.getX() + 1);
        //update();
    }

    private void moveLeft(int speed) {
        x = x - speed;
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
        coords.setY(coords.getY() - 1);
    }

    private void moveChunkDown() {
        coords.setY(coords.getY() + 1);
    }

    private void moveChunkRight() {
        coords.setX(coords.getX() + 1);
    }

    private void moveChunkLeft() {
        coords.setX(coords.getX() - 1);
    }

    public void setCurrentDirection(PlayerDirection dir) {
        currentDirection = dir;
    }

    public PlayerDirection getCurrentDirection() {
        return currentDirection;
    }

    public Coord getCoords() {
        return coords;
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

    public void setCoords(Coord coords) {
        this.coords = coords;
    }

    public Image getImage(boolean switched, boolean edible ) {
        if(edible){
            if(switched){
                return edibleImg1;
            } else {
                return edibleImg0;
            }
        }
        else {
            if (switched) {
                return coronaImg1;
            } else {
                return coronaImg0;
            }
        }

    }

/*    public void switchImage() {
        isImageSwitched = !isImageSwitched;
        if (isImageSwitched) {
            Image cache = coronaImg0;
            coronaImg0 = coronaImg1;
            coronaImg1 = cache;
        } else {
            Image cache = coronaImg1;
            coronaImg1 = coronaImg0;
            coronaImg0 = cache;
        }
    }*/
}