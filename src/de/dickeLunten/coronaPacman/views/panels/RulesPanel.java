package de.dickeLunten.coronaPacman.views.panels;

import de.dickeLunten.coronaPacman.ViewListener;
import de.dickeLunten.coronaPacman.controller.NavigationPanels;
import de.dickeLunten.coronaPacman.models.panel.RulesModel;
import util.Bundle;
import util.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Kümmert sich um die Anzeige einer Hintergrundgeschichte zum Spiel.
 *
 * @author Felix Rosner
 * @author Colin Clauss
 * @author Yannick Wegel
 * @author Daniel Bund
 * @author Jake Finch
 * @version 2021.15.07
 */

public class RulesPanel extends PanelView {

    //Model und Viewlistener der View
    private final RulesModel model;
    private final ViewListener viewListener;

    //Layout des Startpanels
    private final GridBagConstraints constraints = new GridBagConstraints();

    //Knöpfe des StartPanels
    private final JButton backB;
    private final JLabel rulesL;
    private final JButton deB;
    private final JButton enB;

    /**
     * Sorgt für die Erzeugung des RulesPanels und die
     * Anzeige aller Buttons und Labels
     *
     * @param model        Das Model der RulesPanel-Klasse
     * @param viewListener Der Beobachter der RulesPanel-Klasse
     */

    public RulesPanel(RulesModel model, ViewListener viewListener) {
        this.model = model;
        this.viewListener = viewListener;
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.DARK_GRAY);

        constraints.weightx = 1.0;
        constraints.weighty = 1.0;

        deB = new JButton("DE");
        deB.setFont(new Font("sans", Font.PLAIN, 60));
        deB.setBackground(new Color(50, 0, 0));
        deB.setForeground(Color.WHITE);
        deB.setBorderPainted(false);

        enB = new JButton("EN");
        enB.setFont(new Font("sans", Font.PLAIN, 60));
        enB.setBackground(new Color(0, 50, 0));
        enB.setForeground(Color.WHITE);
        enB.setBorderPainted(false);

        deB.addActionListener(new ActionListener() {
            /**
             * Setzt die Sprache der Regeln auf Englisch und vertauscht die Farben des deB und enB
             *
             * @param e neues ActionEvent
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                deB.setBackground(new Color(0, 50, 0));
                enB.setBackground(new Color(50, 0, 0));
                rulesL.setText(model.getRulesL_DE());
            }
        });

        enB.addActionListener(new ActionListener() {
            /**
             * Setzt die Sprache der Regeln auf Englisch und vertauscht die Farben des deB und enB
             *
             * @param e neues ActionEvent
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                enB.setBackground(new Color(0, 50, 0));
                deB.setBackground(new Color(50, 0, 0));
                rulesL.setText(model.getRulesL_EN());
            }
        });

        backB = new JButton("back");
        backB.setFont(Data.setPacFont());
        backB.setBackground(new Color(50, 50, 50));
        backB.setForeground(Color.WHITE);
        backB.setBorderPainted(false);
        backB.addActionListener(new ActionListener() {
            /**
             * Navigiert zurück zum StartPanel
             *
             * @param e neues ActionEvent
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                viewListener.onNavigate(NavigationPanels.START_PANEL, Bundle.emptyBundle());
            }
        });

        rulesL = new JLabel("", SwingConstants.CENTER);
        rulesL.setBackground(Color.DARK_GRAY);
        rulesL.setForeground(Color.WHITE);
        rulesL.setFont(new Font("sans", Font.PLAIN, 60));
        rulesL.setText(model.getRulesL_EN());

        constraints.fill = GridBagConstraints.HORIZONTAL;

        addGB(enB, 1, 0);
        addGB(deB, 2, 0);
        constraints.fill = GridBagConstraints.NONE;
        constraints.ipadx = 950;
        constraints.gridwidth = 3;
        addGB(rulesL, 0, 1);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        addGB(backB, 0, 2);

    }

    /**
     * Fügt JComponent zu GridBagLayout hinzu
     *
     * @param component hinzuzufügender JComponent
     * @param x         x Koordinate
     * @param y         y Koordinate
     */
    void addGB(Component component, int x, int y) {
        constraints.gridx = x;
        constraints.gridy = y;
        add(component, constraints);
    }
}
