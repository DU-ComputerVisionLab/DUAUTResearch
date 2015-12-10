package com.du.edu.aut.log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.FileSystemException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * Log class for keeping track of different labeled log files.
 * Created by David on 7/24/2015.
 */
class Log {

    /**
     * The name of the log
     */
    private String name;

    /**
     * Array list containing logged messages
     */
    private ArrayList<String> messages;


    /**
     * Display log with the STDERR of the main program
     */
    private boolean LogToSTDERR = false;

    /**
     * Display the log with the STDOUT of the main program
     */
    private boolean LogToSTDOUT = false;

    /**
     * Construct a log with the given name
     * @param logName The name of the log
     */
    public Log(String logName){
        this.name = logName;
        this.messages = new ArrayList<String>();
    }

    /**
     * Create a log with the option of log to the std error of the program
     * @param logName The name of the log
     * @param LogToSTDERR Whether or not you should log to std err
     */
    public Log(String logName, boolean LogToSTDERR){
        this.name = logName;
        this.messages = new ArrayList<String>();
        this.LogToSTDERR = true;
    }

    /**
     * Create a log with the option of log to both the std error and the std out of the program
     * @param logName The name of the log
     * @param LogToSTDERR Whether or not you should log to std err
     */
    public Log(String logName, boolean LogToSTDERR, boolean LogToSTDOUT){
        this.name = logName;
        this.messages = new ArrayList<String>();
        this.LogToSTDERR = true;
        this.LogToSTDOUT = true;
    }

    /**
     * Writes a message to the standard debug
     * @param message The message to write to debug
     */
    public void writeDebug(String message){
        String output_message = getTimestamp() + " - [" + this.name + "] " + message;
        messages.add(output_message);
        if (LogToSTDOUT)
            System.out.println(output_message);
        if (LogToSTDERR)
            System.err.println(output_message);
    }

    /**
     * Writes an error message to the log.
     * @param message The message to log
     */
    public void writeError(String message){
        String output_message = getTimestamp() + " - ERROR - [" + this.name + "] " + message;
        messages.add(output_message);
        if (LogToSTDOUT)
            System.out.println(output_message);
        if (LogToSTDERR)
            System.err.println(output_message);
    }

    /**
     * Writes a fatal error. This will close the program after flushing the logs. You probably
     * shouldn't really ever call this.
     * @param message The message to print
     */
    public void writeFatal(String message){
        String output_message = getTimestamp() + " - FATAL ERROR - [" + this.name + "] " + message;
        messages.add(output_message);
        if (LogToSTDOUT)
            System.out.println(output_message);
        if (LogToSTDERR)
            System.err.println(output_message);

        //Handle termination code

        //First we should flush all of the logs in the log handler to a file
        LogHandler.flushLogs();

       //Now we throw a fatal exception
        Runtime.getRuntime().halt(1);
    }

    /**
     * Get a time stamp for log
     * @return The current time in a formatted format
     */
    private String getTimestamp(){
        Date date= new Date();
        return (new Timestamp(date.getTime())).toString();
    }

    public boolean flush(){
        try{
            String logFile = "Logs" + File.separator + LogHandler.getInstanceStamp() + File.separator + name + File.separator + name + ".log";
            if(!new File(logFile.substring(0,logFile.lastIndexOf(File.separatorChar))).mkdirs())
                throw new FileSystemException("Could not create directory for log " + name + ".");

            PrintWriter log_writer = new PrintWriter(logFile);
            for (String m : this.messages){
                log_writer.println(m);
            }

            log_writer.close();
            System.err.println("Saved log file to: " + new File(logFile).getAbsolutePath());



        } catch (FileSystemException E){
            System.err.println("Problem writing log file directories: " + E.getMessage());
            return false;
        } catch (FileNotFoundException E){
            System.err.println("FileNotFoundException while writing log file: " + E.getMessage());
            return false;
        } catch (Exception E){
            System.err.println("Unidentified error while writing log file: " + E.getMessage());
            return false;
        }

        return true;
    }

    /**
     * Writes a debug message to the main debug, which specifies the log that it comes from
     * @param message The message to send
     * @param log The logs which are sources
     */
    public void writeDebug(String message, String[] log) {
        String output_message = getTimestamp() + " - " + Arrays.toString(log) + " " + message;
        messages.add(output_message);
        if (LogToSTDOUT)
            System.out.println(output_message);
        if (LogToSTDERR)
            System.err.println(output_message);
    }


    /**
     * Writes an error message to the main debug, which specifies the log that it comes from
     * @param message The message to send
     * @param log The logs which are sources
     */
    public void writeError(String message, String[] log){
        String output_message = getTimestamp() + " - ERROR - " + Arrays.toString(log) + " " + message;
        messages.add(output_message);
        if (LogToSTDOUT)
            System.out.println(output_message);
        if (LogToSTDERR)
            System.err.println(output_message);
    }


}
