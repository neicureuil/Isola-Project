package fr.isola.deepl;

import java.util.Vector;

public class IaTrainer {

    public void TrainNeuralIaMovement(Dataset moveDatas, int sizeX, int sizeY) {
        NeuralNetwork moveNN = new NeuralNetwork(sizeX*sizeY + 4, 48, 8);
        Vector<double[]> datas = moveDatas.loadDatas();

        Vector<double[]> inputs = new Vector<double[]>();
        Vector<double[]> targets = new Vector<double[]>();

        for(int i=0; i<datas.size(); i++) {
            double[] inputsLine = new double[moveNN.getInputSize()];
            double[] outputLine = new double[moveNN.getOutputSize()];

            for(int j=0; j<moveNN.getInputSize(); j++) {
                inputsLine[j] = datas.elementAt(i)[j];
            }
            for(int j=0; j< moveNN.getOutputSize(); j++) {
                outputLine[j] = (j == datas.elementAt(i)[datas.elementAt(i).length-1]) ? 1 : 0;
            }

            inputs.add(inputsLine);
            targets.add(outputLine);
        }

        moveNN.train(1000, 100, inputs, targets, true);
        moveNN.save("move_"+sizeX+"_"+sizeY+".model");
    }

}
