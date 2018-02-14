package com.modscleo4.mathpp.lib.matrix;

import com.sun.istack.internal.NotNull;

import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.pow;

/**
 * Matrix.java
 * Purpose: Provides a Matrix class with common operations
 *
 * @author Dhiego Cassiano Fogaça Barbosa
 * @version 1.2
 */
public class Matrix {
    private int height, width;
    private double[][] matrix;

    /**
     * Constructor method (empty Matrix mode)
     *
     * @param h Height of the new Matrix
     * @param w Width of the new Matrix
     */
    public Matrix(int h, int w) {
        if (h < 1) {
            throw new MatrixException("Height minor than 1");
        }

        if (w < 1) {
            throw new MatrixException("Width minor than 1");
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
        if (arr == null) {
            throw new MatrixException("double[][] arr is null");
        }

        int h = arr.length;
        int w = arr[0].length;

        height = h;
        width = w;

        matrix = new double[height][width];
        matrix = arr;
    }

    /**
     * Gets the width of the Matrix
     *
     * @return The Matrix width
     */
    public int width() {
        return width;
    }

    /**
     * Gets the height of the Matrix
     *
     * @return The Matrix height
     */
    public int height() {
        return height;
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
        if (arr == null) {
            throw new MatrixException("double[][] arr is null");
        }

        matrix = arr;
    }

    /**
     * Resizes the Matrix (Warning: all the values are lost)
     *
     * @param newHeight The new height
     * @param newWidth  The new width
     */
    public void resize(int newHeight, int newWidth) {
        if (newHeight < 1) {
            throw new MatrixException("Height minor than 1");
        }

        if (newWidth < 1) {
            throw new MatrixException("Width minor than 1");
        }

        if (newHeight == height && newWidth == width) {
            throw new MatrixException("Cannot resize the Matrix to the same size");
        }

        height = newHeight;
        width = newWidth;

        matrix = new double[height][width];
    }

    /**
     * Checks if the Matrix is null
     *
     * @return A boolean value (true if Matrix is null)
     */
    public boolean isNull() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (matrix[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
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
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (!((i == j && matrix[i][j] == 1) || (i != j && matrix[i][j] == 0))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if this is a diagonal Matrix (only has values on the main diagonal)
     *
     * @return A boolean value (true if this is a diagonal Matrix)
     */
    public boolean isDiagonal() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (!((i == j && matrix[i][j] != 0) || (i != j && matrix[i][j] == 0))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if this Matrix is the opposite (has inverted signals) of mat
     *
     * @param mat The Matrix to compare with this
     * @return A boolean value (true if this Matrix is the opposite of mat)
     */
    public boolean isOpposite(@NotNull Matrix mat) {
        if (mat == null) {
            throw new MatrixException("Matrix mat is null");
        }

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
        double[][] a = this.matrix;
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
    public void random(int limitMin, int limitMax) {
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
        if (mat == null) {
            throw new MatrixException("Matrix mat is null");
        }

        if (this.width == mat.width && this.height == mat.height) {
            double[][] a = this.matrix;
            double[][] b = mat.matrix;
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (a[i][j] != b[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        }

        return false;
    }

    /**
     * Inverts the signal of the Matrix
     *
     * @return A new Matrix with the signals of this Matrix inverted
     */
    public Matrix invertSignal() {
        double[][] mat = this.matrix;
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
     * @param column  The column to remove from Matrix
     * @return A new Matrix with the line and column of this Matrix removed
     */
    public Matrix smaller(int line, int column) {
        if (line < 0) {
            throw new MatrixException("line parameter minor than 0");
        }

        if (column < 0) {
            throw new MatrixException("column parameter minor than 0");
        }

        if (this.isSquare() && width > 1) {
            double[][] matSmall = new double[height - 1][width - 1];
            double[][] mat = this.matrix;
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (i < line) {
                        if (j < column) {
                            matSmall[i][j] = mat[i][j];
                        }
                        else if (j > column) {
                            matSmall[i][j - 1] = mat[i][j];
                        }
                    }
                    else if (i > line) {
                        if (j < column) {
                            matSmall[i - 1][j] = mat[i][j];
                        }
                        else if (j > column) {
                            matSmall[i - 1][j - 1] = mat[i][j];
                        }
                    }
                }
            }

            return new Matrix(matSmall);
        }

        if (!isSquare()) {
            throw new MatrixException("Matrix is not square");
        }
        else {
            throw new MatrixException("Matrix size minor than 1");
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

        throw new MatrixException("Matrix is not square");
    }

    /**
     * Calculates the cofactor (line, column) of the Matrix
     *
     * @param line   The desired line
     * @param column The desired column
     * @return the cofactor of the selected line and column
     */
    public double cofactor(int line, int column) {
        if (line < 0) {
            throw new MatrixException("line parameter minor than 0");
        }

        if (column < 0) {
            throw new MatrixException("column parameter minor than 0");
        }

        if (isSquare()) {
            return pow((-1), (line + column)) * this.smaller(line, column).determinant();
        }

        throw new MatrixException("Matrix is not square");
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
                transp[i][j] = matrix[j][i];
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

            mat = new Matrix(mat).transpose().matrix;
            double det = this.determinant();
            if (det == 0D) {
                throw new MatrixException("This Matrix does not have an inverse. Determinant = 0");
            }

            for (int i = 0; i < width; i++) {
                for (int j = 0; j < width; j++) {
                    mat[i][j] = (1 / det) * mat[i][j];
                }
            }

            return new Matrix(mat);
        }

        throw new MatrixException("Matrix is not square");
    }

    /**
     * Sum this Matrix with another Matrix
     *
     * @param toSum The Matrix to Sum
     * @return The two matrix summed
     */
    public Matrix sum(@NotNull Matrix toSum) {
        if (toSum == null) {
            throw new MatrixException("Matrix toSum is null");
        }

        if (this.height != toSum.height) {
            throw new MatrixException("Matrix A height not equals to Matrix B height");
        }

        if (this.width != toSum.width) {
            throw new MatrixException("Matrix A width not equals to Matrix B width");
        }

        double[][] summed = new double[height][width];
        double[][] matA = this.matrix;
        double[][] matB = toSum.matrix;

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
        if (toSub == null) {
            throw new MatrixException("Matrix mat is null");
        }

        if (this.height != toSub.height) {
            throw new MatrixException("Matrix A height not equals to Matrix B height");
        }

        if (this.width != toSub.width) {
            throw new MatrixException("Matrix A width not equals to Matrix B width");
        }

        double[][] subtracted = new double[height][width];
        double[][] matA = this.matrix;
        double[][] matB = toSub.matrix;

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
        if (toMult == null) {
            throw new MatrixException("Matrix mat is null");
        }


        if (this.width == toMult.height) {
            double[][] multiplied = new double[this.height][toMult.width];
            double[][] matA = this.matrix;
            double[][] matB = toMult.matrix;

            for (int i = 0; i < this.height; i++) {
                for (int j = 0; j < toMult.width; j++) {
                    for (int k = 0; k < width; k++)
                        multiplied[i][j] += matA[i][k] * matB[k][j];
                }
            }

            return new Matrix(multiplied);

        }

        throw new MatrixException("Matrix A width not equals to matrix B height");
    }
}
