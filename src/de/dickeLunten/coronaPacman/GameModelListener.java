package de.dickeLunten.coronaPacman;

public interface GameModelListener extends ModelListener{

    void onFpsChanged(int fps);

    void onScoreChanged(int score);

}