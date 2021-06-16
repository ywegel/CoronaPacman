package de.dickeLunten.coronaPacman.views.panels;

import de.dickeLunten.coronaPacman.ModelListener;
import de.dickeLunten.coronaPacman.controller.NavigationPanels;

import javax.swing.*;

public abstract class PanelView extends JComponent implements ModelListener {
    @Override
    public void update() {

    }

    @Override
    public void onNavigate(NavigationPanels destination) {

    }
}
