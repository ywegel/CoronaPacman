package de.dickeLunten.coronaPacman.views.entities;

import de.dickeLunten.coronaPacman.models.entities.Player;
import util.Data;

import java.awt.*;

public class PlayerView extends EntityView {
    private final Player model;
    private Image img;

    public PlayerView(Player model){
        this.model = model;
        this.model.addListener(this);

//        setPreferredSize(new Dimension(model.getWidth(), model.getHeight()));
        setPreferredSize(new Dimension(800, 800));

        img = Data.loadImage("img/virus1.png");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, model.getX(), model.getY(), 500, 500, this);

/*        size = getSize();
        System.out.println(size.height + " + " + size.width);

        g.setColor(Color.RED);
        System.out.println(size.height + " + " + size.width);
        g.fillRect(0, 0, size.width, size.height);*/
    }

    public void update(){
        //repaint();
    }

    public Image getImg() {
        return img;
    }
}