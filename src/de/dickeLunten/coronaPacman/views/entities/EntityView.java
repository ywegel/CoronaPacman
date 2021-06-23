package de.dickeLunten.coronaPacman.views.entities;

import de.dickeLunten.coronaPacman.ModelListener;
import de.dickeLunten.coronaPacman.controller.NavigationPanels;
import de.dickeLunten.coronaPacman.models.Model;

import javax.swing.*;

public abstract class EntityView extends JComponent implements ModelListener {

    public void finishGame(int score){ }

}
