package fr.isola.deepl;

import fr.isola.utils.Matrix;

import java.io.*;
import java.util.List;

/**
 * Classe représentant et gérant un réseau neuronal basique.
 */
public class NeuralNetwork {

    /**
     * Matices representant les points entres la layer d'input et l'hidden.
     */
    Matrix weights_ih;
    /**
     * Matices representant les points entres la layer hidden et l'output.
     */
    Matrix weights_ho;
    /**
     * Bias pour la layer hidden.
     */
    Matrix bias_h;
    /**
     * Bias pour la layer output.
     */
    Matrix bias_o;
    /**
     * Vitesse d'apprentissage du réseau.
     */
    double l_rate=0.01;

    /**
     * Nombre d'unit (neurone) dans la la layer input.
     */
    private  int nbInputs;
    /**
     * Nombre d'unit (neurone) dans la la layer hidden.
     */
    private int nbHiddens;
    /**
     * Nombre d'unit (neurone) dans la la layer ouptut.
     */
    private int nbOutputs;

    /**
     * Contructeur permetant d'initialiser un NeuralNetwork.
     * @param i Nombre d'unit (neurone) dans la la layer input.
     * @param h Nombre d'unit (neurone) dans la la layer hidden.
     * @param o Nombre d'unit (neurone) dans la la layer ouptut.
     */
    public NeuralNetwork(int i,int h,int o) {
        this.nbHiddens = h;
        this.nbInputs = i;
        this.nbOutputs = o;

        weights_ih = new Matrix(h,i);
        weights_ho = new Matrix(o,h);

        bias_h= new Matrix(h,1);
        bias_o= new Matrix(o,1);
    }

    /**
     * Fonction qui permet de faire une prédiction a partie de données.
     * @param X Une array de double contenant les données d'entrées pour la prédiction.
     * @return Une liste de double contant les valeurs de la layeur d'output apres la prédiction.
     */
    public List<Double> predict(double[] X) {
        Matrix input = Matrix.fromArray(X);
        Matrix hidden = Matrix.multiply(weights_ih, input);
        hidden.add(bias_h);
        hidden.sigmoid();

        Matrix output = Matrix.multiply(weights_ho,hidden);
        output.add(bias_o);
        output.sigmoid();

        return output.toArray();
    }


    /**
     * Fonction permettant de preparer et lancer l'entrainement du réseau.
     * @param X Une liste des données d'entrées.
     * @param Y Une liste des résultats attendues des données d'entrées.
     * @param epochs Nombre de d'iterations d'entrainement.
     */
    public void fit(double[][]X,double[][]Y,int epochs) {
        for(int i=0;i<epochs;i++) {
            System.out.println("Epoch : " + (i+1) + "/" + epochs);
            int sampleN =  (int)(Math.random() * X.length);
            this.train(X[sampleN], Y[sampleN]);
        }
    }

    /**
     * Fonction qui peremet d'effectuer une itération d'entrainement.
     * @param X Les données d'entrées.
     * @param Y Le résultat attendue.
     */
    public void train(double [] X,double [] Y) {
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

    /**
     * Permet de sauvegarder le réseau dans un fichier.
     * @param fileName Nom du fichier de sortie.
     */
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

    /**
     * Permet de charger un réseau sauvegardé dans un fichier.
     * @param fileName Nom du fichier contenant le réseau (doit être dans resources/models et terminer en .model).
     * @return Une instance du NeuralNetwork.
     */
    public static NeuralNetwork load(String fileName) {
        System.out.println("Start Model Loading ...");
        NeuralNetwork nn = null;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(NeuralNetwork.class.getResourceAsStream("/resources/models/" + fileName + ".model")));

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