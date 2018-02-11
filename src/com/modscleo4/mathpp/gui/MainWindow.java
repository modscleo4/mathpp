package com.modscleo4.mathpp.gui;

import com.modscleo4.mathpp.lang.Lang;
import com.modscleo4.mathpp.lib.matrix.InvalidMatrixException;
import com.modscleo4.mathpp.settings.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.ThreadLocalRandom;

import static com.modscleo4.mathpp.lib.baseConverter.Binary.*;
import static com.modscleo4.mathpp.lib.baseConverter.Decimal.*;
import static com.modscleo4.mathpp.lib.baseConverter.Hexadecimal.*;
import static com.modscleo4.mathpp.lib.baseConverter.Octal.*;
import com.modscleo4.mathpp.lib.matrix.Matrix;
import static com.modscleo4.mathpp.settings.GlobalSettings.*;
import static com.modscleo4.mathpp.lang.Lang.*;

public class MainWindow {
    private int bin, dec, oc;
    private String hex;

    public MainWindow() {
        loadText();

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
                    lang = "en_US";
                else if (radioPT.isSelected())
                    lang = "pt_BR";
                debug = debugConfig.isSelected();
                limitMax = Integer.valueOf(textLimitMax.getText());
                limitMin = Integer.valueOf(textLimitMin.getText());
                showC = showCConfig.isSelected();
                Settings.saveSettings();
                Lang.loadRes();
                loadText();
            }
        });

        // Load config
        showBaseConfig.setSelected(showBase);
        if (lang.equals("en_US"))
            radioEN.setSelected(true);
        else if (lang.equals("pt_BR"))
            radioPT.setSelected(true);

        debugConfig.setSelected(debug);
        textLimitMax.setText(String.valueOf(limitMax));
        textLimitMin.setText(String.valueOf(limitMin));
        showCConfig.setSelected(showC);

        CreateDet.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                if (!detSize.getText().isEmpty()) {
                    int size = Integer.valueOf(detSize.getText());
                    matrixPanelDet.removeAll();
                    matrixPanelDet.revalidate();
                    matrixDetArr = new JTextField[size][size];
                    for (int i = 0; i < size; i++)
                        for (int j = 0; j < size; j++)
                            matrixDetArr[i][j] = new JTextField( (detRandom.isSelected()) ? ("" + ThreadLocalRandom.current().nextDouble(limitMin, limitMax + 1)) : "" );

                    matrixPanelDet.setLayout(new GridLayout(size, size));
                    for (int i = 0; i < size; i++)
                        for (int j = 0; j < size; j++)
                        matrixPanelDet.add(matrixDetArr[i][j]);

                    matrixPanelDet.repaint();
                }
            }
        });

        detCalc.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                if (matrixDetArr != null) {
                    int size = Integer.valueOf(detSize.getText());
                    double[][] matrix = new double[size][size];
                    boolean isFilled = true;
                    for (int i = 0; i < size; i++)
                        for (int j = 0; j < size; j++)
                            if (isFilled) {
                                try {
                                    matrix[i][j] = Double.valueOf(matrixDetArr[i][j].getText());
                                } catch (NumberFormatException e) {
                                    isFilled = false;
                                }

                            }

                    if (isFilled) {
                        detRes.setVisible(true);
                        try {
                            detRes.setText(String.format("Determinante: %.3f", new Matrix(size, size, matrix).determinant()));
                        } catch (InvalidMatrixException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    private void loadText() {
        mainTabPane.setTitleAt(0, resTabBaseConv);
        mainTabPane.setTitleAt(1, resTabMatrix);
        mainTabPane.setTitleAt(2, resTabConf);

        labelBin.setText(resLabelBinary);
        labelDec.setText(resLabelDecimal);
        labelOc.setText(resLabelOctal);
        labelHex.setText(resLabelHex);

        matrixTabPane.setTitleAt(0, resTabMatDeterminant);

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame(resWindowTitle);
        frame.setContentPane(new MainWindow().jpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        // Menu bar
        JMenuBar menuBar;
        JMenu menuFile, menuHelp;
        menuBar = new JMenuBar();

        menuFile = new JMenu(resMenuFile);
        menuHelp = new JMenu(resMenuAbout);
        menuFile.setMnemonic(KeyEvent.VK_F);
        menuHelp.setMnemonic(KeyEvent.VK_H);

        menuBar.add(menuFile);
        menuBar.add(menuHelp);

        JMenuItem fileExit = new JMenuItem("Sair");

        JMenuItem helpAbout = new JMenuItem("Sobre");

        menuFile.add(fileExit);
        menuHelp.add(helpAbout);

        frame.setJMenuBar(menuBar);

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
    private JTabbedPane matrixTabPane;
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
    private JCheckBox detRandom;
    private JButton detCalc;
    private JLabel detRes;
    private JPanel tabMultiply;
    private JPanel tabTranspose;
    private JPanel tabInverse;
    private JLabel labelBin;
    private JLabel labelDec;
    private JLabel labelOc;
    private JLabel labelHex;
    private JPanel tabSum;
    private JPanel tabSubtract;
    private JLabel labelDetSize;

    private JTextField[][] matrixDetArr;
}
