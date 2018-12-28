package com.modscleo4.mathpp.gui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
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

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        jpanel = new JPanel();
        jpanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        jpanel.setMinimumSize(new Dimension(800, 600));
        jpanel.setOpaque(true);
        jpanel.setPreferredSize(new Dimension(800, 600));
        mainTabPane = new JTabbedPane();
        mainTabPane.setTabLayoutPolicy(0);
        mainTabPane.setTabPlacement(1);
        jpanel.add(mainTabPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        tabBase = new JPanel();
        tabBase.setLayout(new GridLayoutManager(5, 3, new Insets(0, 0, 0, 0), -1, -1));
        mainTabPane.addTab("Conversor de base", tabBase);
        binary = new JTextField();
        binary.setMargin(new Insets(0, 0, 0, 0));
        binary.setToolTipText("Binary");
        tabBase.add(binary, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final Spacer spacer1 = new Spacer();
        tabBase.add(spacer1, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        decimal = new JTextField();
        decimal.setMargin(new Insets(0, 0, 0, 0));
        decimal.setToolTipText("Decimal");
        tabBase.add(decimal, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        octal = new JTextField();
        octal.setMargin(new Insets(0, 0, 0, 0));
        octal.setToolTipText("Octal");
        tabBase.add(octal, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        hexadecimal = new JTextField();
        hexadecimal.setMargin(new Insets(0, 0, 0, 0));
        hexadecimal.setToolTipText("Hexadecimal");
        tabBase.add(hexadecimal, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final Spacer spacer2 = new Spacer();
        tabBase.add(spacer2, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        labelBin = new JLabel();
        labelBin.setText("Binário");
        tabBase.add(labelBin, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelDec = new JLabel();
        labelDec.setText("Decimal");
        tabBase.add(labelDec, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelOc = new JLabel();
        labelOc.setText("Octal");
        tabBase.add(labelOc, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelHex = new JLabel();
        labelHex.setText("Hexadecimal");
        tabBase.add(labelHex, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tabMatrix = new JPanel();
        tabMatrix.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainTabPane.addTab("Matrizes", tabMatrix);
        matrixTabPane = new JTabbedPane();
        tabMatrix.add(matrixTabPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        tabDeterminant = new JPanel();
        tabDeterminant.setLayout(new GridLayoutManager(3, 8, new Insets(0, 0, 0, 0), -1, -1));
        matrixTabPane.addTab("Determinante", tabDeterminant);
        matrixPanelDet = new JPanel();
        matrixPanelDet.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabDeterminant.add(matrixPanelDet, new GridConstraints(2, 0, 1, 8, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        detSize = new JTextField();
        tabDeterminant.add(detSize, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        createDet = new JButton();
        createDet.setEnabled(true);
        createDet.setText("Definir tamanho");
        tabDeterminant.add(createDet, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        detRandom = new JCheckBox();
        detRandom.setText("Aleatório");
        tabDeterminant.add(detRandom, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        tabDeterminant.add(spacer3, new GridConstraints(0, 4, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        detCalc = new JButton();
        detCalc.setText("Calcular");
        tabDeterminant.add(detCalc, new GridConstraints(0, 7, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        detRes = new JLabel();
        detRes.setText("Determinante: ");
        detRes.setVisible(false);
        tabDeterminant.add(detRes, new GridConstraints(1, 7, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelDetSize = new JLabel();
        labelDetSize.setText("Label");
        tabDeterminant.add(labelDetSize, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tabMultiply = new JPanel();
        tabMultiply.setLayout(new GridLayoutManager(2, 5, new Insets(0, 0, 0, 0), -1, -1));
        matrixTabPane.addTab("Multiplicação", tabMultiply);
        multMatA = new JButton();
        multMatA.setText("Matriz A");
        tabMultiply.add(multMatA, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        multMatB = new JButton();
        multMatB.setText("Matriz B");
        tabMultiply.add(multMatB, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        tabMultiply.add(spacer4, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        matMultCalc = new JButton();
        matMultCalc.setText("Calcular");
        tabMultiply.add(matMultCalc, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("x");
        tabMultiply.add(label1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        matrixMultPanel = new JPanel();
        matrixMultPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabMultiply.add(matrixMultPanel, new GridConstraints(1, 0, 1, 5, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        tabSum = new JPanel();
        tabSum.setLayout(new GridLayoutManager(2, 5, new Insets(0, 0, 0, 0), -1, -1));
        matrixTabPane.addTab("Soma", tabSum);
        sumMatA = new JButton();
        sumMatA.setText("Matriz A");
        tabSum.add(sumMatA, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        tabSum.add(spacer5, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("+");
        tabSum.add(label2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sumMatB = new JButton();
        sumMatB.setText("Matriz B");
        tabSum.add(sumMatB, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        matSumCalc = new JButton();
        matSumCalc.setText("Calcular");
        tabSum.add(matSumCalc, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        matrixSumPanel = new JPanel();
        matrixSumPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabSum.add(matrixSumPanel, new GridConstraints(1, 0, 1, 5, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        tabSubtract = new JPanel();
        tabSubtract.setLayout(new GridLayoutManager(2, 5, new Insets(0, 0, 0, 0), -1, -1));
        matrixTabPane.addTab("Subtração", tabSubtract);
        subMatA = new JButton();
        subMatA.setText("Matriz A");
        tabSubtract.add(subMatA, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        tabSubtract.add(spacer6, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("-");
        tabSubtract.add(label3, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        subMatB = new JButton();
        subMatB.setText("Matriz B");
        tabSubtract.add(subMatB, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        matSubCalc = new JButton();
        matSubCalc.setText("Calcular");
        tabSubtract.add(matSubCalc, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        matrixSubPanel = new JPanel();
        matrixSubPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabSubtract.add(matrixSubPanel, new GridConstraints(1, 0, 1, 5, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        tabTranspose = new JPanel();
        tabTranspose.setLayout(new GridLayoutManager(2, 4, new Insets(0, 0, 0, 0), -1, -1));
        matrixTabPane.addTab("Transposta", tabTranspose);
        transpMatA = new JButton();
        transpMatA.setText("Matriz A");
        tabTranspose.add(transpMatA, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer7 = new Spacer();
        tabTranspose.add(spacer7, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("t");
        tabTranspose.add(label4, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        matrixTranspPanel = new JPanel();
        matrixTranspPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabTranspose.add(matrixTranspPanel, new GridConstraints(1, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        matTranspCalc = new JButton();
        matTranspCalc.setText("Calcular");
        tabTranspose.add(matTranspCalc, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tabInverse = new JPanel();
        tabInverse.setLayout(new GridLayoutManager(2, 4, new Insets(0, 0, 0, 0), -1, -1));
        matrixTabPane.addTab("Inversa", tabInverse);
        invMatA = new JButton();
        invMatA.setText("Matriz A");
        tabInverse.add(invMatA, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer8 = new Spacer();
        tabInverse.add(spacer8, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("-1");
        tabInverse.add(label5, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        matInvCalc = new JButton();
        matInvCalc.setText("Calcular");
        tabInverse.add(matInvCalc, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        matrixInvPanel = new JPanel();
        matrixInvPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabInverse.add(matrixInvPanel, new GridConstraints(1, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        tabConfig = new JPanel();
        tabConfig.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainTabPane.addTab("Configurações", tabConfig);
        ConfigTabPane = new JTabbedPane();
        tabConfig.add(ConfigTabPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        configtabBasic = new JPanel();
        configtabBasic.setLayout(new GridLayoutManager(6, 3, new Insets(0, 0, 0, 0), -1, -1));
        ConfigTabPane.addTab("Geral", configtabBasic);
        showBaseConfig = new JCheckBox();
        showBaseConfig.setEnabled(false);
        showBaseConfig.setSelected(false);
        showBaseConfig.setText("Exibir a base (console)");
        configtabBasic.add(showBaseConfig, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer9 = new Spacer();
        configtabBasic.add(spacer9, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer10 = new Spacer();
        configtabBasic.add(spacer10, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        showCConfig = new JCheckBox();
        showCConfig.setEnabled(false);
        showCConfig.setText("Exibir as contas (console)");
        configtabBasic.add(showCConfig, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textLimitMax = new JTextField();
        configtabBasic.add(textLimitMax, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), null, 0, false));
        textLimitMin = new JTextField();
        configtabBasic.add(textLimitMin, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Limite máximo");
        configtabBasic.add(label6, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("Limite mínimo");
        configtabBasic.add(label7, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        debugConfig = new JCheckBox();
        debugConfig.setEnabled(false);
        debugConfig.setText("Depuração");
        configtabBasic.add(debugConfig, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        configtabLang = new JPanel();
        configtabLang.setLayout(new GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
        ConfigTabPane.addTab("Idioma", configtabLang);
        radioEN = new JRadioButton();
        radioEN.setSelected(false);
        radioEN.setText("Inglês");
        configtabLang.add(radioEN, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer11 = new Spacer();
        configtabLang.add(spacer11, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer12 = new Spacer();
        configtabLang.add(spacer12, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        radioPT = new JRadioButton();
        radioPT.setSelected(false);
        radioPT.setText("Português");
        configtabLang.add(radioPT, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        saveConfigBtn = new JButton();
        saveConfigBtn.setText("Salvar");
        tabConfig.add(saveConfigBtn, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelBin.setLabelFor(binary);
        labelDec.setLabelFor(decimal);
        labelOc.setLabelFor(octal);
        labelHex.setLabelFor(hexadecimal);
        labelDetSize.setLabelFor(detSize);
        label6.setLabelFor(textLimitMax);
        label7.setLabelFor(textLimitMin);
        ButtonGroup buttonGroup;
        buttonGroup = new ButtonGroup();
        buttonGroup.add(radioPT);
        buttonGroup.add(radioEN);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return jpanel;
    }

}
