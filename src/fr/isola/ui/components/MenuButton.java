package fr.isola.ui.components;

import javax.swing.*;
import java.awt.*;

public class MenuButton extends JButton {

    public MenuButton(String text) {
        this(text, true);
    }

    public MenuButton(String text, int w, int h) {
        this(text, false);
        setPreferredSize(new Dimension(w,h));
    }


    public MenuButton(String text, boolean resize) {
        super(text);

        if(resize) setPreferredSize(new Dimension(200,50));

        setBorder(BorderFactory.createLineBorder(Color.WHITE));
        setBackground(Color.BLACK);
        setFont(getFont().deriveFont(25f));
        setForeground(Color.WHITE);

        setFocusPainted(false);
    }

}
