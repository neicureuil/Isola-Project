package fr.isola.ui.panels;

import fr.isola.ui.components.MenuButton;

import javax.swing.*;
import java.awt.*;

/**
 * Panneau du menu gauche.
 */
public class LeftMenuPanel extends JPanel {

    /**
     * Bouton pour afficher la menu de paramètre de création de jeu.
     */
    private JButton playBtn;
    /**
     * Bouton pour afficher la menu de paramètre d'entrainement de l'IA.
     */
    private JButton iaBtn;
    /**
     * Bouton pour quitter l'application.
     */
    private JButton quitBtn;

    /**
     * Constructeur qui initialise les composant.
     */
    public LeftMenuPanel() {
        setBackground(new Color(18,18,18));
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(250,720));

        JLabel titleLabel = new JLabel("Isola", SwingConstants.CENTER);
        titleLabel.setFont(titleLabel.getFont().deriveFont(50f));
        titleLabel.setForeground(Color.WHITE);

        playBtn = new MenuButton("Jouer");
        iaBtn = new MenuButton("IA");
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
        add(iaBtn, gbc);
        gbc.anchor = GridBagConstraints.PAGE_END;
        add(quitBtn, gbc);

    }

    /**
     * @return Le bouton d'affichhe du menu du jeu.
     */
    public JButton getPlayBtn() {
        return playBtn;
    }

    /**
     * @return Le bouton pour quitter l'app.
     */
    public JButton getQuitBtn() {
        return quitBtn;
    }

    /**
     * @return Le bouton pour afficher le menu IA.
     */
    public JButton getIaBtn() {
        return iaBtn;
    }
}
