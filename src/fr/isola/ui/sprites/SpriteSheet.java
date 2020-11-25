package fr.isola.ui.sprites;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Singleton des Sprites de personnages.
 */
public class SpriteSheet {

    /**
     * Instance du singleton.
     */
    public static SpriteSheet INSTANCE = new SpriteSheet();
    /**
     * Nombre de sprites différents.
     */
    public static  final int Numbers = 5;

    /**
     * Chemin du fichier.
     */
    private String path;
    /**
     * Taille d'un sprite dans le fichier.
     */
    private int spriteSize;

    /**
     * Liste des sprites.
     */
    private List<BufferedImage> sprites;

    /**
     * Initialise la classe.
     * Appel LoadSprites().
     */
    public SpriteSheet() {
        this.path = "/resources/images/spritesheet.png";
        this.spriteSize = 48;
        this.sprites = new ArrayList<BufferedImage>();

        LoadSprites();
    }

    /**
     * Charge en mémoire les différents sprites du fichier.
     */
    private void LoadSprites() {
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i=0; i<Numbers; i++) {
            sprites.add(image.getSubimage(i * spriteSize, 0, spriteSize, spriteSize));
        }
    }

    /**
     * Permet de récuperer un sprite spécifique.
     * @param index L'index du sprite.
     * @return Le sprote correspondant.
     */
    public BufferedImage getSprite(int index) {
        return sprites.get(index);
    }



}
