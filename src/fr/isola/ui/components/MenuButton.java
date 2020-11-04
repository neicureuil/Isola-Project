package fr.isola.ui.components;

import javax.swing.*;
import java.awt.*;

public class MenuButton extends JButton {

    public MenuButton(String text) {
        super(text);

        setPreferredSize(new Dimension(200,50));

        setBorder(BorderFactory.createLineBorder(Color.WHITE));
        setBackground(Color.BLACK);
        setFont(getFont().deriveFont(25f));
        setForeground(Color.WHITE);

        setFocusPainted(false);
    }

}
