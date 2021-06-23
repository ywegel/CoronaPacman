package de.dickeLunten.coronaPacman.views.panels;

import de.dickeLunten.coronaPacman.ViewListener;
import de.dickeLunten.coronaPacman.controller.NavigationPanels;
import de.dickeLunten.coronaPacman.models.panel.CreditsModel;
import util.Bundle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreditsPanel extends PanelView{

    private CreditsModel model;
    private GridBagConstraints constraints = new GridBagConstraints();
    private JButton backb;
    private JLabel creditsl;

    ViewListener viewListener;




    public CreditsPanel(CreditsModel model, ViewListener viewListener){

        this.viewListener = viewListener;
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.DARK_GRAY);

        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        backb = new JButton("back");
        backb.setFont(new Font("sans", Font.PLAIN, 70));
        backb.setBackground(new Color(50,50,50));
        backb.setForeground(Color.WHITE);
        backb.setBorderPainted(false);
        backb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewListener.onNavigate(NavigationPanels.START_PANEL, Bundle.emptyBundle());
            }
        });

        creditsl = new JLabel("<html><br /><br />Created by<br /><i>Yannick Wegel, Colin Clauß,<br />Jake Finch, Felix Rosner, Daniel Bund</i></html>", SwingConstants.CENTER);
        //creditsl.setForeground(Color.WHITE);
        creditsl.setFont(new Font("sans", Font.PLAIN, 70));
        creditsl.setForeground(Color.WHITE);

        addGB(creditsl, 0, 0);
        addGB(backb, 0, 1);
    }

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
