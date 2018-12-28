package com.modscleo4.mathpp.gui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import com.modscleo4.mathpp.lib.matrix.Matrix;
import com.modscleo4.mathpp.lib.matrix.MatrixException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.ThreadLocalRandom;

import static com.modscleo4.mathpp.lang.Lang.*;
import static com.modscleo4.mathpp.settings.GlobalSettings.limitMax;
import static com.modscleo4.mathpp.settings.GlobalSettings.limitMin;

public class MatADialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel matrixPanel;
    private JTextField width;
    private JTextField height;
    private JButton generate;
    private JCheckBox random;
    private JLabel labelMatCols;
    private JLabel labelMatLines;
    private JTextField[][] matrixArr;

    private static int h, w;
    public static Matrix matA;

    public MatADialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        loadRes();

        if (matA != null) {
            h = matA.height();
            w = matA.width();
        }

        if (h != 0 && w != 0) {
            height.setText(String.valueOf(h));
            width.setText(String.valueOf(w));

            matrixPanel.removeAll();
            matrixPanel.revalidate();
            matrixArr = new JTextField[h][w];
            double[][] arr = matA.toDoubleArray();

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    matrixArr[i][j] = new JTextField(String.valueOf(arr[i][j]));
                }
            }

            matrixPanel.setLayout(new GridLayout(h, w));
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    matrixPanel.add(matrixArr[i][j]);
                }
            }

            matrixPanel.repaint();
        }

        generate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!width.getText().isEmpty() && !height.getText().isEmpty()) {
                    try {
                        h = Integer.valueOf(height.getText());
                        w = Integer.valueOf(width.getText());
                        if (h < 1) {
                            throw new MatrixException(h + "is not a valid Matrix height");
                        }
                        if (w < 1) {
                            throw new MatrixException(w + "is not a valid Matrix width");
                        }
                    } catch (NumberFormatException | MatrixException e) {
                        ExceptionDialog.main(e.getLocalizedMessage(), e.getClass().getName());
                        return;
                    }
                    matA = new Matrix(h, w);
                    matrixPanel.removeAll();
                    matrixPanel.revalidate();
                    matrixArr = new JTextField[h][w];
                    for (int i = 0; i < h; i++) {
                        for (int j = 0; j < w; j++) {
                            matrixArr[i][j] = new JTextField((random.isSelected()) ? ("" + ThreadLocalRandom.current().nextDouble(limitMin, limitMax + 1)) : "");
                        }
                    }

                    matrixPanel.setLayout(new GridLayout(h, w));
                    for (int i = 0; i < h; i++) {
                        for (int j = 0; j < w; j++) {
                            matrixPanel.add(matrixArr[i][j]);
                        }
                    }

                    matrixPanel.repaint();
                }
            }
        });
    }

    private void onOK() {
        // add your code here
        double[][] arr = new double[h][w];
        boolean isFilled = true;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (isFilled) {
                    try {
                        arr[i][j] = Double.valueOf(matrixArr[i][j].getText());
                    } catch (NumberFormatException e) {
                        isFilled = false;
                    }

                }
            }
        }
        matA.set(arr);
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void loadRes() {
        buttonOK.setText(resOk);
        buttonCancel.setText(resCancel);
        labelMatCols.setText(resMatColumns);
        labelMatLines.setText(resMatLines);
    }

    public static void main() {
        MatADialog dialog = new MatADialog();
        dialog.pack();
        dialog.setVisible(true);
    }

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
        contentPane = new JPanel();
        contentPane.setLayout(new GridLayoutManager(3, 7, new Insets(10, 10, 10, 10), -1, -1));
        contentPane.setMinimumSize(new Dimension(640, 480));
        contentPane.setPreferredSize(new Dimension(640, 480));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel1, new GridConstraints(2, 0, 1, 7, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1, true, false));
        panel1.add(panel2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonOK = new JButton();
        buttonOK.setText("OK");
        panel2.add(buttonOK, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonCancel = new JButton();
        buttonCancel.setText("Cancel");
        panel2.add(buttonCancel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        matrixPanel = new JPanel();
        matrixPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(matrixPanel, new GridConstraints(1, 0, 1, 7, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        labelMatLines = new JLabel();
        labelMatLines.setText("Linhas:");
        contentPane.add(labelMatLines, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelMatCols = new JLabel();
        labelMatCols.setText("Colunas:");
        contentPane.add(labelMatCols, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        generate = new JButton();
        generate.setText("Criar Matriz");
        contentPane.add(generate, new GridConstraints(0, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        contentPane.add(spacer2, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        random = new JCheckBox();
        random.setText("Aleatório");
        contentPane.add(random, new GridConstraints(0, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        width = new JTextField();
        width.setText("");
        contentPane.add(width, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        height = new JTextField();
        contentPane.add(height, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }

}
