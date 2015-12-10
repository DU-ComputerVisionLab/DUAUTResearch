package com.du.edu.aut.robots;

/**
 * Abstract interface for a robot. This class is made to be easily expandable for more robots.
 *
 * Created by David on 7/24/2015.
 */
public abstract class Robot {
    public String ip_address;
    public String name;
    public String type;

    public Robot(String ip, String name, String type){
        this.ip_address = ip;
        this.name = name;
        this.type = type;
    }

    abstract public boolean connect() throws RobotError;
    abstract public boolean disconnect() throws RobotError;
    abstract public void sleep(long time) throws RobotError;
    abstract public void say(String message) throws RobotError;
    abstract public void animate(String animation) throws RobotError;

    @Override
    public String toString(){
        return name + " at " + ip_address + " (" + type + ")";
    }
}
