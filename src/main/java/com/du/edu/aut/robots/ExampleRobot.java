package com.du.edu.aut.robots;

/**
 * Created by David on 7/26/2015.
 */
public class ExampleRobot extends Robot {

    public ExampleRobot(String ip, String name) {
        super(ip, name, "Example Robot");
    }

    @Override
    public boolean connect() throws RobotError {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean disconnect() throws RobotError {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void sleep(long time) throws RobotError {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void say(String message) throws RobotError {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void animate(String animation) throws RobotError {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
