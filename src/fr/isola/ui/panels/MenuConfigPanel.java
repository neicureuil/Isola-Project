package fr.isola.ui.panels;

import fr.isola.game.Game;
import fr.isola.game.GameConfig;
import fr.isola.game.players.Player;
import fr.isola.ui.components.MenuButton;
import fr.isola.ui.components.NumberSelection;
import fr.isola.ui.components.PlayerSelection;

import javax.swing.*;
import java.awt.*;

public class MenuConfigPanel extends JPanel {

    private JButton confirmPlayBtn;
    private PlayerSelection playerSelect1, playerSelect2;
    private NumberSelection widthSelect, heightSelect;

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

        // ------ OPTION PANEL ------
        JPanel gameOptionPanel = new JPanel();
        gameOptionPanel.setOpaque(false);
        gameOptionPanel.setLayout(new GridBagLayout());

        GridBagConstraints optGbc = new GridBagConstraints();
        optGbc.fill = GridBagConstraints.HORIZONTAL;
        optGbc.insets = new Insets(10,10,10,10);

        widthSelect = new NumberSelection("Largeur", 1,18, 1, 8);
        heightSelect = new NumberSelection("Hauteur", 1,13, 1, 6);;

        optGbc.gridy = 0;
        optGbc.gridx = 0;
        gameOptionPanel.add(widthSelect, optGbc);
        optGbc.gridx = 1;
        gameOptionPanel.add(heightSelect, optGbc);

        playerSelect1 = new PlayerSelection();
        playerSelect2 = new PlayerSelection();

        optGbc.gridy = 1;
        optGbc.gridx = 0;
        gameOptionPanel.add(playerSelect1, optGbc);
        optGbc.gridx = 1;
        gameOptionPanel.add(playerSelect2, optGbc);

        // ------ END OPTION PANEL ------

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
        Player p1 = Game.GetPlayerTypeFromId(playerSelect1.getPlayerType(), widthSelect.getValue(), heightSelect.getValue());
        p1.setSprite(playerSelect1.getSelectedSprite());

        Player p2 = Game.GetPlayerTypeFromId(playerSelect2.getPlayerType(), widthSelect.getValue(), heightSelect.getValue());
        p2.setSprite(playerSelect2.getSelectedSprite());

        return new GameConfig(p1, p2, widthSelect.getValue(),heightSelect.getValue(), false);
    }
}
