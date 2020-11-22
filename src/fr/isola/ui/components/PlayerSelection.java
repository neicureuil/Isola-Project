package fr.isola.ui.components;

import fr.isola.ui.sprites.SpriteSheet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PlayerSelection extends JPanel {

    private JComboBox playerTypeBox;
    private JButton nextButton, previousButton;
    private Image centerImage;
    private JLabel imageLabel;

    private int imageIndex = 0;

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

    private void nextSprite(ActionEvent e) {
        imageIndex = (imageIndex+1)%SpriteSheet.Numbers;

        updateImage();
    }

    private void previousSprite(ActionEvent e) {
        imageIndex--;
        if(imageIndex < 0) imageIndex = SpriteSheet.Numbers - 1;
        updateImage();
    }

    private void updateImage() {
        centerImage = SpriteSheet.INSTANCE.getSprite(imageIndex).getScaledInstance(64,64,1);
        imageLabel.setIcon(new ImageIcon(centerImage));
    }

    public int getSelectedSprite() {
        return imageIndex;
    }

    public int getPlayerType() {
        return playerTypeBox.getSelectedIndex();
    }

}


