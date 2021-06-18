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

    ViewListener viewListener;

    public EndPanel(EndModel model, ViewListener viewListener, Bundle bundle) {

        this.score = bundle.get("score");
        System.out.println("Score:" + score);

        this.viewListener = viewListener;

        this.model = model;
        this.setLayout(new GridBagLayout());

        scorel = new JLabel("ScoreLabel");
        scorel.setBackground(Color.GRAY);
        if (model.getScore() == 0) {
            scorel.setText("Du bist infiziert und hast verloren >_<" + '\n' + "Wie soll es weiter gehen?");
        }
        else{
            scorel.setText("Du hast gewonnen und " + score + " Punkte erreicht" + '\n' + "Wie soll es weiter gehen?");
        }
        scorel.setText("Du hast " + model.getScore() + " erreicht");
        scorel.setFont(new Font("sans", Font.PLAIN, 70));
        scorel.setForeground(Color.WHITE);


        replayb = new JButton("replay");
        replayb.setBackground(new Color(50, 80, 0));
        replayb.setFont(new Font("sans", Font.PLAIN, 70));
        replayb.setForeground(Color.WHITE);


        quitb = new JButton("quit");
        quitb.setBackground(new Color(50, 0, 0));
        quitb.setFont(new Font("sans", Font.PLAIN, 70));
        quitb.setForeground(Color.WHITE);

        exitb = new JButton("exit");
        exitb.setBackground(new Color(50,50,50));
        exitb.setFont(new Font("sans", Font.PLAIN, 70));
        exitb.setForeground(Color.WHITE);

        this.add(scorel);
        this.add(replayb);
        this.add(quitb);
        this.add(exitb);


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



}
