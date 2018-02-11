package com.modscleo4;

import com.modscleo4.mathpp.lib.apps.*;
import com.modscleo4.gui.MainWindow;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static boolean firstRun = true;
    /*private static void options() throws IOException {
        print(readLine(29));
        print(readLine(30));
        print(readLine(31));
        print(readLine(32));
        print(readLine(33));
        print(readLine(34));
        print(readLine(35));
    }*/
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        boolean valid = false;
        GlobalSettings.main(null);
        MainWindow.main(null);
        /*String choose = "";
        if (firstRun)
            print(readLine(27));
        else
            print(readLine(28));
            options();
        choose = read(choose);
        while (!valid) {
            switch (choose) {
                case "d":
                case "D": {
                    valid = true;
                    firstRun = false;
                    Scan.main();
                    break;
                }
                case "m":
                case "M": {
                    valid = true;
                    firstRun = false;
                    Matrix.main(null);
                    break;
                }
                case "c":
                case "C": {
                    valid = true;
                    firstRun = false;
                    Converter.main(null);
                    break;
                }
                case "s":
                case "S": {
                    valid = true;
                    firstRun = false;
                    Settings.main(null);
                    break;
                }
                case "g":
                case "G": {
                    valid = true;
                    firstRun = false;
                    GlobalSettings.showGlobalSettings();
                    break;
                }
                case "l":
                case "L": {
                    valid = true;
                    firstRun = false;
                    LangEditor.main(null);
                    break;
                }
                case "q":
                case "Q": {
                    valid = true;
                    break;
                }
                default: {
                    valid = false;
                    print(readLine(36));
                    options();
                    choose = read(choose);
                    break;
                }
            }
        }*/
        /*boolean istrue = condition;
        char[] arr = String.valueOf(condition).toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 't')
                if (arr[i + 1] == 'r')
                    if (arr[i + 2] == 'u')
                        if (arr[i + 3] == 'e')
                            istrue = !(!Boolean.valueOf("True"));
        }
        if (((istrue) ? 1 : 0) == Integer.valueOf("1")) {
            //
        }*/
    }
}
