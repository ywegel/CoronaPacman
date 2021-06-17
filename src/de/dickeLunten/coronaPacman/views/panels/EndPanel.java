package de.dickeLunten.coronaPacman.views.panels;

import de.dickeLunten.coronaPacman.ModelListener;
import de.dickeLunten.coronaPacman.ViewListener;
import de.dickeLunten.coronaPacman.controller.NavigationPanels;
import de.dickeLunten.coronaPacman.models.panel.EndModel;
import de.dickeLunten.coronaPacman.views.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndPanel extends PanelView {
    private EndModel model;
    private JLabel scorel;
    private JButton replayb;
    private JButton quitb;
    private String score;

    ViewListener viewListener;

    public EndPanel(EndModel model, ViewListener viewListener) {

        this.viewListener = viewListener;

        this.model = model;
        this.setLayout(new GridLayout(2,2, 20,20));

        scorel = new JLabel("ScoreLabel");
        scorel.setBackground(Color.GRAY);
        if(model.getScore() == 0){
            scorel.setText("Du bist infiziert und hast verloren >_<" + '\n' + "Wie soll es weiter gehen?");
        }
        scorel.setText("Du hast " + model.getScore() + " erreicht");

        replayb = new JButton("replay");
        replayb.setBackground(new Color(50,80,0));
        replayb.setFont(new Font("sans", Font.PLAIN, 70));
        replayb.setForeground(Color.WHITE);


        quitb = new JButton("quit");
        quitb.setBackground(new Color(50,0,0));
        quitb.setFont(new Font("sans", Font.PLAIN, 70));
        quitb.setForeground(Color.WHITE);

        this.add(scorel, 0,0);
        this.add(replayb, 1,0);
        this.add(quitb, 1,1);

        replayb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewListener.onNavigate(NavigationPanels.GAME_PANEL);

            }
        });


        quitb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewListener.onNavigate(NavigationPanels.START_PANEL);
            }
        });


    }

}
