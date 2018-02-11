package com.modscleo4.mathpp.lib.binomial;

/**
 * Created by modsc on 16/08/2017.
 */
public class Factorial {
    public static int factorial(int n) {
        if (n == 1 || n == 0)
            return 1;
        else
            return n * factorial(n - 1);
    }
}
