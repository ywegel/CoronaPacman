package de.dickeLunten.coronaPacman.views.entities;

import de.dickeLunten.coronaPacman.models.entities.Corona;
import de.dickeLunten.coronaPacman.views.View;

import java.awt.*;

public class CoronaView extends EntityView {
    private Corona model;
    private Image ediblevirus1;
    private Image ediblevirus2;
    private Image virus1;
    private Image virus2;



    public CoronaView(Corona model){
        this.model = model;
        //ediblevirus1 = Data.loadImage();


    }

    @Override
    public void update() {

    }
}