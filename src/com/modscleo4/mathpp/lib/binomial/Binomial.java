package com.modscleo4.mathpp.lib.binomial;

/**
 * Created by modsc on 16/08/2017.
 */
public class Binomial {
    public static int binom(int n, int d) {
        if (d == 0 || d == n)
            return 1;
        else if (d == 1 || d == (n - 1))
            return n;
        else
            return binom(n - 1, d - 1) + binom(n - 1, d);
    }
}
