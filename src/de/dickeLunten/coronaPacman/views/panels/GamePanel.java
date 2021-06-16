package de.dickeLunten.coronaPacman.views.panels;

import de.dickeLunten.coronaPacman.ModelListener;
import de.dickeLunten.coronaPacman.controller.NavigationPanels;
import de.dickeLunten.coronaPacman.models.panel.GameModel;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private GameModel model;

    public GamePanel(GameModel model, ModelListener modelListener){
        this.modelListener = modelListener;
        this.model = model;
        setBackground(Color.PINK);
    }
}
