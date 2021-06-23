package de.dickeLunten.coronaPacman.models.entities;

import de.dickeLunten.coronaPacman.ModelListener;
import util.Coord;

public class Player extends EntityModel {

    private int height, width, x, y;
    private ModelListener modelListeners;
    private PlayerDirection currentDirection = PlayerDirection.UP;
    private Coord cords;
    private int lives;

    public Player(){
        cords = new Coord(0,0);
        x = 0;
        y = 0;
        lives = 3;
/*        width = 10;
        height = 20;*/
        width = 100;
        height = 200;
    }

    public void addListener(ModelListener ml) {
        this.modelListeners = ml;
    }


    public void update(){
        modelListeners.update();
    }

    public void move() {
            switch(currentDirection){
                case UP -> moveUp();
                case DOWN -> moveDown();
                case RIGHT -> moveRight();
                case LEFT -> moveLeft();
            }
    }

    public void moveUp(){
        y = y - 5;
        cords.setY(cords.getY() - 5);
        //update();
    }
    public void moveDown(){
        y = y + 5;
        cords.setY(cords.getY() + 5);
        //update();
    }
    public void moveRight(){
        x = x + 5;
        cords.setX(cords.getX() + 5);
        //update();
    }
    public void moveLeft(){
        x = x - 5;
        cords.setX(cords.getX() - 5);
        //update();
    }

    public Coord getCoords(){
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

    public int getLives(){return lives;}

    public void setLives(int a){lives = a;}
}
