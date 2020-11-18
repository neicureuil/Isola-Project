package fr.isola.ui.panels;

import fr.isola.ui.components.MenuButton;

import javax.swing.*;
import java.awt.*;

public class ResultPanel extends JPanel {

    private JButton confirmButton;
    private JLabel resultText;

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

    public void SetText(String txt) {
        this.resultText.setText(txt);
    }

    public JButton getConfirmButton() {
        return confirmButton;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1280, 670);
    }
}
