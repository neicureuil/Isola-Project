package fr.isola.ui.components;

import javax.swing.*;
import java.awt.*;

/**
 * Bouton avec un rendu propore.
 */
public class MenuButton extends JButton {

    /**
     * Initialise le bouton.
     * @param text Le texte dans le bouton.
     */
    public MenuButton(String text) {
        this(text, true);
    }

    /**
     * Initialise le bouton avec une taille prédefinie.
     * @param text Le texte dans le bouton.
     * @param w La largeur du bouton.
     * @param h La hauteur du bouton.
     */
    public MenuButton(String text, int w, int h) {
        this(text, false);
        setPreferredSize(new Dimension(w,h));
    }


    /**
     * Initialise le bouton.
     * @param text Le texte dans le bouton.
     * @param resize Indique si il a une taille prédéfinit
     */
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
