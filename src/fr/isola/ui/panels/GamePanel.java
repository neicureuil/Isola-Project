package fr.isola.ui.panels;

import fr.isola.game.Game;
import fr.isola.game.players.Player;
import fr.isola.ui.sprites.ImageUtils;
import fr.isola.ui.sprites.SpriteSheet;
import fr.isola.ui.sprites.TileMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements MouseListener, MouseMotionListener {

    private Game game;
    private GameInfoPanel infos;

    private int tileSize = 48;
    private int sizeX, sizeY;
    private int offsetX, offsetY;
    private int lineW, lineH;
    private int cMouseX = 0, cMouseY = 0;

    private int mouseOverMask = 0xff00ff00;
    private int moveMask = 0xff00ff00;
    private int destroyMask = 0xffff0000;

    private TileMap tmap;

    public GamePanel(Game game) {
        this.game = game;
        this.infos = new GameInfoPanel();

        setBackground(new Color(6,66,115) );
        setLayout(new BorderLayout());
        add(infos, BorderLayout.NORTH);

        addMouseListener(this);
        addMouseMotionListener(this);

        Init();
    }

    private void Init() {
        tmap = new TileMap("/resources/images/tilemap.png", 16, 10, 5);

        sizeX = game.getConfig().getSizeX();
        sizeY = game.getConfig().getSizeY();

        lineW = sizeX * tileSize;
        lineH = sizeY * tileSize;

        offsetX = (getPreferredSize().width - lineW) / 2;
        offsetY = (getPreferredSize().height - lineH) / 2;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        UpdateTitleText();

        g.setColor(Color.GREEN);

        for(int i=0; i<sizeX; i++) {
            for(int j=0; j<sizeY; j++) {
                drawTile(g, i, j);
            }
        }

        drawPlayer(g, game.getConfig().getP1());
        drawPlayer(g, game.getConfig().getP2());
    }

    private void drawPlayer(Graphics g, Player p) {
        int x1 = offsetX + p.getX() * tileSize;
        int y1 = offsetY + p.getY() * tileSize;
        g.drawImage(SpriteSheet.INSTANCE.getSprite(p.getSprite()), x1,y1, x1+tileSize, y1+tileSize, 0, 0, 16, 16, null);
    }

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

    public void UpdateTitleText() {
        String txt = "Joueur " + game.getActivePlayerId() + " : ";
        if(game.getState() == Game.GameState.MOVE) {
            txt += "Move";
        }else if(game.getState() == Game.GameState.DESTROY) {
            txt += "Destroy";
        }
        infos.setInfoText(txt);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1080, 720);
    }


    @Override
    public void mouseClicked(MouseEvent e) {


    }

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

    @Override
    public void mouseMoved(MouseEvent e) {
        cMouseX = e.getX();
        cMouseY = e.getY();
    }

}

