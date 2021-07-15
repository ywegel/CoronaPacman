package de.dickeLunten.coronaPacman.controller;

import de.dickeLunten.coronaPacman.models.Model;
import de.dickeLunten.coronaPacman.views.View;
import util.WAVPlayer;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

/**
 * Controller und Gameloop des Spiels
 *
 * @author Yannick Wegel
 * @version 2021-07-15
 */

public class GameLoop implements Runnable {

    //Anzahl an Nanosekunden pro Sekunde
    private static final long NANOS_PER_SECOND = 1_000_000_000L;

    //Zustand des Spiels
    private volatile boolean gameIsRunning;
    private volatile boolean gameIsPaused;

    //Mutex zum Stoppen des Threads
    private final Object mutex = new Object();

    //Model und View des Controllers
    private final Model model;
    private final View view;

    /**
     * Konstruktor des Gameloop-Controllers.
     * Erzeugt ein Gameloop-Objekt mit den Startwerten und übergibt Model und View vom Haupt-Controller
     *
     * @param model Model des MVC-Musters
     * @param view  View des MVC-Musters
     */
    public GameLoop(Model model, View view) {
        this.model = model;
        this.view = view;
        gameIsRunning = true;
        gameIsPaused = false;
    }

    /**
     * Startet die loop-Methode und benachrichtigt wenn diese beendet wurde
     */
    @Override
    public void run() {
        try {
            loop();
        } finally {
            System.out.println(Thread.currentThread().getName() + ": Thread and Runnable stopped");
        }
    }

    /**
     * Loop der sich um das Malen des Spiels und die Logik kümmert
     */
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

    /**
     * Führt die Spiellogik aus
     */
    private void tick(int tick) {
        model.getGameModel().gameTick(tick);
    }

    /**
     * Malt das Spiel auf das Fenster
     */
    private void render() {
        view.getGamePanel().update();
    }

    /**
     * Pausiert das Spiel
     */
    public void pauseGame() {
        gameIsPaused = true;
    }

    /**
     * Lässt das Spiel weiterlaufen
     */
    public void continueGame() {
        gameIsPaused = false;
        synchronized (mutex) {
            mutex.notifyAll();
        }
    }

    /**
     * Beendet das Spiel durch das Stoppen der GameLoop-Schleife
     */
    public void exitGame() {
        gameIsRunning = false;
        synchronized (mutex) {
            gameIsRunning = false;
            mutex.notifyAll();
        }
    }
}