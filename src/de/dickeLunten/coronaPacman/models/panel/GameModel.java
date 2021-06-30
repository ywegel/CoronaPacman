package de.dickeLunten.coronaPacman.models.panel;

import de.dickeLunten.coronaPacman.ModelListener;
import de.dickeLunten.coronaPacman.ViewListener;
import de.dickeLunten.coronaPacman.models.entities.Player;
import de.dickeLunten.coronaPacman.models.entities.Vac;
import util.MapChunkValues;
import util.Coord;
import util.PlayerMovableDir;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class GameModel extends PanelModel {
    private Player player;
    private Vac[] vacs;
    private boolean coronaEdible;
    private ModelListener gamePanel;

    private int fps = 0;


    private HashMap<Coord, MapChunkValues> gameMap;

    private int score;

    public GameModel() {
        gameMap = new HashMap<>();
        score = 0;
        coronaEdible = false;

        player = new Player();

        vacs = new Vac[4];
        vacs[0] = new Vac(30, 30);
        vacs[1] = new Vac(130, 30);
        vacs[2] = new Vac(130, 130);
        vacs[3] = new Vac(30, 130);
    }



    public void setGamePanel(ModelListener vl){gamePanel = vl;}

    public boolean doesNotCollide() {
        return switch (player.getCurrentDirection()) {
            case UP -> getMovDir().isUp();
            case DOWN -> getMovDir().isDown();
            case LEFT -> getMovDir().isLeft();
            case RIGHT -> getMovDir().isRight();
        };
    }


    public void gameTick() {
        score++;
        if (doesNotCollide()) {
            player.move();
        }
        if (gameMap.get(player.getCoords()).isHasCorona()) {

            if(player.getLives() > 1 && !coronaEdible){
                player.setLives(player.getLives() - 1);
                //TODO TP player back to spawn
            }
            else if(coronaEdible){
                //TODO remove this Corona
            }
            else if(player.getLives() == 1 && !coronaEdible){
                gamePanel.finishGame(score);
            }

        } else if (gameMap.get(player.getCoords()).isHasDot()) {
            //TODO remove dot
            //TODO wenn das der letzte dot war --> Spiel gewonnen


        } else if (gameMap.get(player.getCoords()).isHasToiletPaper()) {
            player.setLives(player.getLives() + 1);
            //TODO remove ToiletPaper

        } else if (gameMap.get(player.getCoords()).isHasVac()) {
            coronaEdible = true;

            TimerTask task = new TimerTask() {
                public void run() {
                    coronaEdible = false;
                }
            };
            Timer timer = new Timer("Timer");

            long delay = 15 * 1000L;
            timer.schedule(task, delay);

            //TODO remove Vac
            //TODO change Vac design
        }
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

    public int getScore() {
        return score;
    }



}

