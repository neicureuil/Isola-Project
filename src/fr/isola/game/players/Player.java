package fr.isola.game.players;

public abstract class Player {

    private  int x, y;
    private int sprite;

    public Player() {

    }

    public void setSprite(int s) { this.sprite = s; };

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) { this.y = y; }

    public int getSprite() {return sprite; }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
