package fr.isola.game.players;

public abstract class Player {

    private  int x, y;

    public Player() {

    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) { this.y = y; }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
