package de.dickeLunten.coronaPacman;

import de.dickeLunten.coronaPacman.controller.NavigationPanels;

public interface ModelListener {

    public void update();
    public void onNavigate(NavigationPanels destination);

}
