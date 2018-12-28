package com.modscleo4.mathpp;

import com.modscleo4.mathpp.gui.ExceptionDialog;
import com.modscleo4.mathpp.gui.MainWindow;
import com.modscleo4.mathpp.lang.Lang;
import com.modscleo4.mathpp.settings.Settings;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Main {
    public static void main(String[] args) {
        try {
            File f = new File("lib/");
            if (!f.exists()) {
                if (f.mkdir()) {
                    f = new File("lib/sqlite-jdbc.jar");
                    if (!f.exists()) {
                        URL website = new URL("http://central.maven.org/maven2/org/xerial/sqlite-jdbc/3.21.0/sqlite-jdbc-3.21.0.jar");
                        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
                        FileOutputStream fos = new FileOutputStream("lib/sqlite-jdbc.jar");
                        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
                    }

                    f = new File("lib/forms_rt.jar");
                    if (!f.exists()) {
                        URL website = new URL("http://central.maven.org/maven2/com/intellij/forms_rt/7.0.3/forms_rt-7.0.3.jar");
                        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
                        FileOutputStream fos = new FileOutputStream("lib/forms_rt.jar");
                        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
                    }
                }
            }
        } catch (IOException e) {
            ExceptionDialog.main(e.getLocalizedMessage(), e.getClass().getName());
        }

        Settings.checkSettings();
        Lang.loadRes();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            ExceptionDialog.main(e.getLocalizedMessage(), e.getClass().getName());
        }

        MainWindow.main(null);
    }
}
