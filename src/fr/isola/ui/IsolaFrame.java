package fr.isola.ui;

import fr.isola.game.Game;
import fr.isola.game.players.HumanPlayer;
import fr.isola.game.players.IaPlayer;
import fr.isola.ui.panels.GamePanel;
import fr.isola.ui.panels.MenuPanel;

import javax.swing.*;
import java.awt.*;

public class IsolaFrame extends JFrame {

    public IsolaFrame() {
        setTitle("Isola");
        //setPreferredSize(new Dimension(1080, 720));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);


        getContentPane().add(new GamePanel(new Game(new HumanPlayer(), new IaPlayer(), 8, 6)));


        pack();
        setLocationRelativeTo(null);
    }

}
