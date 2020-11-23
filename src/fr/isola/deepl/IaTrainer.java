package fr.isola.deepl;

import java.util.List;
import java.util.Vector;

public class IaTrainer {

    /*
    public void TrainNeuralIaMovement(Dataset moveDatas, int sizeX, int sizeY) {
        NeuralNetwork moveNN = new NeuralNetwork(sizeX*sizeY + 4, 52, 8);
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

        moveNN.train(500, 2, inputs, targets, true);
        moveNN.save("move_"+sizeX+"_"+sizeY+".model");
    }
    */

    public void TrainNeuralIaMovement(Dataset moveDatas, int sizeX, int sizeY) {

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

        NeuralNetwork nn = new NeuralNetwork(52,128,8);
        List<Double> output;

        nn.fit(X, Y, 50000);

        output = nn.predict(X[1]);
        System.out.println(output.get(0).toString());
        System.out.println(output.get(1).toString());
        System.out.println(output.get(2).toString());
        System.out.println(output.get(3).toString());
        System.out.println(output.get(4).toString());
        System.out.println(output.get(5).toString());
        System.out.println(output.get(6).toString());
        System.out.println(output.get(7).toString());

        nn.save("model_move.model");
    }

}
