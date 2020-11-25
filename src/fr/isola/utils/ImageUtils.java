package fr.isola.utils;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Classe contenant des fonctions utiles pour la gestion d'images.
 */
public class ImageUtils {

    /**
     * Applique un masque a une image.
     * @param img L'image sur laquelle on souhaite appliquer le masque.
     * @param mask La valeur du masque.
     * @return Une BufferedImage qui est l'image avec le masque.
     */
    public static BufferedImage GetImageWithMask(BufferedImage img, int mask) {
        BufferedImage result = copyImage(img);
        for (int i = 0; i < result.getWidth(); i++) {
            for (int j = 0; j < result.getHeight(); j++) {
                int pixel = img.getRGB(i, j) & mask;
                result.setRGB(i, j, pixel);
            }
        }
        return result;
    }

    /**
     * Permet de dupliquer une image (en créer une nouvelle instance).
     * @param source L'image source.
     * @return Une BufferedImage qui est une copie de l'image.
     */
    public static BufferedImage copyImage(BufferedImage source){
        BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
        Graphics g = b.getGraphics();
        g.drawImage(source, 0, 0, null);
        g.dispose();
        return b;
    }

}
