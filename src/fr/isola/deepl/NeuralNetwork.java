package fr.isola.deepl;

import fr.isola.utils.MathUtils;
import fr.isola.utils.Matrix;

import java.io.*;
import java.util.Vector;

public class NeuralNetwork {

    private double learningRate = 0.1;
    private int nbInputs, nbHiddens, nbOutputs;

    private Matrix weight_ih, weight_ho;
    private Matrix bias_h, bias_o;

    public NeuralNetwork(int nbInputs, int nbHiddens, int nbOutputs) {
        this.nbHiddens = nbHiddens;
        this.nbInputs = nbInputs;
        this.nbOutputs = nbOutputs;

        this.weight_ih = new Matrix(nbHiddens, nbInputs);
        this.weight_ho = new Matrix(nbOutputs, nbHiddens);
        this.weight_ih.randomize();
        this.weight_ho.randomize();

        this.bias_h = new Matrix(nbHiddens, 1);
        this.bias_o = new Matrix(nbOutputs,1);
        this.bias_h.randomize();
        this.bias_o.randomize();
    }

    public double[] predict(double[] inputs) {
        return feedForward(Matrix.fromArray(inputs)).toArray();
    }

    public Matrix feedForward(Matrix inputs) {
        Matrix hidden = Matrix.multiply(this.weight_ih, inputs);
        hidden.add(this.bias_h);
        hidden.map(ActivationFunc::sigmoid);

        Matrix outputs = Matrix.multiply(this.weight_ho, hidden);
        outputs.add(this.bias_o);
        outputs.map(ActivationFunc::sigmoid);

        return outputs;
    }

    public void train(int epochs, int batchSize, Vector<double[]> inputs, Vector<double[]> targets, boolean randomize) {
        for(int i=0; i<epochs; i++) {
            System.out.println("NeuralNetwork Training : " + (i+1) + "/" +epochs);
            for(int j=0; j<inputs.size(); j++) {
                int id = j;
                if(randomize) {
                    id = MathUtils.random(0, inputs.size()-1);
                }
                double err = train(Matrix.fromArray(inputs.elementAt(id)), Matrix.fromArray(targets.elementAt(id)));
                System.out.println("Loss : " + err);
            }
        }
    }

    public double train(Matrix inputs, Matrix targets) {
        Matrix hidden = Matrix.multiply(this.weight_ih, inputs);
        hidden.add(this.bias_h);
        hidden.map(ActivationFunc::sigmoid);

        Matrix outputs = Matrix.multiply(this.weight_ho, hidden);
        outputs.add(this.bias_o);
        outputs.map(ActivationFunc::sigmoid);

        // Calcul de l'erreur de outputs
        Matrix outputs_err = Matrix.subtract(targets, outputs);

        // Calcul de l'erreur de hidden
        Matrix weight_ho_t = Matrix.transpose(this.weight_ho);
        Matrix hidden_err = Matrix.multiply(weight_ho_t, outputs_err);

        // Calcul du gradient pour outputs
        Matrix gradients = Matrix.map(outputs, ActivationFunc::dsigmoid);
        gradients.multiply(outputs_err);
        gradients.multiply(this.learningRate);

        // Calcul des deltas  pour weight_ho
        Matrix hidden_t = Matrix.transpose(hidden);
        Matrix weight_ho_deltas = Matrix.multiply(gradients, hidden_t);
        this.weight_ho.add(weight_ho_deltas);
        this.bias_o.add(gradients);

        // Calcul du gradient pour hidden
        Matrix hidden_gradient = Matrix.map(hidden, ActivationFunc::dsigmoid);
        hidden_gradient.multiply(hidden_err);
        hidden_gradient.multiply(this.learningRate);

        // Calcul des deltas  pour weight_ih
        Matrix inputs_t = Matrix.transpose(inputs);
        Matrix weight_ih_deltas = Matrix.multiply(hidden_gradient, inputs_t);
        this.weight_ih.add(weight_ih_deltas);
        this.bias_h.add(hidden_gradient);

        // Calcul de l'erreur global => Methode MeanSquareError;
        double mse = 0;
        double[] errorArr = outputs_err.toArray();
        for(int i=0; i<errorArr.length; i++) {
            mse += Math.pow(errorArr[i], 2);
        }
        mse = 1.0 / errorArr.length * mse;
        return mse;
    }

    public void save(String fileName) {
        BufferedWriter fileWriter = null;
        try {
            fileWriter = new BufferedWriter(new FileWriter("./"+fileName));

            fileWriter.write(nbInputs + " " + nbHiddens + " " + nbOutputs);
            fileWriter.newLine();

            double[] w_ih_arr = this.weight_ih.toArray();
            for(double d : w_ih_arr) fileWriter.write(Double.toString(d) + " ");
            fileWriter.newLine();

            double[] bias_h_arr = this.bias_h.toArray();
            for(double d : bias_h_arr) fileWriter.write(Double.toString(d) + " ");
            fileWriter.newLine();

            double[] w_ho_arr = this.weight_ho.toArray();
            for(double d : w_ho_arr) fileWriter.write(Double.toString(d) + " ");
            fileWriter.newLine();

            double[] bias_o_arr = this.bias_o.toArray();
            for(double d : bias_o_arr) fileWriter.write(Double.toString(d) + " ");

            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static NeuralNetwork load(String fileName) {
        System.out.println("Start Model Loading ...");
        NeuralNetwork nn = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader("./"+fileName));

            // LOAD MODEL SIZE
            String[] modelDataStr = br.readLine().split(" ");
            nn = new NeuralNetwork(Integer.parseInt(modelDataStr[0]), Integer.parseInt(modelDataStr[1]), Integer.parseInt(modelDataStr[2]));

            // LOAD MODEL INPUT_HIDDEN WEIGHT
            String[] modelWihStr = br.readLine().split(" ");
            int counter = 0;
            for(int i=0; i<nn.weight_ih.getRows(); i++) {
                for (int j=0; j<nn.weight_ih.getCols(); j++) {
                    nn.weight_ih.getMatrix()[i][j] = Double.parseDouble(modelWihStr[counter]);
                    counter++;
                }
            }

            // LOAD MODEL HIDDEN BIAS
            String[] modelBiasHStr = br.readLine().split(" ");
            for(int i=0; i<nn.bias_h.getRows(); i++) {
                nn.bias_h.getMatrix()[i][0] = Double.parseDouble(modelBiasHStr[i]);
            }

            // LOAD MODEL HIDDEN_OUTPUT WEIGHT
            String[] modelWhoStr = br.readLine().split(" ");
            counter = 0;
            for(int i=0; i<nn.weight_ho.getRows(); i++) {
                for (int j=0; j<nn.weight_ho.getCols(); j++) {
                    nn.weight_ho.getMatrix()[i][j] = Double.parseDouble(modelWhoStr[counter]);
                    counter++;
                }
            }

            // LOAD MODEL OUTPUT BIAS
            String[] modelBiasOStr = br.readLine().split(" ");
            for(int i=0; i<nn.bias_o.getRows(); i++) {
               nn.bias_o.getMatrix()[i][0] = Double.parseDouble(modelBiasOStr[i]);
            }

            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Model Loaded !");
        return nn;
    }

    public int getInputSize() {
        return this.nbInputs;
    }

    public int getOutputSize() {
        return this.nbOutputs;
    }

}
