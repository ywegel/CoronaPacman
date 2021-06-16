package de.dickeLunten.coronaPacman.models.panel;

import util.Data;

import java.awt.*;

import javax.swing.ImageIcon;

public class StartModel {
    private Image backgroundImg;



    public StartModel(){
        backgroundImg = Data.loadImage("vvmap.png").getScaledInstance(200,200,backgroundImg.SCALE_FAST);
    }

    public void setBackgroundImg( Image backgroundImg ) {
        this.backgroundImg = backgroundImg;
    }

    public Image getBackgroundImg() {
        return backgroundImg;
    }


}