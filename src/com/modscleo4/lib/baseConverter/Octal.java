package com.modscleo4.lib.baseConverter;

import static com.modscleo4.lib.baseConverter.Decimal.dec_to_hex;
import static com.modscleo4.readWrite.textReadWrite.*;
import static java.lang.Math.pow;

/**
 * Created by modscleo4 on 01/05/2017.
 */
public class Octal {
    public static int oc_to_dec(int o) {
        int p, a, sum = 0, length, l;
        String aa = String.valueOf(o);
        if (aa.contains("9") || aa.contains("8")) {
            return -1; // -1 is error code.
        }
        if (o < 0) {
            return -1; // -1 is error code.
        }
        dbgPrint("aa = " + aa);
	    length = aa.length();
	    dbgPrint("length = " + length);
	    l = length - 1;
	    dbgPrint("l = " + l);

        for (int i = length; i >= 1; i--) {
		    p = (int)pow(10, l);
		    dbgPrint("p = " + p);
		    a = o / p % 10;
		    dbgPrint("a = " + a);
            a = a * (int)pow(8, l);
            dbgPrint("a = " + a);
            sum += a;
            dbgPrint("sum = " + sum);
            l--;
        }
        return sum;
    }

    public static int oc_to_bin(int o) {
        String aa = String.valueOf(o);
        if (aa.contains("9") || aa.contains("8")) {
            return -1; // -1 is error code.
        }
        if (o < 0) {
            return -1; // -1 is error code.
        }
        String result = "";
        int length = aa.length();
        dbgPrint("length = " + length);
	    int l = length - 1;
        dbgPrint("l = " + l);
	    int size = length;
        dbgPrint("size = " + size);
        int pos = 0;
	    int[] arr_o = new int[size];
	    dbgPrint("=====assigning values to array");
        for (int i = length; i >= 1; i--) {
		    int p = (int)pow(10, l--);
            dbgPrint("p = " + p);
		    int a = o / p % 10;
            dbgPrint("a = " + a);
            arr_o[pos] = a;
            dbgPrint("arr_o[" + pos +  "] = " + arr_o[pos]);
            pos++;
        }

        pos = 0;
        for (int i = pos; i < size; i++) {
            int b = arr_o[i];
            dbgPrint("b = " + b);
            int c = 2;
            while (c > 1) {
                if (b == 1 || b == 0) {
                    result += "00" + String.valueOf(b);
                    dbgPrint("result = " + result);
                    c = 0;
                } else {
				    int mod = 2, quo = 2, ai = 2;
                    String r, mods, rmods;
                    r = mods = aa = "";
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

    public static String oc_to_hex(int o) {
        String aa = String.valueOf(o);
        if (aa.contains("9") || aa.contains("8")) {
            return "-1"; // -1 is error code.
        }
        if (o < 0) {
            return "-1"; // -1 is error code.
        }
        int o_d = oc_to_dec(o);
        String d_x = dec_to_hex(o_d);
        return d_x;
    }
}
