package com.modscleo4.mathpp.lib.baseConverter;

import org.jetbrains.annotations.NotNull;

import static java.lang.Math.pow;

/**
 * Binary.java
 * Purpose: Provides number base conversion (binary to dec, oc, hex)
 *
 * @deprecated
 * @author Dhiego Cassiano Fogaça Barbosa
 * @version 1.0
 */

public class Binary {
    private long value;
    public int size;

    /**
     * Constructor method
     *
     * @param value The binary number on a long var
     */
    public Binary(@NotNull long value) {
        String aa = String.valueOf(value);
        if ((aa.contains("2") || aa.contains("3") || aa.contains("4") || aa.contains("5") || aa.contains("6") || aa.contains("7") || aa.contains("8") || aa.contains("9")) || value < 0) {
            throw new NumberBaseException("Not a binary number");
        }

        this.value = value;
        size = aa.length();
    }

    /**
     * Returns the internal long var
     *
     * @return The value internal long
     */
    public long toLong() {
        return value;
    }

    /**
     * Converts this Binary to Decimal
     *
     * @return The Decimal equivalent
     */
    public Decimal toDecimal() {
        long sum = 0, b = this.toLong();

        int l = size - 1;
        for (int i = size; i >= 1; i--) {
            long p = (long)pow(10, l);
            long a = b / p % 10;
            a = a * (long)pow(2, l);
            sum = sum + a;
            l--;
        }
        return new Decimal(sum);
    }

    /**
     * Converts this Binary to Octal
     *
     * @return The Octal equivalent
     */
    public Octal toOctal() {
        return new Octal(this.toDecimal().toOctal().toLong());
    }

    /**
     * Converts this Binary to Hexadecimal
     *
     * @return The Hexadecimal equivalent
     */
    public Hexadecimal toHex() {
        return new Hexadecimal(this.toDecimal().toHex().toString());
    }
}
