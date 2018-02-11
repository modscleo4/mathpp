package com.modscleo4.mathpp.lib.matrix;

import com.sun.istack.internal.NotNull;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.pow;

public class Matrix {
    public int height, width;
    private double[][] matrix;

    public Matrix(@NotNull int h, @NotNull int w) throws InvalidMatrixException {
        if (h < 1)
            throw new InvalidMatrixException("Height minor than 1");

        if (w < 1)
            throw new InvalidMatrixException("Width minor than 1");

        height = h;
        width = w;

        matrix = new double[height][width];
    }

    public Matrix(@NotNull int h, @NotNull int w, @NotNull double[][] arr) throws InvalidMatrixException {
        if (h < 1)
            throw new InvalidMatrixException("Height minor than 1");

        if (w < 1)
            throw new InvalidMatrixException("Width minor than 1");

        height = h;
        width = w;

        matrix = new double[height][width];
        matrix = arr;
    }

    public double[][] toDoubleArray() {
        return matrix;
    }

    public void set(@NotNull double[][] arr) {
        matrix = arr;
    }

    public boolean isNull() {
        if (matrix == null)
            return true;
        else {
            boolean ret = false;
            for (int i = 0; i < height; i++)
                for (int j = 0; j < width; j++)
                    if (matrix[i][j] == 0)
                        ret = true;
                    else
                        return false;
            return ret;
        }
    }

    public boolean isSquare() {
        return height == width;
    }

    public boolean isIdentity() {
        boolean ret = false;
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                if (i == j && matrix[i][j] == 1)
                    ret = true;
                else if (i != j && matrix[i][j] == 0)
                    ret = true;
                else
                    return false;
        return ret;
    }

    public void random(@NotNull int limitMin, @NotNull int limitMax) {
        if (limitMin > limitMax)
            throw new IllegalArgumentException("limitMin greater than limitMax");

        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++) {
                matrix[i][j] = ThreadLocalRandom.current().nextDouble(limitMin, limitMax + 1);
            }
    }

    public Matrix smaller() throws InvalidMatrixException {
        if (this.isSquare() && width > 1) {
            double[][] matSmall = new double[height - 1][width - 1];
            for (int i = 0; i < height - 1; i++)
                for (int j = 0; j < width - 1; j++)
                    matSmall[i][j] = matrix[i][j];

            return new Matrix(height - 1, width - 1, matSmall);
        } else
            if (!isSquare())
                throw new InvalidMatrixException("Matrix is not square");
            else
                throw new InvalidMatrixException("Matrix size minor than 1");
    }

    public double determinant() throws InvalidMatrixException {
        if (isSquare()) {
            if (width == 1)
                return matrix[0][0];

            double sum = 0;
            int s;

            for (int i = 0; i < width; i++) {
                if (i % 2 == 0)
                    s = 1;
                else
                    s = -1;

                sum += s * matrix[0][i] * this.smaller().determinant();
            }
            return sum;
        } else
            throw new InvalidMatrixException("Matrix is not square");
    }

    public double cofactor(@NotNull int line, @NotNull int column) throws Exception {
        if (isSquare())
            return pow((-1), (line + column)) * this.determinant();
        else
            throw new InvalidMatrixException("Matrix is not square");
    }

    public Matrix transpose() throws InvalidMatrixException {
        double[][] transp = new double[width][height];
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                transp[j][i] = matrix[i][j];
        return new Matrix(width, height, transp);
    }

    public Matrix inverse() throws Exception {
        if (isSquare()) {
            double[][] mat = new double[width][width];
            for (int i = 0; i < width; i++)
                for (int j = 0; j < width; j++)
                    mat[i][j] = this.cofactor(i, j);

            mat = new Matrix(width, width, mat).transpose().toDoubleArray();

            for (int i = 0; i < width; i++)
                for (int j = 0; j < width; j++)
                    mat[i][j] = (1/this.determinant()) * mat[i][j];

            return new Matrix(width, width, mat);
        } else
            throw new InvalidMatrixException("Matrix is not square");
    }

    public Matrix sum(@NotNull Matrix toSum) throws InvalidMatrixException {
        double[][] summed = new double[height][width];
        double[][] matA = this.toDoubleArray();
        double[][] matB = toSum.toDoubleArray();

        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                summed[i][j] = matA[i][j] + matB[i][j];

        return new Matrix(height, width, summed);
    }

    public Matrix subtract(@NotNull Matrix toSub) throws InvalidMatrixException {
        double[][] subtracted = new double[height][width];
        double[][] matA = this.toDoubleArray();
        double[][] matB = toSub.toDoubleArray();

        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                subtracted[i][j] = matA[i][j] - matB[i][j];

        return new Matrix(height, width, subtracted);
    }

    public Matrix multiply(@NotNull Matrix toMult) throws InvalidMatrixException {
        if (this.width == toMult.height) {
            double[][] multiplied = new double[height][width];
            double[][] matA = this.toDoubleArray();
            double[][] matB = toMult.toDoubleArray();

            for (int i = 0; i < this.height; i++)
                for (int j = 0; j < toMult.width; j++)
                    for (int k = 0; k < width; k++)
                        multiplied[i][j] += matA[i][k] * matB[k][j];

            return new Matrix(height, width, multiplied);

        } else
            throw new InvalidMatrixException("Matrix A width not equals to matrix B height");
    }
}
