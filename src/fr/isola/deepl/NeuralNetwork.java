package fr.isola.deepl;

import fr.isola.utils.Matrix;

public class NeuralNetwork {

    private double learningRate;
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

        this.learningRate = 0.1;
    }

    public Matrix feedForward(double[] inputs) {
        return feedForward(Matrix.fromArray(inputs));
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

    public void train(double[] inputs, double[] targets) {
        train(Matrix.fromArray(inputs), Matrix.fromArray(targets));
    }

    public void train(Matrix inputs, Matrix targets) {
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

    }

    public void save() {

    }

    public void load() {

    }

}
