package de.dickeLunten.coronaPacman.models;

import de.dickeLunten.coronaPacman.models.entities.Player;
import de.dickeLunten.coronaPacman.models.panel.*;

import javax.xml.stream.events.StartDocument;

public class Model {

    StartModel startModel;
    GameModel gameModel;
    PauseModel pauseModel;
    EndModel endModel;
    CreditsModel creditsModel;

    public Model(StartModel startModel, GameModel gameModel, PauseModel pauseModel, EndModel endModel, CreditsModel creditsModel) {
        this.startModel = startModel;
        this.gameModel = gameModel;
        this.pauseModel = pauseModel;
        this.endModel = endModel;
        this.creditsModel = creditsModel;
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

    public CreditsModel getCreditsModel() {
        return creditsModel;
    }

    public void setCreditsModel(CreditsModel creditsModel) {
        this.creditsModel = creditsModel;
    }

    public Player getPlayer() {
        return gameModel.getPlayer();
    }


}