package fr.isola.utils;

import java.util.Random;

/**
 * Classe contenant des fonction de maths utiles.
 */
public class MathUtils {

    /**
     * Genere un nombre float aléatoire entre 2 bornes.
     * @param min Borne inférieur.
     * @param max Borne supérieur.
     * @return Le nombre alétaoire généré.
     */
    public static float random(float min, float max) {
        Random r = new Random();
        return (min + r.nextFloat() * (max - min));
    }

    /**
     * Genere un nombre double aléatoire entre 2 bornes.
     * @param min Borne inférieur.
     * @param max Borne supérieur.
     * @return Le nombre alétaoire généré.
     */
    public static double random(double min, double max) {
        Random r = new Random();
        return (min + r.nextDouble() * (max - min));
    }

    /**
     * Genere un nombre int aléatoire entre 2 bornes.
     * @param min Borne inférieur.
     * @param max Borne supérieur.
     * @return Le nombre alétaoire généré.
     */
    public static int random(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    /**
     * Permet de calculer l'invese d'un nombre.
     * @param n Le nombre a inverser.
     * @return L'invese du nombre.
     */
    public static double invert(double n) {
        if(n == 0) return n;
        return 1/n;
    }


}
