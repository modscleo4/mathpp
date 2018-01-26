package com.modscleo4.lib.matrix;

import static com.modscleo4.readWrite.textReadWrite.*;

/**
 *
 * @author modscleo4
 */
public class Determinant {

    public static float determinant(float[][] matrix) { //method sig. takes a matrix (two dimensional array), returns determinant.
        int sum=0;
        int s;
        if (matrix.length==1) {  //bottom case of recursion. size 1 matrix determinant is itself.
            return(matrix[0][0]);
        }
        for (int i = 0; i < matrix.length; i++) { //finds determinant using row-by-row expansion
            dbgPrint("i = " + i);
            float[][]smaller = new float[matrix.length - 1][matrix.length - 1]; //creates smaller matrix- values not in same row, column
            for (int a = 1; a < matrix.length; a++) {
                dbgPrint("a = " + a);
                for (int b = 0; b < matrix.length; b++) {
                    dbgPrint("b = " + b);
                    if (b < i) {
                        smaller[a - 1][b] = matrix[a][b];
                        dbgPrint("smaller[" + (a - 1) + "][" + b + "] = " + smaller[a - 1][b]);
                    } else if (b > i) {
                        smaller[a-1][b-1] = matrix[a][b];
                        dbgPrint("smaller[" + (a - 1) + "][" + (b - 1) + "] = " + smaller[a - 1][b - 1]);
                    }
                }
            }
            printc("[0][" + i + "]: " + matrix[0][i] + "{");
            printc("Submatrix: {");
            matrixPrint(smaller, 1);
            printc("}");
            if (i % 2 == 0) { //sign changes based on i
                s = 1;
            } else {
               s=-1;
            }
            printc(s + " * " + matrix[0][i] + " * " + determinant(smaller) + " = " + (s*matrix[0][i]*(determinant(smaller))));
            sum += s*matrix[0][i]*(determinant(smaller));
            printc("determinant = " + sum + "\n}");
            dbgPrint("sum = " + sum);
        }
        return (sum); //returns determinant value. once stack is finished, returns final determinant.
      }

    public static float mDet(float values[][]) {
        float det;
        det = determinant(values);
        return det;
    }

}
