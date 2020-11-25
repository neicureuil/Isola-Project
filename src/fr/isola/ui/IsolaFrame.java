package fr.isola.ui;

import fr.isola.audio.AudioManager;
import fr.isola.game.Game;
import fr.isola.ui.panels.GamePanel;
import fr.isola.ui.panels.MenuPanel;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Fenetre de l'application.
 */
public class IsolaFrame extends JFrame implements KeyListener {

    /**
     * Panel du menu de l'application.
     */
    private MenuPanel menu;
    /**
     * Timer permettant de gerer le frame rate.
     */
    private Timer timer;

    /**
     * Constructeur qui initialise la fenetre ainsi que l'actualisation de cette derniere.
     * Affiche le menu de l'application.
     */
    public IsolaFrame() {
        setTitle("Isola");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        addKeyListener(this);

        menu = new MenuPanel(this);
        ShowMenu();

        setLocationRelativeTo(null);

        TimerTask task = new TimerTask(){
            public void run() {
                repaint();
            }
        };
        timer = new Timer();
        timer.scheduleAtFixedRate(task, 0, 1000/30);
    }


    /**
     * Permet d'afficher une partie dans l'application.
     * @param game L'instance de la partie a affichier.
     */
    public void ShowGame(Game game) {
        getContentPane().removeAll();
        getContentPane().add(new GamePanel(this, game));
        pack();

        AudioManager.INSTANCE.PlayerBackgroundMusic("game_theme");
    }
    /**
     * Permet d'afficher le menu de l"pplication.
     */
    public  void ShowMenu() {
        getContentPane().removeAll();
        getContentPane().add(menu);
        pack();

        AudioManager.INSTANCE.PlayerBackgroundMusic("menu_theme");
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    /**
     * Intercept les appuies sur une touche afin de gérér l'audio de l'application.
     * @param e L'event d'appuie sur une touche.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == 109) {
            AudioManager.INSTANCE.DecreaseVolume();
        }else if(e.getKeyCode() == 107) {
            AudioManager.INSTANCE.IncreaseVolume();
        }
    }
}
