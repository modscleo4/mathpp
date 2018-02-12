package com.modscleo4.mathpp.lib.matrix;

import com.sun.istack.internal.NotNull;

import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.pow;

/**
 * Matrix.java
 * Purpose: Provides a Matrix class with common operations
 *
 * @author Dhiego Cassiano Fogaça Barbosa
 * @version 1.0
 */

public class Matrix {
    public int height, width;
    private double[][] matrix;

    /**
     * Constructor method (empty Matrix mode)
     *
     * @param h Height of the new Matrix
     * @param w Width of the new Matrix
     */
    public Matrix(@NotNull int h, @NotNull int w) {
        if (h < 1) {
            throw new InvalidMatrixException("Height minor than 1");
        }

        if (w < 1) {
            throw new InvalidMatrixException("Width minor than 1");
        }

        height = h;
        width = w;

        matrix = new double[height][width];
    }

    /**
     * Constructor method (assignment mode)
     *
     * @param arr double[][] array with the Matrix values (Not Null)
     */
    public Matrix(@NotNull double[][] arr) {
        int h = arr.length;
        int w = arr[0].length;
        if (h < 1) {
            throw new InvalidMatrixException("Height minor than 1");
        }

        if (w < 1) {
            throw new InvalidMatrixException("Width minor than 1");
        }

        height = h;
        width = w;

        matrix = new double[height][width];
        matrix = arr;
    }

    /**
     * Returns the internal matrix double[][] array
     *
     * @return Internal double[][] array
     */
    public double[][] toDoubleArray() {
        return matrix;
    }

    /**
     * assign new values to Matrix
     *
     * @param arr double[][] array with the new values
     */
    public void set(@NotNull double[][] arr) {
        matrix = arr;
    }

    /**
     * Checks if the Matrix is null
     *
     * @return A boolean value (true if Matrix is null)
     */
    public boolean isNull() {
        if (matrix == null) {
            return true;
        }
        else {
            boolean ret = false;
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (matrix[i][j] == 0) {
                        ret = true;
                    }
                    else {
                        return false;
                    }
                }
            }
            return ret;
        }
    }

    /**
     * Checks if this is a square Matrix
     *
     * @return A boolean value (true if the Matrix is square)
     */
    public boolean isSquare() {
        return height == width;
    }

    /**
     * Checks if this is a identity Matrix (1 on main diagonal, 0 on other positions)
     *
     * @return A boolean value (true if this is a identity Matrix)
     */
    public boolean isIdentity() {
        boolean ret = false;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == j && matrix[i][j] == 1) {
                    ret = true;
                }
                else if (i != j && matrix[i][j] == 0) {
                    ret = true;
                }
                else {
                    return false;
                }
            }
        }
        return ret;
    }

    /**
     * Checks if this is a diagonal Matrix (only has values on the main diagonal)
     *
     * @return A boolean value (true if this is a diagonal Matrix)
     */
    public boolean isDiagonal() {
        boolean ret = false;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == j && matrix[i][j] != 0) {
                    ret = true;
                }
                else if (i != j && matrix[i][j] == 0) {
                    ret = true;
                }
                else {
                    return false;
                }
            }
        }
        return ret;
    }

    /**
     * Checks if this Matrix is the opposite (has inverted signals) of mat
     *
     * @param mat The Matrix to compare with this
     * @return A boolean value (true if this Matrix is the opposite of mat)
     */
    public boolean isOpposite(@NotNull Matrix mat) {
        return (this.equals(mat.invertSignal()));
    }

    /**
     * Checks if this Matrix is symmetric (is equals to this Matrix transposed)
     *
     * @return A boolean value (true if is symmetric)
     */
    public boolean isSymmetric() {
        return (this.equals(this.transpose()));
    }

    /**
     * Checks if this Matrix is anti-symmetric (is equals to this Matrix transposed and signal-inverted)
     *
     * @return A boolean value (true if is symmetric)
     */
    public boolean isAntiSymmetric() {
        return (this.equals(this.transpose().invertSignal()));
    }

    /**
     * Checks if this Matrix is triangular (the values above or below the main diagonal are 0)
     *
     * @return A boolean value (true if this is a triangular Matrix)
     */
    public boolean isTriangular() {
        boolean ret = false;
        double[][] a = this.toDoubleArray();
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                if (i < j)
                    if (a[i][j] == 0) {
                        ret = true;
                    }
                    else {
                        ret = false;
                        break;
                    }

        if (!ret) {
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (i > j) {
                        if (a[i][j] == 0) {
                            ret = true;
                        }
                        else {
                            ret = false;
                            break;
                        }
                    }
                }
            }
        }

        return ret;
    }

    /**
     * Assign random values to the Matrix
     *
     * @param limitMin The smaller value allowed
     * @param limitMax The bigger value allowed
     */
    public void random(@NotNull int limitMin, @NotNull int limitMax) {
        if (limitMin > limitMax) {
            throw new IllegalArgumentException("limitMin greater than limitMax");
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                matrix[i][j] = ThreadLocalRandom.current().nextDouble(limitMin, limitMax + 1);
            }
        }
    }

    /**
     * Checks if this matrix is equals to another one
     *
     * @param mat The Matrix to be compared
     * @return A boolean value (true if this Matrix is equals to mat)
     */
    public boolean equals(@NotNull Matrix mat) {
        if (this.width == mat.width && this.height == mat.height) {
            boolean ret = false;
            double[][] a = this.toDoubleArray();
            double[][] b = mat.toDoubleArray();
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (a[i][j] == b[i][j]) {
                        ret = true;
                    }
                    else {
                        return false;
                    }
                }
            }
            return ret;
        }

        return false;
    }

    /**
     * Inverts the signal of the Matrix
     *
     * @return A new Matrix with the signals of this Matrix inverted
     */
    public Matrix invertSignal() {
        double[][] mat = this.toDoubleArray();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                mat[i][j] *= -1;
            }
        }
        return new Matrix(mat);
    }

    /**
     * Creates a smaller Matrix (removes a selected line and column of the original)
     *
     * @param line The desired line to remove
     * @param col  The column to remove from Matrix
     * @return A new Matrix with the line and column of this Matrix removed
     */
    public Matrix smaller(@NotNull int line, @NotNull int col) {
        if (this.isSquare() && width > 1) {
            double[][] matSmall = new double[height - 1][width - 1];
            double[][] mat = this.toDoubleArray();
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (i < line) {
                        if (j < col) {
                            matSmall[i][j] = mat[i][j];
                        }
                        else if (j > col) {
                            matSmall[i][j - 1] = mat[i][j];
                        }
                    }
                    else if (i > line) {
                        if (j < col) {
                            matSmall[i - 1][j] = mat[i][j];
                        }
                        else if (j > col) {
                            matSmall[i - 1][j - 1] = mat[i][j];
                        }
                    }
                }
            }

            return new Matrix(matSmall);
        }

        if (!isSquare()) {
            throw new InvalidMatrixException("Matrix is not square");
        }
        else {
            throw new InvalidMatrixException("Matrix size minor than 1");
        }
    }

    /**
     * Calculates the determinant of this Matrix
     *
     * @return The determinant of the Matrix (double)
     */
    public double determinant() {
        if (isSquare()) {
            if (width == 1)
                return matrix[0][0];

            double sum = 0;

            for (int i = 0; i < width; i++) {
                sum += ((i % 2 == 0) ? 1 : -1) * matrix[0][i] * this.smaller(0, i).determinant();
            }

            return sum;
        }

        throw new InvalidMatrixException("Matrix is not square");
    }

    /**
     * Calculates the cofactor (line, column) of the Matrix
     *
     * @param line   The desired line
     * @param column The desired column
     * @return the cofactor of the selected line and column
     */
    public double cofactor(@NotNull int line, @NotNull int column) {
        if (isSquare()) {
            return pow((-1), (line + column)) * this.determinant();
        }

        throw new InvalidMatrixException("Matrix is not square");
    }

    /**
     * Transpose this Matrix and returns a new one
     *
     * @return This Matrix transposed
     */
    public Matrix transpose() {
        double[][] transp = new double[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                transp[j][i] = matrix[i][j];
            }
        }

        return new Matrix(transp);
    }

    /**
     * Inverts this Matrix (creates a cofactor's Matrix, transposes it and divides 1 by each element multiplied by the determinant of the original Matrix)
     *
     * @return This Matrix inverted
     */
    public Matrix inverse() {
        if (isSquare()) {
            double[][] mat = new double[width][width];
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < width; j++) {
                    mat[i][j] = this.cofactor(i, j);
                }
            }

            mat = new Matrix(mat).transpose().toDoubleArray();

            for (int i = 0; i < width; i++) {
                for (int j = 0; j < width; j++) {
                    mat[i][j] = (1 / this.determinant()) * mat[i][j];
                }
            }

            return new Matrix(mat);
        }

        throw new InvalidMatrixException("Matrix is not square");
    }

    /**
     * Sum this Matrix with another Matrix
     *
     * @param toSum The Matrix to Sum
     * @return The two matrix summed
     */
    public Matrix sum(@NotNull Matrix toSum) {
        double[][] summed = new double[height][width];
        double[][] matA = this.toDoubleArray();
        double[][] matB = toSum.toDoubleArray();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                summed[i][j] = matA[i][j] + matB[i][j];
            }
        }

        return new Matrix(summed);
    }

    /**
     * Subtracts this Matrix with another Matrix
     *
     * @param toSub the Matrix to subtract
     * @return A new Matrix with the values of this subtracted of toSub
     */
    public Matrix subtract(@NotNull Matrix toSub) {
        double[][] subtracted = new double[height][width];
        double[][] matA = this.toDoubleArray();
        double[][] matB = toSub.toDoubleArray();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                subtracted[i][j] = matA[i][j] - matB[i][j];
            }
        }

        return new Matrix(subtracted);
    }

    /**
     * Multiplies two Matrices
     *
     * @param toMult the Matrix to multiply with this
     * @return A new Matrix with the values of both multiplied
     */
    public Matrix multiply(@NotNull Matrix toMult) {
        if (this.width == toMult.height) {
            double[][] multiplied = new double[height][width];
            double[][] matA = this.toDoubleArray();
            double[][] matB = toMult.toDoubleArray();

            for (int i = 0; i < this.height; i++) {
                for (int j = 0; j < toMult.width; j++) {
                    for (int k = 0; k < width; k++)
                        multiplied[i][j] += matA[i][k] * matB[k][j];
                }
            }

            return new Matrix(multiplied);

        }

        throw new InvalidMatrixException("Matrix A width not equals to matrix B height");
    }
}
