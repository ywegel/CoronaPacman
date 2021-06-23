package de.dickeLunten.coronaPacman.views.panels;

import de.dickeLunten.coronaPacman.ViewListener;
import de.dickeLunten.coronaPacman.controller.NavigationPanels;
import de.dickeLunten.coronaPacman.models.panel.GameModel;
import de.dickeLunten.coronaPacman.views.entities.PlayerView;
import de.dickeLunten.coronaPacman.views.entities.VacView;
import util.Bundle;
import util.Dimensions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends PanelView {
    public static final String KEY_SCORE = "game_panel";

    private final GameModel model;
    private final ViewListener viewListener;


    private PlayerView playerView;
    private VacView[] vacViews;

    public GamePanel(GameModel model, ViewListener viewListener){
        this.viewListener = viewListener;
        this.model = model;

        playerView = new PlayerView(model.getPlayer());

        setBackground(Color.PINK);
        JButton swb = new JButton("hi");
        this.add(swb);

        this.add(playerView);

        swb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewListener.onNavigate(NavigationPanels.END_PANEL, new Bundle().put(KEY_SCORE, model.getScore()));
            }
        });
    }

    public void finishGame(){
        viewListener.onNavigate(NavigationPanels.END_PANEL, new Bundle().put(KEY_SCORE, model.getScore()));
    }

    @Override
    public void update() {
        //paintImmediately(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        repaint();
    }
}