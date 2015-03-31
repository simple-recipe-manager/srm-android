package org.bdawg.simplerecipemanager.utils;

/**
 * Created by breland on 1/30/2015.
 */
public class RationalUtil {

    public static String toRational(double d) {
        double tolerance = 1.0E-6;
        double h1 = 1;
        double h2 = 0;
        double k1 = 0;
        double k2 = 1;
        double b = d;
        do {
            double a = Math.floor(b);
            double aux = h1;
            h1 = a * h1 + h2;
            h2 = aux;
            aux = k1;
            k1 = a * k1 + k2;
            k2 = aux;
            b = 1 / (b - a);
        } while (Math.abs(d - h1 / k1) > d * tolerance);

        return (int)h1 + "/" + (int)k1;
    }
}

