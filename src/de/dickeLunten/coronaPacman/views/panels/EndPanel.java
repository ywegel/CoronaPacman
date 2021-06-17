package de.dickeLunten.coronaPacman.views.panels;

import de.dickeLunten.coronaPacman.models.panel.EndModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class EndPanel extends PanelView {
    private EndModel model;
    private JLabel scorel;
    private JButton replayb;
    private JButton quitb;
    private String score;

    public EndPanel(EndModel model){

        this.model = model;
        this.setLayout(new BorderLayout());

        scorel = new JLabel("ScoreLabel");
        scorel.setBackground(Color.GRAY);

        replayb = new JButton("replay");
        replayb.setBackground(Color.GRAY);

        quitb = new JButton("quit");
        quitb.setBackground(Color.GRAY);

        this.add(scorel, BorderLayout.PAGE_START);
        this.add(replayb, BorderLayout.CENTER);
        this.add(quitb, BorderLayout.PAGE_END);




    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == replayb){

        }
        if(e.getSource() == quitb){

        }
    }

}
