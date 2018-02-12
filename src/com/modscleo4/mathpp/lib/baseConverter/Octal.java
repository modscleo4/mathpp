package com.modscleo4.mathpp.lib.baseConverter;

import com.sun.istack.internal.NotNull;

import static java.lang.Math.pow;

/**
 * Octal.java
 * Purpose: Provides number base conversion (octal to bin, dec, hex)
 *
 * @author Dhiego Cassiano Fogaça Barbosa
 * @version 1.0
 */
public class Octal {
    private long value;
    public int size;

    /**
     * Constructor method
     *
     * @param value The octal number on a long var
     */
    public Octal(@NotNull long value) {
        String aa = String.valueOf(value);
        if ((aa.contains("9") || aa.contains("8")) || value < 0) {
            throw new NumberBaseException("Not an octal number");
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
     * Converts this Octal to Binary
     *
     * @return The Binary equivalent
     */
    public Binary toBinary() {
        return new Binary(this.toDecimal().toBinary().toLong());
    }

    /**
     * Converts this Octal to Decimal
     *
     * @return the Decimal equivalent
     */
    public Decimal toDecimal() {
        long p, a, sum = 0, l, o = this.toLong();
        l = size - 1;

        for (int i = size; i >= 1; i--) {
            p = (int)pow(10, l);
            a = o / p % 10;
            a = a * (int)pow(8, l);
            sum += a;
            l--;
        }
        return new Decimal(sum);
    }

    /**
     * Converts this Octal to Hexadecimal
     *
     * @return The Hexadecimal equivalent
     */
    public Hexadecimal toHex() {
        return new Hexadecimal(this.toDecimal().toHex().toString());
    }
}
