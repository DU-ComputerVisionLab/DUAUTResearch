package com.du.edu.aut.robots.gui;

import com.du.edu.aut.robots.Robot;
import com.du.edu.aut.robots.RobotRegistry;

import javax.swing.*;

/**
 * Created by David on 7/26/2015.
 */
public class DisconnectSwingWorker extends SwingWorker{

    private Robot rob;
    private Disconnecting dialog;

    public DisconnectSwingWorker(Robot m, Disconnecting diag){
        super();
        this.rob = m;
        dialog = diag;
    }

    @Override
    protected Boolean doInBackground() throws Exception {
        boolean ret = rob.disconnect();
        if(ret){
            RobotRegistry.removeRobot(rob.name);
        }
        return ret;
    }

    @Override
    public void done(){
        dialog.dispose();
    }
}
