package com.du.edu.aut.log;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

/**
 * Class for handling different logs - contains methods for writing log information.
 * Singleton Class
 *
 * Created by David on 7/24/2015.
 */
class LogHandler {

    /**
     * Hash map which contains references to the logs that exist in the
     * program
     */
    private static HashMap<String, Log> logs = new HashMap<String, Log>();

    /**
     * Main DEBUG log which keeps all log messages, and is impossible to delete/remove.
     */
    private static Log DEBUG;

    /**
     * Boolean which keeps track of weather or not the log handler has been initialized. This must be set to
     * true to use log
     */
    private static boolean hasInit = false;

    /**
     * Stamp for the current instance
     */
    private static String instanceStamp;


    /**
     * Initializes the log handler. This will add the debug log
     * and set the init flags properly
     */
    public static void init(){

        //Create the debug log
        DEBUG = new Log("DEBUG");

        //Get the instance stamp
        Date date= new Date();
        instanceStamp = "Log_" + (new Timestamp(date.getTime())).toString().replaceAll("[^a-zA-Z0-9.-]", "_");

        //Set the intialized value to true
        hasInit = true;
    }

    /**
     * Checks to see that the handler has been initialized. If it hasn't, then it does so.
     */
    public static void checkInit(){
        if (!hasInit)
            init();
    }


    /**
     * Get the instance stamp of the log file
     * @return The instance stamp in String form
     */
    public static String getInstanceStamp(){
        return instanceStamp;
    }

    /**
     * Accessor for the DEBUG log
     * @return A reference to the debug log
     */
    public static Log getDEBUG(){
        return DEBUG;
    }

    /**
     * Returns a specific log based on the log name
     * @param logName The name of the log to return
     * @return The log, or a new log if one does not exist.
     */
    public static Log getLog(String logName){
        if (logs.containsKey(logName.toUpperCase()))
            return logs.get(logName.toUpperCase());

        addLog(logName);
        return logs.get(logName.toUpperCase());
    }



    /**
     * Adds a log to the log handler. Logs are case insensitive.
     * @param logName The name of the log
     * @return True if log does not already exist, false otherwise
     */
    public static boolean addLog(String logName){
        checkInit();
        if(logs.containsKey(logName.toUpperCase()))
            return false;
        logs.put(logName.toUpperCase(), new Log(logName.toUpperCase()));
        return true;
    }

    /**
     * Remove a log from the log handler. Logs are case insensitive,
     * @param logName The name of the log to remove
     * @return True if the log has been removed from the handler
     */
    public static boolean removeLog(String logName){
        checkInit();
        //Check for DEBUG - if so, just return false
        if(logName.toUpperCase().equals("DEBUG"))
            return false;

        //Check for and remove a log
        if(logs.containsKey(logName.toUpperCase())) {
            logs.remove(logName.toUpperCase());
            return true;
        } else {
            return false;
        }
    }

    /**
     * Flush all of the logs to files
     */
    public static void flushLogs(){
        if (!hasInit)
            return;
        for(Log l: logs.values()){
            l.flush();
        }
        DEBUG.flush();
    }
}
