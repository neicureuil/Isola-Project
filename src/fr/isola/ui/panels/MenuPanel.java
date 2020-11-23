package fr.isola.ui.panels;

import fr.isola.game.Game;
import fr.isola.ui.IsolaFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class MenuPanel extends JPanel {

    private IsolaFrame mainFrame;

    private MenuIaPanel iaPanel;
    private MenuConfigPanel optionPanel;
    private LeftMenuPanel leftPanel;
    private boolean optionPanelShow = false;

    private Image bgImage;

    public MenuPanel(IsolaFrame mainFrame) {
        this.mainFrame = mainFrame;

        try {
            this.bgImage = ImageIO.read(getClass().getResource("/resources/images/menu.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setBackground(new Color(6,66,115) );
        setLayout(new BorderLayout());

        // LEFT PANEL
        leftPanel = new LeftMenuPanel();
        leftPanel.getPlayBtn().addActionListener(this::PlayButtonClicked);
        leftPanel.getIaBtn().addActionListener(this::IaButtonClicked);
        leftPanel.getQuitBtn().addActionListener(this::QuitButtonClicked);
        // END LEFT PANEL


        // IA PANEL
        iaPanel = new MenuIaPanel();
        // IA PANEL END

        // OPTION PANEL
        optionPanel = new MenuConfigPanel();
        optionPanel.getConfirmPlayBtn().addActionListener(this::LaunchGameClicked);
        // END OPTION PANEL

        add(leftPanel, BorderLayout.WEST);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImage, leftPanel.getWidth(), 0, getWidth(),getHeight(),0,0, bgImage.getWidth(null),bgImage.getHeight(null), null);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1280, 720);
    }

    public void PlayButtonClicked(ActionEvent e) {
        optionPanelShow = !optionPanelShow;

        if(optionPanelShow) add(optionPanel, BorderLayout.CENTER);
        else  remove(optionPanel);

        validate();
    }

    public void IaButtonClicked(ActionEvent e) {
        optionPanelShow = !optionPanelShow;

        if(optionPanelShow) add(optionPanel, BorderLayout.CENTER);
        else  remove(optionPanel);

        validate();
    }


    public void QuitButtonClicked(ActionEvent e) {
        JComponent comp = (JComponent) e.getSource();
        Window win = SwingUtilities.getWindowAncestor(comp);
        win.dispose();
        System.exit(0);
    }

    public void LaunchGameClicked(ActionEvent e) {
        mainFrame.ShowGame(new Game(optionPanel.getGameConfig()));
    }
}

