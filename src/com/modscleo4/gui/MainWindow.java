package com.modscleo4.gui;

import com.modscleo4.apps.Settings;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;

import static com.modscleo4.apps.GlobalSettings.*;

import static com.modscleo4.lib.baseConverter.Binary.*;
import static com.modscleo4.lib.baseConverter.Decimal.*;
import static com.modscleo4.lib.baseConverter.Octal.*;
import static com.modscleo4.lib.baseConverter.Hexadecimal.*;

public class MainWindow {
    public int bin, dec, oc;
    public String hex;

    public MainWindow() {
        binary.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent keyEvent) {
                super.keyReleased(keyEvent);
                bin = Integer.valueOf(binary.getText());
                decimal.setText(String.valueOf(bin_to_dec(bin)));
                octal.setText(String.valueOf(bin_to_oc(bin)));
                hexadecimal.setText(bin_to_hex(bin));
            }
        });

        decimal.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent keyEvent) {
                super.keyReleased(keyEvent);
                dec = Integer.valueOf(decimal.getText());
                binary.setText(String.valueOf(dec_to_bin(dec)));
                octal.setText(String.valueOf(dec_to_oc(dec)));
                hexadecimal.setText(dec_to_hex(dec));
            }
        });

        octal.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent keyEvent) {
                super.keyReleased(keyEvent);
                oc = Integer.valueOf(octal.getText());
                binary.setText(String.valueOf(oc_to_bin(oc)));
                decimal.setText(String.valueOf(oc_to_dec(oc)));
                hexadecimal.setText(oc_to_hex(oc));
            }
        });
        
        hexadecimal.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent keyEvent) {
                super.keyReleased(keyEvent);
                hex = hexadecimal.getText();
                binary.setText(String.valueOf(hex_to_bin(hex)));
                decimal.setText(String.valueOf(hex_to_dec(hex)));
                octal.setText(String.valueOf(hex_to_oc(hex)));
            }
        });

        saveConfigBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                showBase = showBaseConfig.isSelected();
                if (radioEN.isSelected())
                    lang = "_en";
                else if (radioPT.isSelected())
                    lang = "_pt";
                debug = debugConfig.isSelected();
                limitMax = Integer.valueOf(textLimitMax.getText());
                limitMin = Integer.valueOf(textLimitMin.getText());
                showC = showCConfig.isSelected();
                try {
                    Settings.main(null);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        // Load config
        showBaseConfig.setSelected(showBase);
        if (lang.equals("_en"))
            radioEN.setSelected(true);
        else if (lang.equals("_pt"))
            radioPT.setSelected(true);

        debugConfig.setSelected(debug);
        textLimitMax.setText(String.valueOf(limitMax));
        textLimitMin.setText(String.valueOf(limitMin));
        showCConfig.setSelected(showC);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainWindow");
        frame.setContentPane(new MainWindow().jpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel jpanel;
    private JTabbedPane mainTabPane;
    private JPanel tabBase;
    private JPanel tabMatrix;
    private JPanel tabConfig;
    private JTabbedPane ConfigTabPane;
    private JPanel configtabBasic;
    private JCheckBox showBaseConfig;
    private JTextField binary;
    private JTextField decimal;
    private JTextField octal;
    private JTextField hexadecimal;
    private JTabbedPane MatrixTabPane;
    private JCheckBox showCConfig;
    private JPanel configtabLang;
    private JRadioButton radioEN;
    private JRadioButton radioPT;
    private JTextField textLimitMax;
    private JTextField textLimitMin;
    private JCheckBox debugConfig;
    private JButton saveConfigBtn;
    private JPanel tabDeterminant;
    private JPanel matrixPanelDet;
    private JButton CreateDet;
    private JTextField detSize;
}
