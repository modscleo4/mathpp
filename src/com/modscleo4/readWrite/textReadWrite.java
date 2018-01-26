package com.modscleo4.readWrite;

import java.util.Scanner;

import static com.modscleo4.apps.GlobalSettings.*;

public class textReadWrite {
    // Print
    public static void print(String s) {
        System.out.println(s);
    }

    public static void print(int i) {
        System.out.println(i);
    }

    public static void print(double d) {
        System.out.println(d);
    }

    public static void print(boolean b) {
        System.out.println(b);
    }

    public static void print(char c) {
        System.out.println(c);
    }

    public static void dbgPrint(String s) {
        if (debug) {
            System.out.println(s);
        }
    }

    // Read
    public static int read(int i) {
        Scanner iScanner = new Scanner(System.in);
        i = iScanner.nextInt();
        return i;
    }

    public static String read(String s) {
        Scanner sScanner = new Scanner(System.in);
        s = sScanner.nextLine();
        return s;
    }

    public static double read(double d) {
        Scanner dScanner = new Scanner(System.in);
        d = dScanner.nextDouble();
        return d;
    }
    public static float read(float f) {
        Scanner dScanner = new Scanner(System.in);
        f = dScanner.nextFloat();
        return f;
    }

    public static boolean read(boolean b) {
        Scanner bScanner = new Scanner(System.in);
        b = bScanner.nextBoolean();
        return b;
    }

    public static void matrixPrint(float[][] matrix, int a) {
        if (a == 1) {
            if (showC)
                run(matrix);
        } else {
            run(matrix);
        }
    }

    public static void run(float[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] > 0)
                    System.out.print("|\t+" + matrix[i][j] + "\t");
                else
                    System.out.print("|\t" + matrix[i][j] + "\t");
            }
            print("|");
        }
    }

    public static void printc(String s) {
        if (showC)
            System.out.println(s);
    }

    public static String readConfig() {
        String a = " ";

        return a;
    }

    public static void main(String[] args) {

    }
}
