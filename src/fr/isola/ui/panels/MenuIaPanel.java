package fr.isola.ui.panels;

import fr.isola.ui.components.NumberSelection;
import fr.isola.ui.components.PlayerSelection;

import javax.swing.*;
import java.awt.*;

public class MenuIaPanel extends JPanel {

    public MenuIaPanel() {
        setBackground(new Color(20,20,20,100));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(10,0,10,0);
        gbc.weighty = 1.0;
        gbc.gridx = 0;

        JLabel label = new JLabel("Ia Settings", SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(label.getFont().deriveFont(40f));



        gbc.anchor = GridBagConstraints.PAGE_START;
        add(label, gbc);
    }



}
