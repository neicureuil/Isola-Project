package fr.isola.game.players;

import fr.isola.deepl.NeuralNetwork;

import java.util.List;
import java.util.Vector;

public class NeuralIa extends Player implements IaPlayer{

    private NeuralNetwork moveBrain;
    private int sizeX, sizeY;

    public NeuralIa(int sizeX, int sizeY) {
        super();
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.moveBrain = NeuralNetwork.load("model_move.model");
    }

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

        System.out.println(inputs.length);

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

    @Override
    public Point destroy(Player opponent, boolean[][] map) {
        return new Point(0,0);
    }

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
