package com.du.edu.aut.robots;

import com.du.edu.aut.log.Logger;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Robot Registry class which allows us to add and remove robots, as well as access open robots that have already
 * been created.
 * Created by David on 7/24/2015.
 */
@SuppressWarnings("unused, unchecked")
public class RobotRegistry {

    /** Hash map storing the bot information, keyed by name */
    private static HashMap<String, Robot> robots = new HashMap<String, Robot>();

    /**
     * Initialize the robot registry
     */
    public static void initializeRegistry(){
        //NOT NEEDED RIGHT NOW
    }

    /**
     * Adds a robot to the registry
     * @param bot The robot to add to the registry
     */
    public static void addRobot(Robot bot){
        robots.put(bot.name, bot);
    }

    /**
     * Removes a robot from the registry by name
     * @param bot The name of the robot to remove from the registry
     * @return True if the robot really was removed
     */
    public static boolean removeRobot(String bot){
        if (robots.containsKey(bot)){
            try {
                robots.get(bot).disconnect();
            } catch (RobotError robotError) {
                Logger.logError("Failed to disconnect from the robot " + bot + " successfully.");
                return false;
            }
            robots.remove(bot);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Get a robot from the registry by name
     * @param name The name of the robot to get from the registry
     * @return The robot which corresponds to that name
     */
    public static Robot getRobot(String name){
        return robots.get(name);
    }

    /**
     * Get a list of all connected robots
     * @return An ArrayList of connected robots
     */
    public static ArrayList<Robot> listConnectedRobots(){
        return new ArrayList<Robot>(robots.values());
    }
}
