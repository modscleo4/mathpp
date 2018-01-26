package com.modscleo4.lib.matrix;

import static com.modscleo4.readWrite.textReadWrite.*;

/**
 * Created by modsc on 18/05/2017.
 */
public class Multiply {
    public static float[][] multiply(int ln2, int col1, int cols, int lines, float[][] matrix1, float[][] matrix2) {
        float[][] result = new float[lines][cols];
        float sum = 0;
        int k = 0, m = 0;
        for (int i = 0; i < lines; i++) {
            dbgPrint("i = " + i);
            for (int j = 0; j < cols; j++) {
                dbgPrint("j = " + j);
                for (int l = 0; l < col1; l++) {
                    dbgPrint("l = " + l);
                    float a = matrix1[k][l] * matrix2[l][m];
                    dbgPrint("a = " + a);
                    sum += a;
                    dbgPrint("sum = " + sum);
                }
                result[i][j] = sum;
                dbgPrint("result[" + i + "][" + j + "] = " + result[i][j]);
                sum = 0;
                if (m < (cols - 1)) {
                    m++;
                }
            }
            m = 0;
            if (k < (lines - 1)) {
                k++;
            }
        }
        return result;
    }
}
