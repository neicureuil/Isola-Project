package fr.isola.ui;

import fr.isola.game.Game;
import fr.isola.game.players.HumanPlayer;
import fr.isola.game.players.IaPlayer;
import fr.isola.ui.panels.GamePanel;
import fr.isola.ui.panels.MenuPanel;

import javax.swing.*;
import java.awt.*;

public class IsolaFrame extends JFrame {

    private MenuPanel menu;

    public IsolaFrame() {
        setTitle("Isola");
        //setPreferredSize(new Dimension(1080, 720));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        menu = new MenuPanel(this);
        ShowMenu();

        setLocationRelativeTo(null);
    }

    public void ShowGame(Game game) {
        getContentPane().removeAll();
        getContentPane().add(new GamePanel(game));
        pack();
        repaint();
    }

    public  void ShowMenu() {
        getContentPane().removeAll();
        getContentPane().add(menu);
        pack();
    }

}
