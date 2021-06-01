package de.dickeLunten.coronaPacman.controller;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

public class GameController implements KeyListener {
    private volatile boolean isRunning;

    private void loop(){
        long deltaTime = 0;
        long startTime = 0;
        while(isRunning){
            updateInput();

            tick();
            render();
        }
    }

    private void updateInput(){
        //Scanner d = new Scanner()
    }

    private void tick(){

    }

    private void render(){

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:

                break;
            case KeyEvent.VK_RIGHT:

                break;

            case KeyEvent.VK_UP:

                break;

            case KeyEvent.VK_DOWN:

                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}