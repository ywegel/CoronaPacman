package de.dickeLunten.coronaPacman.views.panels;

import de.dickeLunten.coronaPacman.ViewListener;
import de.dickeLunten.coronaPacman.models.panel.CreditsModel;
import util.Bundle;

import javax.swing.*;
import java.awt.*;

public class CreditsPanel extends PanelView{

    private CreditsModel model;
    private GridBagConstraints constraints = new GridBagConstraints();
    private JButton backb;
    private JLabel creditsl;

    ViewListener viewListener;




    public CreditsPanel(CreditsModel model, ViewListener viewListener, Bundle bundle){

        this.viewListener = viewListener;
        this.setLayout(new GridBagLayout());
        constraints.gridwidth = 1;

        backb = new JButton("back");
        backb.setBackground(new Color(50,50,50));
        backb.setForeground(Color.WHITE);
        backb.setFont(new Font("sans", Font.PLAIN, 70));
        backb.setBorderPainted(false);


        creditsl = new JLabel("");

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
}
