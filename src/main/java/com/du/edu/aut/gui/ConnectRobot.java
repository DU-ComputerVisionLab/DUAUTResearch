package com.du.edu.aut.gui;

import com.du.edu.aut.robots.RobotType;
import com.du.edu.aut.robots.gui.ConnectSwingWorker;
import com.du.edu.aut.robots.gui.Connecting;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConnectRobot extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JTextField textField2;

    public ConnectRobot() {
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
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

        DefaultComboBoxModel RobotTypes = (DefaultComboBoxModel) comboBox1.getModel();
        for(RobotType rt : RobotType.values()){
            RobotTypes.addElement(rt);
        }


    }

    private static final String PATTERN =
            "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

    public static boolean validateIP(final String ip){
        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(ip);
        return matcher.matches();
    }

    private void onOK() {

        if (!validateIP(textField1.getText())){
            JOptionPane.showMessageDialog(null, "Invalid IP address. Please correct this error.");
            return;
        }

        Connecting dialog = new Connecting();
        ConnectSwingWorker ct = new ConnectSwingWorker(dialog, textField1.getText(), textField2.getText(), (RobotType) comboBox1.getSelectedItem());
        ct.execute();
        dialog.pack();
        dialog.setVisible(true);
        Boolean successful = false;
        try {
            successful = ct.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if(successful){
            JOptionPane.showMessageDialog(null, "Connection successful!");
            dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Something went wrong while connecting to the robot. Please check the information and try again.");
        }
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public static void INIT() {
        ConnectRobot dialog = new ConnectRobot();
        dialog.pack();
        dialog.setVisible(true);
    }
}
