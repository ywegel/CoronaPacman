package de.dickeLunten.coronaPacman.controller;

import de.dickeLunten.coronaPacman.models.entities.Corona;
import de.dickeLunten.coronaPacman.models.entities.Player;
import de.dickeLunten.coronaPacman.views.entities.CoronaView;
import de.dickeLunten.coronaPacman.views.entities.PlayerView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;
import java.util.ArrayList;

public class GameController {
    private volatile boolean isRunning;

    //Models
    private Player playerModel;
    private ArrayList<Corona> coronaModels;

    //Views
    private PlayerView playerView;
    private ArrayList<CoronaView> coronaViews;

    //actions/events
    private UpAction upAction;
    private DownAction downAction;
    private LeftAction leftAction;
    private RightAction rightAction;

    public GameController(){
        isRunning = true;
        loop();
    }

    private void loop(){
        long deltaTime = 0;
        long startTime = 0;
        int fps;
/*        while(isRunning){
            long now = System.nanoTime();
            tick();
            render();
            if(fps % 20 >= 60){

            }
            //|---------------------|
            //|----------|sleep-----|
            //|---|idle-------------|
            //|----------------|idle|
            while (now < 1000000000/60){

            }
        }*/
    }

    private void inputMapping(){
        /*playerView.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "upAction");
        playerView.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('w'), "upAction");
        playerView.getActionMap().put("upAction", upAction);

        playerView.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "downAction");
        playerView.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('s'), "downAction");
        playerView.getActionMap().put("downAction", downAction);

        playerView.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "leftAction");
        playerView.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('a'), "leftAction");
        playerView.getActionMap().put("leftAction", leftAction);

        playerView.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "rightAction");
        playerView.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('d'), "rightAction");
        playerView.getActionMap().put("rightAction", rightAction);*/
    }

    private void updateInput(){

    }

    private void tick(){

    }

    private void render(){

    }

    public class UpAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("1: " + e.paramString());
        }
    }

    public class DownAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("2: " + e.paramString());
        }
    }

    public class LeftAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("3: " + e.paramString());
        }
    }

    public class RightAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("4: " + e.paramString());
        }
    }

}