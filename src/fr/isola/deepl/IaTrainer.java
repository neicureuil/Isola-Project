package fr.isola.deepl;

import java.util.Vector;

public class IaTrainer {

    public void TrainNeuralIaMovement(Dataset moveDatas, int sizeX, int sizeY) {
        NeuralNetwork moveNN = new NeuralNetwork(sizeX*sizeY + 4, 64, 8);
        Vector<double[]> datas = moveDatas.loadDatas();

    }

}
