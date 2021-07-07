package de.dickeLunten.coronaPacman.models.panel;

import de.dickeLunten.coronaPacman.GameModelListener;
import de.dickeLunten.coronaPacman.ModelListener;
import de.dickeLunten.coronaPacman.models.entities.Corona;
import de.dickeLunten.coronaPacman.models.entities.Player;
import de.dickeLunten.coronaPacman.models.entities.PlayerDirection;
import de.dickeLunten.coronaPacman.models.entities.Vac;
import util.Data;
import util.MapChunkValues;
import util.Coord;
import util.PlayerMovableDir;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class GameModel extends PanelModel {
    private Player player;
    private Vac[] vacs;
    private boolean coronaEdible;
    private ModelListener gamePanel;
    private ArrayList<Corona> coronas;

    private GameModelListener gameModelListener;

    private int fps = 0;

    private final HashMap<Coord, MapChunkValues> gameMap;

    private int score;

    private Image mapImage;

    public GameModel() {
        gameMap = Data.getGameHashMap();
        mapImage = Data.loadImage("img/VirusVendor.png").getScaledInstance(800,1048, Image.SCALE_FAST);
        System.out.println(gameMap.get(new Coord(0, 0)).isHasCorona());
        score = 0;
        coronaEdible = false;
        coronas = new ArrayList<Corona>();

        player = new Player();

//        vacs = new Vac[4];
//        vacs[0] = new Vac(30, 30);
//        vacs[1] = new Vac(130, 30);
//        vacs[2] = new Vac(130, 130);
//        vacs[3] = new Vac(30, 130);
    }

    public void setGamePanel(ModelListener vl) {
        gamePanel = vl;
    }

    public void setGameModelListener(GameModelListener gl) {
        gameModelListener = gl;
    }

    public boolean doesNotCollidePlayer() {
        return switch (player.getCurrentDirection()) {
            case UP -> getMovDir().isUp();
            case DOWN -> getMovDir().isDown();
            case LEFT -> getMovDir().isLeft();
            case RIGHT -> getMovDir().isRight();
        };
    }

    public boolean doesNotCollideCorona() {
        return switch (player.getCurrentDirection()) {
            case UP -> getMovDir().isUp();
            case DOWN -> getMovDir().isDown();
            case LEFT -> getMovDir().isLeft();
            case RIGHT -> getMovDir().isRight();
        };
    }

    public void gameTick(int tick) {
        score++;
        updateScore();
        System.out.println(gameMap.get(player.getCoords()).getPlayerMovableDir().isRight());
        System.out.println(player.getCurrentDirection().toString());
        System.out.println(doesNotCollidePlayer());
        System.out.println("-------------");
        //Player Movement
        if (doesNotCollidePlayer()) {
            player.move();
            if(tick % 50 == 0) {
                player.moveChunk();
            }
        }
        //Corona Movement
        for(Corona c: coronas){
            if(doesNotCollideCorona()){
                c.move();
                if(tick % 50 == 0){
                    c.moveChunk();
                }
            }
            else if(!doesNotCollideCorona()){

            }
        }

        //System.out.println(gameMap.get(player.getCoords()).isHasCorona());
        if (gameMap.get(player.getCoords()).isHasCorona()) {


            if (player.getLives() > 1 && !coronaEdible) {
                player.setLives(player.getLives() - 1);
                //TODO TP player back to spawn
            } else if (coronaEdible) {
                for(Corona c: coronas){
                    if(player.getCoords() == c.getCords()){
                        coronas.remove(c);
                    }
                }
            } else if (player.getLives() == 1 && !coronaEdible) {
                //gamePanel.finishGame(score);
            }

        } else if (gameMap.get(player.getCoords()).isHasDot()) {
            System.out.println("Dot getroffem");
            //TODO remove dot
            //TODO wenn das der letzte dot war --> Spiel gewonnen


        } else if (gameMap.get(player.getCoords()).isHasToiletPaper()) {
            System.out.println("TP getroffem");
            player.setLives(player.getLives() + 1);
            //TODO remove ToiletPaper

        } else if (gameMap.get(player.getCoords()).isHasVac()) {
            System.out.println("Vac getroffem");

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

    public PlayerDirection randomDirection(){
        int randomNum = 1 + (int)(Math.random() * 4);
        switch (randomNum){
            case 1: return PlayerDirection.UP;
            case 2: return PlayerDirection.DOWN;
            case 3: return PlayerDirection.LEFT;
            case 4: return PlayerDirection.RIGHT;
        }
        return PlayerDirection.RIGHT;
    }

    public void addCorona(Corona c){
        coronas.add(c);
    }

    public void removeCorona(Corona c){
        coronas.remove(c);
    }

    private void updateScore() {
        gameModelListener.onScoreChanged(score);
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

    public int getFps() {
        return fps;
    }

    public void setFps(int fps) {
        this.fps = fps;
        gameModelListener.onFpsChanged(fps);
    }

    public Image getMapImage() {
        return mapImage;
    }
}

