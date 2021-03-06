package fr.isola.ui.panels;

import fr.isola.deepl.Dataset;
import fr.isola.deepl.IaTrainer;
import fr.isola.ui.components.MenuButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 * Menu de configuration des IA.
 */
public class MenuIaPanel extends JPanel {

    /**
     * Bouton d'entrainement du déplacement de l'IA.
     */
    private JButton trainMoveBtn;
    /**
     * Bouton d'entrainement de la destruction de case de l'IA.
     */
    private JButton trainDestroyBtn;
    /**
     * Gestionnaire d'entrainement d'IA.
     */
    private IaTrainer trainer;

    /**
     * Constructeur qui initialise les éléments du panel.
     */
    public MenuIaPanel() {
        this.trainer = new IaTrainer();

        setBackground(new Color(20,20,20,100));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(10,0,10,0);
        gbc.weighty = 1.0;
        gbc.gridx = 0;

        JLabel label = new JLabel("Ia Settings", SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(label.getFont().deriveFont(40f));

        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.insets = new Insets(10,10,10,10);

        this.trainMoveBtn = new MenuButton("Train Move");
        this.trainMoveBtn.addActionListener(this::trainMoveClick);
        gbc2.gridy = 0;
        gbc2.gridx = 0;
        contentPanel.add(trainMoveBtn, gbc2);

        this.trainDestroyBtn = new MenuButton("Train Destroy");
        this.trainDestroyBtn.addActionListener(this::trainDestroyClick);
        gbc2.gridx = 1;
        contentPanel.add(trainDestroyBtn, gbc2);


        gbc.anchor = GridBagConstraints.PAGE_START;
        add(label, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        add(contentPanel, gbc);

    }

    /**
     * Action quand on clique sur le bouton d'entrainement du deplacement.
     * @param e L'evenement.
     */
    public void trainMoveClick(ActionEvent e) {
        if((new File("moves_dataset_8_6.txt")).exists())
            trainer.TrainNeuralIaMovement(new Dataset("moves_dataset_8_6.txt"));
    }

    /**
     * Action quand on clique sur le bouton de destruction d'une case.
     * @param e L'evenement.
     */
    public void trainDestroyClick(ActionEvent e) {
        if((new File("destroy_dataset_8_6.txt")).exists())
            trainer.TrainNeuralIaDestroy(new Dataset("destroy_dataset_8_6.txt"));
    }


}
