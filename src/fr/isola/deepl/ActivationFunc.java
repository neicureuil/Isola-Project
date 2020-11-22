package fr.isola.deepl;

import fr.isola.utils.MathUtils;
import fr.isola.utils.Matrix;

public class ActivationFunc {

    public static double sigmoid(double x) {
        return 1 / (1 + Math.exp(-x));
    }
    public static double dsigmoid(double x) {
        return x * (1-x);
    }

    public static Matrix softmax(Matrix m) {
        Matrix shiftX = Matrix.add(m, -m.getMax());
        shiftX.map(MathUtils::exp);
        shiftX.divideValues(shiftX.getSum());
        return shiftX;
    }

    public static Matrix dsoftmax(Matrix m) {
        Matrix deriv = m.copy();
        deriv.map(MathUtils::exp);
        double sum = deriv.getSum();
        deriv.map(MathUtils::invert);
        deriv.multiply(sum);
        return  deriv;
    }

    public static double relu(double x) {
        if(x < 0) return 0;
        else  return x;
    }

    public static double drelu(double x) {
        if(x < 0) return 0;
        else  return 1;
    }

}
