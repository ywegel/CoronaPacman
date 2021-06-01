package de.dickeLunten.coronaPacman.views.panels;

import de.dickeLunten.coronaPacman.ViewListener;
import de.dickeLunten.coronaPacman.controller.NavigationPanels;
import de.dickeLunten.coronaPacman.models.panel.StartModel;
import util.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPanel extends JPanel {
    private StartModel model;
    private ViewListener viewListener;

    private Image pogImg;

    public StartPanel(StartModel model, ViewListener viewListener){
        this.model = model;
        this.viewListener = viewListener;
        setBackground(Color.CYAN);
        setLayout(new BorderLayout());

        /*        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResource("/froschi.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        //^das selbe wie da unten

        pogImg = Data.loadImage("him.jpeg");


        JButton startBtn = new JButton("Start");
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewListener.onNavigate(NavigationPanels.GAME_PANEL);
            }
        });
        add(startBtn, BorderLayout.SOUTH);

        JLabel highscoreLabel = new JLabel("Highscores: ");
        add(highscoreLabel, BorderLayout.NORTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(pogImg, 0, 0, null);
    }
}