package com.modscleo4.lib.baseConverter;
import static com.modscleo4.lib.baseConverter.Binary.bin_to_dec;
import static com.modscleo4.lib.baseConverter.Decimal.*;
import static com.modscleo4.lib.baseConverter.utils.Hex.*;
import static java.lang.Math.pow;
import static com.modscleo4.readWrite.textReadWrite.*;

/**
 * Created by modsc on 01/05/2017.
 */
public class Hexadecimal {
    public static int hex_to_bin(String x) {
        String result = "";
        int length = x.length();
        dbgPrint("length = " + length);
        int l = length - 1;
        dbgPrint("l = " + l);
        int size = length;
        dbgPrint("size = " + size);
        int pos = 0;
        char[] arr_x_temp = x.toCharArray();
        int[] arr_x = new int[size];
        for (int i = 0; i <= l; i++) {
            int xNum = hexb(String.valueOf(arr_x_temp[i]));
            dbgPrint("xNum = " + xNum);
            arr_x[i] = xNum;
            dbgPrint("arr_x[" + i +  "] = " + arr_x[i]);
        }

        for (int i = pos; i < size; i++) {
            int b = arr_x[i];
            dbgPrint("b = " + b);
            int c = 2;
            while (c > 1) {
                if (b == 1 || b == 0) {
                    result += "000" + String.valueOf(b);
                    dbgPrint("result = " + result);
                    c = 0;
                } else {
                    int mod = 2, quo = 2, ai = 2;
                    String r, mods, rmods;
                    r = mods = "";
                    while (ai > 1) {
                        mod = b % 2;
                        dbgPrint("mod = " + mod);
                        quo = b / 2;
                        dbgPrint("quo = " + quo);
                        r = String.valueOf(mod);
                        dbgPrint("r = " + r);
                        mods += r;
                        dbgPrint("mods = " + mods);

                        if (quo == 0 || quo == 1) {
                            rmods = new StringBuilder(mods).reverse().toString();
                            dbgPrint("rmods = " + rmods);
                            int tamanho = rmods.length() + 1;
                            dbgPrint("tamanho = " + tamanho);
                            if (tamanho == 2) {
                                result += "00" + String.valueOf(quo) + rmods;
                                dbgPrint("result = " + result);
                            } else if (tamanho == 3) {
                                result += "0" + String.valueOf(quo) + rmods;
                                dbgPrint("result = " + result);
                            } else {
                                result += String.valueOf(quo) + rmods;
                                dbgPrint("result = " + result);
                            }
                            ai = 0;
                            c = 0;
                        }
                        b = quo;
                        dbgPrint("b = " + b);
                    }
                    b = quo;
                }
            }
        }
        return Integer.valueOf(result);
    }
    public static int hex_to_dec(String x) {
        /*int length = x.length();
        dbgPrint("length = " + length);
        int l = length - 1;
        dbgPrint("l = " + l);
        int size = length;
        dbgPrint("size = " + size);
        int pos = 0, p = 0, a = 0, sum = 0;
        char[] arr_x_temp = x.toCharArray();
        int[] arr_x = new int[size];
        for (int i = 0; i <= l; i++) {
            int xNum = hexb(String.valueOf(arr_x_temp[i]));
            dbgPrint("xNum = " + xNum);
            arr_x[i] = xNum;
            dbgPrint("arr_x[" + i + "] = " + arr_x[i]);
        }

        for (int i = length; i >= 1; i--) {
            a = arr_x[l];
            dbgPrint("a = " + a);
            a = a * (int)pow(16, l);
            dbgPrint("a = " + a);
            sum += a;
            dbgPrint("sums = " + sum);
            l--;
        }

        return sum;*/
        return bin_to_dec(hex_to_bin(x));
    }

    public static int hex_to_oc(String x) {
        return dec_to_oc(hex_to_dec(x));
    }
}
