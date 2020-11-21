package fr.isola.ui;

import fr.isola.audio.AudioManager;
import fr.isola.game.Game;
import fr.isola.ui.panels.GamePanel;
import fr.isola.ui.panels.MenuPanel;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class IsolaFrame extends JFrame {

    private MenuPanel menu;
    private Timer timer;

    public IsolaFrame() {
        setTitle("Isola");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

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

    public void ShowGame(Game game) {
        getContentPane().removeAll();
        getContentPane().add(new GamePanel(this, game));
        pack();
    }

    public  void ShowMenu() {
        getContentPane().removeAll();
        getContentPane().add(menu);
        pack();

        AudioManager.INSTANCE.PlayerBackgroundMusic("menu_theme");
        AudioManager.INSTANCE.SetBackgroundMusicVolume(90f);
    }
}
