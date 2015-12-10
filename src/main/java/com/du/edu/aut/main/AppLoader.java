package com.du.edu.aut.main;

import com.du.edu.aut.experiment.ExperimentRegistry;
import com.du.edu.aut.gui.MainMenu;
import com.du.edu.aut.log.Logger;
import com.du.edu.aut.robots.RobotRegistry;

import javax.swing.*;

/**
 * App loading class for the main project. This class will be responsible for loading
 * the remainder of the classes, and managing high level fatal exceptions.
 *
 * Created by David on 7/24/2015.
 */
@SuppressWarnings("anonymous")
public class AppLoader {
    /**
     * Main loader function for the java application. Begins by launching the handler threads
     * and continues by loading the GUI.
     * @param args No arguments at the moment (args ignored)
     */
    public static void main(String[] args){
        Logger.log("Beginning Execution");
        try{
            Logger.log("Initializing experiment registry");
            ExperimentRegistry.initializeRegistry();

            Logger.log("Initializing robot controls");
            RobotRegistry.initializeRegistry();

            Logger.log("Initializing UI");
            SwingUtilities.invokeLater(new Runnable(){
                public void run(){
                    MainMenu.INIT();
                }
            });
        } catch (Exception E){
            E.printStackTrace();
        }
    }
}
