package com.modscleo4.apps;

import com.modscleo4.Main;

import java.io.IOException;
import java.sql.SQLException;

import static com.modscleo4.Main.firstRun;
import static com.modscleo4.readWrite.textReadWrite.print;

public class GlobalSettings {
    public static boolean showBase = true;
    public static String lang = "_en";
    public static boolean debug = true;
    public static int limitMin = -20;
    public static int limitMax = 20;
    public static boolean showC = true;

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        Settings.settingsChecker();
    }

    public static void showGlobalSettings() throws IOException, SQLException, ClassNotFoundException {
        Settings.settingsChecker();
        print("firstRun = " + firstRun);
        print("showBase = " + showBase);
        print("lang = " + lang);
        print("debug = " + debug);
        print("limitMax = " + limitMax);
        print("limitMin = " + limitMin);
        print("showC = " + showC);
        Main.main(null);
    }

}