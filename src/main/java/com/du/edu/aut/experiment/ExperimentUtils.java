package com.du.edu.aut.experiment;

import com.du.edu.aut.robots.Robot;
import com.du.edu.aut.robots.RobotRegistry;
import com.du.edu.aut.util.ExcelWriter;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import javax.swing.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

/**
 *
 * Experiment utility class that is used to hold easy methods for initializing experiments.
 * This class should help cut down on the reuse of code in the experiments section of the
 * program.
 *
 * Created by Nathan on 7/27/2015.
 * Edited by David on 7/27/2015
 */
class ExperimentUtils {

    public static Random rand = new Random();

    /**
     * Code that is designed to allow a user to select a robot
     * @return The robot that the user has selected
     * @throws ExperimentInterruptedException
     */
    public static Robot selectRobot() throws ExperimentInterruptedException {
        if (RobotRegistry.listConnectedRobots().size() == 0){
            JOptionPane.showMessageDialog(null, "You need to connect a robot to run this experiment. Please do so and" +
                    "try again");
            throw new ExperimentInterruptedException("Please connect a robot before using this experiment");
        }

        //Get the robot that you would like to use for this experiment
        Robot main_bot = null;
        do {
            main_bot = (Robot) JOptionPane.showInputDialog(null,
                    "Which robot would you like to use for this experiment?",
                    "Robot Selection",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    RobotRegistry.listConnectedRobots().toArray(),
                    null
            );
        } while (main_bot == null);

        return main_bot;
    }

    public static ExcelWriter loadExcel(String file_name, int type) throws IOException,
            InvalidFormatException {
        return new ExcelWriter(file_name + ".xlsx", type);
    }

    public static void flushExcel(ExcelWriter ew){
        if (ew != null){
            ew.closeFile();
        }
    }

    public static String getDate() {
        return new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    }

    public static String getTime() {
        return new SimpleDateFormat(" HH:mm:ss ").format(Calendar.getInstance().getTime());
    }

}
