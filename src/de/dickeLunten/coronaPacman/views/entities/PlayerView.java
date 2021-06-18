package de.dickeLunten.coronaPacman.views.entities;

import de.dickeLunten.coronaPacman.models.entities.Player;
import de.dickeLunten.coronaPacman.views.View;
import util.Data;

import javax.swing.*;
import java.awt.*;

public class PlayerView extends EntityView {
    private final Player model;
    private Image img;

    public PlayerView(Player model){
        this.model = model;
        img = Data.loadImage("him.jpeg");
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(img, 100, 100, 500, 500, this);
    }

    public void update(){}


}
