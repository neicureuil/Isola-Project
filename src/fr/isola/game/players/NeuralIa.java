package fr.isola.game.players;

import fr.isola.deepl.NeuralNetwork;

public class NeuralIa extends Player implements IaPlayer{

    private NeuralNetwork moveBrain;
    private int sizeX, sizeY;

    public NeuralIa(int sizeX, int sizeY) {
        super();
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.moveBrain = NeuralNetwork.load("move_"+sizeX+"_"+sizeY+".model");
    }

    @Override
    public Point move(Player opponent, boolean[][] map) {
        double[] inputs = new double[sizeX*sizeY+4];
        inputs[0] = getX();
        inputs[1] = getY();
        inputs[2] = opponent.getX();
        inputs[3] = opponent.getY();

        int counter = 4;
        for (int i=0; i<sizeX; i++) {
            for (int j=0; j<sizeY; j++){
                if((getX() == i && getY() == j) || (opponent.getX() == i && opponent.getY() == j) || !map[i][j]){
                    inputs[counter] = -1;
                }else{
                    inputs[counter] = 1;
                }
                counter++;
            }
        }

        int ptsId = getIndexOfLargest(moveBrain.predict(inputs));

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

    public int getIndexOfLargest( double[] array )
    {
        if ( array == null || array.length == 0 ) return -1; // null or empty

        int largest = 0;
        for ( int i = 1; i < array.length; i++ )
        {
            if ( array[i] > array[largest] ) largest = i;
        }
        return largest; // position of the first largest found
    }
}
