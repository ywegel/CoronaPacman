package de.dickeLunten.coronaPacman;

import de.dickeLunten.coronaPacman.controller.Controller;

public class Application {

    public static void main(String[] args) {
        new Application();
    }

    public Application(){
        Controller mainController = new Controller();
    }
}