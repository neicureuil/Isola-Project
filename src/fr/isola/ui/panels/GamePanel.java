package fr.isola.ui.panels;

import fr.isola.audio.AudioManager;
import fr.isola.game.Game;
import fr.isola.ui.IsolaFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Panel principal du jeu.
 */
public class GamePanel extends JPanel  {

    /**
     * Reference de la fenetre principale.
     */
    private IsolaFrame mainPanel;
    /**
     * Instance du jeu a afficher.
     */
    private Game game;
    /**
     * Panneau supérieur d'information.
     */
    private GameInfoPanel infos;
    /**
     * Panneau de rendu du jeu.
     */
    private GameRenderPanel renderer;
    /**
     * Panneau d'affichage des resultat de la partie.
     */
    private ResultPanel results;

    /**
     * Etat temporaire du jeu affiché.
     */
    private Game.GameState tmpState;

    /**
     * Constructeur qui initialise les composants.
     * @param mainPanel Fenetre principale.
     * @param game Instance du jeu a afficher.
     */
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

    /**
     * Rendu des composants.
     * @param g Graphisme
     */
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

    /**
     * Mise a jours du texte d'information de l'etat de la partie.
     */
    public void UpdateTitleText() {
        String txt = "Joueur " + game.getActivePlayerId() + " : ";
        if(game.getState() == Game.GameState.MOVE) {
            txt += "Move";
        }else if(game.getState() == Game.GameState.DESTROY) {
            txt += "Destroy";
        }
        infos.setInfoText(txt);
    }

    /**
     * Affiche les resultats de la partie.
     */
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

    /**
     * Demande un retours au menu principal.
     * @param e L'evenement declancheur.
     */
    public void ReturnToMenu(ActionEvent e) {
        mainPanel.ShowMenu();
    }


}

