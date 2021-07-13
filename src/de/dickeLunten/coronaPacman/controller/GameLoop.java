package de.dickeLunten.coronaPacman.controller;

import de.dickeLunten.coronaPacman.models.Model;
import de.dickeLunten.coronaPacman.views.View;
import util.WAVPlayer;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class GameLoop implements Runnable {
    private static final long NANOS_PER_SECOND = 1_000_000_000L;

    private volatile boolean gameIsRunning;
    private volatile boolean gameIsPaused;
    private final Object mutex = new Object();

    private Model model;
    private View view;

    public GameLoop(Model model, View view) {
        this.model = model;
        this.view = view;
        gameIsRunning = true;
        gameIsPaused = false;
    }

    @Override
    public void run() {
        try {
            loop();
        } finally {
            System.out.println(Thread.currentThread().getName() + " ");
        }
    }

    private void loop() {
        final int fps = 60;

        final long nanosPerFrame = NANOS_PER_SECOND / fps;

        long statt = System.nanoTime();
        int frameCount = 0;

        int ticksss = 0;

        while (gameIsRunning) {

            if (gameIsPaused) {
                synchronized (mutex) {
                    try {
                        WAVPlayer.pauseMusic();
                        mutex.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            long loopStart = System.nanoTime();

            //Pass render method to java.swing thread and !!wait
            try {
                SwingUtilities.invokeAndWait(this::render);
            } catch (InterruptedException | InvocationTargetException e) {
                e.printStackTrace();
            }

            tick(ticksss);
            ticksss++;

            long deltaLoop = System.nanoTime() - loopStart;

            if (deltaLoop < nanosPerFrame) {
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
        }
    }

    private void tick(int tick) {
        model.getGameModel().gameTick(tick);
    }

    private void render() {
        view.getGamePanel().update();
    }

    public void pauseGame() {
        gameIsPaused = true;
    }

    public void continueGame() {
        gameIsPaused = false;
        synchronized (mutex) {
            mutex.notifyAll();
        }
    }

    public void exitGame() {
        gameIsRunning = false;
        synchronized (mutex) {
            gameIsRunning = false;
            mutex.notifyAll();
        }
    }

}