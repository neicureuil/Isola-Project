package fr.isola.ui.panels;

import fr.isola.game.Game;
import fr.isola.game.players.Player;
import fr.isola.ui.sprites.SpriteSheet;
import fr.isola.ui.sprites.TileMap;
import fr.isola.utils.ImageUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Panneau de rendu du jeu.
 */
public class GameRenderPanel extends JPanel implements MouseListener, MouseMotionListener {

    /**
     * Instance du jeu a afficher.
     */
    private Game game;

    /**
     * Taille d'un tile affiché.
     */
    private int tileSize = 48;
    /**
     * Taille horizontal du terrain.
     */
    private int sizeX;
    /**
     * Taille verticale du terrain.
     */
    private  int sizeY;
    /**
     * Decalage horizontal du terrain.
     */
    private int offsetX;
    /**
     * Decalage horizontal du terrain.
     */
    private  int offsetY;
    /**
     * Taille horizontale du terrain affiché.
     */
    private int lineW;
    /**
     * Taille verticale du terrain affiché.
     */
    private int lineH;
    /**
     * Stockage temporaire des coords x de la sourie.
     */
    private int cMouseX = 0;
    /**
     * Stockage temporaire des coords y de la sourie.
     */
    private int cMouseY = 0;

    /**
     * Mask d'un tile pour quand la souris est decu.
     */
    private int mouseOverMask = 0xff00ff00;
    /**
     * Mask d'un tile sur lequel ont peu se deplacer.
     */
    private int moveMask = 0xff00ff00;
    /**
     * Mask d'un tile pour quand la souris est decu et que l'utilisateur doit en choisir un a detruire.
     */
    private int destroyMask = 0xffff0000;

    /**
     * TileMap qui contient les differents tiles.
     */
    private TileMap tmap;
    /**
     * Image de fond du panel.
     */
    private Image bgImage;

    /**
     * Constructeur qui initialise les composants.
     * @param game L'instance du jeu a afficher.
     */
    public GameRenderPanel(Game game) {
        this.game = game;
        setBackground(new Color(6,66,115) );

        try {
            this.bgImage = ImageIO.read(getClass().getResource("/resources/images/game.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        addMouseListener(this);
        addMouseMotionListener(this);

        Init();
    }

    /**
     * Initialisation du rendu du jeu et des éléments correspondants.
     */
    private void Init() {
        tmap = new TileMap("/resources/images/tilemap.png", 16, 10, 5);

        sizeX = game.getConfig().getSizeX();
        sizeY = game.getConfig().getSizeY();

        lineW = sizeX * tileSize;
        lineH = sizeY * tileSize;

        offsetX = (getPreferredSize().width - lineW) / 2;
        offsetY = (getPreferredSize().height - lineH) / 2;
    }

    /**
     * Rendu des différents éléments du jeu.
     * @param g Graphisme
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.drawImage(bgImage, 0, 0, getWidth(),getHeight(),0,0, bgImage.getWidth(null),bgImage.getHeight(null), null);

        g.setColor(Color.GREEN);

        for(int i=0; i<sizeX; i++) {
            for(int j=0; j<sizeY; j++) {
                drawTile(g, i, j);
            }
        }

        drawPlayer(g, game.getConfig().getP1());
        drawPlayer(g, game.getConfig().getP2());
    }

    /**
     * Rendu d'un joueur.
     * @param g Graphisme
     * @param p Le joueur a afficher.
     */
    private void drawPlayer(Graphics g, Player p) {
        int x1 = offsetX + p.getX() * tileSize;
        int y1 = offsetY + p.getY() * tileSize;
        g.drawImage(SpriteSheet.INSTANCE.getSprite(p.getSprite()), x1,y1, x1+tileSize, y1+tileSize, 0, 0, 48, 48, null);
    }

    /**
     * Rendu d'un tile.
     * @param g Graphisme
     * @param x Coords x du tile.
     * @param y Coords y du tile.
     */
    private void drawTile(Graphics g, int x, int y) {
        int x1 = offsetX + x * tileSize;
        int y1 = offsetY + y * tileSize;
        if(game.getTile(x,y)){
            BufferedImage tile = tmap.getTile(getTileKey(x,y));
            if(cMouseX > x1 && cMouseX < (x1 + tileSize) && cMouseY > y1 && cMouseY < (y1 + tileSize)) {
                if(game.getState() == Game.GameState.DESTROY) {
                    tile = ImageUtils.GetImageWithMask(tile, destroyMask);
                }else {
                    tile = ImageUtils.GetImageWithMask(tile, mouseOverMask);
                }
            } else if(game.getState() == Game.GameState.MOVE && Math.abs(game.getActivePlayer().getX()-x) <= 1 && Math.abs(game.getActivePlayer().getY()-y) <= 1) {
                tile = ImageUtils.GetImageWithMask(tile, moveMask);
            }


            g.drawImage(tile, x1,y1, x1+tileSize, y1+tileSize, 0, 0, 16, 16, null);
        }
    }

    /**
     * Genere la clé d'un tile (informations sur les tiles autours).
     * @param x Coords x du tile.
     * @param y Coords y du tile.
     * @return La clé.
     */
    private String getTileKey(int x, int y) {
        String key = "";

        for(int j=y-1; j<=y+1; j++) {
            for(int i=x-1; i<=x+1; i++) {
                if(i==x && j==y) continue;
                if(i<0 || i >= sizeX|| j<0 || j >= sizeY) {
                    key += "W";
                }else {
                    key += (game.getTile(i, j)) ? "I" : "W";
                }
            }
        }

        return key;
    }

    /**
     * Evenement quand on clique sur un bouton de la souris.
     * @param e L'evenement.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() != MouseEvent.BUTTON1) return;

        if(e.getX() > offsetX && e.getX() < (offsetX + lineW ) && e.getY() > offsetY  && e.getY() < (offsetY + lineH )) {
            int xTile = (e.getX() - offsetX) / tileSize;
            int yTile = (e.getY() - offsetY) / tileSize;
            game.SetPositionOnGrid(xTile, yTile);
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {


    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    /**
     * Recupere les coords x et y de la souris.
     * @param e L'evenement.
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        cMouseX = e.getX();
        cMouseY = e.getY();
    }

    /**
     * @return Taille rencommendait du panneau.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1280, 670);
    }
}
