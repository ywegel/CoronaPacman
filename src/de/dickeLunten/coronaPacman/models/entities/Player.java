package de.dickeLunten.coronaPacman.models.entities;

import de.dickeLunten.coronaPacman.ModelListener;
import de.dickeLunten.coronaPacman.models.Model;
import de.dickeLunten.coronaPacman.views.entities.EntityView;
import de.dickeLunten.coronaPacman.views.entities.PlayerView;

import java.util.ArrayList;

public class Player extends EntityModel {

    private int length, width, x, y;
    private ArrayList<ModelListener> modelListeners;
    private PlayerDirection currentDirection = PlayerDirection.UP;

    public Player(){
        x = 0;
        y = 0;
        length = 20;
        width = 10;
        modelListeners = new ArrayList<ModelListener>();
    }

    public void add(ModelListener modelListener){
        modelListeners.add(modelListener);
    }

    public void remove(){
        modelListeners.remove(0);
    }

    public void update(){
        for(ModelListener ml:modelListeners){
            ml.update();
        }
    }

    public void moveup(){
        y = y - 5;
        update();
    }
    public void movedown(){
        y = y + 5;
        update();
    }
    public void moveright(){
        x = x + 5;
        update();
    }
    public void moveleft(){
        x = x - 5;
        update();
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

    public PlayerDirection getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(PlayerDirection currentDirection) {
        this.currentDirection = currentDirection;
    }
}
