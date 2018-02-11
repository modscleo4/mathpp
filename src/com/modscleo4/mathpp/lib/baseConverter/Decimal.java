package com.modscleo4.lib.baseConverter;

import static com.modscleo4.lib.baseConverter.utils.Hex.hexa;
import static com.modscleo4.readWrite.textReadWrite.*;

public class Decimal {
    public static int dec_to_bin(int d) {
        int c = 2, mod, quo = 2, ai = 2;
        if (d < 0) {
            return -1; // -1 is error code.
        }
        String r, mods = "", rmods, ans = "";
        while (c > 1) {
            if (d == 1 || d == 0) {
                return d;
            } else {
                while (ai > 1) {
                    mod = d % 2;
                    dbgPrint("mod = " + mod);
                    quo = d / 2;
                    dbgPrint("quo = " + quo);
                    r = String.valueOf(mod);
                    dbgPrint("r = " + r);
                    mods += r;
                    dbgPrint("mods = " + mods);

                    if (quo == 0 || quo == 1) {
                        rmods = new StringBuilder(mods).reverse().toString();
                        dbgPrint("rmods = " + rmods);
                        ans = String.valueOf(quo);
                        dbgPrint("ans = " + ans);
                        ans += String.valueOf(rmods);
                        dbgPrint("ans = " + ans);
                        ai = 0;
                        c = 0;
                    }
                    d = quo;
                }
                d = quo;
            }
        }
        return Integer.valueOf(ans);
    }
    public static int dec_to_oc(int d) {
        int c = 2, mod, quo = 8, ai = 8;
        if (d < 0) {
            return -1; // -1 is error code.
        }
        String r, mods = "", rmods, ans = "";
        while (c > 1) {
            if (d >=0 && d <= 7) {
                return d;
            } else {
                while (ai > 1) {
                    mod = d % 8;
                    dbgPrint("mod = " + mod);
                    quo = d / 8;
                    dbgPrint("quo = " + quo);
                    r = String.valueOf(mod);
                    dbgPrint("r = " + r);
                    mods += r;
                    dbgPrint("mods = " + mods);

                    if (quo >= 0 && quo <= 7) {
                        rmods = new StringBuilder(mods).reverse().toString();
                        dbgPrint("rmods = " + rmods);
                        ans = String.valueOf(quo);
                        dbgPrint("ans = " + ans);
                        ans += String.valueOf(rmods);
                        dbgPrint("ans = " + ans);
                        ai = 0;
                        c = 0;
                    }
                    d = quo;
                }
                d = quo;
            }
        }
        return Integer.valueOf(ans);
    }

    public static String dec_to_hex(int d) {
        int c = 2, mod, quo = 16, ai = 16;
        if (d < 0) {
            return "-1"; // -1 is error code.
        }
        String aad, modu, quos, r = "", rmods, ans = "", mods = "";
        while (c > 1) {
            if (d >=0 && d < 10) {
                return String.valueOf(d);
            } else if (d > 9 && d < 16) {
                aad = hexa(d);
                dbgPrint("aad = " + aad);
                return aad;
            } else {
                while (ai > 1) {
                    mod = d % 16;
                    dbgPrint("mod = " + mod);
                    quo = d / 16;
                    dbgPrint("quo = " + quo);
                    if (mod > 9 && mod < 16) {
                        modu = hexa(mod);
                        dbgPrint("modu = " + modu);
                        mods += modu;
                        dbgPrint("mods = " + mods);
                    } else {
                        mods += mod;
                        dbgPrint("mods = " + mods);
                    }

                    if (quo >= 0 && quo <= 9) {
                        rmods = new StringBuilder(mods).reverse().toString();
                        dbgPrint("rmods = " + rmods);
                        ans = String.valueOf(quo);
                        dbgPrint("ans = " + ans);
                        ans += String.valueOf(rmods);
                        dbgPrint("ans = " + ans);
                        ai = 0;
                        c = 0;
                    } else if (quo > 9 && quo < 16) {
                        rmods = new StringBuilder(mods).reverse().toString();
                        dbgPrint("rmods = " + rmods);
                        quos = hexa(quo);
                        dbgPrint("quos = " + quos);
                        ans = String.valueOf(quos);
                        dbgPrint("ans = " + ans);
                        ans += String.valueOf(rmods);
                        dbgPrint("ans = " + ans);
                        ai = 0;
                        c = 0;
                    }
                    d = quo;
                }
                d = quo;
            }
        }
        return ans;
    }
}
