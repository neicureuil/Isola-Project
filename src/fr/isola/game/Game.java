package fr.isola.game;

import fr.isola.deepl.Dataset;
import fr.isola.game.players.HumanPlayer;
import fr.isola.game.players.IaPlayer;
import fr.isola.game.players.Player;
import fr.isola.game.players.Point;

public class Game {

    public enum GameState{ MOVE, DESTROY, P1LOST, P2LOST }

    private boolean isFinished;

    private GameState state;
    private GameConfig config;

    private boolean map[][]; // MAP => TRUE = NON DETRUIT (ON PEUT SE DEPLACER DESSUS) - FALSE = DETRUITE

    private Player p1, p2;
    private Player active_player;

    private Dataset moveDatas, destroyDatas;

    public Game(GameConfig config) {
        this.config = config;
        this.map = new boolean[config.getSizeX()][config.getSizeY()];
        this.p1 = config.getP1();
        this.p2 = config.getP2();

        this.isFinished = false;

        Init();
        if(this.config.isExtractDatas()) InitDatasets();
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

    void InitDatasets() {
        this.moveDatas = new Dataset("moves_dataset_"+config.getSizeX()+ "_"+config.getSizeY()+ ".txt");
        this.moveDatas.initWriter();
        this.destroyDatas = new Dataset("destroy_dataset_"+config.getSizeX()+ "_"+config.getSizeY()+ ".txt");
        this.destroyDatas.initWriter();
    }

    void EndDatasets() {
        this.moveDatas.closeWriter();
        this.destroyDatas.closeWriter();
    }

    void PlacePlayers() {
        int x = (int)(Math.random()*(config.getSizeX()/2));
        int y = (int)(Math.random()*config.getSizeY());
        MovePlayer(p1, x, y, false);
        x = (int)(Math.random()*(config.getSizeX()/2)+config.getSizeX()/2);
        y = (int)(Math.random()*config.getSizeY());
        MovePlayer(p2, x, y, false);
    }

    void PlayGame() {
        active_player = (active_player.equals(p1))?(p2):(p1);
        state = GameState.MOVE;
        if(active_player instanceof IaPlayer) {
            IaPlayer ia_player = ((IaPlayer)active_player);
            Player opponent = (active_player.equals(p1))?(p2):(p1);
            // IA MOVEMENT
            Point movePts = ia_player.move(opponent, map);
            if(IsValidMove(active_player, movePts.getX(), movePts.getY())) {
                MovePlayer(active_player, movePts.getX(), movePts.getY(), true);
            }else{
                System.out.println("[WARNING] Ia want to move to a wrong case !");
            }
            // IA DESTROY
            if(!IsGameOver()) {
                state = GameState.DESTROY;
                Point destryPts = ia_player.destroy(opponent, map);
                if(IsValidCase(destryPts.getX(), destryPts.getY())) {
                    DestroyCase(active_player, destryPts.getX(), destryPts.getY());
                }else{
                    System.out.println("[WARNING] Ia want to destroy to a wrong case !");
                }
                EndTurn();
            }
        }

    }

    public void SetPositionOnGrid/*SomeoneClicked*/(int x, int y) {
        if(state==GameState.MOVE && active_player instanceof HumanPlayer){
            if(IsValidMove(active_player, x, y)) {
                MovePlayer(active_player, x, y, true);
                state = GameState.DESTROY;
            }
        }
        else if(state==GameState.DESTROY && active_player instanceof HumanPlayer) {
            if(IsValidCase(x, y)) {
                DestroyCase(active_player, x, y);
                EndTurn();
            }
        }
    }

    void EndTurn() {
        if(!IsGameOver()) PlayGame();
    }

    void MovePlayer(Player p, int x, int y, boolean isRegistable) {
        if(isRegistable && config.isExtractDatas()) {
            RegisterData(moveDatas, p,x,y);
        }
        p.setX(x);
        p.setY(y);
    }

    void DestroyCase(Player p, int x, int y) {
        if(config.isExtractDatas()) {
            RegisterData(destroyDatas, p, x, y);
        }
        map[x][y] = false;
    }

    boolean IsGameOver() {
        if(checkIfLost(p1)){
            state = GameState.P1LOST;
        } else if(checkIfLost(p2)){
            state = GameState.P2LOST;
        }
        if(state == GameState.P1LOST || state == GameState.P2LOST) {
            isFinished = true;
            if(config.isExtractDatas()) EndDatasets();
        }
        return isFinished;
    }

    boolean IsValidCase(int x, int y) {
        if(map[x][y] && (p1.getX()!=x || p1.getY()!=y) && (p2.getX()!=x  || p2.getY()!=y)) {
            return true;
        }
        return false;
    }

    boolean IsValidMove(Player p, int x, int y) {
        if(map[x][y] && Math.abs( p.getX() - x ) <= 1 && Math.abs( p.getY() - y ) <= 1 && (p1.getX()!=x || p1.getY()!=y) && (p2.getX()!=x  || p2.getY()!=y) ) {
            return true;
        }
        return false;
    }

    boolean checkIfLost(Player p) {
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


    void RegisterData(Dataset dataset, Player p, int x, int y) {
        double[] datas = new double[config.getSizeX()*config.getSizeY() + 6];
        datas[0] = p.getX();
        datas[1] = p.getY();
        Player opponent = (p.equals(p1)) ? p2 : p1;
        datas[2] = opponent.getX();
        datas[3] = opponent.getY();

        int counter = 4;
        for (int i=0; i<config.getSizeX(); i++) {
            for (int j=0; j<config.getSizeY(); j++){

                if((p1.getX() == i && p1.getY() == j) || (p2.getX() == i && p2.getY() == j) || !map[i][j]){
                    datas[counter] = -1;
                }else{
                    datas[counter] = 1;
                }

                counter++;
            }
        }
        datas[datas.length-1] = x;
        datas[datas.length-2] = y;
        dataset.addData(datas);
    }

    public GameConfig getConfig() { return config; }

    public boolean getTile(int x, int y) { return map[x][y]; }

    public GameState getState() { return state; }

    public int getActivePlayerId() { return (active_player.equals(p1))?(1):(2); }

    public Player getActivePlayer() { return active_player; }

    public boolean isFinished() { return isFinished; }
}
