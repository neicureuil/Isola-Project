package fr.isola.ui.panels;

import fr.isola.game.GameConfig;
import fr.isola.game.players.HumanPlayer;
import fr.isola.game.players.IaPlayer;
import fr.isola.ui.components.MenuButton;

import javax.swing.*;
import java.awt.*;

public class MenuConfigPanel extends JPanel {

    private JButton confirmPlayBtn;

    public MenuConfigPanel() {
        setBackground(new Color(20,20,20,100));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(10,0,10,0);
        gbc.weighty = 1.0;
        gbc.gridx = 0;

        JLabel label = new JLabel("Settings", SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(label.getFont().deriveFont(40f));

        confirmPlayBtn = new MenuButton("Lancer la partie");

        JPanel gameOptionPanel = new JPanel();
        gameOptionPanel.setOpaque(false);

        gbc.anchor = GridBagConstraints.PAGE_START;
        add(label, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        add(gameOptionPanel, gbc);

        gbc.anchor = GridBagConstraints.PAGE_END;
        add(confirmPlayBtn, gbc);
    }

    public JButton getConfirmPlayBtn() {
        return this.confirmPlayBtn;
    }

    public GameConfig getGameConfig() {
        return new GameConfig(new HumanPlayer(), new IaPlayer(), 16,8);
    }
}
