package com.modscleo4.lib.matrix;

import static com.modscleo4.readWrite.textReadWrite.dbgPrint;

/**
 * Created by modsc on 24/05/2017.
 */
public class MinorsMatrix {
    public static float[][] minors(int line, int column, int size, float[][] matrix) {
        int a = 0, b;
        float[][] matrix2 = new float[size - 1][size - 1];
        for (int i = 0; i < size; i++) {
            dbgPrint("i = " + i);
            if (i != line) {
                dbgPrint("true");
                b = 0;
                for (int j = 0; j < size; j++) {
                    dbgPrint("j = " + j);
                    if (j != column) {
                        dbgPrint("true");
                        matrix2[a][b] = matrix[i][j];
                        b++;
                    }
                }
                a++;
            }
        }
        return matrix2;
    }
}
