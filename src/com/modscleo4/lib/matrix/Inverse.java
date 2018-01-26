package com.modscleo4.lib.matrix;

import static com.modscleo4.lib.matrix.Cofactor.cofactor;
import static com.modscleo4.lib.matrix.Determinant.determinant;
import static com.modscleo4.lib.matrix.Transpose.transpose;
import static com.modscleo4.readWrite.textReadWrite.dbgPrint;

/**
 * Created by modsc on 20/05/2017.
 */
public class Inverse {
    public static float[][] inverse(int size, float[][] matrix) {
        float[][] matrix2 = new float[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix2[i][j] = cofactor(i, j, size, matrix);
                dbgPrint("matrix2[" + i + "][" + j + "] = " + matrix2[i][j]);
            }
        }
        matrix2 = transpose(size, size, matrix2);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix2[i][j] = (1/determinant(matrix)) * matrix2[i][j];
                dbgPrint("matrix2[" + i + "][" + j + "] = " + matrix2[i][j]);
            }
        }
        return matrix2;
    }
}