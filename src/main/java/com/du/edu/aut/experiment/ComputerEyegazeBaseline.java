package com.du.edu.aut.experiment;

import javax.swing.*;

/**
 * Created by David on 7/26/2015.
 */
class ComputerEyegazeBaseline  extends Experiment{
    public static final String name = "Computer Eyegaze Baseline";
    public ComputerEyegazeBaseline(){ super(name); }


    @Override
    public void runExperiment() throws ExperimentInterruptedException{
        JOptionPane.showMessageDialog(null, "This is a computer eyegaze baseline. The computer is not " +
                "required for this task. We will wait 2s and return to the main menu");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            //TODO: Add Logging
        }
    }
}
