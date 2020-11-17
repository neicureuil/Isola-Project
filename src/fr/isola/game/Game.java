package fr.isola.game;

import fr.isola.game.players.Player;

public class Game {

    private GameConfig config;
    // MAP => TRUE = NON DETRUIT (ON PEUT SE DEPLACER DESSUS) - FALSE = DETRUITE
    private boolean map[][];
    private Player p1, p2;

    public Game(GameConfig config) {
        this.config = config;
        this.map = new boolean[config.getSizeX()][config.getSizeY()];
        p1 = config.getP1();
        p2 = config.getP1();

        Init();
        //PlayGame();
    }

    void Init() {
        // Initialisation de la map
        for(int i=0; i<config.getSizeX(); i++) {
            for (int j=0; j<config.getSizeY(); j++) {
                map[i][j] = true;
            }
        }
        PlacePlayers();
    }

    void PlacePlayers() {
        int x = (int)(Math.random()*(config.getSizeX()/2));
        int y = (int)(Math.random()*config.getSizeY());
        p1.setX(x);
        p1.setY(y);
        x = (int)(Math.random()*(config.getSizeX()/2)+config.getSizeX()/2);
        y = (int)(Math.random()*config.getSizeY());
        p2.setX(x);
        p2.setY(y);
    }

    void PlayGame() {
        boolean isGameFinished = false;
        do {
            //CHANGEMENT DE JOUEUR ACTIF
            //LANCER JOUEUR.TOUR
            //TESTER SI UN DES JOUEURS EST BLOQUE -> isGameFinished = true
        } while(isGameFinished == false);
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
