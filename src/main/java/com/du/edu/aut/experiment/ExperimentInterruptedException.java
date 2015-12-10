package com.du.edu.aut.experiment;

import javax.swing.*;

/**
 * Created by David on 7/27/2015.
 */
public class ExperimentInterruptedException extends Exception {
    public ExperimentInterruptedException(String message) {
        super(message);
        JOptionPane.showMessageDialog(null, "ERROR: Experiment Aborted. Reason: " + message,
                "Error", JOptionPane.ERROR_MESSAGE);
    }
}
