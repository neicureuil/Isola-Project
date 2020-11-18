package fr.isola.ui.panels;

import fr.isola.game.players.Player;
import fr.isola.ui.components.MenuButton;
import fr.isola.ui.layouts.LeftAndCenterLayout;

import javax.swing.*;
import java.awt.*;

public class GameInfoPanel extends JPanel {

    private JButton closeButton;
    private JLabel currentPlayerLabel;

    public GameInfoPanel() {
        setBackground(new Color(20,20,20,100));
        setLayout(new LeftAndCenterLayout());

        currentPlayerLabel = new JLabel("Tour du joueur 1");
        currentPlayerLabel.setForeground(Color.WHITE);
        currentPlayerLabel.setFont(currentPlayerLabel.getFont().deriveFont(25f));

        closeButton = new MenuButton("X", 35,35);

        add(currentPlayerLabel, LeftAndCenterLayout.CENTERED);
        add(closeButton, LeftAndCenterLayout.LEFT);
    }

    public void setInfoText(String txt) {
        this.currentPlayerLabel.setText(txt);
    }

    public JButton getCloseButton() { return this.closeButton; }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(getWidth(),50);
    }

}
