package com.du.edu.aut.robots.gui;


import com.du.edu.aut.log.Logger;
import com.du.edu.aut.robots.ExampleRobot;
import com.du.edu.aut.robots.RobokindR50;
import com.du.edu.aut.robots.RobotRegistry;
import com.du.edu.aut.robots.RobotType;

import javax.swing.*;

/**
 * Created by David on 7/26/2015.
 */
public class ConnectSwingWorker extends SwingWorker<Boolean, String> {

    private Connecting dia;
    private String name;
    private String ip;
    private RobotType type;

    public ConnectSwingWorker(Connecting dialog, String ip, String name, RobotType type){
        super();
        dia = dialog;
        this.name = name;
        this.ip = ip;
        this.type = type;
    }

    @Override
    protected Boolean doInBackground() throws Exception {
        switch(type){
            case ROBOKIND_R50:
                RobokindR50 robot = new RobokindR50(ip, name);
                if(robot.connect()){
                    RobotRegistry.addRobot(robot);
                }
                else
                    return false;
                break;
            case DEFAULT_ROBOT:
                ExampleRobot example = new ExampleRobot(ip, name);
                if(example.connect()) {
                    RobotRegistry.addRobot(example);
                }
                else
                    return false;
                break;
            default:
                Logger.logError("Tried to connect a robot of an unknown type", "Robot");
                return false;
        }
        return true;
    }

    @Override
    public void done(){
        dia.dispose();
    }

}
