package fr.isola.ui.components;

import javax.swing.*;
import java.awt.*;

/**
 * Selecteur de nombre avec un label.
 */
public class NumberSelection extends JPanel {

    /**
     * Le label du selecteur.
     */
    private JLabel nameLabel;
    /**
     * Le selecteur de nombre.
     */
    private JSpinner spinner;

    /**
     * Initialise le selecteur de nombre.
     * @param name Le nom de selecteur qui sera affichier dans son label.
     * @param min La valeur minimum.
     * @param max La valeur maximum.
     * @param step Le pas d'incrementation.
     * @param def Le nombre affichier par défaut.
     */
    public NumberSelection(String name, int min, int max, int step, int def) {
        setLayout(new BorderLayout(5,5));
        setOpaque(false);

        nameLabel = new JLabel(name, JLabel.CENTER);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(nameLabel.getFont().deriveFont(15f));

        spinner = new JSpinner(new SpinnerNumberModel(def, min, max, step));

        add(nameLabel, BorderLayout.NORTH);
        add(spinner, BorderLayout.CENTER);
    }

    /**
     * @return La valeur du selecteur.
     */
    public int getValue() {
        return (int) spinner.getValue();
    }

    /**
     * Change la valeur du selecteur.
     * @param v La valeur a mettre.
     */
    public void setValue(int v) {
        spinner.setValue(v);
    }

}
