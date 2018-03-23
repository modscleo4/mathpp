package com.modscleo4.mathpp;

import com.modscleo4.mathpp.gui.ExceptionDialog;
import com.modscleo4.mathpp.gui.MainWindow;
import com.modscleo4.mathpp.lang.Lang;
import com.modscleo4.mathpp.settings.Settings;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
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
