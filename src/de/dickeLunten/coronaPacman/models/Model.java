package de.dickeLunten.coronaPacman.models;

import de.dickeLunten.coronaPacman.models.entities.Player;
import de.dickeLunten.coronaPacman.models.panel.EndModel;
import de.dickeLunten.coronaPacman.models.panel.GameModel;
import de.dickeLunten.coronaPacman.models.panel.PauseModel;
import de.dickeLunten.coronaPacman.models.panel.StartModel;

import javax.xml.stream.events.StartDocument;

public class Model {

    StartModel startModel;
    GameModel gameModel;
    PauseModel pauseModel;
    EndModel endModel;

    public Model(StartModel startModel, GameModel gameModel, PauseModel pauseModel, EndModel endModel) {
        this.startModel = startModel;
        this.gameModel = gameModel;
        this.pauseModel = pauseModel;
        this.endModel = endModel;
    }

    public StartModel getStartModel() {
        return startModel;
    }

    public void setStartModel(StartModel startModel) {
        this.startModel = startModel;
    }

    public GameModel getGameModel() {
        return gameModel;
    }

    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public PauseModel getPauseModel() {
        return pauseModel;
    }

    public void setPauseModel(PauseModel model) {
        this.pauseModel = model;
    }

    public EndModel getEndModel() {
        return endModel;
    }

    public void setEndModel(EndModel endModel) {
        this.endModel = endModel;
    }

    public Player getPlayer() {
        return gameModel.getPlayer();
    }


}