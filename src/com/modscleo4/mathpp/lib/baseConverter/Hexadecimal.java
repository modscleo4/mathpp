package com.modscleo4.mathpp.lib.baseConverter;

import com.sun.istack.internal.NotNull;

import static com.modscleo4.mathpp.lib.baseConverter.utils.Hex.hexb;

/**
 * Hexadecimal.java
 * Purpose: Provides number base conversion (hex to bin, dec, oc)
 *
 * @deprecated
 * @author Dhiego Cassiano Fogaça Barbosa
 * @version 1.0
 */
public class Hexadecimal {
    private String value;
    public int size;

    /**
     * Constructor method
     *
     * @param value The hexadecimal value on a String var
     */
    public Hexadecimal(@NotNull String value) {
        this.value = value;
        size = value.length();
    }

    /**
     * Returns the internal String var
     *
     * @return The value internal String
     */
    public String toString() {
        return value;
    }

    /**
     * Converts this Hexaecimal to Binary
     *
     * @return The Binary equivalent
     */
    public Binary toBinary() {
        StringBuilder result = new StringBuilder();
        long l = size - 1;
        int pos = 0;
        char[] arr_x_temp = this.toString().toCharArray();
        long[] arr_x = new long[size];
        for (int i = 0; i <= l; i++) {
            long xNum = hexb(String.valueOf(arr_x_temp[i]));
            arr_x[i] = xNum;
        }

        for (int i = pos; i < size; i++) {
            long b = arr_x[i];
            long c = 2;
            while (c > 1) {
                if (b == 1 || b == 0) {
                    result.append("000").append(String.valueOf(b));
                    c = 0;
                } else {
                    long mod, quo = 2, ai = 2;
                    String r, mods, rmods;
                    mods = "";
                    while (ai > 1) {
                        mod = b % 2;
                        quo = b / 2;
                        r = String.valueOf(mod);
                        mods += r;

                        if (quo == 0 || quo == 1) {
                            rmods = new StringBuilder(mods).reverse().toString();
                            int tamanho = rmods.length() + 1;
                            if (tamanho == 2) {
                                result.append("00").append(String.valueOf(quo)).append(rmods);
                            } else if (tamanho == 3) {
                                result.append("0").append(String.valueOf(quo)).append(rmods);
                            } else {
                                result.append(String.valueOf(quo)).append(rmods);
                            }
                            ai = 0;
                            c = 0;
                        }
                        b = quo;
                    }
                    b = quo;
                }
            }
        }
        return new Binary(Long.valueOf(result.toString()));
    }

    /**
     * Converts this Hexaecimal to Decimal
     *
     * @return The Decimal equivalent
     */
    public Decimal toDecimal() {
        return new Decimal(this.toBinary().toDecimal().toLong());
    }

    /**
     * Converts this Hexaecimal to Octal
     *
     * @return The Octal equivalent
     */
    public Octal toOctal() {
        return new Octal(this.toBinary().toOctal().toLong());
    }
}
