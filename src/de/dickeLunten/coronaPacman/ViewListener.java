package de.dickeLunten.coronaPacman;

import de.dickeLunten.coronaPacman.controller.NavigationPanels;

public interface ViewListener {

    void onNavigate(NavigationPanels destination, int... options);

}
