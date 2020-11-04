package fr.isola.game;

import fr.isola.game.players.Player;

public class Game {

    private GameConfig config;
    // MAP => TRUE = NON DETRUIT (ON PEUT SE DEPLACER DESSUS) - FALSE = DETRUITE
    private boolean map[][];

    public Game(GameConfig config) {
        this.config = config;
        this.map = new boolean[config.getSizeX()][config.getSizeY()];

        Init();
    }

    void Init() {
        // Initialisation de la map
        for(int i=0; i<config.getSizeX(); i++) {
            for (int j=0; j<config.getSizeY(); j++) {
                map[i][j] = true;
            }
        }
    }

    public void Start() {

    }

    public void SetPositionOnGrid(int x, int y) {
        // TODO CHECK PLAYER TURN
        // DEBUG TILE MAP => A SUPPR
        map[x][y] = false;
        // END DEBUG TILE MAP
    }

    public GameConfig getConfig() { return config; }

    public boolean getTile(int x, int y) { return map[x][y]; }
}
