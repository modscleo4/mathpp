package com.modscleo4.mathpp.lib.statistic;

import static com.modscleo4.mathpp.lib.statistic.Sigma.sigma;

/**
 * Created by modsc on 28/05/2017.
 */
public class Average {
    public static float average(float[] xi, float[] fi) {
        float[] values = new float[xi.length];
        for (int i = 0; i < xi.length; i++)
            values[i] = xi[i] * fi[i];
        return sigma(values) / values.length;
    }
}
