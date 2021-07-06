package de.dickeLunten.coronaPacman.views.panels;

import de.dickeLunten.coronaPacman.GameModelListener;
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

public class GamePanel extends PanelView implements GameModelListener {
    public static final String KEY_SCORE = "game_panel";

    private final GameModel model;
    private final ViewListener viewListener;

    private PlayerView playerView;
    private VacView[] vacViews;

    private JLabel background;
    private JLabel fpsCounter;
    private JLabel scoreView;

    public GamePanel(GameModel model, ViewListener viewListener) {
        this.viewListener = viewListener;
        this.model = model;

        model.setGamePanel(this);
        model.setGameModelListener(this);

        playerView = new PlayerView(model.getPlayer());

        setBackground(Color.PINK);

        background = new JLabel();
        background.prepareImage(playerView.getImg(), this);
        add(background);

        JButton swb = new JButton("hi");
        this.add(swb);

        this.add(playerView);

        swb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewListener.onNavigate(NavigationPanels.END_PANEL, new Bundle().put(KEY_SCORE, model.getScore()));
            }
        });

        fpsCounter = new JLabel();
        fpsCounter.setText(String.valueOf(model.getFps()));
        add(fpsCounter);

        scoreView = new JLabel();
        scoreView.setText(String.valueOf(model.getScore()));
        add(scoreView);
    }

    @Override
    public void paint(Graphics g) {
        //g.drawImage(model.getMapImage(), Dimensions.getScreenResolution().getKey() / 2, 0, this);
        //g.drawImage(playerView.getImg(), model.getPlayer().getX() + Dimensions.getScreenResolution().getKey() / 2, model.getPlayer().getY(), this);
        super.paint(g);
    }

    @Override
    public void onFpsChanged(int fps) {
        System.out.println("changed--------------------------------------------------------------------------------");
        fpsCounter.setText(String.valueOf(model.getFps()));
    }

    @Override
    public void onScoreChanged(int score) {
        scoreView.setText(String.valueOf(score));
    }

    @Override
    public void finishGame(int score) {
        viewListener.onNavigate(NavigationPanels.END_PANEL, new Bundle().put(KEY_SCORE, score));
    }

    @Override
    public void update() {
        paintImmediately(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
    }
}