package de.dickeLunten.coronaPacman.views.panels;

import de.dickeLunten.coronaPacman.ModelListener;
import de.dickeLunten.coronaPacman.controller.NavigationPanels;
import de.dickeLunten.coronaPacman.models.panel.GameModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends PanelView {
    private final GameModel model;
    private final ViewListener viewListener;

    public GamePanel(GameModel model, ModelListener modelListener){
        this.modelListener = modelListener;
        this.model = model;
        setBackground(Color.PINK);
        swb = new JButton("hi");
        this.add(swb);
        swb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelListener.onNavigate(NavigationPanels.END_PANEL);
            }
        });
    }

}
