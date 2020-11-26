package fr.isola.ui.panels;

import fr.isola.ui.components.MenuButton;
import fr.isola.ui.layouts.LeftAndCenterLayout;

import javax.swing.*;
import java.awt.*;

/**
 * Panel d'information du jeu (panneau du haut).
 */
public class GameInfoPanel extends JPanel {

    /**
     * Button qui ferme l'instance du jeu et retourne au menu.
     */
    private JButton closeButton;
    /**
     * Label qui affiche l'etat de la partie.
     */
    private JLabel currentPlayerLabel;

    /**
     * Constructeur qui initialise les composants.
     */
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

    /**
     * Change le texte d'information.
     * @param txt Le nouveau texte.
     */
    public void setInfoText(String txt) {
        this.currentPlayerLabel.setText(txt);
    }

    /**
     * @return Le boutton de fermeture de l'instance.
     */
    public JButton getCloseButton() { return this.closeButton; }

    /**
     * @return Taille recommendait du panel.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(getWidth(),50);
    }

}
