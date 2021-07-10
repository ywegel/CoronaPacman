package de.dickeLunten.coronaPacman.views.panels;

import de.dickeLunten.coronaPacman.ViewListener;
import de.dickeLunten.coronaPacman.controller.NavigationPanels;
import de.dickeLunten.coronaPacman.models.panel.CreditsModel;
import de.dickeLunten.coronaPacman.models.panel.RulesModel;
import util.Bundle;
import util.Data;
import util.Dimensions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RulesPanel extends PanelView{

    private CreditsModel model;
    private GridBagConstraints constraints = new GridBagConstraints();
    private JButton backb;
    private JLabel rulesL;
    private JButton deB;
    private JButton enB;

    ViewListener viewListener;

    public RulesPanel (RulesModel model, ViewListener viewListener){
        this.viewListener = viewListener;
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.DARK_GRAY);

        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        //constraints.fill = GridBagConstraints.HORIZONTAL;

        deB = new JButton("DE");
        deB.setFont(new Font("sans", Font.PLAIN, 60));
        deB.setBackground(new Color(0,50,0));
        deB.setForeground(Color.WHITE);
        deB.setBorderPainted(false);

        enB = new JButton("EN");
        enB.setFont(new Font("sans", Font.PLAIN, 60));
        enB.setBackground(new Color(50,0,0));
        enB.setForeground(Color.WHITE);
        enB.setBorderPainted(false);

        deB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deB.setBackground(new Color(0, 50, 0));
                enB.setBackground(new Color(50, 0, 0));
                rulesL.setText(model.getRulesL_DE());
            }
        });

        enB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enB.setBackground(new Color(0, 50, 0));
                deB.setBackground(new Color(50, 0, 0));
                rulesL.setText(model.getRulesL_EN());
            }
        });



        backb = new JButton("back");
        //backb.setFont(new Font("sans", Font.PLAIN, 70));
        backb.setFont(Data.setPacFont());
        backb.setBackground(new Color(50,50,50));
        backb.setForeground(Color.WHITE);
        backb.setBorderPainted(false);
        backb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewListener.onNavigate(NavigationPanels.START_PANEL, Bundle.emptyBundle());
            }
        });

        rulesL = new JLabel("",SwingConstants.CENTER);
        rulesL.setBackground(Color.DARK_GRAY);
        rulesL.setForeground(Color.WHITE);
        rulesL.setFont(new Font("sans", Font.PLAIN, 60));
        rulesL.setText(model.getRulesL_DE());

        constraints.fill = GridBagConstraints.HORIZONTAL;

        addGB(enB, 1,0);
        addGB(deB, 2,0);
        constraints.fill = GridBagConstraints.NONE;
        constraints.ipadx = 950;
        constraints.gridwidth = 3;
        addGB(rulesL, 0, 1);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        addGB(backb, 0, 2);

    }

    void addGB(Component component, int x, int y) {
        constraints.gridx = x;
        constraints.gridy = y;
        add(component, constraints);
    }
}
