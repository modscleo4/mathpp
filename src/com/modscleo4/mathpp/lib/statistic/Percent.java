package com.modscleo4.mathpp.lib.statistic;

import static com.modscleo4.mathpp.lib.statistic.Sigma.fc;
import static com.modscleo4.mathpp.lib.statistic.Sigma.sigma;

/**
 * Created by modsc on 08/06/2017.
 */
public class Percent {
    public static float[] fri(float[] values) {
        float[] frip = new float[values.length];
        int n = (int)sigma(values);
        for (int i = 0; i < values.length; i++) {
            float p = values[i] * n / 100;
            frip[i] = p;
        }
        return frip;
    }

    public static float[] facp(float[] values) {
        return fc(fri(values));
    }
}
