package de.dickeLunten.coronaPacman.views.panels;

import de.dickeLunten.coronaPacman.ViewListener;
import de.dickeLunten.coronaPacman.controller.NavigationPanels;
import de.dickeLunten.coronaPacman.models.panel.GameModel;
import de.dickeLunten.coronaPacman.views.entities.PlayerView;
import de.dickeLunten.coronaPacman.views.entities.VacView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends PanelView {
    private final GameModel model;
    private final ViewListener viewListener;

    private PlayerView playerView;
    private VacView[] vacViews;

    public GamePanel(GameModel model, ViewListener viewListener){
        this.viewListener = viewListener;
        this.model = model;

        playerView = new PlayerView(model.getPlayer());

        //TODO add HIM

        setBackground(Color.PINK);
        JButton swb = new JButton("hi");
        this.add(swb);

        this.add(playerView);

        swb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewListener.onNavigate(NavigationPanels.END_PANEL);
            }
        });
    }
}