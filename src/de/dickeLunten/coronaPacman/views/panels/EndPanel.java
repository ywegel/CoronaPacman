package de.dickeLunten.coronaPacman.views.panels;

import de.dickeLunten.coronaPacman.ViewListener;
import de.dickeLunten.coronaPacman.controller.NavigationPanels;
import de.dickeLunten.coronaPacman.models.panel.EndModel;
import util.Bundle;
import util.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndPanel extends PanelView {
    private EndModel model;
    private JLabel scoreL;
    private JButton replayB;
    private JButton quitB;
    private JButton exitB;
    private int score;
    private GridBagConstraints constraints = new GridBagConstraints();

    ViewListener viewListener;

    public EndPanel(EndModel model, ViewListener viewListener, Bundle bundle) {
        this.score = bundle.get(GamePanel.KEY_SCORE);
        System.out.println("Score:" + score);

        this.viewListener = viewListener;

        System.out.println("THREAD COUNT 3: " + Thread.activeCount());

        this.model = model;
        this.setLayout(new GridBagLayout());
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        scoreL = new JLabel("ScoreLabel", SwingConstants.CENTER);
        scoreL.setOpaque(true);
        scoreL.setBackground(Color.DARK_GRAY);
        if (score == 0) {
            scorel.setText("Du bist infiziert und hast verloren  >_<");
        }
        else{
            scorel.setText("Du hast gewonnen und " + score + " Punkte erreicht");
        }
        scorel.setFont(new Font("sans", Font.PLAIN, 70));
        scorel.setForeground(Color.WHITE);

        replayB = new JButton("replay");
        replayB.setFont(Data.setPacFont());
        replayB.setBackground(new Color(50, 80, 0));
        //replayb.setFont(new Font("sans", Font.PLAIN, 70));
        replayB.setForeground(Color.WHITE);
        replayB.setBorderPainted(false);


        quitB = new JButton("  quit  ");
        quitB.setFont(Data.setPacFont());
        quitB.setBackground(new Color(50, 0, 0));
        //quitb.setFont(new Font("sans", Font.PLAIN, 70));
        quitB.setForeground(Color.WHITE);
        quitB.setBorderPainted(false);


        exitB = new JButton("  exit  ");
        exitB.setFont(Data.setPacFont());
        exitB.setBackground(new Color(10, 10, 10));
        //exitb.setFont(new Font("sans", Font.PLAIN, 70));
        exitB.setForeground(Color.WHITE);
        exitB.setBorderPainted(false);


        constraints.gridwidth = 3; // span three columns
        this.addGB(scoreL, 0, 0);
        constraints.gridwidth = 1; // set it back
        this.addGB(replayB, 0, 1);
        this.addGB(quitB, 1, 1);
        this.addGB(exitB, 2, 1);


        replayB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewListener.onNavigate(NavigationPanels.GAME_PANEL, Bundle.emptyBundle());
            }
        });


        quitB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewListener.onNavigate(NavigationPanels.START_PANEL, Bundle.emptyBundle());
            }
        });

        exitB.addActionListener(new ActionListener() {
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