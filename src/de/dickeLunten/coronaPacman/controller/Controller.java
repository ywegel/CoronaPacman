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
import java.lang.reflect.InvocationTargetException;

enum InputAction {
    ACTION_UP,
    ACTION_DOWN,
    ACTION_LEFT,
    ACTION_RIGHT
}

public class Controller implements ViewListener {
    private volatile boolean gameIsRunning = false;
    private volatile boolean gameIsPaused = false;
    private final Object lock = new Object();

    private Thread musicThread;

    private static final long NANOS_PER_SECOND = 1_000_000_000L;

    private ActionEnter actionEnter;

    private Model model;
    private View view;

    public Controller() {
        //TODO only initiate models when screen starts, so that every time it starts new screen!!!
        this.model = new Model(new StartModel(), new GameModel(), new PauseModel(), new EndModel(), new CreditsModel(), new RulesModel());
        this.view = new View(model, this);
        initStartInput(view.getStartPanel());
        initGameInput(view.getGamePanel());
    }

    private void loop() {
        //https://pavelfatin.com/low-latency-painting-in-awt-and-swing/

        final int fps = 60;

        final long nanosPerFrame = NANOS_PER_SECOND / fps;

        //System.out.println("NanosPerFrame :" + nanosPerFrame);

        long statt = System.nanoTime();
        int frameCount = 0;

        int ticksss = 0;

        musicThread = new Thread(WAVPlayer::playGameMusic);
        musicThread.start();

        while (gameIsRunning) {

            if(gameIsPaused) {
                synchronized (lock) {
                    try {
                        WAVPlayer.pauseMusic();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            long loopStart = System.nanoTime();

            //update game screen
            long startRender = System.nanoTime();

            //Pass render method to java.swing thread and !!wait
            try {
                SwingUtilities.invokeAndWait(this::render);
            } catch (InterruptedException | InvocationTargetException e) {
                e.printStackTrace();
            }
            long deltaRender = System.nanoTime() - startRender;

            //System.out.println("DELTA RENDER: " + deltaRender);

            long now = System.nanoTime();

            long startTick = System.nanoTime();
            tick(ticksss);
            ticksss++;

            long deltaTick = System.nanoTime() - startTick;

            long deltaLoop = System.nanoTime() - loopStart;

            if(deltaLoop < nanosPerFrame) {
                try {
                    Thread.sleep((nanosPerFrame - deltaLoop) / 1_000_000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            frameCount++;
            long deltaStatt = System.nanoTime() - statt;
            if (deltaStatt > NANOS_PER_SECOND) {
                System.out.println("FPS: " + frameCount);
                model.getGameModel().setFps(frameCount);
                frameCount = 0;
                statt = System.nanoTime();
            }

/*            final long delayms = ((loopStart + rateLimit) - System.nanoTime()) / NANOS_PER_SECOND;
            System.out.println("Delay: " + delayms);*/
            //System.out.println("DeltaRender: " + deltaRender + " ;DeltaTick: " + deltaTick);
            //System.out.println("--------------------------");
/*            if (delayms > 0) {
                // more than a millisecond wait, do it....
                try {
                    Thread.sleep(delayms);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }*/
/*            try {
                System.out.println("-----Thread sleepy");
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
    }


    private void tick(int tick) {
        //System.out.println("Tick");
        model.getGameModel().gameTick(tick);
    }

    private void render() {
        //System.out.println("Render");
        view.getGamePanel().update();
    }

    @Override
    public void pauseGame() {
        gameIsPaused = true;
    }

    @Override
    public void continueGame() {
        gameIsPaused = false;

        synchronized (lock) {
            lock.notifyAll();
        }
        WAVPlayer.continueMusic();
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
        }
    }

    @Override
    public void onNavigate(NavigationPanels destination, Bundle bundle) {
        view.onNavigate(destination, bundle);

        if (destination == NavigationPanels.GAME_PANEL) {
            gameIsRunning = true;
            new Thread(this::loop).start();
        } else {
            gameIsRunning = false;
            WAVPlayer.killThread();
            System.out.println("is music thread alive: " + musicThread.isAlive());
        }

    }
}