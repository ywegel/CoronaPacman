package de.dickeLunten.coronaPacman;

public interface GameModelListener {
    void onFpsChanged(int fps);

    void onScoreLivesChanged(int score, int lives);

    void finishGame(int score);

    void update();
}