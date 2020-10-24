package fr.isola.ui;

import fr.isola.ui.panels.MenuPanel;

import javax.swing.*;
import java.awt.*;

public class IsolaFrame extends JFrame {

    public IsolaFrame() {
        setTitle("Isola");
        setPreferredSize(new Dimension(1080, 720));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);


        getContentPane().add(new MenuPanel());


        pack();
        setLocationRelativeTo(null);
    }

}
