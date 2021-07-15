package de.dickeLunten.coronaPacman.views.panels;

import de.dickeLunten.coronaPacman.ViewListener;
import de.dickeLunten.coronaPacman.controller.NavigationPanels;
import de.dickeLunten.coronaPacman.models.panel.CreditsModel;
import util.Bundle;
import util.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CreditsPanel extends PanelView{

    private final CreditsModel model;
    private final GridBagConstraints constraints = new GridBagConstraints();
    private final JButton backB;
    private final JLabel creditsL;

    ViewListener viewListener;



    public CreditsPanel(CreditsModel model, ViewListener viewListener){
        this.model = model;
        this.viewListener = viewListener;
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.DARK_GRAY);

        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        backB = new JButton("back");
        backB.setFont(Data.setPacFont());
        backB.setBackground(new Color(50,50,50));
        backB.setForeground(Color.WHITE);
        backB.setBorderPainted(false);
        backB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewListener.onNavigate(NavigationPanels.START_PANEL, Bundle.emptyBundle());
            }
        });

        creditsL = new JLabel(model.getCreditslTXT(), SwingConstants.CENTER);
        //creditsl.setForeground(Color.WHITE);
        creditsL.setFont(new Font("sans", Font.PLAIN, 70));
        creditsL.setForeground(Color.WHITE);

        addGB(creditsL, 0, 0);
        addGB(backB, 0, 1);
    }


    void addGB(Component component, int x, int y) {
        constraints.gridx = x;
        constraints.gridy = y;
        add(component, constraints);
    }
}