package com.modscleo4.mathpp.lib.baseConverter;

import com.sun.istack.internal.NotNull;

import static java.lang.Math.pow;

public class Octal {
    private long value;
    public int size;

    public Octal(@NotNull long value) throws NumberBaseException {
        String aa = String.valueOf(value);
        if ((aa.contains("9") || aa.contains("8")) || value < 0) {
            throw new NumberBaseException("Not an octal number");
        }
        this.value = value;
        size = aa.length();
    }

    public long toLong() {
        return value;
    }

    public Binary toBinary() throws NumberBaseException {
        return new Binary(this.toDecimal().toBinary().toLong());
    }

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

    public Hexadecimal toHex() throws NumberBaseException {
        return new Hexadecimal(this.toBinary().toHex().toString());
    }
}
