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

    public Octal toOctal() {
        long sum;
        
        long mod = size % 3, rest = 3 - mod;
        long q = size + rest, l = size - 1;

        long[] nums = new long[size];

        String bb = "";
        for (int i = (int)rest; i <= l; i++) {
            long p = (long)pow(10, --q);
            long a = this.value / p % 10;
            nums[i] = a;
        }
        long[] conv = new long[3];
        int pos = 0;
        for (int i = 0; i < size;) {
            long max = i + 3;
            for (int j = i; j < max; j++) {
                conv[pos] = nums[j];
                pos++;
                i++;
            }
            pos = 0;
            sum = 0;
            long posB;
            for (int posA=0; posA < 3; posA++) {
                posB = 2 - posA;
                long a = conv[posA];
                long power = (long)pow(2, posB);
                a = a * power;
                sum += a;
            }
            bb += String.valueOf(sum);
        }
        return new Octal(Long.valueOf(bb));
    }

    public Hexadecimal toHex() {

        String sumA = "";

        long sum;

        long mod = size % 4, rest = 4 - mod;
        long q = size + rest, l = size - 1;

        long[] nums = new long[size];

        for (int i = (int)rest; i <= l; i++) {
            long p = (long)pow(10, --q);
            long a = this.value / p % 10;
            nums[i] = a;
        }

        long[] conv = new long[4];
        int pos = 0;

        for (int i = 0; i < size;) {
            long max = i + 4;
            for (int j = i; j < max; j++) {
                conv[pos] = nums[i];
                pos++;
                i++;
            }
            pos = 0;
            sum = 0;
            long posB;
            for (int posA = 0; posA < 4; posA++) {
                posB = 3 - posA;
                long a = conv[posA];
                long power = (long)pow(2, posB);
                a = a * power;
                sum += a;
            }
            if (sum > 9 && sum < 16)
                sumA += hexa((int)sum);
            else
                sumA += String.valueOf(sum);
        }
        return new Hexadecimal(sumA);
    }
}
