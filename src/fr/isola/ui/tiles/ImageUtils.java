package fr.isola.ui.tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageUtils {

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

    public static BufferedImage copyImage(BufferedImage source){
        BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
        Graphics g = b.getGraphics();
        g.drawImage(source, 0, 0, null);
        g.dispose();
        return b;
    }

}
