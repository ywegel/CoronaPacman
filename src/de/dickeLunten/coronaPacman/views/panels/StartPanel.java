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

    private Image pogImg;

    public StartPanel(StartModel model, ViewListener viewListener) {
        this.model = model;
        this.viewListener = viewListener;
        setBackground(Color.DARK_GRAY);
        setLayout(new BorderLayout());

        add(new JLabel(new ImageIcon(model.getBackgroundImg())));

        JButton startBtn = new JButton("Press ENTER to start");
        //startBtn.setFont(new Font("Pac-Font", Font.PLAIN, 40));
        startBtn.setLocation(720, 5);
        startBtn.setSize(300, 300);
        startBtn.setOpaque(false);
        startBtn.setContentAreaFilled(false);
        startBtn.setBorderPainted(false);
        startBtn.setFont(new Font("sans", Font.PLAIN, 70));

        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewListener.onNavigate(NavigationPanels.GAME_PANEL, Bundle.emptyBundle());
            }
        });

        JButton exitBtn = new JButton(("exit"));
        exitBtn.setFont(new Font("sans", Font.PLAIN, 70));
        exitBtn.setBackground(Color.DARK_GRAY);
        exitBtn.setForeground(Color.WHITE);
        exitBtn.setOpaque(false);
        exitBtn.setContentAreaFilled(false);
        exitBtn.setBorderPainted(false);

        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        add(startBtn, BorderLayout.NORTH);
        add(exitBtn, BorderLayout.SOUTH);

        //JLabel highscoreLabel = new JLabel("Highscores: ");
        //add(highscoreLabel, BorderLayout.NORTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(pogImg, 0, 0, null);
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
