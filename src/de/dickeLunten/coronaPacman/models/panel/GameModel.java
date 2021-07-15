package de.dickeLunten.coronaPacman.models.panel;

import de.dickeLunten.coronaPacman.GameModelListener;
import de.dickeLunten.coronaPacman.models.entities.*;
import util.*;

import java.awt.*;
import java.util.*;

public class GameModel extends PanelModel {
    private final Player player;
    private final ArrayList<Vac> vacs;
    private TPaper tPaper;
    private final ArrayList<Corona> coronas;
    private final HashMap<Coord, MapChunkValues> gameMap;

    private final Image mapImage;

    private GameModelListener gameModelListener;

    private boolean coronaEdible;

    private int fps;
    private int score;
    private int nomNomCount;
    private int vacCount;
    private int coronaSpeed;
    private boolean coronaAnimationState = false;
    private boolean playerAnimationState = false;
    private boolean turning, immune;

    public GameModel() {
        gameMap = Data.loadMapDataFromFile();
        mapImage = Data.loadImageFromRes("img/vvmap.png").getScaledInstance(Dimensions.MAP_PIXEL_WIDTH, Dimensions.MAP_PIXEL_HEIGHT, Image.SCALE_FAST);
        score = -1;
        fps = -1;
        nomNomCount = -1;
        coronaSpeed = 2;
        coronaEdible = false;
        turning = false;
        coronas = new ArrayList<>();
        immune = false;

        for (int i = 0; i < 4; i++) {
            coronas.add(randomCorona());
        }

        player = new Player();

        vacCount = 4;
        vacs = new ArrayList<>();
        vacs.add(new Vac(new Coord(1, 2), -23, 15));
        vacs.add(new Vac(new Coord(17, 2), 697, 15));
        vacs.add(new Vac(new Coord(1, 19), -23, 848));
        vacs.add(new Vac(new Coord(17, 19), 697, 848));

        tPaper = null;
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

        if (coronaEdible) {
            if (tick % Dimensions.TICKS_PER_FAST_ANIMATION_SWITCH == 0) {
                coronaAnimationState = !coronaAnimationState;
            }
        } else {
            if (tick % Dimensions.TICKS_PER_ANIMATION_SWITCH == 0) {
                coronaAnimationState = !coronaAnimationState;
            }
        }
        //switch animation Image

        if (tick % Dimensions.TICKS_PER_PLAYER_ANIMATION_SWITCH == 0) {
            playerAnimationState = !playerAnimationState;
        }

        score++;
        updateScoreLives();

        
        if(score % 1000 == 0){
            coronas.add(randomCorona());
        }

        //TP
        if(player.getCords().getY() == 9 && player.getCords().getX() == 3 && player.getPlannedDirection() == PlayerDirection.LEFT){
            player.setX(600);
            player.setCurrentDirection(PlayerDirection.LEFT);
        }
        else if(player.getCords().getY() == 9 && player.getCords().getX() == 13 && player.getPlannedDirection() == PlayerDirection.RIGHT){
            player.setX(150);
            player.setCurrentDirection(PlayerDirection.RIGHT);
        }

//-------------------------------------------------------------------------------------------------------------------------------------------------

        if (doesNotCollidePlayer(player.getPlannedDirection()) && player.getPlannedDirection() != player.getCurrentDirection()) {
            turning = true;
        }
        if (turning) {
            switch (player.getCurrentDirection()) {
                case UP:
                    if ((((double) player.getY()) / Dimensions.PIXEL_PER_CHUNK_Y) - player.getY() / Dimensions.PIXEL_PER_CHUNK_Y > 0.25) {
                        player.move();
                    } else {
                        player.setCurrentDirection(player.getPlannedDirection());
                        turning = false;
                    }
                    break;
                case DOWN:
                    if ((((double) player.getY()) / Dimensions.PIXEL_PER_CHUNK_Y) - player.getY() / Dimensions.PIXEL_PER_CHUNK_Y < 0.25) {
                        player.move();
                    } else {
                        player.setCurrentDirection(player.getPlannedDirection());
                        turning = false;
                    }
                    break;
                case LEFT:
                    if ((((double) player.getX()) / Dimensions.PIXEL_PER_CHUNK_X) - player.getX() / Dimensions.PIXEL_PER_CHUNK_X > 0.25) {
                        player.move();
                    } else {
                        player.setCurrentDirection(player.getPlannedDirection());
                        turning = false;
                    }
                    break;
                case RIGHT:
                    if ((((double) player.getX()) / Dimensions.PIXEL_PER_CHUNK_X) - player.getX() / Dimensions.PIXEL_PER_CHUNK_X < 0.25) {
                        player.move();
                    } else {
                        player.setCurrentDirection(player.getPlannedDirection());
                        turning = false;
                    }
                    break;
            }
        }

//-------------------------------------------------------------------------------------------------------------------------------------------------
        //Player Movement
        if (doesNotCollidePlayer(player.getCurrentDirection()) && !turning) {
            player.move();
        }
        //Player Keeps moving until centred in chunk
        if (!doesNotCollidePlayer(player.getCurrentDirection())) {
            switch (player.getCurrentDirection()) {
                case UP:
                    if ((((double) player.getY()) / Dimensions.PIXEL_PER_CHUNK_Y) - player.getY() / Dimensions.PIXEL_PER_CHUNK_Y > 0.25) {
                        player.move();
                    }
                    break;
                case DOWN:
                    if ((((double) player.getY()) / Dimensions.PIXEL_PER_CHUNK_Y) - player.getY() / Dimensions.PIXEL_PER_CHUNK_Y < 0.25) {
                        player.move();
                    }
                    break;
                case LEFT:
                    if ((((double) player.getX()) / Dimensions.PIXEL_PER_CHUNK_X) - player.getX() / Dimensions.PIXEL_PER_CHUNK_X > 0.25) {
                        player.move();
                    }
                    break;
                case RIGHT:
                    if ((((double) player.getX()) / Dimensions.PIXEL_PER_CHUNK_X) - player.getX() / Dimensions.PIXEL_PER_CHUNK_X < 0.25) {
                        player.move();
                    }
                    break;
            }
        }

        //changes player coord when in new Chunk
        int xCord = player.getX() / Dimensions.PIXEL_PER_CHUNK_X;
        int yCord = player.getY() / Dimensions.PIXEL_PER_CHUNK_Y;
        player.setCords(new Coord(xCord, yCord));
        if(coronaEdible){
            coronaSpeed = 1;
        }
        else {
            coronaSpeed = 2;
        }

        for (Corona c : coronas) {
            if (doesNotCollideCorona(c)) {
                c.move(coronaSpeed);
            }

            //Corona keeps moving until centred in chunk then changes direction
            if (!doesNotCollideCorona(c)) {
                switch (c.getCurrentDirection()) {
                    case UP:
                        if ((((double) c.getY()) / Dimensions.PIXEL_PER_CHUNK_Y) - c.getY() / Dimensions.PIXEL_PER_CHUNK_Y > 0.25) {
                            c.move(coronaSpeed);
                        } else {
                            c.setCurrentDirection(randomDirection());
                        }
                        break;
                    case DOWN:
                        if ((((double) c.getY()) / Dimensions.PIXEL_PER_CHUNK_Y) - c.getY() / Dimensions.PIXEL_PER_CHUNK_Y < 0.25) {
                            c.move(coronaSpeed);
                        } else {
                            c.setCurrentDirection(randomDirection());
                        }
                        break;
                    case LEFT:
                        if ((((double) c.getX()) / Dimensions.PIXEL_PER_CHUNK_X) - c.getX() / Dimensions.PIXEL_PER_CHUNK_X > 0.25) {
                            c.move(coronaSpeed);
                        } else {
                            c.setCurrentDirection(randomDirection());
                        }
                        break;
                    case RIGHT:
                        if ((((double) c.getX()) / Dimensions.PIXEL_PER_CHUNK_X) - c.getX() / Dimensions.PIXEL_PER_CHUNK_X < 0.25) {
                            c.move(coronaSpeed);
                        } else {
                            c.setCurrentDirection(randomDirection());
                        }
                        break;
                }
            }

            //updates Corona Coords when in new Chunk
            int xCordCorona = c.getX() / Dimensions.PIXEL_PER_CHUNK_X;
            int yCordCorona = c.getY() / Dimensions.PIXEL_PER_CHUNK_Y;
            c.setCoords(new Coord(xCordCorona, yCordCorona));

        }

        //Collisions
        for (Iterator<Corona> iterator = coronas.iterator(); iterator.hasNext(); ) {
            Corona c = iterator.next();
            if (player.getCords().getX() == c.getCoords().getX() && player.getCords().getY() == c.getCoords().getY()) {
                System.out.println("Mit Corona Collided");
                if (player.getLives() >= 1 && !coronaEdible && !immune) {
                    System.out.println("Leben removed");
                    player.setLives(player.getLives() - 1);

                    if (tPaper == null) {
                        tPaper = new TPaper(randomMapPosition());
                        gameMap.put(tPaper.getCoords(), gameMap.get(tPaper.getCoords()).setHasToiletPaper(true));
                        System.out.println("hier bins ich " + tPaper.getCoords().getX() + " + " + tPaper.getCoords().getY());
                    }

                    immune = true;
                    TimerTask task = new TimerTask() {
                        public void run() {
                            immune = false;
                        }
                    };
                    Timer timer = new Timer("Timer");
                    long delay = 3 * 1000L;
                    timer.schedule(task, delay);
                } else if (coronaEdible) {
                    System.out.println("Corona gegessen");
                    iterator.remove();
                } else if (player.getLives() == 0 && !coronaEdible) {
                    System.out.println("Spielende");
                    gameModelListener.finishGame(0);
                }
            }
        }

        if (gameMap.get(player.getCords()).isHasDot()) {

            gameMap.put(getPlayer().getCords(), gameMap.get(getPlayer().getCords()).setHasDot(false));

            nomNomCount++;

            //184 dots mit vacs ; 180 nur dots
            if (nomNomCount == 180 && vacCount == 0) {
                gameModelListener.finishGame(score);
            }
        }
        if (gameMap.get(player.getCords()).isHasVac() && !coronaEdible) {
            gameMap.put(getPlayer().getCords(), gameMap.get(getPlayer().getCords()).setHasVac(false));
            vacCount--;
            System.out.println("Number of Vacs: " + vacCount);

            Vac cacheVac = null;
            for (Vac v : vacs) {
                if (v.getCords().equals(new Coord(player.getCords().getX() + 1, player.getCords().getY() + 1))) {
                    cacheVac = v;
                }
            }
            vacs.remove(cacheVac);


            if (nomNomCount == 180 && vacCount == 0) {
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
        }
        if(gameMap.get(player.getCords()).isHasToiletPaper()) {
            player.setLives(player.getLives() + 1);
            gameMap.replace(tPaper.getCoords(), gameMap.get(tPaper.getCoords()).setHasToiletPaper(false));
            tPaper = null;
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
    }

    public void requestTurn(PlayerDirection dir) {
        player.setPlannedDirection(dir);
    }

    public boolean isCoronaEdible() {
        return coronaEdible;
    }

    public ArrayList<Corona> getCovList() {
        return coronas;
    }

    private void updateScoreLives() {
        gameModelListener.onScoreLivesChanged(score, player.getLives());
    }


    private PlayerMovableDir getPlayerMovDir() {
        return gameMap.get(player.getCords()).getPlayerMovableDir();
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

