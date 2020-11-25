package fr.isola.deepl;

import java.util.List;
import java.util.Vector;

/**
 * Classe qui gère l'entrement des IA en DeepLearning.
 */
public class IaTrainer {

    /**
     * Fonction qui permet d'entrainer une réseau neuronal pour le déplacement.
     * Sauvegarde le résultat dans le fichier move_8_6.model.
     * @param moveDatas Dataset des données d'entrainement pour le déplacement.
     */
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

        NeuralNetwork nn = new NeuralNetwork(52,48*2,8);
        List<Double> output;

        nn.fit(X, Y, 100000);

        nn.save("move_8_6.model");
    }

    /**
     * Fonction qui permet d'entrainer une réseau neuronal pour la destruction de case.
     * Sauvegarde le résultat dans le fichier destroy_8_6.model.
     * @param destroyData Dataset des données d'entrainement pour le destruction de case.
     */
    public void TrainNeuralIaDestroy(Dataset destroyData) {

        Vector<double[]> datas = destroyData.loadDatas();
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

        NeuralNetwork nn = new NeuralNetwork(52,48*2,48);
        List<Double> output;

        nn.fit(X, Y, 100000);

        List<Double> pred = nn.predict(X[0]);

        nn.save("destroy_8_6.model");
    }

}
