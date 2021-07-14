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
    private boolean turning, immune;

    public GameModel() {
        gameMap = Data.getGameHashMap();
        mapImage = Data.loadImageFromRes("img/vvmap.png").getScaledInstance(Dimensions.MAP_PIXEL_WIDTH, Dimensions.MAP_PIXEL_HEIGHT, Image.SCALE_FAST);
        score = -1;
        fps = -1;
        nomNomCount = -1;
        coronaEdible = false;
        turning = false;
        coronas = new ArrayList<Corona>();
        immune = false;

        for (int i = 0; i < 4; i++) {
            coronas.add(randomCorona());
        }

        player = new Player();

        vacCount = 4;
        vacs = new ArrayList<Vac>();
        vacs.add(new Vac(new Coord(1, 2), -23, 15));
        vacs.add(new Vac(new Coord(17, 2), 697, 15));
        vacs.add(new Vac(new Coord(1, 19), -23, 848));
        vacs.add(new Vac(new Coord(17, 19), 697, 848));

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

        if(coronaEdible) {
            if (tick % Dimensions.TICKS_PER_FAST_ANIMATION_SWITCH == 0) {
                coronaAnimationState = !coronaAnimationState;
            }
        }else {
            if (tick % Dimensions.TICKS_PER_ANIMATION_SWITCH == 0) {
                coronaAnimationState = !coronaAnimationState;
            }
        }
        //switch animation Image

        if (tick % Dimensions.TICKS_PER_PLAYER_ANIMATION_SWITCH == 0) {
            playerAnimationState = !playerAnimationState;
        }

        score++;
        updateScore();
        updateLives();
//-------------------------------------------------------------------------------------------------------------------------------------------------

        if(doesNotCollidePlayer(player.getPlannedDirection()) && player.getPlannedDirection() != player.getCurrentDirection()){
            turning = true;
            //System.out.println(player.getPlannedDirection());
        }
        if(turning){
            switch (player.getCurrentDirection()) {
                case UP:
                    if ((((double) player.getY()) / Dimensions.PIXEL_PER_CHUNK_Y) - player.getY() / Dimensions.PIXEL_PER_CHUNK_Y > 0.25) {
                            player.move();
                        //System.out.println("Move nach Oben erlaubt");
                    }
                    else{
                        player.setCurrentDirection(player.getPlannedDirection());
                        turning = false;
                    }
                    break;
                case DOWN:
                    if ((((double) player.getY()) / Dimensions.PIXEL_PER_CHUNK_Y) - player.getY() / Dimensions.PIXEL_PER_CHUNK_Y < 0.25) {
                        player.move();
                        //System.out.println("Move nach Unten erlaubt");
                    }
                    else{
                        player.setCurrentDirection(player.getPlannedDirection());
                        turning = false;
                    }
                    break;
                case LEFT:
                    if ((((double) player.getX()) / Dimensions.PIXEL_PER_CHUNK_X) - player.getX() / Dimensions.PIXEL_PER_CHUNK_X > 0.25) {
                        player.move();
                        //System.out.println("Move nach Links erlaubt");
                    }
                    else{
                        player.setCurrentDirection(player.getPlannedDirection());
                        turning = false;
                    }
                    break;
                case RIGHT:
                    if ((((double) player.getX()) / Dimensions.PIXEL_PER_CHUNK_X) - player.getX() / Dimensions.PIXEL_PER_CHUNK_X < 0.25) {
                        player.move();
                        //System.out.println("Move nach Rechts erlaubt");
                    }
                    else{
                        player.setCurrentDirection(player.getPlannedDirection());
                        turning = false;
                    }
                    break;
            }
        }

//-------------------------------------------------------------------------------------------------------------------------------------------------
        //Player Movement
        System.out.println(turning);
        if (doesNotCollidePlayer(player.getCurrentDirection()) && !turning) {
            player.move();
        }
        //Player Keeps moving until centred in chunk
        if (!doesNotCollidePlayer(player.getCurrentDirection())) {
            switch (player.getCurrentDirection()) {
                case UP:
                    if ((((double) player.getY()) / Dimensions.PIXEL_PER_CHUNK_Y) - player.getY() / Dimensions.PIXEL_PER_CHUNK_Y > 0.25) {
                        player.move();
                        //System.out.println("Move nach Oben erlaubt");
                    }
                    break;
                case DOWN:
                    if ((((double) player.getY()) / Dimensions.PIXEL_PER_CHUNK_Y) - player.getY() / Dimensions.PIXEL_PER_CHUNK_Y < 0.25) {
                        player.move();
                        //System.out.println("Move nach Unten erlaubt");
                    }
                    break;
                case LEFT:
                    if ((((double) player.getX()) / Dimensions.PIXEL_PER_CHUNK_X) - player.getX() / Dimensions.PIXEL_PER_CHUNK_X > 0.25) {
                        player.move();
                        //System.out.println("Move nach Links erlaubt");
                    }
                    break;
                case RIGHT:
                    if ((((double) player.getX()) / Dimensions.PIXEL_PER_CHUNK_X) - player.getX() / Dimensions.PIXEL_PER_CHUNK_X < 0.25) {
                        player.move();
                        //System.out.println("Move nach Rechts erlaubt");
                    }
                    break;
            }
        }

        //changes player coord when in new Chunk
        int xCord = player.getX() / Dimensions.PIXEL_PER_CHUNK_X;
        int yCord = player.getY() / Dimensions.PIXEL_PER_CHUNK_Y;
        player.setCords(new Coord(xCord, yCord));

//        System.out.println("X: " + player.getCords().getX() + "; Y: " + player.getCords().getY());
//        System.out.println("Exact Coords:  X: " + ((double) player.getX()) / Dimensions.PIXEL_PER_CHUNK_X + "; Y: " + ((double) player.getY()) / Dimensions.PIXEL_PER_CHUNK_Y);

//-------------------------------------------------------------------------------------------------------------------------------------------------
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
//-------------------------------------------------------------------------------------------------------------------------------------------------
//Yannick's Version
        /*

//        boolean TPCX = (tick) % Dimensions.TICKS_PER_CHUNK_X == 0;
//        boolean TPCY = (tick) % Dimensions.TICKS_PER_CHUNK_Y == 0;

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
        }*/
//-------------------------------------------------------------------------------------------------------------------------------------------------
        //Corona Movement
        for (Corona c : coronas) {
            if (doesNotCollideCorona(c)) {
                c.move();
            }
            //Corona Ändert auch an Kreuzungen die Richtung ||| ABER: Zu buggy
            /*if (tick % 15 == 0) {

                if (difDirPos(c)) {
                    int i = (int) Math.floor(Math.random() * (1 - 0 + 1) + 0);
                    if (i == 0) {
                        c.setCurrentDirection(randomDirection());
                    }
                }

            }*/
            //Corona keeps moving until centred in chunk then changes direction
            if (!doesNotCollideCorona(c)) {
                switch (c.getCurrentDirection()) {
                    case UP:
                        if ((((double) c.getY()) / Dimensions.PIXEL_PER_CHUNK_Y) - c.getY() / Dimensions.PIXEL_PER_CHUNK_Y > 0.25) {
                            c.move();
//                            System.out.println("Move nach Oben erlaubt");
                        } else {
                            c.setCurrentDirection(randomDirection());
                        }
                        break;
                    case DOWN:
                        if ((((double) c.getY()) / Dimensions.PIXEL_PER_CHUNK_Y) - c.getY() / Dimensions.PIXEL_PER_CHUNK_Y < 0.25) {
                            c.move();
//                            System.out.println("Move nach Unten erlaubt");
                        } else {
                            c.setCurrentDirection(randomDirection());
                        }
                        break;
                    case LEFT:
                        if ((((double) c.getX()) / Dimensions.PIXEL_PER_CHUNK_X) - c.getX() / Dimensions.PIXEL_PER_CHUNK_X > 0.25) {
                            c.move();
//                            System.out.println("Move nach Links erlaubt");
                        } else {
                            c.setCurrentDirection(randomDirection());
                        }
                        break;
                    case RIGHT:
                        if ((((double) c.getX()) / Dimensions.PIXEL_PER_CHUNK_X) - c.getX() / Dimensions.PIXEL_PER_CHUNK_X < 0.25) {
                            c.move();
//                            System.out.println("Move nach Rechts erlaubt");
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

//-------------------------------------------------------------------------------------------------------------------------------------------------
//Yannick's Version
        /*        for (Corona c : coronas) {
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

                int counter = 0;
                while (!doesNotCollideCorona(c)) {
                    c.setCurrentDirection(randomDirection());
                    if (counter > 30) {
                        throw new IllegalStateException("POG POG POGPO PGOP OGP OGPOAPSDG");
                    }
                    counter++;
                }
            }
        }*/

        //Collisions
        //TODO remove corona collide boolean from mapChunkValues
        for (Iterator<Corona> iterator = coronas.iterator(); iterator.hasNext(); ) {
            Corona c = iterator.next();
            if (player.getCords().getX() == c.getCoords().getX() && player.getCords().getY() == c.getCoords().getY()) {
                System.out.println("Mit Corona Collided");
                if (player.getLives() > 1 && !coronaEdible && !immune) {
                    System.out.println("Leben removed");
                    player.setLives(player.getLives() - 1);
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
                } else if (player.getLives() == 1 && !coronaEdible) {
                    System.out.println("Spielende");
                    gameModelListener.finishGame(score);
                }
            }
        }
/*        if (gameMap.get(player.getCords()).isHasCorona()) {


            if (player.getLives() > 1 && !coronaEdible) {
                player.setLives(player.getLives() - 1);
                player.setX(0);
                player.setY(0);
            } else if (coronaEdible) {
                for (Corona c : coronas) {
                    if (player.getCords() == c.getCoords()) {
                        coronas.remove(c);
                    }
                }
            } else if (player.getLives() == 1 && !coronaEdible) {
                gameModelListener.finishGame(score);
            }

        }*/
        if (gameMap.get(player.getCords()).isHasDot()) {

            gameMap.put(getPlayer().getCords(), gameMap.get(getPlayer().getCords()).setHasDot(false));

            nomNomCount++;

            //184 dots mit vacs ; 180 nur dots
            if (nomNomCount == 180 && vacCount == 0) {
                gameModelListener.finishGame(score);
            }

        } else if (gameMap.get(player.getCords()).isHasToiletPaper()) {
            System.out.println("TP getroffen");
            player.setLives(player.getLives() + 1);
            //TODO remove ToiletPaper

        } else if (gameMap.get(player.getCords()).isHasVac()) {
            gameMap.put(getPlayer().getCords(), gameMap.get(getPlayer().getCords()).setHasVac(false));
            vacCount--;
            System.out.println("Number of Vacs: " + vacCount);

            Vac cacheVac = null;
            for (Vac v : vacs) {
//                System.out.println("Player: " + updatedPlayerPosition().getX()+ "   " + updatedPlayerPosition().getY());
//                System.out.println("Vac: " + v.getCords().getX()+ "   " + v.getCords().getY());
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

    private boolean difDirPos(Corona c) {
        switch (c.getCurrentDirection()) {
            case UP:
            case DOWN:
                if (gameMap.get(c.getCoords()).getPlayerMovableDir().isLeft()) {
                    return true;
                }
                if (gameMap.get(c.getCoords()).getPlayerMovableDir().isRight()) {
                    return true;
                }
                break;
            case LEFT:
            case RIGHT:
                if (gameMap.get(c.getCoords()).getPlayerMovableDir().isUp()) {
                    return true;
                }
                if (gameMap.get(c.getCoords()).getPlayerMovableDir().isDown()) {
                    return true;
                }
                break;
        }
        return false;
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
          player.setPlannedDirection(dir);

//        if (doesNotCollidePlayer(dir)) {
//            //playerTurnRequest = dir;
//            player.setCurrentDirection(dir);
//        }
    }

    public boolean isCoronaEdible() {
        return coronaEdible;
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

    private void updateLives(){
        gameModelListener.onLivesChanged(player.getLives());
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

