package com.du.edu.aut.robots.gui;

import javax.swing.*;

public class Connecting extends JDialog {
    private JPanel contentPane;
    private JProgressBar progressBar1;

    public Connecting() {
        setContentPane(contentPane);
        setModal(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        progressBar1.setIndeterminate(true);
    }

}
