package fr.isola.ui.panels;

import fr.isola.ui.components.MenuButton;

import javax.swing.*;
import java.awt.*;

public class LeftMenuPanel extends JPanel {

    private JButton playBtn;
    private JButton quitBtn;

    public LeftMenuPanel() {
        setBackground(new Color(18,18,18));
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(250,720));

        JLabel titleLabel = new JLabel("Isola", SwingConstants.CENTER);
        titleLabel.setFont(titleLabel.getFont().deriveFont(50f));
        titleLabel.setForeground(Color.WHITE);

        playBtn = new MenuButton("Jouer");
        quitBtn = new MenuButton("Quitter");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10,0,10,0);
        gbc.weighty = 1.0;
        gbc.gridx = 0;

        gbc.anchor = GridBagConstraints.PAGE_START;
        add(titleLabel, gbc);
        gbc.anchor = GridBagConstraints.CENTER;
        add(playBtn, gbc);
        gbc.anchor = GridBagConstraints.PAGE_END;
        add(quitBtn, gbc);

    }

    public JButton getPlayBtn() {
        return playBtn;
    }

    public JButton getQuitBtn() {
        return quitBtn;
    }
}
