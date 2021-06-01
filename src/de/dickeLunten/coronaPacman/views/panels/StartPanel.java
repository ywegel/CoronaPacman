package de.dickeLunten.coronaPacman.views.panels;

import de.dickeLunten.coronaPacman.models.panel.StartModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class StartPanel extends JPanel {
    private StartModel model;

    public StartPanel(StartModel model){
        this.model = model;
        setBackground(Color.CYAN);

        setLayout(new BorderLayout());

        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResource("/froschi.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JButton button = new JButton("Start", new ImageIcon(image));
        add(button, BorderLayout.SOUTH);
    }

}