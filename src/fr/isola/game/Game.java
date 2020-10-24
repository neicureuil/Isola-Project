package fr.isola.game;

import fr.isola.game.players.Player;

public class Game {

    private Player p1;
    private Player p2;

    private int sizeX, sizeY;

    public Game(Player p1, Player p2, int sizeX, int sizeY) {
        this.p1 = p1;
        this.p2 = p2;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        Init();
    }

    void Init() {

    }

    public void Start() {

    }

    public Player getP1() {
        return p1;
    }

    public Player getP2() {
        return p2;
    }

    public int getSizeX() { return sizeX; }

    public int getSizeY() { return sizeY; }
}
