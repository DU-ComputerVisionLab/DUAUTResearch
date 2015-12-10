package com.du.edu.aut.gui;

import com.du.edu.aut.experiment.ExperimentInterruptedException;
import com.du.edu.aut.experiment.ExperimentOptions;

import javax.swing.*;
import java.awt.event.*;

public class ParticipantSetup extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;

    public ParticipantSetup() {
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
    }

    private void onOK() {
// add your code here
        ExperimentOptions.participantName = textField1.getText();
        ExperimentOptions.participantID = textField2.getText();
        ExperimentOptions.sessionName = textField3.getText();
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        int i = JOptionPane.showConfirmDialog(null, "Are you sure you want to close this? If you don't set up the" +
                " participant, then you can't use the program.");
        if (i  == JOptionPane.YES_OPTION){
            dispose();
            System.exit(0);
        }
    }

    public static void INIT() {
        ParticipantSetup dialog = new ParticipantSetup();
        dialog.pack();
        dialog.setVisible(true);
    }
}
