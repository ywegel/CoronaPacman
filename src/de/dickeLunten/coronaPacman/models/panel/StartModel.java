package de.dickeLunten.coronaPacman.models.panel;

import util.Data;

import java.awt.*;

public class StartModel {
    private Image backgroundImg;
    private Image creditsbImg;
    private Font pacFont;

    public StartModel(){                                        //---Aspect Ratio = 1/1.41402942955---
        backgroundImg = Data.loadImage("vvmap.png").getScaledInstance(600,848, Image.SCALE_FAST);
        //creditsbImg =
    }

    public void setBackgroundImg( Image backgroundImg ) {
        this.backgroundImg = backgroundImg;
    }

    public Image getBackgroundImg() {
        return backgroundImg;
    }
}