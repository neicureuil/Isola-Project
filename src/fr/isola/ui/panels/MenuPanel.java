package fr.isola.ui.panels;

import fr.isola.game.Game;
import fr.isola.ui.IsolaFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * Panneau du menu de l'application.
 */
public class MenuPanel extends JPanel {

    /**
     * Reference sur la fenetre principale.
     */
    private IsolaFrame mainFrame;

    /**
     * Menu de configuration de l'IA.
     */
    private MenuIaPanel iaPanel;
    /**
     * Menu de configuration de la partie.
     */
    private MenuConfigPanel optionPanel;
    /**
     * Menu gauche.
     */
    private LeftMenuPanel leftPanel;
    /**
     * Definit si le menu de configuration du jeu est afficher ou non.
     */
    private boolean optionPanelShow = false;
    /**
     * Definit si le menu de configuration de l'ia est afficher ou non.
     */
    private boolean iaPanelShow = false;

    /**
     * Image de fond.
     */
    private Image bgImage;

    /**
     * Contructeur qui les initialises les éléments.
     * @param mainFrame Reference sur la fenetre princpale.
     */
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

    /**
     * Affichage des élements et de l'image de fond.
     * @param g L'evenement.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImage, leftPanel.getWidth(), 0, getWidth(),getHeight(),0,0, bgImage.getWidth(null),bgImage.getHeight(null), null);
    }

    /**
     * @return La taille recommendée du panneau.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1280, 720);
    }

    /**
     * Fonction appelée quand on clique sur un le boutton jouer.
     * @param e L'evenement.
     */
    public void PlayButtonClicked(ActionEvent e) {
        optionPanelShow = !optionPanelShow;

        if(optionPanelShow) {
            if(iaPanelShow) {
                iaPanelShow = false;
                remove(iaPanel);
            }
            add(optionPanel, BorderLayout.CENTER);
        }else {
            remove(optionPanel);
        }
        validate();
    }

    /**
     * Fonction appelée quand on clique sur un le boutton IA.
     * @param e L'evenement.
     */
    public void IaButtonClicked(ActionEvent e) {
        iaPanelShow = !iaPanelShow;

        if(iaPanelShow){
            if(optionPanelShow) {
                optionPanelShow = false;
                remove(optionPanel);
            }
            add(iaPanel, BorderLayout.CENTER);
        }else {
            remove(iaPanel);
        }
        validate();
    }

    /**
     * Fonction appelée quand on clique sur le boutton quitter.
     * @param e L'evenement.
     */
    public void QuitButtonClicked(ActionEvent e) {
        JComponent comp = (JComponent) e.getSource();
        Window win = SwingUtilities.getWindowAncestor(comp);
        win.dispose();
        System.exit(0);
    }

    /**
     * Fonction appelée quand on clique sur le bouton de lancement du jeu?.
     * @param e L'evenement.
     */
    public void LaunchGameClicked(ActionEvent e) {
        mainFrame.ShowGame(new Game(optionPanel.getGameConfig()));
    }
}

