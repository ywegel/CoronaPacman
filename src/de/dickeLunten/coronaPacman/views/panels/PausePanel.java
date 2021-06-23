package de.dickeLunten.coronaPacman.views.panels;

import de.dickeLunten.coronaPacman.models.panel.PauseModel;

import javax.swing.*;

public class PausePanel extends PanelView {
    private PauseModel model;

    public PausePanel(PauseModel model){
        this.model = model;
    }

    @Override
    public void update() {

    }
    public void finishGame(int score){ }
}
