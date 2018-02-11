package com.modscleo4.lib.baseConverter;

import static com.modscleo4.readWrite.textReadWrite.*;
import static java.lang.Math.pow;
import static com.modscleo4.lib.baseConverter.utils.Hex.*;

public class Binary {
    public static int bin_to_dec(int b) {
        int sum = 0;

        String aa = String.valueOf(b);
        if (aa.contains("2") || aa.contains("3") || aa.contains("4") || aa.contains("5") || aa.contains("6") || aa.contains("7") || aa.contains("8") || aa.contains("9")) {
            return -1; // -1 is error code.
        }
        if (b < 0) {
            return -1; // -1 is error code.
        }
	    int length = aa.length();
	    dbgPrint("length = " + length);
	    int l = length - 1;
	    dbgPrint("l = " + l);
        for (int i = length; i >= 1; i--) {
		    int p = (int)pow(10, l);
		    dbgPrint("p = " + p);
		    int a = b / p % 10;
		    dbgPrint("a = " + a);
            a = a * (int)pow(2, l);
            dbgPrint("a = " + a);
            sum = sum + a;
            dbgPrint("sum = " + sum);
            l--;
        }
        return sum;
    }

    public static int bin_to_oc(int b) {
        int sum = 0;

        String bb = "", aa = String.valueOf(b);
        if (aa.contains("2") || aa.contains("3") || aa.contains("4") || aa.contains("5") || aa.contains("6") || aa.contains("7") || aa.contains("8") || aa.contains("9")) {
            return -1; // -1 is error code.
        }
        if (b < 0) {
            return -1; // -1 is error code.
        }
	    int length = aa.length();
        dbgPrint("length = " + length);
	    int q = length;
	    dbgPrint("q = " + q);
					
	    int mod = length % 3;
	    dbgPrint("mod = " + mod);
	    int rest = 3 - mod;
	    dbgPrint("rest = " + rest);
        length = length + rest;
        dbgPrint("length = " + length);
	    int l = length - 1;
        dbgPrint("l = " + l);
	    int size = length;
	    dbgPrint("size = " + size);
        int[] nums = new int[size];

        dbgPrint("======assigning values to array======");
        for (int i = rest; i <= l; i++) {
		    int p = (int)pow(10, --q);
		    dbgPrint("p = " + p);
		    int a = b / p % 10;
            dbgPrint("a = " + a);
            nums[i] = a;
            dbgPrint("nums[" + i + "] = " + nums[i]);
        }
	    int[] conv = new int[3];
        int pos = 0;
        for (int i=0; i<length;) {
            int max = i + 3;
            dbgPrint("max = " + max);
                for (int j = i; j < max; j++) {
                    conv[pos] = nums[j];
                    dbgPrint("conv[" + pos + "] = " + conv[pos]);
                    pos++;
                    i++;
                }
            pos = 0;
            sum = 0;
            int posB;
            for (int posA=0; posA < 3; posA++) {
                posB = 2 - posA;
                dbgPrint("posB = " + posB);
                int a = conv[posA];
                dbgPrint("a = " + a);
                int power = (int)pow(2, posB);
                dbgPrint("power = " + power);
                a = a * power;
                dbgPrint("a = " + a);
                sum += a;
                dbgPrint("sum = " + sum);
            }
            bb += String.valueOf(sum);
        }
        return Integer.valueOf(bb);
    }

    public static String bin_to_hex(int b) {

        String aa = String.valueOf(b), ca = "", sumA = "";
        if (aa.contains("2") || aa.contains("3") || aa.contains("4") || aa.contains("5") || aa.contains("6") || aa.contains("7") || aa.contains("8") || aa.contains("9")) {
            return "-1"; // -1 is error code.
        }
        if (b < 0) {
            return "-1"; // -1 is error code.
        }
	    int sum = 0;
	    int length = aa.length();
	    dbgPrint("length = " + length);
	    int q = length;
	    dbgPrint("q = " + q);
	
	    int mod = length % 4;
	    dbgPrint("mod = " + mod);
	    int rest = 4 - mod;
	    dbgPrint("rest = " + rest);
        length = length + rest;
        dbgPrint("length = " + length);
	    int l = length - 1;
	    dbgPrint("l = " + l);
	    int size = length;
	    dbgPrint("size = " + size);

        int[] nums = new int[size];

        dbgPrint("======assigning values to array======");
        for (int i = rest; i <= l; i++) {
            int p = (int)pow(10, --q);
            dbgPrint("p = " + p);
            int a = b / p % 10;
            dbgPrint("a = " + a);
            nums[i] = a;
            dbgPrint("nums[" + i + "] = " + nums[i]);
        }
	
	    int[] conv = new int[4];
	    int pos = 0;

        for (int i = 0; i < length;) {
		int max = i + 4;
		dbgPrint("max = " + max);
            for (int j = i; j < max; j++) {
                conv[pos] = nums[i];
                dbgPrint("conv[" + pos + "] = " + conv[pos]);
                pos++;
                i++;
            }
            pos = 0;
            sum = 0;
		int posB;
            for (int posA=0; posA < 4; posA++) {
                posB = 3 - posA;
                dbgPrint("posB = " + posB);
			    int a = conv[posA];
			    dbgPrint("a = " + a);
			    int power = (int)pow(2, posB);
			    dbgPrint("power = " + power);
                a = a * power;
                dbgPrint("a = " + a);
                sum += a;
                dbgPrint("sum = " + sum);
            }
            if (sum > 9 && sum < 16) {
                ca = hexa(sum);
                dbgPrint("ca = " + ca);
                sumA += ca;
                dbgPrint("sumA = " + sumA);
            } else {
                String aaa = String.valueOf(sum);
                dbgPrint("aaa = " + aaa);
                sumA += aaa;
                dbgPrint("sumA = " + sumA);
            }
        }
        return sumA;
    }
}
