package fr.isola.utils;

import java.util.Random;

public class MathUtils {

    public static float random(float min, float max) {
        Random r = new Random();
        return (min + r.nextFloat() * (max - min));
    }

    public static double random(double min, double max) {
        Random r = new Random();
        return (min + r.nextDouble() * (max - min));
    }

    public static int random(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }


}
