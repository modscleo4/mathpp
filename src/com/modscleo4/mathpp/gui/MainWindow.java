package com.modscleo4.mathpp.gui;

import com.modscleo4.mathpp.lang.Lang;
import com.modscleo4.mathpp.lib.matrix.Matrix;
import com.modscleo4.mathpp.lib.matrix.MatrixException;
import com.modscleo4.mathpp.settings.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.ThreadLocalRandom;

import static com.modscleo4.mathpp.lang.Lang.*;
import static com.modscleo4.mathpp.settings.GlobalSettings.*;

public class MainWindow {
    private String bin, dec, oc, hex;

    public MainWindow() {
        loadText();

        binary.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent keyEvent) {
                super.keyReleased(keyEvent);
                bin = binary.getText().replace(" ", "");
                if (!bin.equals("")) {
                    try {
                        decimal.setText(String.valueOf(Long.parseLong(bin, 2)));
                        octal.setText(Long.toOctalString(Long.parseLong(bin, 2)));
                        hexadecimal.setText(Long.toHexString(Long.parseLong(bin, 2)));
                    } catch (NumberFormatException e) {
                        ExceptionDialog.main(e.getLocalizedMessage(), e.getClass().getName());
                    }
                }
            }
        });

        decimal.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent keyEvent) {
                super.keyReleased(keyEvent);
                dec = decimal.getText().replace(" ", "");
                if (!dec.equals("")) {
                    try {
                        binary.setText(Long.toBinaryString(Long.parseLong(dec)));
                        octal.setText(Long.toOctalString(Long.parseLong(dec)));
                        hexadecimal.setText(Long.toHexString(Long.parseLong(dec)));
                    } catch (NumberFormatException e) {
                        ExceptionDialog.main(e.getLocalizedMessage(), e.getClass().getName());
                    }
                }
            }
        });

        octal.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent keyEvent) {
                super.keyReleased(keyEvent);
                oc = octal.getText().replace(" ", "");
                if (!oc.equals("")) {
                    try {
                        binary.setText(Long.toBinaryString(Long.parseLong(oc, 8)));
                        decimal.setText(String.valueOf(Long.parseLong(oc, 8)));
                        hexadecimal.setText(Long.toHexString(Long.parseLong(oc, 8)));
                    } catch (NumberFormatException e) {
                        ExceptionDialog.main(e.getLocalizedMessage(), e.getClass().getName());
                    }
                }
            }
        });
        
        hexadecimal.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent keyEvent) {
                super.keyReleased(keyEvent);
                hex = hexadecimal.getText().replace(" ", "");
                if (!hex.equals("")) {
                    try {
                        binary.setText(Long.toBinaryString(Long.parseLong(hex, 16)));
                        decimal.setText(String.valueOf(Long.parseLong(hex, 16)));
                        octal.setText(Long.toOctalString(Long.parseLong(hex, 16)));
                    } catch (NumberFormatException e) {
                        ExceptionDialog.main(e.getLocalizedMessage(), e.getClass().getName());
                    }
                }
            }
        });

        saveConfigBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
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

        createDet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!detSize.getText().isEmpty()) {
                    int size;
                    try {
                        size = Integer.valueOf(detSize.getText());
                        if (size < 1) {
                            throw new MatrixException(size + " is not a valid Matrix size");
                        }
                    } catch (NumberFormatException | MatrixException e) {
                        ExceptionDialog.main(e.getLocalizedMessage(), e.getClass().getName());
                        return;
                    }
                    matrixPanelDet.removeAll();
                    matrixPanelDet.revalidate();
                    matrixDetArr = new JTextField[size][size];
                    for (int i = 0; i < size; i++) {
                        for (int j = 0; j < size; j++) {
                            matrixDetArr[i][j] = new JTextField((detRandom.isSelected()) ? ("" + ThreadLocalRandom.current().nextDouble(limitMin, limitMax + 1)) : "");
                        }
                    }

                    matrixPanelDet.setLayout(new GridLayout(size, size));
                    for (int i = 0; i < size; i++) {
                        for (int j = 0; j < size; j++) {
                            matrixPanelDet.add(matrixDetArr[i][j]);
                        }
                    }

                    matrixPanelDet.repaint();
                }
            }
        });
        detCalc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (matrixDetArr != null) {
                    int size;
                    try {
                        size = Integer.valueOf(detSize.getText());
                        if (size < 1) {
                            throw new MatrixException(size + " is not a valid Matrix size");
                        }
                    } catch (NumberFormatException | MatrixException e) {
                        ExceptionDialog.main(e.getLocalizedMessage(), e.getClass().getName());
                        return;
                    }
                    double[][] matrix = new double[size][size];
                    boolean isFilled = true;
                    for (int i = 0; i < size; i++) {
                        for (int j = 0; j < size; j++) {
                            if (isFilled) {
                                try {
                                    matrix[i][j] = Double.valueOf(matrixDetArr[i][j].getText());
                                } catch (NumberFormatException e) {
                                    isFilled = false;
                                }

                            }
                        }
                    }
                    MatADialog.matA = new Matrix(matrix);

                    if (isFilled) {
                        detRes.setVisible(true);
                        try {
                            detRes.setText(String.format("Determinante: %.3f", new Matrix(matrix).determinant()));
                        } catch (MatrixException e) {
                            ExceptionDialog.main(e.getLocalizedMessage(), e.getClass().getName());
                        }
                    }
                }
            }
        });

        multMatA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MatADialog.main();
            }
        });
        multMatB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MatBDialog.main();
            }
        });
        matMultCalc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    double[][] arr;
                    try {
                        arr = MatADialog.matA.multiply(MatBDialog.matB).toDoubleArray();
                    } catch (MatrixException e) {
                        ExceptionDialog.main(e.getLocalizedMessage(), e.getClass().getName());
                        return;
                    }
                    matrixMultPanel.removeAll();
                    matrixMultPanel.revalidate();
                    matrixMultArr = new JTextField[arr.length][arr[0].length];

                    for (int i = 0; i < arr.length; i++) {
                        for (int j = 0; j < arr[0].length; j++) {
                            matrixMultArr[i][j] = new JTextField(String.valueOf(arr[i][j]));
                        }
                    }

                    matrixMultPanel.setLayout(new GridLayout(arr.length, arr[0].length));
                    for (int i = 0; i < arr.length; i++) {
                        for (int j = 0; j < arr[0].length; j++) {
                            matrixMultPanel.add(matrixMultArr[i][j]);
                        }
                    }

                    matrixMultPanel.repaint();
                } catch (NullPointerException e) {
                    ExceptionDialog.main(e.getLocalizedMessage(), e.getClass().getName());
                }
            }
        });

        sumMatA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MatADialog.main();
            }
        });
        sumMatB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MatBDialog.main();
            }
        });
        matSumCalc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    double[][] arr;
                    try {
                        arr = MatADialog.matA.sum(MatBDialog.matB).toDoubleArray();
                    } catch (MatrixException e) {
                        ExceptionDialog.main(e.getLocalizedMessage(), e.getClass().getName());
                        return;
                    }
                    matrixSumPanel.removeAll();
                    matrixSumPanel.revalidate();
                    matrixSumArr = new JTextField[arr.length][arr[0].length];

                    for (int i = 0; i < arr.length; i++) {
                        for (int j = 0; j < arr[0].length; j++) {
                            matrixSumArr[i][j] = new JTextField(String.valueOf(arr[i][j]));
                        }
                    }

                    matrixSumPanel.setLayout(new GridLayout(arr.length, arr[0].length));
                    for (int i = 0; i < arr.length; i++) {
                        for (int j = 0; j < arr[0].length; j++) {
                            matrixSumPanel.add(matrixSumArr[i][j]);
                        }
                    }

                    matrixSumPanel.repaint();
                } catch (NullPointerException e) {
                    ExceptionDialog.main(e.getLocalizedMessage(), e.getClass().getName());
                }
            }
        });

        subMatA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MatADialog.main();
            }
        });
        subMatB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MatBDialog.main();
            }
        });
        matSubCalc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    double[][] arr;
                    try {
                        arr = MatADialog.matA.subtract(MatBDialog.matB).toDoubleArray();
                    } catch (MatrixException e) {
                        ExceptionDialog.main(e.getLocalizedMessage(), e.getClass().getName());
                        return;
                    }
                    matrixSubPanel.removeAll();
                    matrixSubPanel.revalidate();
                    matrixSubtArr = new JTextField[arr.length][arr[0].length];

                    for (int i = 0; i < arr.length; i++) {
                        for (int j = 0; j < arr[0].length; j++) {
                            matrixSubtArr[i][j] = new JTextField(String.valueOf(arr[i][j]));
                        }
                    }

                    matrixSubPanel.setLayout(new GridLayout(arr.length, arr[0].length));
                    for (int i = 0; i < arr.length; i++) {
                        for (int j = 0; j < arr[0].length; j++) {
                            matrixSubPanel.add(matrixSubtArr[i][j]);
                        }
                    }

                    matrixSubPanel.repaint();
                } catch (NullPointerException e) {
                    ExceptionDialog.main(e.getLocalizedMessage(), e.getClass().getName());
                }
            }
        });

        transpMatA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MatADialog.main();
            }
        });
        matTranspCalc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    double[][] arr;
                    try {
                        arr = MatADialog.matA.transpose().toDoubleArray();
                    } catch (MatrixException e) {
                        ExceptionDialog.main(e.getLocalizedMessage(), e.getClass().getName());
                        return;
                    }
                    matrixTranspPanel.removeAll();
                    matrixTranspPanel.revalidate();
                    matrixTranspArr = new JTextField[arr.length][arr[0].length];

                    for (int i = 0; i < arr.length; i++) {
                        for (int j = 0; j < arr[0].length; j++) {
                            matrixTranspArr[i][j] = new JTextField(String.valueOf(arr[i][j]));
                        }
                    }

                    matrixTranspPanel.setLayout(new GridLayout(arr.length, arr[0].length));
                    for (int i = 0; i < arr.length; i++) {
                        for (int j = 0; j < arr[0].length; j++) {
                            matrixTranspPanel.add(matrixTranspArr[i][j]);
                        }
                    }

                    matrixTranspPanel.repaint();
                } catch (NullPointerException e) {
                    ExceptionDialog.main(e.getLocalizedMessage(), e.getClass().getName());
                }
            }
        });

        invMatA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MatADialog.main();
            }
        });
        matInvCalc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    double[][] arr;
                    try {
                        arr = MatADialog.matA.inverse().toDoubleArray();
                    } catch (MatrixException e) {
                        ExceptionDialog.main(e.getLocalizedMessage(), e.getClass().getName());
                        return;
                    }
                    matrixInvPanel.removeAll();
                    matrixInvPanel.revalidate();
                    matrixInvArr = new JTextField[arr.length][arr[0].length];

                    for (int i = 0; i < arr.length; i++) {
                        for (int j = 0; j < arr[0].length; j++) {
                            matrixInvArr[i][j] = new JTextField(String.valueOf(arr[i][j]));
                        }
                    }

                    matrixInvPanel.setLayout(new GridLayout(arr.length, arr[0].length));
                    for (int i = 0; i < arr.length; i++) {
                        for (int j = 0; j < arr[0].length; j++) {
                            matrixInvPanel.add(matrixInvArr[i][j]);
                        }
                    }

                    matrixInvPanel.repaint();
                } catch (NullPointerException e) {
                    ExceptionDialog.main(e.getLocalizedMessage(), e.getClass().getName());
                }
            }
        });
        binary.addKeyListener(new KeyAdapter() {
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
        detCalc.setText(resCalc);

        multMatA.setText(resMatA);
        multMatB.setText(resMatB);
        matMultCalc.setText(resCalc);

        sumMatA.setText(resMatA);
        sumMatB.setText(resMatB);
        matSumCalc.setText(resCalc);

        subMatA.setText(resMatA);
        subMatB.setText(resMatB);
        matSubCalc.setText(resCalc);
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
    private JButton multMatA;
    private JButton multMatB;
    private JButton matMultCalc;
    private JPanel matrixMultPanel;
    private JButton sumMatA;
    private JButton sumMatB;
    private JButton matSumCalc;
    private JButton subMatA;
    private JButton subMatB;
    private JButton matSubCalc;
    private JPanel matrixSumPanel;
    private JPanel matrixSubPanel;
    private JButton transpMatA;
    private JButton matTranspCalc;
    private JButton invMatA;
    private JButton matInvCalc;
    private JPanel matrixInvPanel;
    private JPanel matrixTranspPanel;

    private JTextField[][] matrixDetArr;
    private JTextField[][] matrixMultArr;
    private JTextField[][] matrixSumArr;
    private JTextField[][] matrixSubtArr;
    private JTextField[][] matrixTranspArr;
    private JTextField[][] matrixInvArr;
}
