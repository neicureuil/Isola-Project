package fr.isola.ui.components;

import javax.swing.*;
import java.awt.*;

public class NumberSelection extends JPanel {

    private JLabel nameLabel;
    private JSpinner spinner;

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

    public int getValue() {
        return (int) spinner.getValue();
    }

}
