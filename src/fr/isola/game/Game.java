package fr.isola.game;

import fr.isola.game.players.Player;

public class Game {

    private Player p1;
    private Player p2;

    private int sizeX, sizeY;

    // MAP => TRUE = NON DETRUIT (ON PEUT SE DEPLACER DESSUS) - FALSE = DETRUITE
    private boolean map[][];

    public Game(Player p1, Player p2, int sizeX, int sizeY) {
        this.p1 = p1;
        this.p2 = p2;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.map = new boolean[sizeX][sizeY];

        Init();
    }

    void Init() {
        // Initialisation de la map
        for(int i=0; i<sizeX; i++) {
            for (int j=0; j<sizeY; j++) {
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

    public Player getP1() {
        return p1;
    }

    public Player getP2() {
        return p2;
    }

    public int getSizeX() { return sizeX; }

    public int getSizeY() { return sizeY; }

    public boolean getTile(int x, int y) { return map[x][y]; }
}
