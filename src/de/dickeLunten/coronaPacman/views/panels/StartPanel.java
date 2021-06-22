package de.dickeLunten.coronaPacman.views.panels;

import de.dickeLunten.coronaPacman.ModelListener;
import de.dickeLunten.coronaPacman.ViewListener;
import de.dickeLunten.coronaPacman.controller.NavigationPanels;
import de.dickeLunten.coronaPacman.models.panel.StartModel;
import util.Bundle;

import javax.swing.*;
import javax.swing.text.IconView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPanel extends PanelView {
    private StartModel model;
    private ViewListener viewListener;
    private GridBagConstraints constraints = new GridBagConstraints();
    private JButton startb;
    private JButton exitb;
    private JButton creditsb;


    public StartPanel(StartModel model, ViewListener viewListener) {
        this.model = model;
        this.viewListener = viewListener;
        setBackground(Color.DARK_GRAY);
        setLayout(new GridBagLayout());
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.gridwidth = 2; // span two columns
        addGB(new JLabel(new ImageIcon(model.getBackgroundImg()), JLabel.CENTER),0,1);
        constraints.gridwidth = 2; // span one columns

        //create + add startb
        startb = new JButton("Press ENTER to start");
        //startBtn.setFont(new Font("Pac-Font", Font.PLAIN, 40));
        //startb.setSize(300, 300);
        //startb.setBackground(new Color(50, 50, 50));
        startb.setBackground(Color.DARK_GRAY);
        startb.setBorderPainted(false);
        startb.setFont(new Font("sans", Font.PLAIN, 70));
        startb.setForeground(Color.WHITE);

        startb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewListener.onNavigate(NavigationPanels.GAME_PANEL, Bundle.emptyBundle());
            }
        });


        //create + add exitb
        exitb = new JButton(("  exit  "));
        exitb.setFont(new Font("sans", Font.PLAIN, 70));
        exitb.setBackground(Color.DARK_GRAY);
        exitb.setForeground(Color.WHITE);
        //exitb.setOpaque(false);
        //exitb.setContentAreaFilled(false);
        exitb.setBorderPainted(false);

        exitb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        creditsb = new JButton("credits");
        creditsb.setFont(new Font("sans", Font.PLAIN, 70));
        creditsb.setBackground(Color.DARK_GRAY);
        creditsb.setForeground(Color.WHITE);
        //creditsb.setOpaque(false);
        //creditsb.setContentAreaFilled(false);
        creditsb.setBorderPainted(false);

        creditsb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewListener.onNavigate(NavigationPanels.CREDITS_PANEL, Bundle.emptyBundle());

            }
        });

        constraints.gridwidth = 2; // span three columns
        addGB(startb, 0, 0);
        constraints.gridwidth = 1; // span one column
        addGB(exitb, 0, 2);
        addGB(creditsb, 1, 2);

        //JLabel highscoreLabel = new JLabel("Highscores: ");
        //add(highscoreLabel, BorderLayout.NORTH);
    }

/*    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(model.getBackgroundImg(), 0, 0, null);
    }*/

    @Override
    public void update() {

    }

    public void addGB(Component component, int x, int y) {
        constraints.gridx = x;
        constraints.gridy = y;
        add(component, constraints);
    }

}

/*
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResource("/froschi.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
^das selbe wie da unten*/

