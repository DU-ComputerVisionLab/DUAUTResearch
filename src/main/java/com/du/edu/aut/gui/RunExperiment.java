package com.du.edu.aut.gui;

import com.du.edu.aut.experiment.ExperimentRegistry;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RunExperiment extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JList list1;

    public RunExperiment() {
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

        //Set the set to the list of experiments
        list1.setListData(ExperimentRegistry.getExperiments().toArray());

    }

    private void onOK() {
// add your code here
        ExperimentRegistry.runExperiment((String) list1.getSelectedValue());
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public static void INIT() {
        RunExperiment dialog = new RunExperiment();
        dialog.pack();
        dialog.setVisible(true);
    }
}
