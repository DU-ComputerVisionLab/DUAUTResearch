package com.du.edu.aut.experiment;

import com.du.edu.aut.gui.ParticipantSetup;
import com.du.edu.aut.log.Logger;

/**
 * Class which holds the session experiment options. It is intialized by the main
 * menu, and contains important use infomation.
 * Created by David on 7/26/2015.
 */
public class ExperimentOptions {
    /** The participant's ID number */
    public static String participantID;
    /** The participant's name */
    public static String participantName;
    /** The session name */
    public static String sessionName;

    /**
     * Calls a UI to initialize the values of the options
     */
    public static void initialize(){
        ParticipantSetup.INIT();
        Logger.log("Participant ID: " + participantID);
        Logger.log("Participant Name: " + participantName);
        Logger.log("Session Name: " + sessionName);

    }
}
