package de.dickeLunten.coronaPacman.models.entities;

import de.dickeLunten.coronaPacman.ModelListener;
import util.Coord;

public class Player extends EntityModel {

    private int height, width, x, y;
    private ModelListener modelListeners;
    private PlayerDirection currentDirection = PlayerDirection.RIGHT;
    private Coord cords;
    private int x_count, y_count;
    private int lives;

    public Player() {
        cords = new Coord(0, 0);
        x = 10;
        y = 10;
        x_count = 0;
        y_count = 0;
        lives = 3;
/*        width = 10;
        height = 20;*/
        width = 100;
        height = 200;
    }

    public void addListener(ModelListener ml) {
        this.modelListeners = ml;
    }


    public void update() {
        modelListeners.update();
    }

    public void move() {
        switch (currentDirection) {
            case UP -> moveUp();
            case DOWN -> moveDown();
            case RIGHT -> moveRight();
            case LEFT -> moveLeft();
        }
    }

    //TODO IDEE: pro tick 2mal moven --> move->draw->move->draw

    public void moveUp() {
        y = y - 1;
        cords.setY(cords.getY() - 1);
        //update();
    }

    public void moveDown() {
        y = y + 1;
        cords.setY(cords.getY() + 1);
        //update();
    }

    public void moveRight() {
        x = x + 1;
        cords.setX(cords.getX() + 1);
        //update();
    }

    public void moveLeft() {
        x = x - 1;
        cords.setX(cords.getX() - 1);
        //update();
    }

    public Coord getCoords() {
        return cords;
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
}
