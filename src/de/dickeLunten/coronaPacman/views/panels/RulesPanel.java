package de.dickeLunten.coronaPacman.views.panels;

import de.dickeLunten.coronaPacman.ViewListener;
import de.dickeLunten.coronaPacman.controller.NavigationPanels;
import de.dickeLunten.coronaPacman.models.panel.CreditsModel;
import de.dickeLunten.coronaPacman.models.panel.RulesModel;
import util.Bundle;

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
        constraints.fill = GridBagConstraints.HORIZONTAL;

        backb = new JButton("back");
        backb.setFont(new Font("sans", Font.PLAIN, 70));
        backb.setBackground(Color.DARK_GRAY);
        backb.setForeground(Color.WHITE);
        backb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewListener.onNavigate(NavigationPanels.START_PANEL, Bundle.emptyBundle());
            }
        });

        rulesl = new JLabel();
        rulesl.setBackground(Color.DARK_GRAY);
        rulesl.setForeground(Color.WHITE);
        rulesl.setFont(new Font("sans", Font.PLAIN, 30));
        rulesl.setText("<html><i>Regeln:</i><br />Oh nein! Die Welt geht in einer Stunde unter, <b>Jimbo</b> muss aber noch shoppen gehen.<br /><b>Jimbo</b> braucht noch sein Mountain Dew, Doritos und ein Waifu-Pillow (und natürlich Klopapier).<br />Böse Coronaviren wollen <b>Jimbo</b>s Vorhaben aber for some reason verhindern.<br />Hilf <b>Jimbo</b> dabei, mit Impfungen die Viren zu zerstören und <b>Jimbo</b>s local Supermarkt leerzuräumen.  </html>");


        addGB(rulesl, 0, 0);
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
