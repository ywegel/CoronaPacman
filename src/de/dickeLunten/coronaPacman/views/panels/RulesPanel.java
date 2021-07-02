package de.dickeLunten.coronaPacman.views.panels;

import de.dickeLunten.coronaPacman.ViewListener;
import de.dickeLunten.coronaPacman.controller.NavigationPanels;
import de.dickeLunten.coronaPacman.models.panel.CreditsModel;
import de.dickeLunten.coronaPacman.models.panel.RulesModel;
import util.Bundle;
import util.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RulesPanel extends PanelView{

    private CreditsModel model;
    private GridBagConstraints constraints = new GridBagConstraints();
    private JButton backb;
    private JLabel rulesl;

    ViewListener viewListener;

    public RulesPanel (RulesModel model, ViewListener viewListener){
        this.viewListener = viewListener;
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.DARK_GRAY);

        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        //constraints.fill = GridBagConstraints.HORIZONTAL;

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

        rulesl = new JLabel("",SwingConstants.CENTER);
        rulesl.setBackground(Color.DARK_GRAY);
        rulesl.setForeground(Color.WHITE);
        rulesl.setFont(new Font("sans", Font.PLAIN, 60));
        rulesl.setText(model.getRuleslTXT());

        constraints.ipadx = 950;
        addGB(rulesl, 0, 0);
        constraints.fill = GridBagConstraints.HORIZONTAL;
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
