package com.modscleo4.lib.matrix;

import static com.modscleo4.lib.matrix.Determinant.determinant;
import static com.modscleo4.lib.matrix.MinorsMatrix.minors;
import static com.modscleo4.readWrite.textReadWrite.dbgPrint;
import static java.lang.Math.pow;

/**
 * Created by modsc on 23/05/2017.
 */
public class Cofactor {
    public static float cofactor(int line, int column, int size, float[][] matrix) {
        int a = 0, b;
        float[][] matrix2 = minors(line, column, size, matrix);

        float det = determinant(matrix2);
        dbgPrint("det = " + det);
        dbgPrint("pow((-1), (" + line + " + " + column + ")) * " + det + " = " + pow((-1), (line + column)) * det);
        return (float)pow((-1), (line + column)) * det;
    }
}
