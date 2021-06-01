package de.dickeLunten.coronaPacman.views.panels;

import de.dickeLunten.coronaPacman.models.panel.GameModel;

import javax.swing.*;

public class GamePanel extends JPanel {
    private GameModel model;

    public GamePanel(GameModel model){
        this.model = model;
    }
}
