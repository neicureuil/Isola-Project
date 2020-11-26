package fr.isola.ui.panels;

import fr.isola.ui.components.MenuButton;

import javax.swing.*;
import java.awt.*;

/**
 * Panneau d'affichage des resultats de la patie.
 */
public class ResultPanel extends JPanel {

    /**
     * Bouton de confirmation.
     */
    private JButton confirmButton;
    /**
     * Label d'affichage du resultat.
     */
    private JLabel resultText;

    /**
     * Constructeur qui initialise les éléments.
     */
    public ResultPanel() {
        setOpaque(false);
        setLayout(new GridBagLayout());

        resultText = new JLabel("Lorem Ipsum");
        resultText.setForeground(Color.WHITE);
        resultText.setFont(resultText.getFont().deriveFont(25f));

        confirmButton = new MenuButton("OK");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(10,0,10,0);

        gbc.gridy = 0;
        add(resultText, gbc);
        gbc.gridy = 1;
        add(confirmButton, gbc);
    }

    /**
     * Change le texte de resultat.
     * @param txt Le texte.
     */
    public void SetText(String txt) {
        this.resultText.setText(txt);
    }

    /**
     * @return Le bouton de confirmation.
     */
    public JButton getConfirmButton() {
        return confirmButton;
    }

    /**
     * @return La taille recommandé du panel.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1280, 670);
    }
}
