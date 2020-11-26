package fr.isola.ui.components;

import fr.isola.ui.sprites.SpriteSheet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Composant de selection de personnage et de configuration du joueur.
 */
public class PlayerSelection extends JPanel {

    /**
     * ComboBox permettant de selection le type de joueur.
     */
    private JComboBox playerTypeBox;
    /**
     * Bouton pour selectioner le sprite suivant.
     */
    private JButton nextButton;
    /**
     * Bouton pour selectioner le sprite précedent.
     */
    private JButton previousButton;
    /**
     * Image du sprite selectionné.
     */
    private Image centerImage;
    /**
     * Label du permettant d'affichier l'image du sprite centerImage.
     */
    private JLabel imageLabel;

    /**
     * Index du sprite choisit.
     */
    private int imageIndex = 0;

    /**
     * Contructeur qui initialise tous les sous-composants.
     */
    public PlayerSelection() {
        setOpaque(false);
        setLayout(new BorderLayout(5,10));

        playerTypeBox = new JComboBox();
        playerTypeBox.addItem("Humain");
        playerTypeBox.addItem("BestMove Ia");
        playerTypeBox.addItem("Neural Ia");

        nextButton = new MenuButton(">", 30, 64);
        nextButton.addActionListener(this::nextSprite);

        previousButton = new MenuButton("<", 30, 64);
        previousButton.addActionListener(this::previousSprite);

        imageLabel = new JLabel();
        updateImage();

        add(playerTypeBox, BorderLayout.NORTH);
        add(previousButton, BorderLayout.WEST);
        add(imageLabel, BorderLayout.CENTER);
        add(nextButton, BorderLayout.EAST);
    }

    /**
     * Action du bouton nextButton qui selectionne le sprite suivant.
     * @param e L'evenement.
     */
    private void nextSprite(ActionEvent e) {
        imageIndex = (imageIndex+1)%SpriteSheet.Numbers;

        updateImage();
    }

    /**
     * Action du bouton previousButton qui selectionne le sprite precedent.
     * @param e L'evenement.
     */
    private void previousSprite(ActionEvent e) {
        imageIndex--;
        if(imageIndex < 0) imageIndex = SpriteSheet.Numbers - 1;
        updateImage();
    }

    /**
     * Actualise l'image de imageLabel.
     */
    private void updateImage() {
        centerImage = SpriteSheet.INSTANCE.getSprite(imageIndex).getScaledInstance(64,64,1);
        imageLabel.setIcon(new ImageIcon(centerImage));
    }

    /**
     * @return Id du sprite selectionné.
     */
    public int getSelectedSprite() {
        return imageIndex;
    }

    /**
     * @return Type du joueur selectionné.
     */
    public int getPlayerType() {
        return playerTypeBox.getSelectedIndex();
    }

}


