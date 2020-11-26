package fr.isola.game;

import fr.isola.game.players.Player;

public class GameConfig {

    /**
     * Le premier joueur
     */
    private Player p1;
    /**
     * Le second joueur
     */
    private Player p2;

    /**
     * La taille du plateau en x
     */
    private int sizeX;
    /**
     * La taille du plateau en y
     */
    private int sizeY;
    /**
     * Faut-il extraire les données de jeu
     */
    private boolean extractDatas;

    /**
     * Le constructeur de la classe
     * @param p1 le premier joueyr
     * @param p2 le second joueur
     * @param sizeX la taille du terrain en x
     * @param sizeY la taille du terrain en y
     * @param extractDatas le booléen précisant s'il faut enregistrer les données de jeu
     */
    public GameConfig(Player p1, Player p2, int sizeX, int sizeY, boolean extractDatas) {
        this.p1 = p1;
        this.p2 = p2;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.extractDatas = extractDatas;
    }

    /**
     * @return le premier joueur
     */
    public Player getP1() {
        return p1;
    }

    /**
     * @return le second joueur
     */
    public Player getP2() {
        return p2;
    }

    /**
     * @return la taille x
     */
    public int getSizeX() { return sizeX; }

    /**
     * @return la taille y
     */
    public int getSizeY() { return sizeY; }

    /**
     * @return le booléen à propos de l'enregistrement des données de la partie
     */
    public boolean isExtractDatas() { return extractDatas; }
}
