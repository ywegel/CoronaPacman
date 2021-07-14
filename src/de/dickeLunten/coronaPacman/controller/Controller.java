package de.dickeLunten.coronaPacman.controller;

import de.dickeLunten.coronaPacman.ViewListener;
import de.dickeLunten.coronaPacman.models.Model;
import de.dickeLunten.coronaPacman.models.entities.PlayerDirection;
import de.dickeLunten.coronaPacman.models.panel.*;
import de.dickeLunten.coronaPacman.views.View;
import de.dickeLunten.coronaPacman.views.panels.GamePanel;
import de.dickeLunten.coronaPacman.views.panels.StartPanel;
import util.Bundle;
import util.WAVPlayer;

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
    private Thread musicThread, gameLoopThread;
    private GameLoop gameLoop;

    private ActionEnter actionEnter;

    private final Model model;
    private final View view;

    public Controller() {
        //TODO only initiate models when screen starts, so that every time it starts new screen!!!
        this.model = new Model(new StartModel(), new GameModel(), new EndModel(), new CreditsModel(), new RulesModel());
        this.view = new View(model, this);
        initStartInput(view.getStartPanel());
    }

    @Override
    public void notifyPauseGame() {
        gameLoop.pauseGame();
        WAVPlayer.pauseMusic();
    }

    @Override
    public void notifyContinueGame() {
        gameLoop.continueGame();
    }

    @Override
    public void notifyExitGame() {
        gameLoop.exitGame();
        WAVPlayer.killThread();
    }

    @Override
    public void notifyGameStarted() {
        initGameInput(view.getGamePanel());
        gameLoop = new GameLoop(model, view);
        gameLoopThread = new Thread(gameLoop);
        gameLoopThread.start();
        musicThread = new Thread(WAVPlayer::playGameMusic);
        musicThread.start();
    }

    private void initStartInput(StartPanel panel) {
        actionEnter = new ActionEnter();
        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "enter_action");
        panel.getActionMap().put("enter_action", actionEnter);
    }

    private void initGameInput(GamePanel panel) {
        ActionUp actionUp = new ActionUp();
        ActionDown actionDown = new ActionDown();
        ActionLeft actionLeft = new ActionLeft();
        ActionRight actionRight = new ActionRight();

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
            model.getGameModel().requestTurn(PlayerDirection.UP);
        }
    }

    public class ActionDown extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.getGameModel().requestTurn(PlayerDirection.DOWN);
        }
    }

    public class ActionLeft extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.getGameModel().requestTurn(PlayerDirection.LEFT);
        }
    }

    public class ActionRight extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.getGameModel().requestTurn(PlayerDirection.RIGHT);
        }
    }

    public class ActionEnter extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            onNavigate(NavigationPanels.GAME_PANEL, Bundle.emptyBundle());
            notifyGameStarted();
        }
    }

    @Override
    public void onNavigate(NavigationPanels destination, Bundle bundle) {
        view.onNavigate(destination, bundle);
    }
}