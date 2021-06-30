package de.dickeLunten.coronaPacman.views.entities;

import de.dickeLunten.coronaPacman.models.entities.Corona;
import de.dickeLunten.coronaPacman.views.View;

import java.awt.*;

public class CoronaView extends EntityView {
    private Corona model;

    public CoronaView(Corona model){
        this.model = model;
    }

    @Override
    public void update() {

    }
}