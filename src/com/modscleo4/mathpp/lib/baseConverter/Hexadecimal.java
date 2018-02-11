package com.modscleo4.mathpp.lib.baseConverter;

import com.sun.istack.internal.NotNull;

import static com.modscleo4.mathpp.lib.baseConverter.utils.Hex.hexb;

public class Hexadecimal {
    private String value;
    public int size;

    public Hexadecimal(@NotNull String value) {
        this.value = value;
        size = value.length();
    }

    public String toString() {
        return value;
    }

    public Binary toBinary() throws NumberBaseException {
        String result = "";
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
                    result += "000" + String.valueOf(b);
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
                                result += "00" + String.valueOf(quo) + rmods;
                            } else if (tamanho == 3) {
                                result += "0" + String.valueOf(quo) + rmods;
                            } else {
                                result += String.valueOf(quo) + rmods;
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
        return new Binary(Long.valueOf(result));
    }

    public Decimal toDecimal() throws NumberBaseException {
        return new Decimal(this.toBinary().toDecimal().toLong());
    }

    public Octal toOctal() throws NumberBaseException {
        return new Octal(this.toBinary().toOctal().toLong());
    }
}
