package fr.isola.game.players;

/**
 * L'interface qui définie un joueur non organique
 */
public interface IaPlayer {

    /**
     * Gère le déplacement de l'IA
     * @param opponent le joueur adverse
     * @param map le terrain
     * @return le point où l'IA souhaite se déplacer
     */
    public Point move(Player opponent, boolean[][] map);
    /**
     * Gère la destruction de case par l'IA
     * @param opponent le joueur adverse
     * @param map le terrain
     * @return le point que l'IA souhaite détruire
     */
    public Point destroy(Player opponent, boolean[][] map);

}
