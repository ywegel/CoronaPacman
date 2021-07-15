package de.dickeLunten.coronaPacman;

public interface GameModelListener extends ModelListener{
    void onFpsChanged(int fps);

    void onScoreLivesChanged(int score, int lives);

    void finishGame(int score);

    void update();
}