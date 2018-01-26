package com.modscleo4.lib.matrix;

import static com.modscleo4.readWrite.textReadWrite.dbgPrint;

/**
 * Created by modscleo4 on 19/05/2017.
 */
public class MoreorLess {
    public static float[][] sum(int rows, int columns, float matrix1[][], float matrix2[][]) {// Sum matrices
        float[][] summed = new float[rows][columns];
        for (int i = 0; i < rows; i++) {
            dbgPrint("i = " + i);
            for (int j = 0; j < columns; j++) {
                dbgPrint("i = " + j);
                summed[i][j] = matrix1[i][j] + matrix2[i][j];
                dbgPrint("summed[" + i + "][" + j + "] = " + summed[i][j]);
            }
        }
        return summed;
    }
    public static float[][] minus(int rows, int columns, float matrix1[][], float matrix2[][]) {// Subtract matrices
        float[][] subtracted = new float[rows][columns];
        for (int i = 0; i < rows; i++) {
            dbgPrint("i = " + i);
            for (int j = 0; j < columns; j++) {
                dbgPrint("i = " + j);
                subtracted[i][j] = matrix1[i][j] - matrix2[i][j];
                dbgPrint("subtracted[" + i + "][" + j + "] = " + subtracted[i][j]);
            }
        }
        return subtracted;
    }
}
