package fr.isola.deepl;

import fr.isola.utils.MathUtils;
import fr.isola.utils.Matrix;

public class Tester {

    public Tester() {
        NeuralNetwork nn = new NeuralNetwork(2, 4, 3);
        double[] inputs = { 1.23, 0.25};
        double[] outputs = {0, 0, 1};

        //nn.train(inputs, outputs);

    }

}
