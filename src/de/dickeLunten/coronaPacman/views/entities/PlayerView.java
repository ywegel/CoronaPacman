package de.dickeLunten.coronaPacman.views.entities;

import de.dickeLunten.coronaPacman.models.entities.Player;
import de.dickeLunten.coronaPacman.views.View;
import util.Data;

import javax.swing.*;
import java.awt.*;

public class PlayerView extends EntityView {
    private Player model;
    private Image img;

    public PlayerView(Player model){
        this.model = model;
        img = Data.loadImage("him.jpeg");
        add(new JLabel(new ImageIcon(img)));
    }

    public void update(){}


}
