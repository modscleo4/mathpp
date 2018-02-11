package com.modscleo4.mathpp.lang;

import java.util.Locale;
import java.util.ResourceBundle;

import static com.modscleo4.mathpp.settings.GlobalSettings.lang;

public class Lang {
    public static String resWindowTitle;
    public static String resTabBaseConv, resTabMatrix, resTabConf;
    public static String resLabelBinary, resLabelDecimal, resLabelOctal, resLabelHex;

    public static String resTabMatDeterminant;
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

        resTabBaseConv = langBundle.getString("tabBaseConv");
        resTabMatrix = langBundle.getString("tabMatrix");
        resTabConf = langBundle.getString("tabConf");

        resLabelBinary = langBundle.getString("labelBinary");
        resLabelDecimal = langBundle.getString("labelDecimal");
        resLabelOctal = langBundle.getString("labelOctal");
        resLabelHex = langBundle.getString("labelHex");

        resTabMatDeterminant = langBundle.getString("tabMatDeterminant");

        resMenuFile = langBundle.getString("menuFile");
        resMenuAbout = langBundle.getString("menuAbout");
    }
}
