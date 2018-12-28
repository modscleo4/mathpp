package com.modscleo4.mathpp.settings;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;

import static com.modscleo4.mathpp.settings.GlobalSettings.*;

public class Settings {
    public static void saveSettings() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite:settings.db");
            Statement stmt = c.createStatement();

            stmt.executeUpdate("UPDATE settings set showBase = " + ((showBase) ? "1" : "0") +
                    ", lang = '" + lang +
                    "', debug = " + ((debug) ? "1" : "0") +
                    ", limitMax = " + limitMax +
                    ", limitMin = " + limitMin +
                    ", showC = " + ((showC) ? "1" : "0") + ";");

            stmt.close();
            c.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void checkSettings() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite:settings.db");
            Statement stmt = c.createStatement();

            if (Files.size(Paths.get("settings.db")) > 0) {
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
                stmt.executeUpdate("INSERT INTO settings (showBase, lang, debug, limitMax, limitMin, showC) VALUES (1, 'en_US', 0, 20, -20, 0);");
                showBase = true;
                lang = "en_US";
                debug = false;
                limitMax = 20;
                limitMin = -20;
                showC = false;
            }

            stmt.close();
            c.close();
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}

