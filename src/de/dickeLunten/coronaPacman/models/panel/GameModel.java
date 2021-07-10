package de.dickeLunten.coronaPacman.models.panel;

import de.dickeLunten.coronaPacman.GameModelListener;
import de.dickeLunten.coronaPacman.ModelListener;
import de.dickeLunten.coronaPacman.models.entities.*;
import util.*;

import java.awt.*;
import java.util.*;

public class GameModel extends PanelModel {
    private final Player player;
    private final ArrayList<Vac> vacs;
    private final TPaper tPaper;
    private final ArrayList<Corona> coronas;
    private final HashMap<Coord, MapChunkValues> gameMap;

    private final Image mapImage;

    private GameModelListener gameModelListener;

    private boolean coronaEdible;

    private int fps;
    private int score;
    private int nomNomCount;
    private int vacCount;
    private boolean coronaAnimationState = false;
    private boolean playerAnimationState = false;
    private PlayerDirection playerTurnRequest = null;

    public GameModel() {
        gameMap = Data.getGameHashMap();
        mapImage = Data.loadImageFromRes("img/vvmap.png").getScaledInstance(Dimensions.MAP_PIXEL_WIDTH, Dimensions.MAP_PIXEL_HEIGHT, Image.SCALE_FAST);
        score = -1;
        fps = -1;
        nomNomCount = -1;
        coronaEdible = false;
        coronas = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            coronas.add(randomCorona());
        }

        player = new Player();

        vacCount = 4;
        vacs = new ArrayList<Vac>();
        vacs.add(new Vac(new Coord(1,2) , -23, 15));
        vacs.add(new Vac(new Coord(17,2) , 697, 15));
        vacs.add(new Vac(new Coord(1,19) , -23, 848));
        vacs.add(new Vac(new Coord(17,19) , 697, 848));

        tPaper = new TPaper();
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
        if (tick % Dimensions.TICKS_PER_ANIMATION_SWITCH == 0) {
            coronaAnimationState = !coronaAnimationState;
        }
        if (tick % Dimensions.TICKS_PER_PLAYER_ANIMATION_SWITCH == 0) {
            playerAnimationState = !playerAnimationState;
        }

        score++;
        updateScore();

        //Player Movement
        boolean TPCX = (tick) % Dimensions.TICKS_PER_CHUNK_X == 0;
        boolean TPCY = (tick) % Dimensions.TICKS_PER_CHUNK_Y == 0;

        /*        if (doesNotCollidePlayer(player.getCurrentDirection())) {
            player.move();

            if (getPlayer().getCurrentDirection() == PlayerDirection.UP || getPlayer().getCurrentDirection() == PlayerDirection.DOWN) {

                if (player.getY() % (Dimensions.MAP_PIXEL_HEIGHT / Dimensions.MAP_HEIGHT) == 0) {
                    player.moveChunk();
                }

            }else {
                if (player.getX() % (Dimensions.MAP_PIXEL_WIDTH / Dimensions.MAP_WIDTH) == 0) {
                    player.moveChunk();
                }
            }

        }*/

        if (doesNotCollidePlayer(getPlayer().getCurrentDirection())) {
            player.move();
            if (getPlayer().getCurrentDirection() == PlayerDirection.UP || getPlayer().getCurrentDirection() == PlayerDirection.DOWN) {
                //player.increaseChunkOffsetY();
                if (TPCY) {
                    //player.setChunkOffsetY(0);
                    player.moveChunk();
                }
            } else {
                //player.increaseChunkOffsetX();
                if (TPCX) {
                    //player.setChunkOffsetX(0);
                    player.moveChunk();

                }
            }
        }

        //Corona Movement
        for (Corona c : coronas) {
            if (doesNotCollideCorona(c)) {
                c.move();
                if (c.getCurrentDirection() == PlayerDirection.UP || c.getCurrentDirection() == PlayerDirection.DOWN) {
                    if (TPCY) {
                        c.moveChunk();
                    }
                } else {
                    if (TPCX) {
                        c.moveChunk();
                    }
                }
            } else {
                //TODO fix pls
                //TODO corona achtet nicht auf wand und man kann nciht sterben ( map.set has corona)

                int counter = 0;
                while (!doesNotCollideCorona(c)) {
                    c.setCurrentDirection(randomDirection());
                    if (counter > 30) {
                        throw new IllegalStateException("POG POG POGPO PGOP OGP OGPOAPSDG");
                    }
                    counter++;
                }
            }
        }

        //Collisions
        if (gameMap.get(player.getCoords()).isHasCorona()) {

            if (player.getLives() > 1 && !coronaEdible) {
                player.setLives(player.getLives() - 1);
                player.setX(0);
                player.setY(0);
            } else if (coronaEdible) {
                for (Corona c : coronas) {
                    if (player.getCoords() == c.getCoords()) {
                        coronas.remove(c);
                    }
                }
            } else if (player.getLives() == 1 && !coronaEdible) {
                gameModelListener.finishGame(score);
            }

        } else if (gameMap.get(player.getCoords()).isHasDot()) {

            gameMap.put(getPlayer().getCoords(), gameMap.get(getPlayer().getCoords()).setHasDot(false));

            nomNomCount++;

            //184 dots mit vacs ; 180 nur dots
            if(nomNomCount == 180 && vacCount == 0){
                 gameModelListener.finishGame(score);
            }

        } else if (gameMap.get(player.getCoords()).isHasToiletPaper()) {
            System.out.println("TP getroffem");
            player.setLives(player.getLives() + 1);
            //TODO remove ToiletPaper

        } else if (gameMap.get(player.getCoords()).isHasVac()) {
            Coord cache = player.getCoords();
            System.out.println(cache.getX() +"  "+ cache.getY());
            gameMap.put(getPlayer().getCoords(), gameMap.get(getPlayer().getCoords()).setHasVac(false));
            vacCount--;

            for(Vac v: vacs){
                System.out.println(player.getCoords().getX()+ "   " + player.getCoords().getY());
                if(v.getCords() == player.getCoords()){

                    vacs.remove(v);
                }
            }


            if(nomNomCount == 180 && vacCount == 0){
                gameModelListener.finishGame(score);
            }

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
        int x;
        int y;
        do {
            x = r.nextInt(Dimensions.MAP_WIDTH);
            y = r.nextInt(Dimensions.MAP_HEIGHT);
            System.out.println(x);
            System.out.println(y);
        }
        while (!gameMap.get(new Coord(x, y)).isHasDot());
        return new Coord(x, y);
        //TODO constant values file for map etc
    }

    public void requestTurn(PlayerDirection dir) {
        if (doesNotCollidePlayer(dir)) {
            //playerTurnRequest = dir;
            player.setCurrentDirection(dir);
        }
    }

    public int getCoronaCount() {
        return coronas.size() - 1;
    }

    public ArrayList<Corona> getCovList() {
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

    public boolean getCoronaAnimationState() {
        return coronaAnimationState;
    }

    public boolean getPlayerAnimationState() {
        return playerAnimationState;
    }

    public HashMap<Coord, MapChunkValues> getGameMap() {
        return gameMap;
    }

    public ArrayList<Vac> getVacs() {
        return vacs;
    }

    public TPaper getTPaper() {
        return tPaper;
    }
}

