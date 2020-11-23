package fr.isola.deepl;

import java.util.List;
import java.util.Vector;

public class IaTrainer {

    public void TrainNeuralIaMovement(Dataset moveDatas) {

        Vector<double[]> datas = moveDatas.loadDatas();
        double[][] X = new double[datas.size()][52];
        double[][] Y = new double[datas.size()][8];

        for(int i=0; i<datas.size(); i++) {
            for(int j=0; j<8*6+4; j++) {
                X[i][j] = datas.elementAt(i)[j];
            }
            for(int j=0; j<8; j++) {
                Y[i][j] = (j == datas.elementAt(i)[datas.elementAt(i).length-1]) ? 1 : 0;
            }
        }

        NeuralNetwork nn = new NeuralNetwork(52,48,8);
        List<Double> output;

        nn.fit(X, Y, 5000);

        nn.save("move_8_6.model");
    }

    public void TrainNeuralIaDestroy(Dataset moveDatas) {

        Vector<double[]> datas = moveDatas.loadDatas();
        double[][] X = new double[datas.size()][52];
        double[][] Y = new double[datas.size()][48];

        int cc = 0;
        for(int i=0; i<datas.size(); i++) {
            for(int j=0; j<52; j++) {
                X[i][j] = datas.elementAt(i)[j];
            }
            int x = (int) datas.elementAt(i)[datas.elementAt(i).length-2];
            int y = (int) datas.elementAt(i)[datas.elementAt(i).length-1];
            for(int j=0; j<48; j++) {
                Y[i][j] = ( ((int)(j/8) == y && j%8 == x) ? 1 : 0 );
                if(Y[i][j] == 1) cc++;
            }
        }

        NeuralNetwork nn = new NeuralNetwork(52,104,48);
        List<Double> output;

        nn.fit(X, Y, 10000);

        List<Double> pred = nn.predict(X[0]);

        nn.save("destroy_8_6.model");
    }

}
