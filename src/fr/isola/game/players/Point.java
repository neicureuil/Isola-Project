package fr.isola.game.players;

public class Point {

    /**
     * La variable qui représente la coordonnée x
     */
    private int x;
    /**
     * La variable qui représente la coordonnée y
     */
    private int y;

    /**
     * Classe qui représente un point
     * @param x coordonnée x
     * @param y coordonnée y
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return la coordonnée x
     */
    public int getX() {
        return x;
    }

    /**
     * @return la coordonnée y
     */
    public int getY() {
        return y;
    }

}
