package com.modscleo4.mathpp.lib.baseConverter;

import com.sun.istack.internal.NotNull;

import static com.modscleo4.mathpp.lib.baseConverter.utils.Hex.hexa;
import static java.lang.Math.pow;

/**
 * @Author: Modscleo4 (Dhiego Cassiano Fogaça Barbosa)
 * */

public class Binary {
    private long value;
    public int size;

    public Binary(@NotNull long value) throws NumberBaseException {
        String aa = String.valueOf(value);
        if ((aa.contains("2") || aa.contains("3") || aa.contains("4") || aa.contains("5") || aa.contains("6") || aa.contains("7") || aa.contains("8") || aa.contains("9")) || value < 0) {
            throw new NumberBaseException("Not a binary number");
        }

        this.value = value;
        size = aa.length();
    }

    public long toLong() {
        return value;
    }

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

    public Octal toOctal() throws NumberBaseException {
        return new Octal(this.toDecimal().toOctal().toLong());
    }

    public Hexadecimal toHex() {
        return new Hexadecimal(this.toDecimal().toHex().toString());
    }
}
