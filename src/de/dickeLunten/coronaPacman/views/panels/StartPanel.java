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
 *
 * @version 2021.14.07
 */

public class StartPanel extends PanelView {
    private StartModel model;
    private ViewListener viewListener;
    private GridBagConstraints constraints = new GridBagConstraints();
    private JButton startb;
    private JButton exitb;
    private JButton creditsb;
    private JButton rulesb;

    /**
     * Konstruktor der Klasse StartPanel.
     *
     * Erzeugt ein neues StartPanel, setzt Hintergrundfarbe und Layout.
     *
     * Erzeugt die Buttons in der Pacman SChriftart und Standartgröße.
     *
     * ActionListener werden mit Button verknüpft.
     *
     * @param model Model zum StartPanel.
     *
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
        addGB(new JLabel(new ImageIcon(model.getBackgroundImg()), JLabel.CENTER),0,1);
        constraints.gridwidth = 1; // span one column


        startb = new JButton("Press ENTER to start");
        startb.setFont(Data.setPacFont());
        startb.setBackground(Color.DARK_GRAY);
        startb.setBorderPainted(false);
        startb.setForeground(Color.WHITE);

        startb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewListener.onNavigate(NavigationPanels.GAME_PANEL, Bundle.emptyBundle());
                viewListener.notifyGameStarted();
            }
        });


        exitb = new JButton(("  exit  "));
        exitb.setFont(Data.setPacFont());
        exitb.setBackground(Color.DARK_GRAY);
        exitb.setForeground(Color.WHITE);
        exitb.setBorderPainted(false);

        exitb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        creditsb = new JButton("credits");
        creditsb.setFont(Data.setPacFont());
        creditsb.setBackground(Color.DARK_GRAY);
        creditsb.setForeground(Color.WHITE);
        creditsb.setBorderPainted(false);

        creditsb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewListener.onNavigate(NavigationPanels.CREDITS_PANEL, Bundle.emptyBundle());

            }
        });

        rulesb = new JButton(" rules ");
        rulesb.setFont(Data.setPacFont());
        rulesb.setBackground(Color.DARK_GRAY);
        rulesb.setForeground(Color.WHITE);

        rulesb.setBorderPainted(false);

        rulesb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewListener.onNavigate(NavigationPanels.RULES_PANEL, Bundle.emptyBundle());

            }
        });

        constraints.gridwidth = 3; // span three columns
        addGB(startb, 0, 0);
        constraints.gridwidth = 1; // span one column
        addGB(exitb, 2, 2);
        addGB(creditsb, 1, 2);
        addGB(rulesb, 0, 2);


    }


    /**
     * Setzt Buttons an zugewiesene Koordinaten im GridBagLayout.
     */

    public void addGB(Component component, int x, int y) {
        constraints.gridx = x;
        constraints.gridy = y;
        add(component, constraints);
    }
    public void finishGame(int score){ }
}



