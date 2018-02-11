package com.modscleo4.mathpp.lib.statistic;

/**
 * Created by modsc on 28/05/2017.
 */
public class Separatrix {
    public static int median(int n) {
         return n / 2;
    }

    public static int quartis(int a, int n) {
        return a * n / 4;
    }

    public static int decis(int a, int n) {
        return a * n / 10;
    }

    public static int percentis(int a, int n) {
        return a * n / 100;
    }

    public static float separatrix(String sep, float[] values, int a) {
        switch (sep) {
            case "median": {
                int pos = median(values.length);
                break;
            }
            case "quartis": {
                int pos = quartis(a, values.length);
                break;
            }
            case "decis": {
                int pos = decis(a, values.length);
                break;
            }
            case "percentis": {
                int pos = percentis(a, values.length);
                break;
            }
        }

        return 0;
    }
}
