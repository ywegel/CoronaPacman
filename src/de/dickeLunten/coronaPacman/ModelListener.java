package de.dickeLunten.coronaPacman;

import de.dickeLunten.coronaPacman.controller.NavigationPanels;

public interface ModelListener {

    void finishGame(int score);
    void update();

}
