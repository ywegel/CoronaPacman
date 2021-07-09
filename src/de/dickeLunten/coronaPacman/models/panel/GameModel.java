package de.dickeLunten.coronaPacman.models.panel;

import de.dickeLunten.coronaPacman.GameModelListener;
import de.dickeLunten.coronaPacman.ModelListener;
import de.dickeLunten.coronaPacman.models.entities.*;
import util.*;

import java.awt.*;
import java.rmi.UnexpectedException;
import java.util.*;

public class GameModel extends PanelModel {
    private final Player player;
    private final Vac[] vacs;
    private final TPaper tPaper;
    private final ArrayList<Corona> coronas;
    private final HashMap<Coord, MapChunkValues> gameMap;

    private final Image mapImage;

    private GameModelListener gameModelListener;
    private ModelListener gamePanel;

    private boolean coronaEdible;

    private int fps;
    private int score;
    private int nomNomCount;
    private boolean animationState = false;

    public GameModel() {
        gameMap = Data.getGameHashMap();
        mapImage = Data.loadImageFromRes("img/vvmap.png").getScaledInstance(800, 1048, Image.SCALE_FAST);
        score = -1;
        fps = -1;
        nomNomCount = -1;
        coronaEdible = false;
        coronas = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            coronas.add(randomCorona());
        }

        player = new Player();

        vacs = new Vac[4];
        vacs[0] = new Vac(30, 30);
        vacs[1] = new Vac(130, 30);
        vacs[2] = new Vac(130, 130);
        vacs[3] = new Vac(30, 130);

        tPaper = new TPaper();
    }

    public void setGamePanel(ModelListener vl) {
        gamePanel = vl;
    }

    public void setGameModelListener(GameModelListener gl) {
        gameModelListener = gl;
    }

    public boolean doesNotCollidePlayer(PlayerDirection dir) {
        return switch (dir) {
            case UP -> getPlayerMovDir().isUp();
            case DOWN -> getPlayerMovDir().isDown();
            case LEFT -> getPlayerMovDir().isLeft();
            case RIGHT -> getPlayerMovDir().isRight();
        };
    }

    public boolean doesNotCollideCorona(Corona c) {
        return switch (c.getCurrentDirection()) {
            case UP -> getCoronaMovDir(c).isUp();
            case DOWN -> getCoronaMovDir(c).isDown();
            case LEFT -> getCoronaMovDir(c).isLeft();
            case RIGHT -> getCoronaMovDir(c).isRight();
        };
    }

    public void gameTick(int tick) {

        //switch animation Image
        if(tick % Dimensions.TICKS_PER_ANIMATION_SWITCH == 0) {
            animationState = !animationState;
        }

        score++;
        updateScore();
/*        System.out.println(gameMap.get(player.getCoords()).getPlayerMovableDir().isRight());
        System.out.println(player.getCurrentDirection().toString());
        System.out.println(doesNotCollidePlayer(getPlayer().getCurrentDirection()));
        System.out.println("-------------");*/
        //Player Movement
        if (doesNotCollidePlayer(getPlayer().getCurrentDirection())) {
            player.move();
            if (tick % Dimensions.TICKS_PER_CHUNK == 0) {
                player.moveChunk();
            }
        }
        //Corona Movement
        for (Corona c : coronas) {
            if (doesNotCollideCorona(c)) {
                c.move();
                if (tick % Dimensions.TICKS_PER_CHUNK == 0) {
                    c.moveChunk();
                }
            } else {
                //TODO fix pls
                //TODO corona achtet nicht auf wand und man kann nciht sterben ( map.set has corona)
                //TODO JLAbel mit leben
                int counter = 0;
                while (!doesNotCollideCorona(c)){
                    c.setCurrentDirection(randomDirection());
                    if (counter > 30) {
                        throw new IllegalStateException("POG POG POGPO PGOP OGP OGPOAPSDG");
                    }
                    counter++;
                }
            }
        }

        if (gameMap.get(player.getCoords()).isHasCorona()) {

            if (player.getLives() > 1 && !coronaEdible) {
                player.setLives(player.getLives() - 1);
                //TODO TP player back to spawn
            } else if (coronaEdible) {
                for (Corona c : coronas) {
                    if (player.getCoords() == c.getCoords()) {
                        coronas.remove(c);
                    }
                }
            } else if (player.getLives() == 1 && !coronaEdible) {
                //gamePanel.finishGame(score);
            }

        } else if (gameMap.get(player.getCoords()).isHasDot()) {
            System.out.println("Dot getroffem");

            gameMap.put(getPlayer().getCoords(), gameMap.get(getPlayer().getCoords()).setHasDot(false));

            nomNomCount++;

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

    private Corona randomCorona() {
        return new Corona(randomMapPosition(), randomDirection());
    }

    private PlayerDirection randomDirection() {
        int randomNum = 1 + (int) (Math.random() * 4);
        return switch (randomNum) {
            case 1 -> PlayerDirection.UP;
            case 2 -> PlayerDirection.DOWN;
            case 3 -> PlayerDirection.LEFT;
            default -> PlayerDirection.RIGHT;
        };
    }

    private Coord randomMapPosition() {
/*        Random r = new Random();
        return new Coord(r.nextInt(Dimensions.MAP_WIDTH), r.nextInt(Dimensions.MAP_HEIGHT));
        //TODO constant values file for map etc*/
        return new Coord(2, 3);
    }

    public void turnPlayer(PlayerDirection dir) {
        if (doesNotCollidePlayer(dir)) {
            player.setCurrentDirection(dir);
        }
    }

    public int getCoronaCount() {
        return coronas.size() - 1;
    }

    public ArrayList<Corona> getCovList(){
        return coronas;
    }

    private void updateScore() {
        gameModelListener.onScoreChanged(score);
    }

    private PlayerMovableDir getPlayerMovDir() {
        return gameMap.get(player.getCoords()).getPlayerMovableDir();
    }

    private PlayerMovableDir getCoronaMovDir(Corona c) {
        return gameMap.get(c.getCoords()).getPlayerMovableDir();
    }

    public Player getPlayer() {
        return player;
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

    public boolean getAnimationState() {
        return animationState;
    }

    public HashMap<Coord, MapChunkValues> getGameMap() {
        return gameMap;
    }

    public Vac[] getVacs() {
        return vacs;
    }

    public TPaper getTPaper() {
        return tPaper;
    }
}

