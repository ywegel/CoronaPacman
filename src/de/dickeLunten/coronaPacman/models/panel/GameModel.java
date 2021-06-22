package de.dickeLunten.coronaPacman.models.panel;

import de.dickeLunten.coronaPacman.models.entities.Player;
import de.dickeLunten.coronaPacman.models.entities.Vac;
import util.MapChunkValues;
import util.Coord;
import util.PlayerMovableDir;

import java.util.HashMap;

public class GameModel extends PanelModel{
    private Player player;
    private Vac[] vacs;


    private HashMap<Coord, MapChunkValues> gameMap;

    private int score;

    public GameModel() {
        gameMap = new HashMap<>();
        score = 0;

        player = new Player();

        vacs = new Vac[4];
        vacs[0] = new Vac(30, 30);
        vacs[1] = new Vac(130, 30);
        vacs[2] = new Vac(130, 130);
        vacs[3] = new Vac(30, 130);
    }

    public boolean doesCollide() {
        return switch (player.getCurrentDirection()) {
            case UP -> getMovDir().isUp();
            case DOWN -> getMovDir().isDown();
            case LEFT -> getMovDir().isLeft();
            case RIGHT -> getMovDir().isRight();
        };
    }


    public void gameTick(){
        if(doesCollide()){
            player.move();
        }
        if(gameMap.get(player.getCoords()).isHasCorona()){

        }
        else if(gameMap.get(player.getCoords()).isHasDot()){

        }
        else if(gameMap.get(player.getCoords()).isHasToiletPaper()){

        }
        else if(gameMap.get(player.getCoords()).isHasVac()){

        }
    }
//    TODO implement endGame method, soll game beenden und Highscore übergeben
    public int endGame(){


        return 0;

    }

    private PlayerMovableDir getMovDir() {
        return gameMap.get(player.getCoords()).getPlayerMovableDir();
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getScore(){
        return score;
    }

}

