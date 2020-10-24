package fr.isola.ui.panels;

import fr.isola.game.Game;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private Game game;

    private int tileW = 48, tileH = 48;
    private int offset = 4;
    private int offsetX = 10, offsetY;
    private int lineW, lineH;

    public GamePanel(Game game) {
        this.game = game;
        setBackground(new Color(6,66,115) );
        Init();
    }

    private void Init() {
        lineW = game.getSizeX() * (tileW + offset) + offset;
        lineH = game.getSizeY() * (tileH + offset) + offset;

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
                g.fillRect(offsetX + offset + i*(tileW + offset),offsetY + offset + j*(tileH + offset), tileW, tileH);
            }
        }

        // DESSIN DES CONTOURS
        g.setColor(Color.BLACK);

        for(int x=0; x<=game.getSizeX(); x++) {
            g.fillRect( offsetX + x*(offset + tileW) , offsetY, offset, lineH);
        }

        for(int y=0; y<=game.getSizeY(); y++) {
            g.fillRect(offsetX, offsetY + y*(offset +tileH), lineW, offset);
        }

        // TODO  DESSIN DES PLAYERS

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1080, 720);
    }



}

