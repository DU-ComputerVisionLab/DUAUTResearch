package com.du.edu.aut.log;

/**
 * Simple access class for the LogHandler. This class provides an interface method for log methods.
 *
 * Created by David on 7/24/2015.
 */
public class Logger {

    /**
     * Log a standard message
     * @param message The message to be logged
     */
    public static void log(String message){
        LogHandler.checkInit();
        LogHandler.getDEBUG().writeDebug(message);
    }

    /**
     * Log a standard message to a given log
     * @param message The message to be logged
     * @param log The name of the log to log to
     */
    public static void log(String message, String... log){
        LogHandler.checkInit();
        for (String arg : log){
            LogHandler.getLog(arg).writeDebug(message);
        }
        LogHandler.getDEBUG().writeDebug(message, log);

    }

    /**
     * Log an error message to the main log
     * @param message The message to be logged
     */
    public static void logError(String message){
        LogHandler.checkInit();
        LogHandler.getDEBUG().writeError(message);
    }

    /**
     * Log an error message to a given log
     * @param message The message to be logged
     * @param log The name of the log to log to
     */
    public static void logError(String message, String... log){
        LogHandler.checkInit();
        for (String arg : log){
            LogHandler.getLog(arg).writeError(message);
        }
       LogHandler.getDEBUG().writeError(message, log);
    }

    /**
     * Log a fatal error
     * @param message The message to be logged
     */
    public static void logFatal(String message){
        LogHandler.checkInit();
        LogHandler.getDEBUG().writeFatal(message);
    }

    /**
     * Remove a log completely. This is added for extensibility - I don't really ever think that
     * we will ever really use this.
     * @param logName The log to remove
     * @return True if successful
     */
    public static boolean removeLog(String logName){
        LogHandler.checkInit();
        return LogHandler.removeLog(logName);
    }

    /**
     * Flushes all of the logs to their respective files.
     */
    public static void flushLogs(){
        LogHandler.flushLogs();
    }
}
