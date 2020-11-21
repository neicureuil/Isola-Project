package fr.isola.deepl;

public class ActivationFunc {

    public static double sigmoid(double x) {
        return 1 / (1 + Math.exp(-x));
    }
    public static double dsigmoid(double x) {
        return x * (1-x);
    }
}
