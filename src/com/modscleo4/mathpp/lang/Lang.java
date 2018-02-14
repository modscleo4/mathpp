package com.modscleo4.mathpp.lang;

import java.util.Locale;
import java.util.ResourceBundle;

import static com.modscleo4.mathpp.settings.GlobalSettings.lang;

public class Lang {
    public static String resWindowTitle;
    // Main tabs
    public static String resTabBaseConv, resTabMatrix, resTabConf;
    // Base Converter tab
    public static String resLabelBinary, resLabelDecimal, resLabelOctal, resLabelHex;
    // Matrix subtabs
    public static String resTabMatDeterminant, resTabMatMultiply, resTabMatSum, resTabMatSubt, resTabMatTransp, resTabMatInv;
    // Determinant subtab
    public static String resLabelDetSize, resDetRandom, resCreateDet;
    // Matrix general
    public static String resMatA, resMatB, resCalc;
    // Matrix dialog
    public static String resMatLines, resMatColumns;
    public static String resOk, resCancel;

    public static String resMenuFile, resMenuAbout;

    public static void loadRes() {
        ResourceBundle langBundle;
        Locale locale;

        switch (lang) {
            case "en_US":
                locale = new Locale("en", "US");
                break;
            case "pt_BR":
                locale = new Locale("pt", "BR");
                break;
            default:
                locale = Locale.getDefault();
                break;
        }

        langBundle = ResourceBundle.getBundle("com.modscleo4.mathpp.lang", locale);

        resWindowTitle = langBundle.getString("windowTitle");

        // Main tabs
        resTabBaseConv = langBundle.getString("tabBaseConv");
        resTabMatrix = langBundle.getString("tabMatrix");
        resTabConf = langBundle.getString("tabConf");

        // Base Converter tab
        resLabelBinary = langBundle.getString("labelBinary");
        resLabelDecimal = langBundle.getString("labelDecimal");
        resLabelOctal = langBundle.getString("labelOctal");
        resLabelHex = langBundle.getString("labelHex");

        // Matrix subtabs
        resTabMatDeterminant = langBundle.getString("tabMatDeterminant");
        resTabMatMultiply = langBundle.getString("tabMatMultiply");
        resTabMatSum = langBundle.getString("tabMatSum");
        resTabMatSubt = langBundle.getString("tabMatSubt");
        resTabMatTransp = langBundle.getString("tabMatTransp");
        resTabMatInv = langBundle.getString("tabMatInv");

        // Determinant subtab
        resLabelDetSize = langBundle.getString("labelDetSize");
        resDetRandom = langBundle.getString("detRandom");
        resCreateDet = langBundle.getString("createDet");

        // Matrix general
        resCalc = langBundle.getString("calc");
        resMatA = langBundle.getString("matA");
        resMatB = langBundle.getString("matB");

        // Dialog
        resMatLines = langBundle.getString("matLines");
        resMatColumns = langBundle.getString("matColumns");
        resOk = langBundle.getString("ok");
        resCancel = langBundle.getString("cancel");

        resMenuFile = langBundle.getString("menuFile");
        resMenuAbout = langBundle.getString("menuAbout");
    }
}
