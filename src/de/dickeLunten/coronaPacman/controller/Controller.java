package de.dickeLunten.coronaPacman.controller;

import de.dickeLunten.coronaPacman.ViewListener;
import de.dickeLunten.coronaPacman.models.Model;
import de.dickeLunten.coronaPacman.models.entities.PlayerDirection;
import de.dickeLunten.coronaPacman.models.panel.EndModel;
import de.dickeLunten.coronaPacman.models.panel.GameModel;
import de.dickeLunten.coronaPacman.models.panel.PauseModel;
import de.dickeLunten.coronaPacman.models.panel.StartModel;
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

    private ActionUp actionUp;
    private ActionDown actionDown;
    private ActionLeft actionLeft;
    private ActionRight actionRight;
    private ActionEnter actionEnter;

    private Model model;
    private View view;

    public Controller() {
        this.model = new Model(new StartModel(), new GameModel(), new PauseModel(), new EndModel());
        this.view = new View(model, this);
        initStartInput(view.getStartPanel());
        initGameInput(view.getGamePanel());

        while (gameIsRunning){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void tick() {
        model.getPlayer().move();
    }

    @Contract("_ -> param1")
    private void initStartInput(@NotNull StartPanel panel) {
        actionEnter = new ActionEnter();
        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "enter_action");
        panel.getActionMap().put("enter_action", actionEnter);
    }

    private void initGameInput(GamePanel panel) {
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
        if(destination == NavigationPanels.GAME_PANEL) {
            gameIsRunning = true;
        }
        switch (destination) {
            case START_PANEL -> {
                gameIsRunning = false;
            }
            case GAME_PANEL -> {
                gameIsRunning = true;
            }
            case END_PANEL -> {
                gameIsRunning = false;
            }
        }
        //TODO add bundle and put options in there

        //TODO check if bundle travels to gameoanel, if true, then isRunning = true;
    }
}