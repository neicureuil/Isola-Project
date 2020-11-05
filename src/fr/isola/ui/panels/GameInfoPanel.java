package fr.isola.ui.panels;

import fr.isola.game.players.Player;

import javax.swing.*;
import java.awt.*;

public class GameInfoPanel extends JPanel {

    private JLabel currentPlayerLabel;

    public GameInfoPanel() {
        setBackground(new Color(20,20,20,100));
        setLayout(new GridBagLayout());

        currentPlayerLabel = new JLabel("Tour du joueur 1");
        currentPlayerLabel.setForeground(Color.WHITE);
        currentPlayerLabel.setFont(currentPlayerLabel.getFont().deriveFont(25f));

        GridBagConstraints gbc = new GridBagConstraints();
        add(currentPlayerLabel, gbc);
    }

    public void setPlayerTurn(int playerNb) {
        this.currentPlayerLabel.setText("Tour du joueur " + playerNb);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(getWidth(),50);
    }
}
