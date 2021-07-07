package de.dickeLunten.coronaPacman.models.panel;

import util.Data;

import java.awt.*;

public class StartModel {
    private Image backgroundImg;
    private Image creditsbImg;
    private Font pacFont;

    public StartModel(){                                        //---Aspect Ratio = 1/1.41402942955---
        backgroundImg = Data.loadImageFromRes("img/VirusVendor.png").getScaledInstance(1100,700, Image.SCALE_FAST);
    }


    public void setBackgroundImg( Image backgroundImg ) {
        this.backgroundImg = backgroundImg;
    }


    public Image getBackgroundImg() {
        return backgroundImg;
    }
}