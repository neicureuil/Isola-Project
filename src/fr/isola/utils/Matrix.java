package fr.isola.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe permettant de représenter une matrice et d'effectuer des calculs dessus.
 */
public class Matrix {

    /**
     * Liste des valeurs de la matrice.
     */
    double [][]data;
    /**
     * Nombe de lignes de la matrice.
     */
    int rows;
    /**
     * Nombre de colones de la matrice.
     */
    int cols;

    /**
     * Initialise une matrice avec des valeurs aléatoire.
     * @param rows Nombre de lignes.
     * @param cols Nombre de colones.
     */
    public Matrix(int rows,int cols) {
        data= new double[rows][cols];
        this.rows=rows;
        this.cols=cols;
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                data[i][j]=Math.random()*2-1;
            }
        }
    }

    /**
     * Permet d'additionner la matrice a un reel.
     * @param n Le nombre a ajouer.
     */
    public void add(double n) {
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                this.data[i][j]+=n;
            }

        }
    }

    /**
     * Permet de multiplier la matrice a une autre.
     * @param m La matrice a mutiplier.
     */
    public void add(Matrix m) {
        if(cols!=m.cols || rows!=m.rows) {
            System.out.println("Erreur de dimensions");
            return;
        }

        for(int i=0;i<rows;i++) {
            for(int j=0;j<cols;j++) {
                this.data[i][j]+=m.data[i][j];
            }
        }
    }

    /**
     * Genere une matrice a partir d'un tableau de double.
     * @param x Le tableau de double.
     * @return La matrice correspondante.
     */
    public static Matrix fromArray(double[]x) {
        Matrix temp = new Matrix(x.length,1);
        for(int i =0;i<x.length;i++)
            temp.data[i][0]=x[i];
        return temp;
    }

    /**
     * Convertie la matrice en List de double.
     * @return Une liste de double contenant chaque valeur de la matrice.
     */
    public List<Double> toArray() {
        List<Double> temp= new ArrayList<Double>()  ;

        for(int i=0;i<rows;i++) {
            for(int j=0;j<cols;j++) {
                temp.add(data[i][j]);
            }
        }
        return temp;
    }

    /**
     * Soustraire une matrice a une autre.
     * @param a Première matrice.
     * @param b Deuxième matrice.
     * @return Une matrice contenant le résultat de la soustraction.
     */
    public static Matrix subtract(Matrix a, Matrix b) {
        Matrix temp=new Matrix(a.rows,a.cols);
        for(int i=0;i<a.rows;i++) {
            for(int j=0;j<a.cols;j++) {
                temp.data[i][j]=a.data[i][j]-b.data[i][j];
            }
        }
        return temp;
    }

    /**
     * Permet de transposer une matrice.
     * @param a La matrice a transposer.
     * @return La matrice transposée.
     */
    public static Matrix transpose(Matrix a) {
        Matrix temp=new Matrix(a.cols,a.rows);
        for(int i=0;i<a.rows;i++)
        {
            for(int j=0;j<a.cols;j++)
            {
                temp.data[j][i]=a.data[i][j];
            }
        }
        return temp;
    }

    /**
     * Multiplie une matrice a une autre.
     * @param a Première matrice.
     * @param b Deuxième matrice.
     * @return Une matrice contenant le résultat de la multiplication.
     */
    public static Matrix multiply(Matrix a, Matrix b) {
        Matrix temp=new Matrix(a.rows,b.cols);
        for(int i=0;i<temp.rows;i++) {
            for(int j=0;j<temp.cols;j++) {
                double sum=0;
                for(int k=0;k<a.cols;k++)
                {
                    sum+=a.data[i][k]*b.data[k][j];
                }
                temp.data[i][j]=sum;
            }
        }
        return temp;
    }


    /**
     * Multiplie la matrice avec une de meme taille.
     * Ce n'est pas un calcul matriciel mais juste une fonction permettant de multiplier separement chaque valeurs.
     * @param a La matrice a multiplier.
     */
    public void multiply(Matrix a) {
        for(int i=0;i<a.rows;i++) {
            for(int j=0;j<a.cols;j++) {
                this.data[i][j]*=a.data[i][j];
            }
        }
    }

    /**
     * Multiplie la matrice avec un reel.
     * @param a La nombre a multiplier.
     */
    public void multiply(double a) {
        for(int i=0;i<rows;i++) {
            for(int j=0;j<cols;j++) {
                this.data[i][j]*=a;
            }
        }

    }

    /**
     * Applique la fonction sigmoid a chaque valeur de la matrice.
     */
    public void sigmoid() {
        for(int i=0;i<rows;i++)
            for(int j=0;j<cols;j++)
                this.data[i][j] = 1/(1+Math.exp(-this.data[i][j]));
    }
    /**
     * Applique la fonction dsigmoid (derivé de sigmoid) a chaque valeur de la matrice.
     */
    public Matrix dsigmoid() {
        Matrix temp=new Matrix(rows,cols);
        for(int i=0;i<rows;i++)
            for(int j=0;j<cols;j++)
                temp.data[i][j] = this.data[i][j] * (1-this.data[i][j]);
        return temp;
    }

    /**
     * @return Le nombre le de lignes.
     */
    public int getRows() {
        return rows;
    }

    /**
     * @return Le nombe de colones.
     */
    public int getCols() {
        return cols;
    }

    /**
     * @return Le tableau de valeurs de la matrice.
     */
    public double[][] getMatrix() {
        return data;
    }
}