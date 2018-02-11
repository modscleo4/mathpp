package com.modscleo4.mathpp.lib.baseConverter.utils;

public class Hex {
    public static String hexa(int a) {
        String result = "";
        switch (a) {
            case 10:
                result = "A";
                break;
            case 11:
                result = "B";
                break;
            case 12:
                result = "C";
                break;
            case 13:
                result = "D";
                break;
            case 14:
                result = "E";
                break;
            case 15:
                result = "F";
                break;
        }
        return result;
    }

//Function to convert letters to numbers.
    public static int hexb(String a) {
        int result;
        switch (a) {
            case "a":
            case "A":
                result = 10;
                break;
            case "b":
            case "B":
                result = 11;
                break;
            case "c":
            case "C":
                result = 12;
                break;
            case "d":
            case "D":
                result = 13;
                break;
            case "e":
            case "E":
                result = 14;
                break;
            case "f":
            case "F":
                result = 15;
                break;
            default: {
                result = Integer.valueOf(a);
                break;
            }
        }
        return result;
    }
}
