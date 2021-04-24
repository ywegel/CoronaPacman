package de.dickeLunten.coronaPacman;

import de.dickeLunten.coronaPacman.controller.GameController;

public class Application {

    public static void main(String[] args) {
        new Application();
    }

    public Application(){
        GameController mainController = new GameController();
    }

}
