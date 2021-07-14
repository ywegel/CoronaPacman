package de.dickeLunten.coronaPacman;

public interface GameModelListener extends ModelListener{
    void onFpsChanged(int fps);

    void onScoreChanged(int score);

    void finishGame(int score);

    void onLivesChanged(int lives);

    void update();
}