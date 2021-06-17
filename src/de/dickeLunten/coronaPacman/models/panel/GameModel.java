package de.dickeLunten.coronaPacman.models.panel;

import de.dickeLunten.coronaPacman.models.entities.Player;
import de.dickeLunten.coronaPacman.models.entities.Vac;
import de.dickeLunten.coronaPacman.views.entities.PlayerView;
import de.dickeLunten.coronaPacman.views.entities.VacView;
import util.MapChunkValues;
import util.Coord;

import java.util.HashMap;

public class GameModel extends PanelModel{

    private Player player;
    private Vac[] vacs;

    private PlayerView playerView;
    private VacView[] vacViews;

    private HashMap<Coord, MapChunkValues> gameMap;

    public GameModel(){

        gameMap = new HashMap<>();

        player = new Player();
        vacs = new Vac[4];
        vacs[0] = new Vac(30,30);
        vacs[1] = new Vac(130, 30);
        vacs[2] = new Vac(130, 130);
        vacs[3] = new Vac(30,130);

        playerView = new PlayerView(player);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
