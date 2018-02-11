package com.modscleo4.mathpp.lib.statistic;

/**
 * Created by modsc on 28/05/2017.
 */
public class Sigma {
    public static float sigma(float[] values) {
        float sum = 0;
        for (int i = 0; i < values.length; i++)
            sum += values[i];
        return sum;
    }

    public static float[] fc(float[] values) {
        float[] sum = new float[values.length];
        int s = 0;
        for (int i = 0; i < sum.length; i++) {
            s += values[i];
            sum[i] = s;
        }
        return sum;
    }
}
