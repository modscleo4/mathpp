package com.modscleo4.apps;

import com.modscleo4.Main;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

import static com.modscleo4.readWrite.fileReadWrite.*;
import static com.modscleo4.readWrite.textReadWrite.*;
import static com.modscleo4.apps.GlobalSettings.*;

public class Settings {
    //private static String settings;

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        //settingsChecker();
        Class.forName("org.sqlite.JDBC");
        Connection c = DriverManager.getConnection("jdbc:sqlite:settings.db");
        Statement stmt = c.createStatement();
        Path path = Paths.get("settings.db");

        stmt.executeUpdate("UPDATE settings set showBase = " + ((showBase) ? "1" : "0") +
                ", lang = '" + lang +
                "', debug = " + ((debug) ? "1" : "0") +
                ", limitMax = " + limitMax +
                ", limitMin = " + limitMin +
                ", showC = " + ((showC) ? "1" : "0") + ";");

        stmt.close();
        c.close();

        /*print(readLine(52));
        print(readLine(51));
        print(readLine(28));
        print(settings);
        print(readLine(58));
        print(readLine(28));*/
        //settings = readConfig();
        //fileWrite("settings.txt", settings, ";");
        //Main.main(null);
    }

    static void settingsChecker() throws IOException, SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection c = DriverManager.getConnection("jdbc:sqlite:settings.db");
        Statement stmt = c.createStatement();
        Path path = Paths.get("settings.db");

        if (Files.size(path) > 0) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM settings;");
            while (rs.next()) {
                showBase = rs.getBoolean("showBase");
                lang = rs.getString("lang");
                debug = rs.getBoolean("debug");
                limitMax = rs.getInt("limitMax");
                limitMin = rs.getInt("limitMin");
                showC = rs.getBoolean("showC");
            }
            rs.close();
        } else {
            stmt.executeUpdate("CREATE TABLE settings (" +
                    "showBase BOOLEAN NOT NULL, " +
                    "lang CHAR(50) NOT NULL, " +
                    "debug BOOLEAN NOT NULL, " +
                    "limitMax INT NOT NULL, " +
                    "limitMin INT NOT NULL, " +
                    "showC BOOLEAN NOT NULL" +
                    ");");

            stmt.close();
            stmt.executeUpdate("INSERT INTO settings (showBase, lang, debug, limitMax, limitMin, showC) VALUES (1, '_en', 0, 20, -20, 0);");
            showBase = true;
            lang = "_en";
            debug = false;
            limitMax = 20;
            limitMin = -20;
            showC = false;
        }
        Path lang_en = Paths.get("text_en.txt");
        Path lang_pt = Paths.get("text_pt.txt");
        if (!Files.isRegularFile(lang_en)) {
            //
            //fileWrite("text_en.txt", langEn, ";");
        }

        if (!Files.isRegularFile(lang_pt)) {
            //fileWrite("text_pt.txt", langPt, ";");
        }
        stmt.close();
        c.close();
    }
}

