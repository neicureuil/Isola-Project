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
    private LeftMenuPanel leftPanel;
    private boolean optionPanelShow = false;

    public MenuPanel(IsolaFrame mainFrame) {
        this.mainFrame = mainFrame;

        setBackground(new Color(6,66,115) );
        setLayout(new BorderLayout());

        // LEFT PANEL
        leftPanel = new LeftMenuPanel();
        leftPanel.getPlayBtn().addActionListener(this::PlayButtonClicked);
        leftPanel.getQuitBtn().addActionListener(this::QuitButtonClicked);
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

