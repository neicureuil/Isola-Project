package fr.isola.ui.panels;

import fr.isola.audio.AudioManager;
import fr.isola.game.Game;
import fr.isola.game.players.Player;
import fr.isola.ui.IsolaFrame;
import fr.isola.ui.sprites.ImageUtils;
import fr.isola.ui.sprites.SpriteSheet;
import fr.isola.ui.sprites.TileMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel  {

    private IsolaFrame mainPanel;
    private Game game;
    private GameInfoPanel infos;
    private GameRenderPanel renderer;
    private ResultPanel results;

    private Game.GameState tmpState;

    public GamePanel(IsolaFrame mainPanel, Game game) {
        this.game = game;
        this.mainPanel = mainPanel;
        this.infos = new GameInfoPanel();
        this.renderer = new GameRenderPanel(game);
        this.results = new ResultPanel();

        this.tmpState = game.getState();
        UpdateTitleText();

        setBackground(new Color(6,66,115) );

        setLayout(new BorderLayout());

        add(infos, BorderLayout.NORTH);
        add(renderer, BorderLayout.CENTER);

        this.infos.getCloseButton().addActionListener(this::ReturnToMenu);
        this.results.getConfirmButton().addActionListener(this::ReturnToMenu);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(game.getState() != tmpState) {
            tmpState = game.getState();
            if(game.isFinished()) {
                ShowResults();
            }else {
                UpdateTitleText();
            }
        }
    }

    public void UpdateTitleText() {
        String txt = "Joueur " + game.getActivePlayerId() + " : ";
        if(game.getState() == Game.GameState.MOVE) {
            txt += "Move";
        }else if(game.getState() == Game.GameState.DESTROY) {
            txt += "Destroy";
        }
        infos.setInfoText(txt);
    }

    public void ShowResults() {
        infos.setInfoText("Resultat");

        if(game.getState() == Game.GameState.P1LOST) {
            results.SetText("Victoire du joueur 2");
            AudioManager.INSTANCE.PlayVictorySound(game.getConfig().getP2().getSprite() + "");
        }else if(game.getState() == Game.GameState.P2LOST) {
            results.SetText("Victoire du joueur 1");
            AudioManager.INSTANCE.PlayVictorySound(game.getConfig().getP1().getSprite() + "");
        }

        remove(renderer);
        add(results);
    }

    public void ReturnToMenu(ActionEvent e) {
        mainPanel.ShowMenu();
    }


}

