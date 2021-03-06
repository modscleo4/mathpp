package com.modscleo4.mathpp.lib.baseConverter;

import org.jetbrains.annotations.NotNull;

import static com.modscleo4.mathpp.lib.baseConverter.utils.Hex.hexa;

/**
 * Decimal.java
 * Purpose: Provides number base conversion (decimal to bin, oc, hex)
 *
 * @deprecated
 * @author Dhiego Cassiano Fogaça Barbosa
 * @version 1.0
 */
public class Decimal {
    private long value;

    /**
     * Constructor method
     *
     * @param value The decimal value in a long var
     */
    public Decimal(@NotNull long value) {
        this.value = value;
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
     * Converts this Decimal to Binary
     *
     * @return The Binary equivalent
     */
    public Binary toBinary() {
        long c = 2, mod, quo = 2, ai = 2, d = this.toLong();
        String r, mods = "", rmods, ans = "";
        while (c > 1) {
            if (d == 1 || d == 0) {
                return new Binary(d);
            } else {
                while (ai > 1) {
                    mod = d % 2;
                    quo = d / 2;
                    r = String.valueOf(mod);
                    mods += r;

                    if (quo == 0 || quo == 1) {
                        rmods = new StringBuilder(mods).reverse().toString();
                        ans = String.valueOf(quo);
                        ans += String.valueOf(rmods);
                        ai = 0;
                        c = 0;
                    }
                    d = quo;
                }
                d = quo;
            }
        }
        return new Binary(Long.valueOf(ans));
    }

    /**
     * Converts this Decimal to Octal
     *
     * @return The Octal equivalent
     */
    public Octal toOctal() {
        long c = 2, mod, quo = 8, ai = 8, d = this.toLong();

        String r, mods = "", rmods, ans = "";
        while (c > 1) {
            if (d >= 0 && d <= 7) {
                return new Octal(d);
            } else {
                while (ai > 1) {
                    mod = d % 8;
                    quo = d / 8;
                    r = String.valueOf(mod);
                    mods += r;

                    if (quo >= 0 && quo <= 7) {
                        rmods = new StringBuilder(mods).reverse().toString();
                        ans = String.valueOf(quo);
                        ans += String.valueOf(rmods);
                        ai = 0;
                        c = 0;
                    }
                    d = quo;
                }
                d = quo;
            }
        }
        return new Octal(Long.valueOf(ans));
    }

    /**
     * Converts this Decimal to Hexadecimal
     *
     * @return The Hexadecimal equivalent
     */
    public Hexadecimal toHex() {
        long c = 2, mod, quo = 16, ai = 16, d = this.toLong();

        String aad, modu, quos, rmods, ans = "", mods = "";
        while (c > 1) {
            if (d < 10) {
                return new Hexadecimal(String.valueOf(d));
            } else if (d < 16) {
                aad = hexa((int)d);
                return new Hexadecimal(aad);
            } else {
                while (ai > 1) {
                    mod = d % 16;
                    quo = d / 16;
                    if (mod > 9 && mod < 16) {
                        modu = hexa((int)mod);
                        mods += modu;
                    } else
                        mods += mod;

                    if (quo >= 0 && quo <= 9) {
                        rmods = new StringBuilder(mods).reverse().toString();
                        ans = String.valueOf(quo);
                        ans += String.valueOf(rmods);
                        ai = 0;
                        c = 0;
                    } else if (quo > 9 && quo < 16) {
                        rmods = new StringBuilder(mods).reverse().toString();
                        quos = hexa((int)quo);
                        ans = String.valueOf(quos);
                        ans += String.valueOf(rmods);
                        ai = 0;
                        c = 0;
                    }
                    d = quo;
                }
                d = quo;
            }
        }
        return new Hexadecimal(ans);
    }
}
