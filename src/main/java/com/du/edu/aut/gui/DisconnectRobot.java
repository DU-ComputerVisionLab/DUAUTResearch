package com.du.edu.aut.gui;

import com.du.edu.aut.robots.Robot;
import com.du.edu.aut.robots.RobotRegistry;
import com.du.edu.aut.robots.gui.DisconnectSwingWorker;
import com.du.edu.aut.robots.gui.Disconnecting;

import javax.swing.*;
import java.awt.event.*;
import java.util.concurrent.ExecutionException;

public class DisconnectRobot extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JList list1;

    @SuppressWarnings("unchecked")
    public DisconnectRobot() {
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

        list1.setListData(RobotRegistry.listConnectedRobots().toArray());
    }

    private void onOK() {
// add your code here
        if (list1.getSelectedValue() != null){

            Disconnecting dialog = new Disconnecting();
            DisconnectSwingWorker removal = new DisconnectSwingWorker((Robot) list1.getSelectedValue(), dialog);
            removal.execute();
            dialog.pack();
            dialog.setVisible(true);
            try {
                if ((Boolean) removal.get()){
                    JOptionPane.showMessageDialog(null, "Disconnection successful!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Problem disconnecting the robot! Please try again.");
                }
            } catch (InterruptedException e) {
                JOptionPane.showMessageDialog(null, "Problem disconnecting the robot! Please try again.");
            } catch (ExecutionException e) {
                JOptionPane.showMessageDialog(null, "Problem disconnecting the robot! Please try again.");
            }

        }
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public static void INIT() {
        DisconnectRobot dialog = new DisconnectRobot();
        dialog.pack();
        dialog.setVisible(true);
    }
}
