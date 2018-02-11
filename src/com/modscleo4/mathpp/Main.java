package com.modscleo4.mathpp;

import com.modscleo4.mathpp.gui.MainWindow;
import com.modscleo4.mathpp.lang.Lang;
import com.modscleo4.mathpp.settings.Settings;

public class Main {
    public static void main(String[] args) {
        Settings.checkSettings();
        Lang.loadRes();
        MainWindow.main(null);
    }
}
