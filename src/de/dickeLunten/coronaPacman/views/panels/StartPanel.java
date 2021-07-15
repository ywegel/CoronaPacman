package de.dickeLunten.coronaPacman.views.panels;

import de.dickeLunten.coronaPacman.ViewListener;
import de.dickeLunten.coronaPacman.controller.NavigationPanels;
import de.dickeLunten.coronaPacman.models.panel.StartModel;
import util.Bundle;
import util.Data;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Verwaltet das Startpanel.
 *
 * @author Felix Rosner
 * @author Colin Clauss
 * @author Yannick Wegel
 * @author Daniel Bund
 * @author Jake Finch
 *
 * @version 2021.15.07
 */
public class StartPanel extends PanelView {

    //Model und Viewlistener der View
    private final StartModel model;
    private final ViewListener viewListener;

    //Layout des Startpanels
    private final GridBagConstraints constraints = new GridBagConstraints();

    //Knöpfe des StartPanels
    private final JButton startB;
    private final JButton exitB;
    private final JButton creditsB;
    private final JButton rulesB;

    /**
     * Konstruktor der Klasse StartPanel.
     *
     * Erzeugt ein neues StartPanel, setzt Hintergrundfarbe und Layout.
     *
     * Erzeugt die Buttons in der Pacman Schriftart und Standartgröße.
     *
     * ActionListener werden mit Button verknüpft.
     *
     * @param model        Model zum StartPanel.
     * @param viewListener Beobachter des StartPanels.
     */
    public StartPanel(StartModel model, ViewListener viewListener) {
        this.model = model;
        this.viewListener = viewListener;
        setBackground(Color.DARK_GRAY);
        setLayout(new GridBagLayout());
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.gridwidth = 3; // span three columns
        addGB(new JLabel(new ImageIcon(model.getBackgroundImg()), JLabel.CENTER), 0, 1);
        constraints.gridwidth = 1; // span one column


        startB = new JButton("Press ENTER to start");
        startB.setFont(Data.setPacFont());
        startB.setBackground(Color.DARK_GRAY);
        startB.setBorderPainted(false);
        startB.setForeground(Color.WHITE);

        startB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewListener.onNavigate(NavigationPanels.GAME_PANEL, Bundle.emptyBundle());
                viewListener.notifyGameStarted();
            }
        });


        exitB = new JButton(("  exit  "));
        exitB.setFont(Data.setPacFont());
        exitB.setBackground(Color.DARK_GRAY);
        exitB.setForeground(Color.WHITE);
        exitB.setBorderPainted(false);

        exitB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        creditsB = new JButton("credits");
        creditsB.setFont(Data.setPacFont());
        creditsB.setBackground(Color.DARK_GRAY);
        creditsB.setForeground(Color.WHITE);
        creditsB.setBorderPainted(false);

        creditsB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewListener.onNavigate(NavigationPanels.CREDITS_PANEL, Bundle.emptyBundle());

            }
        });

        rulesB = new JButton(" rules ");
        rulesB.setFont(Data.setPacFont());
        rulesB.setBackground(Color.DARK_GRAY);
        rulesB.setForeground(Color.WHITE);

        rulesB.setBorderPainted(false);

        rulesB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewListener.onNavigate(NavigationPanels.RULES_PANEL, Bundle.emptyBundle());

            }
        });

        constraints.gridwidth = 3; // span three columns
        addGB(startB, 0, 0);
        constraints.gridwidth = 1; // span one column
        addGB(exitB, 2, 2);
        addGB(creditsB, 1, 2);
        addGB(rulesB, 0, 2);


    }

    /**
     * Setzt Buttons an zugewiesene Koordinaten im GridBagLayout.
     *
     * @param component Einsetzen des Components der in seiner position verändert werden soll
     * @param x         x-Koordinate
     * @param y         y-Koordinate
     */
    public void addGB(Component component, int x, int y) {
        constraints.gridx = x;
        constraints.gridy = y;
        add(component, constraints);
    }
}