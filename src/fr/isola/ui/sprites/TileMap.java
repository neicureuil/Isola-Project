package fr.isola.ui.sprites;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Classe représentant et gérant une TileMap.
 */
public class TileMap {

    /**
     * Chemin du fichier de la map.
     */
    private String path;
    /**
     * Taille d'une tile dans le fichier.
     */
    private int tileSize;
    /**
     * Tableau des tiles.
     */
    private BufferedImage[][] tiles;

    /**
     * Contructeur qui appel l'initialisation de la TileMap.
     * Appel LoadTiles().
     * @param path Chemin du fichier de la map.
     * @param tileSize Taille d'une tile dans le fichier.
     * @param nbX Nombre de ligne de tiles dans le fichier.
     * @param nbY Nombre de colones de tiles dans le fichier.
     */
    public TileMap(String path, int tileSize, int nbX, int nbY) {
        this.path = path;
        this.tileSize = tileSize;
        this.tiles = new BufferedImage[nbX][nbY];
        LoadTiles(nbX, nbY);
    }

    /**
     * Charge en memoire chaque tile afin d'eviter d'avoir a le refaire a chaque actualisation de la fenetre.
     * @param nbX Nombre de ligne de tiles dans le fichier.
     * @param nbY Nombre de colones de tiles dans le fichier.
     */
    private void LoadTiles(int nbX, int nbY) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i=0; i<nbX; i++)
            for(int j=0;j<nbY;j++)
                tiles[i][j] = image.getSubimage(i * tileSize,j * tileSize, tileSize, tileSize);
    }

    /**
     * Permet de recuperer une tile.
     * @param xi Numero de ligne de la tile.
     * @param yi Numero de colone de la tile.
     * @return La tile.
     */
    private BufferedImage getImageFromMap(int xi, int yi) {
        return tiles[xi][yi];
    }


    /**
     * Permet de recuper une tile a partir d'une clé qui réprésente les tiles autours de celle que l'on souhaite.
     * @param key La clé.
     * @return La tile correspondante.
     */
    public BufferedImage getTile(String key) {
        // 0 1 2
        // 3 * 4
        // 5 6 7
        // W = Water - I - Ile

        if(key.equals("IIIIIIII")) return getImageFromMap(4, 0);

        // HAS WATER UP
        if(key.charAt(1) == 'W') {
            if(key.charAt(3) == 'I' && key.charAt(4) == 'I' && key.charAt(6) == 'I' && key.charAt(7) == 'I' && key.charAt(5) == 'W') return getImageFromMap(9,2);
            if(key.charAt(3) == 'I' && key.charAt(4) == 'I' && key.charAt(6) == 'I' && key.charAt(7) == 'W' && key.charAt(5) == 'W') return getImageFromMap(5,2);
            if(key.charAt(3) == 'I' && key.charAt(4) == 'I' && key.charAt(6) == 'I' && key.charAt(7) == 'W' && key.charAt(5) == 'I') return getImageFromMap(7,2);
            if(key.charAt(3) == 'W' && key.charAt(4) == 'I' && key.charAt(6) == 'I' && key.charAt(7) == 'W') return getImageFromMap(0,1);
            if(key.charAt(3) == 'W' && key.charAt(4) == 'I' && key.charAt(6) == 'I') return getImageFromMap(0,0);

            if(key.charAt(3) == 'W' && key.charAt(4) == 'W' && key.charAt(6) == 'I') return getImageFromMap(1,2);
            if(key.charAt(3) == 'W' && key.charAt(4) == 'W' && key.charAt(6) == 'W') return getImageFromMap(9,0);

            if(key.charAt(3) == 'I' && key.charAt(4) == 'W' && key.charAt(6) == 'I' && key.charAt(5) == 'W') return getImageFromMap(1,1);
            if(key.charAt(3) == 'I' && key.charAt(4) == 'W' && key.charAt(6) == 'I') return getImageFromMap(6,0);

            if(key.charAt(3) == 'I' && key.charAt(4) == 'W' && key.charAt(6) == 'W') return getImageFromMap(6,1);
            if(key.charAt(3) == 'W' && key.charAt(4) == 'I' && key.charAt(6) == 'W') return getImageFromMap(4,1);
            if(key.charAt(3) == 'I' && key.charAt(4) == 'I' && key.charAt(6) == 'W') return getImageFromMap(5,1);
            return getImageFromMap(3,0);
        }

        // HAS WATER DOWN
        if(key.charAt(6) == 'W') {
            if(key.charAt(3) == 'I' && key.charAt(1) == 'I' && key.charAt(4) == 'I' && key.charAt(2) == 'W' && key.charAt(0) == 'I') return getImageFromMap(8,2);
            if(key.charAt(3) == 'I' && key.charAt(1) == 'I' && key.charAt(4) == 'I' && key.charAt(2) == 'W' && key.charAt(0) == 'W') return getImageFromMap(4,2);
            if(key.charAt(3) == 'I' && key.charAt(1) == 'I' && key.charAt(4) == 'I' && key.charAt(2) == 'I' && key.charAt(0) == 'W') return getImageFromMap(6,2);
            if(key.charAt(3) == 'W' && key.charAt(1) == 'I' && key.charAt(4) == 'I' && key.charAt(2) == 'W') return getImageFromMap(2,1);
            if(key.charAt(3) == 'W' && key.charAt(1) == 'I' && key.charAt(4) == 'I') return getImageFromMap(2,0);

            if(key.charAt(3) == 'I' && key.charAt(1) == 'I' && key.charAt(4) == 'W' && key.charAt(0) == 'W') return getImageFromMap(3,1);
            if(key.charAt(3) == 'I' && key.charAt(1) == 'I' && key.charAt(4) == 'W') return getImageFromMap(8,0);

            if(key.charAt(3) == 'W' && key.charAt(1) == 'I' && key.charAt(4) == 'W') return getImageFromMap(3,2);
            return getImageFromMap(5,0);
        }

        // HAS WATER LEFT
        if(key.charAt(3) == 'W') {
            if(key.charAt(1) == 'I' && key.charAt(6) == 'I' && key.charAt(4) == 'I' && key.charAt(2) == 'W' && key.charAt(7) == 'W') return getImageFromMap(0,3);
            if(key.charAt(1) == 'I' && key.charAt(6) == 'I' && key.charAt(4) == 'I' && key.charAt(2) == 'W' && key.charAt(7) == 'I') return getImageFromMap(1,3);
            if(key.charAt(1) == 'I' && key.charAt(6) == 'I' && key.charAt(4) == 'I' && key.charAt(2) == 'I' && key.charAt(7) == 'W') return getImageFromMap(2,3);

            if(key.charAt(1) == 'I' && key.charAt(6) == 'I' && key.charAt(4) == 'W') return getImageFromMap(2,2);
            return getImageFromMap(1,0);
        }

        // HAS WATER RIGHT
        if(key.charAt(4) == 'W') {
            if(key.charAt(0) == 'W' && key.charAt(5) == 'W' && key.charAt(1) == 'I' && key.charAt(6) == 'I' && key.charAt(3) == 'I') return getImageFromMap(3, 3);
            if(key.charAt(0) == 'I' && key.charAt(5) == 'W' && key.charAt(1) == 'I' && key.charAt(6) == 'I' && key.charAt(3) == 'I') return getImageFromMap(4, 3);
            if(key.charAt(0) == 'W' && key.charAt(5) == 'I' && key.charAt(1) == 'I' && key.charAt(6) == 'I' && key.charAt(3) == 'I') return getImageFromMap(5, 3);

            return getImageFromMap(7,0);
        }

        // HAS WATER LEFT UP CORNER
        if(key.charAt(0) == 'W') {
            if(key.charAt(2) == 'I' && key.charAt(5) == 'I' && key.charAt(7) == 'W') return getImageFromMap(6,4);
            if(key.charAt(2) == 'W' && key.charAt(5) == 'W' && key.charAt(7) == 'W') return getImageFromMap(2,4);
            if(key.charAt(2) == 'W' && key.charAt(5) == 'W') return getImageFromMap(0,4);
            if(key.charAt(2) == 'W' && key.charAt(7) == 'W') return getImageFromMap(1,4);
            if(key.charAt(2) == 'I' && key.charAt(0) == 'W' && key.charAt(5) == 'W' && key.charAt(7) == 'W') return getImageFromMap(3,4);
            if(key.charAt(2) == 'I' && key.charAt(5) == 'W') return getImageFromMap(9,3);
            if(key.charAt(2) == 'W') return getImageFromMap(6,3);

            return getImageFromMap(0,2);
        }

        // HAS WATER RIGHT UP CORNER
        if(key.charAt(2) == 'W') {
            if(key.charAt(0) == 'I' && key.charAt(5) == 'W' && key.charAt(7) == 'I') return getImageFromMap(5,4);
            if(key.charAt(7) == 'W' && key.charAt(5) == 'W') return getImageFromMap(4,4);
            if(key.charAt(7) == 'W') return getImageFromMap(8,3);

            return getImageFromMap(9,1);
        }

        // HAS WATER LEFT DOWN CORNER
        if(key.charAt(5) == 'W') {
            if(key.charAt(7) == 'W') return getImageFromMap(7,3);

            return getImageFromMap(8,1);
        }

        // HAS WATER RIGHT DOWN CORNER
        if(key.charAt(7) == 'W') {
            return getImageFromMap(7,1);
        }

        return null;
    }

}
