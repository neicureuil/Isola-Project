package fr.isola.game;

import fr.isola.game.players.Player;

public class GameConfig {

    private Player p1;
    private Player p2;

    private int sizeX, sizeY;
    private boolean extractDatas;

    public GameConfig(Player p1, Player p2, int sizeX, int sizeY, boolean extractDatas) {
        this.p1 = p1;
        this.p2 = p2;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.extractDatas = extractDatas;
    }

    public Player getP1() {
        return p1;
    }

    public Player getP2() {
        return p2;
    }

    public int getSizeX() { return sizeX; }

    public int getSizeY() { return sizeY; }

    public boolean isExtractDatas() { return extractDatas; }
}
