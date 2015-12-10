package com.du.edu.aut.robots;

/**
 * Class for signifying robot errors.
 * Created by David on 7/24/2015.
 */
public class RobotError extends Exception {

    /**
     * Construct a new robot error - this will likely be fatal to the program,
     * so the data should be fairly verbose.
     * @param message The message to send
     */
    public RobotError(String message){
        super(message);
    }
}
