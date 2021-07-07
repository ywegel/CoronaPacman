package de.dickeLunten.coronaPacman.models.panel;

import de.dickeLunten.coronaPacman.GameModelListener;
import de.dickeLunten.coronaPacman.ModelListener;
import de.dickeLunten.coronaPacman.models.entities.*;
import util.*;

import java.awt.*;
import java.util.*;

public class GameModel extends PanelModel {
    private Player player;
    private Vac[] vacs;
    private TPaper tPaper;
    private boolean coronaEdible;
    private ModelListener gamePanel;
    private ArrayList<Corona> coronas;

    private GameModelListener gameModelListener;

    private int fps = 0;

    private final HashMap<Coord, MapChunkValues> gameMap;

    private int score;

    private Image mapImage;

    private boolean animationState = false;

    public GameModel() {
        gameMap = Data.getGameHashMap();
        mapImage = Data.loadImageFromRes("img/vvmap.png").getScaledInstance(800, 1048, Image.SCALE_FAST);
        score = 0;
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

        //switch animation Image
        animationState = tick % Dimensions.TICKS_PER_ANIMATION_SWITCH == 0;

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
            if (doesNotCollideCorona()) {
                c.move();
                if (tick % Dimensions.TICKS_PER_CHUNK == 0) {
                    c.moveChunk();
                }
            } else if (!doesNotCollideCorona()) {

            }
        }

        if (gameMap.get(player.getCoords()).isHasCorona()) {

            if (player.getLives() > 1 && !coronaEdible) {
                player.setLives(player.getLives() - 1);
                //TODO TP player back to spawn
            } else if (coronaEdible) {
                for (Corona c : coronas) {
                    if (player.getCoords() == c.getCords()) {
                        coronas.remove(c);
                    }
                }
            } else if (player.getLives() == 1 && !coronaEdible) {
                //gamePanel.finishGame(score);
            }

        } else if (gameMap.get(player.getCoords()).isHasDot()) {
            System.out.println("Dot getroffem");

            gameMap.put(getPlayer().getCoords(), gameMap.get(getPlayer().getCoords()).setHasDot(false));

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
        Random r = new Random();
        return new Coord(r.nextInt(Dimensions.MAP_WIDTH), r.nextInt(Dimensions.MAP_HEIGHT));
        //TODO constant values file for map etc
    }

    public void turnPlayer(PlayerDirection dir) {
        if (doesNotCollidePlayer(dir)) {
            player.setCurrentDirection(dir);
        }
    }

    public void addCorona(Corona c) {
        coronas.add(c);
    }

    public void removeCorona(Corona c) {
        coronas.remove(c);
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

