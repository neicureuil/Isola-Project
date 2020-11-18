package fr.isola.ui.sprites;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpriteSheet {

    public static SpriteSheet INSTANCE = new SpriteSheet();
    public static  final int Numbers = 4;

    private String path;
    private int spriteSize;

    private List<BufferedImage> sprites;

    public SpriteSheet() {
        this.path = "/resources/images/spritesheet.png";
        this.spriteSize = 48;
        this.sprites = new ArrayList<BufferedImage>();

        LoadSprites();
    }

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

    public BufferedImage getSprite(int index) {
        return sprites.get(index);
    }



}
