package com.modscleo4.mathpp.gui;

import com.modscleo4.mathpp.lang.Lang;
import com.modscleo4.mathpp.lib.baseConverter.*;
import com.modscleo4.mathpp.lib.matrix.InvalidMatrixException;
import com.modscleo4.mathpp.lib.matrix.Matrix;
import com.modscleo4.mathpp.settings.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.ThreadLocalRandom;

import static com.modscleo4.mathpp.lang.Lang.*;
import static com.modscleo4.mathpp.settings.GlobalSettings.*;

public class MainWindow {
    private long bin, dec, oc;
    private String hex;

    public MainWindow() {
        loadText();

        binary.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent keyEvent) {
                super.keyReleased(keyEvent);
                bin = Long.valueOf(binary.getText());
                try {
                    decimal.setText(String.valueOf(new Binary(bin).toDecimal().toLong()));
                    octal.setText(String.valueOf(new Binary(bin).toOctal().toLong()));
                    hexadecimal.setText(new Binary(bin).toHex().toString());
                } catch (NumberBaseException e) {
                    e.printStackTrace();
                }
            }
        });

        decimal.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent keyEvent) {
                super.keyReleased(keyEvent);
                dec = Long.valueOf(decimal.getText());
                try {
                    binary.setText(String.valueOf(new Decimal(dec).toBinary().toLong()));
                    octal.setText(String.valueOf(new Decimal(dec).toOctal().toLong()));
                    hexadecimal.setText(new Decimal(dec).toHex().toString());
                } catch (NumberBaseException e) {
                    e.printStackTrace();
                }
            }
        });

        octal.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent keyEvent) {
                super.keyReleased(keyEvent);
                oc = Long.valueOf(octal.getText());
                try {
                    binary.setText(String.valueOf(new Octal(oc).toBinary().toLong()));
                    decimal.setText(String.valueOf(new Octal(oc).toDecimal().toLong()));
                    hexadecimal.setText(new Octal(oc).toHex().toString());
                } catch (NumberBaseException e) {
                    e.printStackTrace();
                }
            }
        });
        
        hexadecimal.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent keyEvent) {
                super.keyReleased(keyEvent);
                hex = hexadecimal.getText();
                try {
                    binary.setText(String.valueOf(new Hexadecimal(hex).toBinary().toLong()));
                    decimal.setText(String.valueOf(new Hexadecimal(hex).toDecimal().toLong()));
                    octal.setText(String.valueOf(new Hexadecimal(hex).toOctal().toLong()));
                } catch (NumberBaseException e) {
                    e.printStackTrace();
                }
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

        createDet.addMouseListener(new MouseAdapter() {
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
                            detRes.setText(String.format("Determinante: %.3f", new Matrix(matrix).determinant()));
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
        matrixTabPane.setTitleAt(1, resTabMatMultiply);
        matrixTabPane.setTitleAt(2, resTabMatSum);
        matrixTabPane.setTitleAt(3, resTabMatSubt);
        matrixTabPane.setTitleAt(4, resTabMatTransp);
        matrixTabPane.setTitleAt(5, resTabMatInv);

        labelDetSize.setText(resLabelDetSize);
        detRandom.setText(resDetRandom);
        createDet.setText(resCreateDet);
        detCalc.setText(resDetCalc);
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
    private JButton createDet;
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
