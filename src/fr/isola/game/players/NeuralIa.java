package fr.isola.game.players;

import fr.isola.deepl.NeuralNetwork;

import java.util.List;

/**
 * La classe qui apporte une IA de type réseau neuronal
 */
public class NeuralIa extends Player implements IaPlayer{

    /**
     * Le réseau qui gère le mouvement
     */
    private NeuralNetwork moveBrain;
    /**
     * Le réseau qui gère la destruction
     */
    private NeuralNetwork destroyBrain;
    /**
     * Taille x du terrain
     */
    private int sizeX;
    /**
     * Taille y du terrain
     */
    private int sizeY;

    /**
     * Le constructeur de la classe
     * @param sizeX Taille x du terrain
     * @param sizeY Taille y du terrain
     */
    public NeuralIa(int sizeX, int sizeY) {
        super();
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.moveBrain = NeuralNetwork.load("move_8_6");
        this.destroyBrain = NeuralNetwork.load("destroy_8_6");
    }

    /**
     * Gère le déplacement de l'IA
     * @param opponent le joueur adverse
     * @param map le terrain
     * @return le point où l'IA souhaite se déplacer
     */
    @Override
    public Point move(Player opponent, boolean[][] map) {
        double[] inputs = new double[52];

        inputs[0] = getX();
        inputs[1] = getY();
        inputs[2] = opponent.getX();
        inputs[3] = opponent.getY();

        int counter = 4;
        for (int i=0; i<8; i++) {
            for (int j=0; j<6; j++){
                if((getX() == i && getY() == j) || (opponent.getX() == i && opponent.getY() == j) || !map[i][j]){
                    inputs[counter] = -1;
                }else{
                    inputs[counter] = 1;
                }
                counter++;
            }
        }

        List<Double> pred = moveBrain.predict(inputs);
        int ptsId = getIndexOfLargest(pred);

        int pX = getX();
        int pY = getY();

        switch (ptsId) {
            case 0:
                pX--;
                pY--;
                break;
            case 1:
                pY--;
                break;
            case 2:
                pX++;
                pY--;
                break;
            case 3:
                pX--;
                break;
            case 4:
                pX++;
                break;
            case 5:
                pX--;
                pY++;
                break;
            case 6:
                pY++;
                break;
            case 7:
                pX++;
                pY++;
                break;
        }

        return new Point(pX, pY);
    }

    /**
     * Gère la destruction de case par l'IA
     * @param opponent le joueur adverse
     * @param map le terrain
     * @return le point que l'IA souhaite détruire
     */
    @Override
    public Point destroy(Player opponent, boolean[][] map) {
        double[] inputs = new double[52];

        inputs[0] = getX();
        inputs[1] = getY();
        inputs[2] = opponent.getX();
        inputs[3] = opponent.getY();

        int counter = 4;
        for (int i=0; i<8; i++) {
            for (int j=0; j<6; j++){
                if((getX() == i && getY() == j) || (opponent.getX() == i && opponent.getY() == j) || !map[i][j]){
                    inputs[counter] = -1;
                }else{
                    inputs[counter] = 1;
                }
                counter++;
            }
        }

        List<Double> pred = destroyBrain.predict(inputs);
        int ptsId = getIndexOfLargest(pred);

        int pX = ptsId%8;
        int pY = ptsId/8;

        return new Point(pX, pY);
    }

    /**
     * Fonction qui permet de récupérer l'index de la plus grande valeur d'une liste
     * @param array la liste
     * @return l'index
     */
    public int getIndexOfLargest( List<Double> array )
    {
        if ( array == null || array.size() == 0 ) return -1; // null or empty

        int largest = 0;
        for ( int i = 1; i < array.size(); i++ )
        {
            if ( array.get(i) > array.get(largest) ) largest = i;
        }
        return largest; // position of the first largest found
    }
}
