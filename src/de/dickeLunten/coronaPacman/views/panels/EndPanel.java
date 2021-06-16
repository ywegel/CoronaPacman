package de.dickeLunten.coronaPacman.views.panels;

import de.dickeLunten.coronaPacman.models.panel.EndModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class EndPanel extends JPanel {
    private EndModel model;

    public EndPanel(EndModel model){
        this.model = model;
    }
}
