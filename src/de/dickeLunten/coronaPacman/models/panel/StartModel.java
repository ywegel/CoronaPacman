package de.dickeLunten.coronaPacman.models.panel;

import util.Data;

import java.awt.*;

public class StartModel {
    private final Image backgroundImg;
    private Image creditsbImg;
    private Font pacFont;

    public StartModel(){                                        //---Aspect Ratio = 1/1.41402942955---
        backgroundImg = Data.loadImageFromRes("img/VirusVendor.png").getScaledInstance(1100,700, Image.SCALE_FAST);
    }

    public Image getBackgroundImg() {
        return backgroundImg;
    }
}