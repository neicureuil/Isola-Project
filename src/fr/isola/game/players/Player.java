package fr.isola.game.players;


/**
 * La classe abstraite dont hériterons les différents types de joueurs
 */
public abstract class Player {

    /**
     * la position x du joueur
     */
    private  int x;
    /**
     * La position y du joueur
     */
    private int y;
    /**
     * L'apparence du joueur
     */
    private int sprite;

    public Player() {

    }

    /**
     * Modifie la valeur de s
     * @param s le sprite
     */
    public void setSprite(int s) { this.sprite = s; };

    /**
     * Modifie la valeur de x
     * @param x la position x
     */
    public void setX(int x) {
        this.x = x;
    }
    /**
     * Modifie la valeur de y
     * @param y la position y
     */
    public void setY(int y) { this.y = y; }

    /**
     * @return le sprite (sans le secouer)
     */
    public int getSprite() {return sprite; }

    /**
     * @return la position x
     */
    public int getX() {
        return x;
    }

    /**
     * @return la position y
     */
    public int getY() {
        return y;
    }
}
