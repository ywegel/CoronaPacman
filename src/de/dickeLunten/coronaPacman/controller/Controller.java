package de.dickeLunten.coronaPacman.controller;

import de.dickeLunten.coronaPacman.ViewListener;
import de.dickeLunten.coronaPacman.models.Model;
import de.dickeLunten.coronaPacman.models.entities.PlayerDirection;
import de.dickeLunten.coronaPacman.models.panel.*;
import de.dickeLunten.coronaPacman.views.View;
import de.dickeLunten.coronaPacman.views.panels.GamePanel;
import de.dickeLunten.coronaPacman.views.panels.StartPanel;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import util.Bundle;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

enum InputAction {
    ACTION_UP,
    ACTION_DOWN,
    ACTION_LEFT,
    ACTION_RIGHT
}

public class Controller implements ViewListener {
    private volatile boolean gameIsRunning = false;

    private static final long NANOS_PER_SECOND = 1_000_000_000L;

    private ActionEnter actionEnter;

    private Model model;
    private View view;

    public Controller() {
        this.model = new Model(new StartModel(), new GameModel(), new PauseModel(), new EndModel(), new CreditsModel(), new RulesModel());
        this.view = new View(model, this);
        initStartInput(view.getStartPanel());
        initGameInput(view.getGamePanel());
    }

    private void loop() {
        //TODO brokey :(
        long nextTick = System.nanoTime();
        long nextSecond = nextTick;

        // max fps: 120
        final long rateLimit = NANOS_PER_SECOND / 120;

        while (gameIsRunning) {
            long loopStart = System.nanoTime();

            //updateGame
            render();

            final long now = System.nanoTime();

            // catch up with as many ticks as needed (skip them if necessary)
            if (now - nextTick >= 0) {
                tick();
                do {
                    nextTick += NANOS_PER_SECOND;
                }while (now - nextTick >= 0);

            }
/*            while (now - nextSecond >= 0) {
                nextSecond+= NANOS_PER_SECOND;
            }*/
            final long delayms = ((loopStart + rateLimit) - System.nanoTime()) / NANOS_PER_SECOND;
            System.out.println(delayms);
            if (delayms > 0) {
                // more than a millisecond wait, do it....
                try {
                    Thread.sleep(delayms);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void tick() {
        model.getPlayer().move();
        System.out.println(model.getPlayer().getX());

        System.out.println(model.getPlayer().getCurrentDirection());

    }

    private void render() {
        //model.getPlayer().update();
        //view.getGamePanel().update();
    }

    @Contract("_ -> param1")
    private void initStartInput(@NotNull StartPanel panel) {
        actionEnter = new ActionEnter();
        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "enter_action");
        panel.getActionMap().put("enter_action", actionEnter);
    }

    private void initGameInput(GamePanel panel) {
        new Thread(() -> {
            ActionUp actionUp;
            ActionDown actionDown;
            ActionLeft actionLeft;
            ActionRight actionRight;

            actionUp = new ActionUp();
            actionDown = new ActionDown();
            actionLeft = new ActionLeft();
            actionRight = new ActionRight();

            panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), InputAction.ACTION_UP);
            panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('w'), InputAction.ACTION_UP);
            panel.getActionMap().put(InputAction.ACTION_UP, actionUp);

            panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), InputAction.ACTION_DOWN);
            panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('s'), InputAction.ACTION_DOWN);
            panel.getActionMap().put(InputAction.ACTION_DOWN, actionDown);

            panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), InputAction.ACTION_LEFT);
            panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('a'), InputAction.ACTION_LEFT);
            panel.getActionMap().put(InputAction.ACTION_LEFT, actionLeft);

            panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), InputAction.ACTION_RIGHT);
            panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('d'), InputAction.ACTION_RIGHT);
            panel.getActionMap().put(InputAction.ACTION_RIGHT, actionRight);

        }).start();
    }

    public class ActionUp extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("up");
            model.getPlayer().setCurrentDirection(PlayerDirection.UP);
        }
    }

    public class ActionDown extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("down");
            model.getPlayer().setCurrentDirection(PlayerDirection.DOWN);
            model.getPlayer().moveDown();
        }
    }

    public class ActionLeft extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("left");
            model.getPlayer().setCurrentDirection(PlayerDirection.LEFT);
        }
    }

    public class ActionRight extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("right");
            model.getPlayer().setCurrentDirection(PlayerDirection.RIGHT);
        }
    }

    public class ActionEnter extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Pog");
            view.onNavigate(NavigationPanels.GAME_PANEL);
        }
    }

    @Override
    public void onNavigate(NavigationPanels destination, Bundle bundle) {
        view.onNavigate(destination, bundle);
        switch (destination) {
            case START_PANEL -> {
                gameIsRunning = false;
                break;
            }
            case GAME_PANEL -> {
                gameIsRunning = true;
                loop();
                break;
            }
            case END_PANEL -> {
                gameIsRunning = false;
                break;
            }
            case CREDITS_PANEL -> {
                gameIsRunning = false;
                break;
            }
        }
        //TODO add bundle and put options in there

        //TODO check if bundle travels to gameoanel, if true, then isRunning = true;
    }
}