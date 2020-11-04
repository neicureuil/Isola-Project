package fr.isola.ui.panels;

import fr.isola.game.Game;
import fr.isola.game.players.HumanPlayer;
import fr.isola.game.players.IaPlayer;
import fr.isola.ui.IsolaFrame;
import fr.isola.ui.components.MenuButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class MenuPanel extends JPanel {

    private IsolaFrame mainFrame;

    private MenuConfigPanel optionPanel;
    private boolean optionPanelShow = false;

    public MenuPanel(IsolaFrame mainFrame) {
        this.mainFrame = mainFrame;

        setBackground(new Color(6,66,115) );
        setLayout(new BorderLayout());

        // LEFT PANEL
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(18,18,18));
        leftPanel.setLayout(new GridBagLayout());
        leftPanel.setPreferredSize(new Dimension(250,720));

        JLabel titleLabel = new JLabel("Isola", SwingConstants.CENTER);
        titleLabel.setFont(titleLabel.getFont().deriveFont(50f));
        titleLabel.setForeground(Color.WHITE);

        JButton playBtn = new MenuButton("Jouer");
        playBtn.addActionListener(this::PlayButtonClicked);

        JButton quitBtn = new MenuButton("Quitter");
        quitBtn.addActionListener(this::QuitButtonClicked);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10,0,10,0);
        gbc.weighty = 1.0;
        gbc.gridx = 0;

        gbc.anchor = GridBagConstraints.PAGE_START;
        leftPanel.add(titleLabel, gbc);
        gbc.anchor = GridBagConstraints.CENTER;
        leftPanel.add(playBtn, gbc);
        gbc.anchor = GridBagConstraints.PAGE_END;
        leftPanel.add(quitBtn, gbc);

        // END LEFT PANEL

        // OPTION PANEL
        optionPanel = new MenuConfigPanel();
        optionPanel.getConfirmPlayBtn().addActionListener(this::LaunchGameClicked);
        // END OPTION PANEL

        add(leftPanel, BorderLayout.WEST);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1080, 720);
    }

    public void PlayButtonClicked(ActionEvent e) {
        optionPanelShow = !optionPanelShow;

        if(optionPanelShow) add(optionPanel, BorderLayout.CENTER);
        else  remove(optionPanel);

        validate();
        repaint();
    }

    public void QuitButtonClicked(ActionEvent e) {
        JComponent comp = (JComponent) e.getSource();
        Window win = SwingUtilities.getWindowAncestor(comp);
        win.dispose();
    }

    public void LaunchGameClicked(ActionEvent e) {
        mainFrame.ShowGame(new Game(optionPanel.getGameConfig()));
    }
}

