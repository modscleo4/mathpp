package com.modscleo4.lib.matrix;

import static com.modscleo4.readWrite.textReadWrite.dbgPrint;

/**
 * Created by modsc on 19/05/2017.
 */
public class Transpose {
    public static float[][] transpose(int rows, int columns, float matrix[][]) {
       float[][] transp = new float[columns][rows];
       for (int i = 0; i < rows; i++) {
           dbgPrint("i = " + i);
           for (int j = 0; j < columns; j++) {
               dbgPrint("j = " + j);
               transp[j][i] = matrix[i][j];
               dbgPrint("transp[" + j + "][" + i + "] = " + transp[j][i]);
           }
       }
       return transp;
    }
}
