package de.dickeLunten.coronaPacman;

import de.dickeLunten.coronaPacman.controller.Controller;
import de.dickeLunten.coronaPacman.models.Model;
import de.dickeLunten.coronaPacman.models.panel.EndModel;
import de.dickeLunten.coronaPacman.models.panel.GameModel;
import de.dickeLunten.coronaPacman.models.panel.PauseModel;
import de.dickeLunten.coronaPacman.models.panel.StartModel;
import de.dickeLunten.coronaPacman.views.View;

public class Application {

    public static void main(String[] args) {
        new Application();
    }

    public Application(){
        Controller mainController = new Controller();


    }

}