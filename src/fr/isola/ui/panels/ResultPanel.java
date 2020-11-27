package fr.isola.ui.panels;

import fr.isola.ui.components.MenuButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

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
     * Image de fond du panel.
     */
    private Image bgImage;

    /**
     * Constructeur qui initialise les éléments.
     */
    public ResultPanel() {
        setOpaque(false);
        setLayout(new GridBagLayout());

        try {
            this.bgImage = ImageIO.read(getClass().getResource("/resources/images/game.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        resultText = new JLabel("Lorem Ipsum");
        resultText.setForeground(Color.BLACK);
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
     * Affichage des élements et de l'image de fond.
     * @param g L'evenement.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, getWidth(),getHeight(),0,0, bgImage.getWidth(null),bgImage.getHeight(null), null);
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
