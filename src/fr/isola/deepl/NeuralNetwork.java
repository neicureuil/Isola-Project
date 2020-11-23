package fr.isola.deepl;

import fr.isola.utils.MathUtils;
import fr.isola.utils.Matrix;

import java.io.*;
import java.util.List;
import java.util.Vector;

public class NeuralNetwork {

    Matrix weights_ih,weights_ho , bias_h,bias_o;
    double l_rate=0.01;

    private int nbInputs, nbHiddens, nbOutputs;

    public NeuralNetwork(int i,int h,int o) {
        this.nbHiddens = h;
        this.nbInputs = i;
        this.nbOutputs = o;

        weights_ih = new Matrix(h,i);
        weights_ho = new Matrix(o,h);

        bias_h= new Matrix(h,1);
        bias_o= new Matrix(o,1);
    }

    public List<Double> predict(double[] X)
    {
        Matrix input = Matrix.fromArray(X);
        Matrix hidden = Matrix.multiply(weights_ih, input);
        hidden.add(bias_h);
        hidden.sigmoid();

        Matrix output = Matrix.multiply(weights_ho,hidden);
        output.add(bias_o);
        output.sigmoid();

        return output.toArray();
    }


    public void fit(double[][]X,double[][]Y,int epochs)
    {
        for(int i=0;i<epochs;i++)
        {
            System.out.println("Epoch : " + (i+1) + "/" + epochs);
            int sampleN =  (int)(Math.random() * X.length );
            this.train(X[sampleN], Y[sampleN]);
        }
    }

    public void train(double [] X,double [] Y)
    {
        Matrix input = Matrix.fromArray(X);
        Matrix hidden = Matrix.multiply(weights_ih, input);
        hidden.add(bias_h);
        hidden.sigmoid();

        Matrix output = Matrix.multiply(weights_ho,hidden);
        output.add(bias_o);
        output.sigmoid();

        Matrix target = Matrix.fromArray(Y);

        Matrix error = Matrix.subtract(target, output);
        Matrix gradient = output.dsigmoid();
        gradient.multiply(error);
        gradient.multiply(l_rate);

        Matrix hidden_T = Matrix.transpose(hidden);
        Matrix who_delta =  Matrix.multiply(gradient, hidden_T);

        weights_ho.add(who_delta);
        bias_o.add(gradient);

        Matrix who_T = Matrix.transpose(weights_ho);
        Matrix hidden_errors = Matrix.multiply(who_T, error);

        Matrix h_gradient = hidden.dsigmoid();
        h_gradient.multiply(hidden_errors);
        h_gradient.multiply(l_rate);

        Matrix i_T = Matrix.transpose(input);
        Matrix wih_delta = Matrix.multiply(h_gradient, i_T);

        weights_ih.add(wih_delta);
        bias_h.add(h_gradient);
    }

    public void save(String fileName) {
        BufferedWriter fileWriter = null;
        try {
            fileWriter = new BufferedWriter(new FileWriter("./"+fileName));

            fileWriter.write(nbInputs + " " + nbHiddens + " " + nbOutputs);
            fileWriter.newLine();

            List<Double> w_ih_arr = this.weights_ih.toArray();
            for(double d : w_ih_arr) fileWriter.write(Double.toString(d) + " ");
            fileWriter.newLine();

            List<Double> bias_h_arr = this.bias_h.toArray();
            for(double d : bias_h_arr) fileWriter.write(Double.toString(d) + " ");
            fileWriter.newLine();

            List<Double> w_ho_arr = this.weights_ho.toArray();
            for(double d : w_ho_arr) fileWriter.write(Double.toString(d) + " ");
            fileWriter.newLine();

            List<Double> bias_o_arr = this.bias_o.toArray();
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
            for(int i=0; i<nn.weights_ih.getRows(); i++) {
                for (int j=0; j<nn.weights_ih.getCols(); j++) {
                    nn.weights_ih.getMatrix()[i][j] = Double.parseDouble(modelWihStr[counter]);
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
            for(int i=0; i<nn.weights_ho.getRows(); i++) {
                for (int j=0; j<nn.weights_ho.getCols(); j++) {
                    nn.weights_ho.getMatrix()[i][j] = Double.parseDouble(modelWhoStr[counter]);
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

}