package fr.isola.ui.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel implements ActionListener {

    public MenuPanel() {
        setBackground(new Color(6,66,115) );
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1080, 720);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

