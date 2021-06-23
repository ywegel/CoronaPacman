package de.dickeLunten.coronaPacman.views.panels;

import de.dickeLunten.coronaPacman.ModelListener;
import de.dickeLunten.coronaPacman.ViewListener;
import de.dickeLunten.coronaPacman.controller.NavigationPanels;
import de.dickeLunten.coronaPacman.models.panel.EndModel;
import de.dickeLunten.coronaPacman.views.View;
import org.jetbrains.annotations.NotNull;
import util.Bundle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndPanel extends PanelView {
    private EndModel model;
    private JLabel scorel;
    private JButton replayb;
    private JButton quitb;
    private JButton exitb;
    private int score;
    private GridBagConstraints constraints = new GridBagConstraints();

    ViewListener viewListener;

    public EndPanel(EndModel model, ViewListener viewListener, Bundle bundle) {
        this.score = bundle.get(GamePanel.KEY_SCORE);
        System.out.println("Score:" + score);

        this.viewListener = viewListener;

        this.model = model;
        this.setLayout(new GridBagLayout());
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        scorel = new JLabel("ScoreLabel", SwingConstants.CENTER);
        scorel.setOpaque(true);
        scorel.setBackground(Color.DARK_GRAY);
        if (score == 0) {
            scorel.setText("Du bist infiziert und hast verloren  >_<");
        }
        else{
            scorel.setText("Du hast gewonnen und " + score + " Punkte erreicht");
        }
        scorel.setFont(new Font("sans", Font.PLAIN, 70));
        scorel.setForeground(Color.WHITE);

        replayb = new JButton("replay");
        replayb.setBackground(new Color(50, 80, 0));
        replayb.setFont(new Font("sans", Font.PLAIN, 70));
        replayb.setForeground(Color.WHITE);
        replayb.setBorderPainted(false);


        quitb = new JButton("  quit  ");
        quitb.setBackground(new Color(50, 0, 0));
        quitb.setFont(new Font("sans", Font.PLAIN, 70));
        quitb.setForeground(Color.WHITE);
        quitb.setBorderPainted(false);


        exitb = new JButton("  exit  ");
        exitb.setBackground(new Color(10,10,10));
        exitb.setFont(new Font("sans", Font.PLAIN, 70));
        exitb.setForeground(Color.WHITE);
        exitb.setBorderPainted(false);


        constraints.gridwidth = 3; // span three columns
        this.addGB(scorel, 0, 0);
        constraints.gridwidth = 1; // set it back
        this.addGB(replayb, 0, 1);
        this.addGB(quitb, 1, 1);
        this.addGB(exitb, 2, 1);


        replayb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewListener.onNavigate(NavigationPanels.GAME_PANEL, Bundle.emptyBundle());
            }
        });


        quitb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewListener.onNavigate(NavigationPanels.START_PANEL, Bundle.emptyBundle());
            }
        });

        exitb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }

    //Methode für GridBagLayout
    void addGB(Component component, int x, int y) {
        constraints.gridx = x;
        constraints.gridy = y;
        add(component, constraints);
    }

    @Override
    public void update() {

    }
    public void finishGame(int score){ }
}