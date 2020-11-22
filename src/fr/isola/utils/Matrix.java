package fr.isola.utils;

import java.util.function.Function;

public class Matrix {

    int rows, cols;
    double[][] matrix;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        this.matrix = new double[rows][cols];
        for(double[] d : matrix) for(double d2 : d)  d2 = 0;
    }

    public Matrix add(double n) {
        for(int i=0; i<rows; i++)
            for(int j=0; j<cols; j++)
                matrix[i][j] += n;
        return this;
    }

    public Matrix add(Matrix n) {
        for(int i=0; i<rows; i++)
            for(int j=0; j<cols; j++)
                matrix[i][j] += n.matrix[i][j];
        return this;
    }

    public static Matrix add(Matrix a, double n) {
        Matrix result = new Matrix(a.rows, a.cols);
        for(int i=0; i<result.rows; i++)
            for(int j=0; j<result.cols; j++)
                result.matrix[i][j] = a.matrix[i][j] + n;
        return result;
    }

    public static Matrix subtract(Matrix a, Matrix b) {
        Matrix result = new Matrix(a.rows, a.cols);
        for(int i=0; i<result.rows; i++)
            for(int j=0; j<result.cols; j++)
                result.matrix[i][j] = a.matrix[i][j] - b.matrix[i][j];
        return result;
    }

    public Matrix multiply(double n) {
        for(int i=0; i<rows; i++)
            for(int j=0; j<cols; j++)
                matrix[i][j] *= n;
        return this;
    }

    public Matrix multiply(Matrix b) {
        if(cols != b.rows) {
            return null;
        }
        Matrix result = new Matrix(rows, b.cols);
        for(int i=0; i<result.rows; i++) {
            for (int j=0; j<result.cols; j++) {

                double sum = 0;
                for(int k=0; k<cols; k++) {
                    sum += matrix[i][k] * b.matrix[k][j];
                }
                result.matrix[i][j] = sum;

            }
        }
        return this;
    }

    public static Matrix multiply(Matrix a, Matrix b) {
        if(a.cols != b.rows) {
            System.out.println("[WARNING] Return new null Matrix");
            return null;
        }
        Matrix result = new Matrix(a.rows, b.cols);
        for(int i=0; i<result.rows; i++) {
            for (int j=0; j<result.cols; j++) {

                double sum = 0;
                for(int k=0; k<a.cols; k++) {
                    sum += a.matrix[i][k] * b.matrix[k][j];
                }
                result.matrix[i][j] = sum;

            }
        }
        return result;
    }

    public Matrix divideValues(double n) {
        for(int i=0; i<rows; i++)
            for(int j=0; j<cols; j++)
                matrix[i][j] /= n;
        return this;
    }

    public static Matrix transpose(Matrix a) {
        Matrix result = new Matrix(a.cols, a.rows);

        for(int i=0; i<a.rows; i++)
            for(int j=0; j<a.cols; j++)
                result.matrix[j][i] = a.matrix[i][j];

        return result;
    }

    public void randomize(int min, int max) {
        for(int i=0; i<rows; i++)
            for(int j=0; j<cols; j++)
                matrix[i][j] = MathUtils.random(min, max);
    }

    public void randomize() {
        randomize(-1,1);
    }

    public void map(Function<Double, Double> fn) {
        for(int i=0; i<rows; i++)
            for(int j=0; j<cols; j++)
                matrix[i][j] = fn.apply(matrix[i][j]);
    }

    public static Matrix map(Matrix a, Function<Double, Double> fn) {
        Matrix result = new Matrix(a.rows, a.cols);
        for(int i=0; i<result.rows; i++)
            for(int j=0; j<result.cols; j++)
                result.matrix[i][j] = fn.apply(a.matrix[i][j]);
        return result;
    }

    public static Matrix fromArray(double[] arr) {
        Matrix result = new Matrix(arr.length, 1);
        for(int i=0; i<arr.length; i++)
            result.matrix[i][0] = arr[i];
        return result;
    }

    public double[] toArray() {
        double[] result = new double[cols*rows];
        for(int i=0; i<rows; i++)
            for(int j=0; j<cols; j++)
                result[j*rows + i] = matrix[i][j];
        return result;
    }

    public double getMax() {
        double m = matrix[0][0];
        for(int i=0; i<rows; i++)
            for(int j=0; j<cols; j++)
                if(matrix[i][j] > m) m=matrix[i][j];
        return m;
    }

    public double getSum() {
        double m = 0;
        for(int i=0; i<rows; i++)
            for(int j=0; j<cols; j++)
                m += matrix[i][j];
        return m;
    }

    public Matrix copy() {
        Matrix clone = new Matrix(rows, cols);
        for(int i=0; i<rows; i++)
            for(int j=0; j<cols; j++)
                clone.matrix[i][j] = matrix[i][j];
        return clone;
    }

    public double at(int i, int j) {
        return matrix[i][j];
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }
}
