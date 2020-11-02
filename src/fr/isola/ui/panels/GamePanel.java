package fr.isola.ui.panels;

import fr.isola.game.Game;
import fr.isola.ui.tiles.TileMap;
import javafx.scene.input.MouseButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Console;
import java.io.IOException;

public class GamePanel extends JPanel implements MouseListener {

    private Game game;

    private int tileW = 64, tileH = 64;
    private int offsetX, offsetY;
    private int lineW, lineH;

    private TileMap tmap;

    public GamePanel(Game game) {
        this.game = game;
        setBackground(new Color(6,66,115) );

        addMouseListener(this);
        Init();
    }

    private void Init() {
        tmap = new TileMap("/resources/images/spritesheet.png", 16);

        lineW = game.getSizeX() * tileW;
        lineH = game.getSizeY() * tileH;

        offsetX = (getPreferredSize().width - lineW) / 2;
        offsetY = (getPreferredSize().height - lineH) / 2;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // DESSIN DES TILES (A REMPLACER PAR DES IMAGES)
        g.setColor(Color.GREEN);

        for(int i=0; i<game.getSizeX(); i++) {
            for(int j=0; j<game.getSizeY(); j++) {
                drawTile(g, i, j);
            }
        }

        // TODO  DESSIN DES PLAYERS

    }

    private void drawTile(Graphics g, int x, int y) {
        int x1 = offsetX + x * tileW;
        int y1 = offsetY + y * tileH;
        if(game.getTile(x,y)){
            g.drawImage(tmap.getTile(getTileKey(x, y)), x1,y1, x1+tileW, y1+tileH, 0, 0, 16, 16, null);
        }
    }

    private String getTileKey(int x, int y) {
        String key = "";

        for(int j=y-1; j<=y+1; j++) {
            for(int i=x-1; i<=x+1; i++) {
                if(i==x && j==y) continue;
                if(i<0 || i >= game.getSizeX() || j<0 || j >= game.getSizeY()) {
                    key += "W";
                }else {
                    key += (game.getTile(i, j)) ? "I" : "W";
                }
            }
        }

        return key;
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
        // CHECK IF MOUSE IS IN GRID

        if(e.getX() > offsetX && e.getX() < (offsetX + lineW ) && e.getY() > offsetY  && e.getY() < (offsetY + lineH )) {
            int xTile = (e.getX() - offsetX) / (tileW);
            int yTile = (e.getY() - offsetY) / (tileH);
            game.SetPositionOnGrid(xTile, yTile);
            this.repaint();
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
}

