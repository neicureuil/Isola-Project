package fr.isola.game;

import fr.isola.game.players.HumanPlayer;
import fr.isola.game.players.IaPlayer;
import fr.isola.game.players.Player;

public class Game {

    public enum GameState{ MOVE, DESTROY, P1LOST, P2LOST }

    private boolean isFinished;

    private GameState state;
    private GameConfig config;

    private boolean map[][]; // MAP => TRUE = NON DETRUIT (ON PEUT SE DEPLACER DESSUS) - FALSE = DETRUITE

    private Player p1, p2;
    private Player active_player;

    public Game(GameConfig config) {
        this.config = config;
        this.map = new boolean[config.getSizeX()][config.getSizeY()];
        this.p1 = config.getP1();
        this.p2 = config.getP2();

        this.isFinished = false;

        Init();
        PlayGame();
    }

    void Init() {
        // Initialisation de la map
        for(int i=0; i<config.getSizeX(); i++) {
            for (int j=0; j<config.getSizeY(); j++) {
                map[i][j] = true;
            }
        }
        PlacePlayers();
        active_player = p2;
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
        active_player = (active_player.equals(p1))?(p2):(p1);
        state = GameState.MOVE;
        if(active_player instanceof IaPlayer) {
            Player opponent = (active_player.equals(p1))?(p2):(p1);
            ((IaPlayer)active_player).move(opponent, map);
            state = GameState.DESTROY;
            ((IaPlayer)active_player).destroy(opponent, map);
            EndTurn();
        }

    }

    void EndTurn() {
        if(checkIfLost(p1)){
            state = GameState.P1LOST;
            isFinished = true;
        }
        else if(checkIfLost(p2)){
            state = GameState.P2LOST;
            isFinished = true;
        }
        else PlayGame();
    }

    private boolean checkIfLost(Player p) {
        int x_min = p.getX()-1;
        if(x_min<0) x_min = 0;
        int x_max = p.getX()+1;
        if(x_max>=config.getSizeX()) x_max = config.getSizeX()-1;

        int y_min = p.getY()-1;
        if(y_min<0) y_min = 0;
        int y_max = p.getY()+1;
        if(y_max>=config.getSizeY()) y_max = config.getSizeY()-1;

        for(int i=y_min; i<= y_max; i++) {
            for(int j=x_min; j<= x_max; j++) {
                if( map[j][i] && (p1.getX()!=j || p1.getY()!=i) && (p2.getX()!=j || p2.getY()!=i) ) {
                    return false;
                }
            }
        }
        return true;
    }


    public void SetPositionOnGrid/*SomeoneClicked*/(int x, int y) {
        if(state==GameState.MOVE && active_player instanceof HumanPlayer){
            if(map[x][y] && Math.abs( active_player.getX() - x ) <= 1 && Math.abs( active_player.getY() - y ) <= 1 && (p1.getX()!=x || p1.getY()!=y) && (p2.getX()!=x  || p2.getY()!=y) ) {
                active_player.setX(x);
                active_player.setY(y);
                state = GameState.DESTROY;
            }
        }
        else if(state==GameState.DESTROY && active_player instanceof HumanPlayer) {
            if(map[x][y] && (p1.getX()!=x || p1.getY()!=y) && (p2.getX()!=x  || p2.getY()!=y)) {
                map[x][y]=false;
                EndTurn();
            }
        }
    }

    public GameConfig getConfig() { return config; }

    public boolean getTile(int x, int y) { return map[x][y]; }

    public GameState getState() { return state; }

    public int getActivePlayerId() { return (active_player.equals(p1))?(1):(2); }

    public Player getActivePlayer() { return active_player; }

    public boolean isFinished() { return isFinished; }
}
