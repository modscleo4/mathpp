package com.modscleo4.mathpp.gui;

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
}
