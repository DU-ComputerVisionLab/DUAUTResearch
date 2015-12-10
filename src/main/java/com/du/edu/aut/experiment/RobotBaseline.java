package com.du.edu.aut.experiment;

import com.du.edu.aut.clientio.UnityClientIO;
import com.du.edu.aut.log.Logger;
import com.du.edu.aut.robots.Robot;
import com.du.edu.aut.robots.RobotError;
import com.du.edu.aut.util.ExcelWriter;
import edu.emory.mathcs.backport.java.util.Arrays;
import sun.rmi.runtime.Log;

import javax.swing.*;
import java.io.IOException;
import java.net.UnknownHostException;

/**
 * Robot baseline interaction experiment
 * Created by Nathan on 7/27/2015.
 * Edited by David on 7/27/2015
 */
class RobotBaseline extends Experiment {

    private Robot robot;
    public static final String name = "Robot Baseline";
    public RobotBaseline() {
        super(name);
    }

    /**
     * Runs the human baseline experiment. Will throw an interrupted exception if anything goes wrong.
     * @throws ExperimentInterruptedException
     */
    @Override
    public void runExperiment() throws ExperimentInterruptedException {
        Logger.log("Running robot baseline/interaction", "Experiment");
        Logger.log("Creating and saving baseline sequence", "Experiment");

        robot = ExperimentUtils.selectRobot();

        //Generate sequences of activities
        int[] sessionActivityOrder = new int[20];
        for (int i = 0; i < 20; i++){
            //There are 4 activities, so we generate 20 random numbers between 1 and 4 (inclusive) in a random order
            sessionActivityOrder[i] = ExperimentUtils.rand.nextInt(4) + 1;
        }
        int[] sessionEmotionOrder = new int[20];
        for (int i = 0; i < 20; i++){
            //There are 5 emotions, so we generate 20 random numbers between 1 and 5 (inclusive) in a random order
            sessionEmotionOrder[i] = ExperimentUtils.rand.nextInt(5) + 1;
        }

        //Ask the uzer where to save the Excel file, as well as what to call the Log file
        String file = JOptionPane.showInputDialog("What should I call the saved files?",
                ExperimentOptions.participantID +
                        ExperimentUtils.getDate() + "Zeno3");
        Logger.log("Trying to create excel and log file: " + file, "Experiment");

        //Create files with excel and text docs
        ExcelWriter file_out;
        try{
            file_out = ExperimentUtils.loadExcel(file, ExcelWriter.BASELINE);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Excel file failed to be created. Error: " + e.getMessage());
            Logger.logError("Excel file failed to initialize: " + e.getMessage(), "Experiment", file);
            throw new ExperimentInterruptedException("The Excel file failed to initialize");
        }

        //Log loading information
        Logger.log("Participant's ID: " + ExperimentOptions.participantID, file);
        Logger.log("Time started: " + ExperimentUtils.getTime(), file);
        Logger.log("Session activity order: " + Arrays.toString(sessionActivityOrder), file);
        Logger.log("Session emotion order: " + Arrays.toString(sessionEmotionOrder), file);

        //Connect the unity client
        UnityClientIO client;
        try{
            client = new UnityClientIO();
        } catch (UnknownHostException e) {
            Logger.log("Could not resolve the unity client host name. Had to abort experiment");
            throw new ExperimentInterruptedException("Could not resolve client host name in a timely manner");
        }

        JOptionPane.showMessageDialog(null, "Ready to begin. Press OK when you're ready to continue.");

        //Process the emotion activity sequences that were generated above
        String emotion;
        for (int i = 0; i < 3; i++) {

            //Select the emotion to deal with
            if (sessionEmotionOrder[i] == 1) {
                emotion = "happy";
            } else if (sessionEmotionOrder[i] == 2) {
                emotion = "sad";
            } else if (sessionEmotionOrder[i] == 3) {
                emotion = "surprised";
            } else if (sessionEmotionOrder[i] == 4) {
                emotion = "angry";
            } else if (sessionEmotionOrder[i] == 5) {
                emotion = "fear";
            } else {
                emotion = "happy";
                Logger.logError("Unrecognized emotion number: " + sessionEmotionOrder[i] + ". Defaulting to happy.", "Experiment");
            }

            //This code looks really ugly, but it's really just exception handling. If it doesn't work at once,
            //try again. However, if it doesn't work the second time, we should give up on the experiment.

            if (sessionActivityOrder[i] == 1) {
                try{
                    identifyEmotionRB(emotion, client, file, file_out);
                } catch (IOException e){
                    Logger.logError("There was an IO exception while running the identifyEmotionRB method. " +
                            "We are going to re-load the experiment and try again", "Experiment");
                    JOptionPane.showMessageDialog(null, "There was an error communicating with the client." +
                                    "We're going to re-run the experiment. Be prepared to give prompts again.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    try{
                        identifyEmotionRB(emotion, client, file, file_out);
                    } catch (IOException ex){
                        Logger.logError("Failed Experiment re-run. IO Error in identifyEmotionRB. Interrupting the " +
                                "experiment.", "Experiment");
                        throw new ExperimentInterruptedException("IO Error in identifyEmotionRB method");
                    }
                }
            } else if (sessionActivityOrder[i] == 2) {
                try{
                    copyFaceRB(emotion, client, file, file_out);
                } catch (IOException e){
                    Logger.logError("There was an IO exception while running the copyFaceRB method. " +
                            "We are going to re-load the experiment and try again", "Experiment");
                    JOptionPane.showMessageDialog(null, "There was an error communicating with the client." +
                                    "We're going to re-run the experiment. Be prepared to give prompts again.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    try{
                        identifyEmotionRB(emotion, client, file, file_out);
                    } catch (IOException ex){
                        Logger.logError("Failed Experiment re-run. IO Error in copyFaceRB. Interrupting the " +
                                "experiment.", "Experiment");
                        throw new ExperimentInterruptedException("IO Error in copyFaceRB method");
                    }
                }
            } else if (sessionActivityOrder[i] == 3) {
                try{
                    recognizeFaceExpressionRB(emotion, client, file, file_out);
                } catch (IOException e){
                    Logger.logError("There was an IO exception while running the recognizeFaceExpressionRB method. " +
                            "We are going to re-load the experiment and try again", "Experiment");
                    JOptionPane.showMessageDialog(null, "There was an error communicating with the client." +
                                    "We're going to re-run the experiment. Be prepared to give prompts again.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    try{
                        identifyEmotionRB(emotion, client, file, file_out);
                    } catch (IOException ex){
                        Logger.logError("Failed Experiment re-run. IO Error in recognizeFaceExpressionRB. Interrupting the " +
                                "experiment.", "Experiment");
                        throw new ExperimentInterruptedException("IO Error in recognizeFaceExpressionRB method");
                    }
                }
            } else if (sessionActivityOrder[i] == 4) {
                try{
                    selectExpressionRB(emotion, client, file, file_out);
                } catch (IOException e){
                    Logger.logError("There was an IO exception while running the selectExpressionRB method. " +
                            "We are going to re-load the experiment and try again", "Experiment");
                    JOptionPane.showMessageDialog(null, "There was an error communicating with the client." +
                                    "We're going to re-run the experiment. Be prepared to give prompts again.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    try{
                        identifyEmotionRB(emotion, client, file, file_out);
                    } catch (IOException ex){
                        Logger.logError("Failed Experiment re-run. IO Error in selectExpressionRB. Interrupting the " +
                                "experiment.", "Experiment");
                        throw new ExperimentInterruptedException("IO Error in selectExpressionRB method");
                    }
                }
            }
        }

    }

    /**
     * Asks the child to copy the emotion that is displayed on the robot's face face.
     * @param emotion The emotion to identify
     * @param client The client which is passed in
     * @param log_name The name of the log file to write to
     * @param excel_out The excel file in use
     * @throws IOException
     */
    private void copyFaceRB(String emotion, UnityClientIO client, String log_name, ExcelWriter excel_out) throws IOException, ExperimentInterruptedException {
        try {
            robot.say("Press START when you are ready " + ExperimentOptions.participantName);
        } catch (RobotError robotError) {
            Logger.log(robotError.getMessage(), log_name);
            throw new ExperimentInterruptedException(robotError.getMessage());
        }

        Logger.log("Sent message: " + client.send("copy"), log_name);
        Logger.log("Copy face", log_name);
        String startTime = ExperimentUtils.getTime();
        Logger.log("Start time: " + startTime, log_name);
        
        Logger.log("Listen2" + client.get(), log_name);
        Logger.log("    emotion: " + emotion, log_name);

        try {
            robot.say("Copy My Face with your face!");
            robot.say("Press the RECORD FACE button when you have it on your face!");
            robot.say("Here it is");
        } catch (RobotError robotError) {
            Logger.logError(robotError.getMessage(), log_name);
            throw new ExperimentInterruptedException(robotError.getMessage());
        }

        JOptionPane.showMessageDialog(null, "The robot will show an " + emotion + " face and have the child COPY that face. " +
                "Press OK to let the child select the emotion.", "Message", JOptionPane.INFORMATION_MESSAGE);

        Logger.log("Robot animation starting");
        try {
            robot.animate(emotion);
        } catch (RobotError robotError) {
            Logger.log(robotError.getMessage(), log_name);
            throw new ExperimentInterruptedException(robotError.getMessage());
        }

        Logger.log("send3 " + client.send("go"), log_name);
        Logger.log("allowed to select: " + ExperimentUtils.getTime(), log_name);
        // TODO add a GUI were the AUs that are present on the childs face can be input
        JOptionPane.showMessageDialog(null, "Wait for the child to say that they are ready " +
                "by pressing the button. Then input the child's correct facial expression" +
                " features.", "Message", JOptionPane.INFORMATION_MESSAGE);
        Logger.log("listen4:" + client.get(), log_name);
        Logger.log("entered face at" + ExperimentUtils.getTime(), log_name);
        Logger.log("send5" + client.send("main"), log_name);
        try {
            robot.say("lets go to the next activity!");
            robot.animate("happy");
        } catch (RobotError robotError) {
            Logger.log(robotError.getMessage(), log_name);
            throw new ExperimentInterruptedException(robotError.getMessage());
        }

        //TODO: Finish this method. There's some stuff that doesn't actually work here

        String endTime = ExperimentUtils.getTime();
        excel_out.writeCopy("", emotion, startTime, endTime);
        Logger.log("listen6" + client.get(), log_name);
    }

    /**
     * Asks the child to recognize the displayed emotion.
     * @param emotion The emotion to identify
     * @param client The client which is passed in
     * @param log_name The name of the log file to write to
     * @param excel_out The excel file in use
     * @throws IOException
     */
    private void recognizeFaceExpressionRB(String emotion, UnityClientIO client, String log_name, ExcelWriter excel_out) throws IOException, ExperimentInterruptedException {
        try {
            robot.say("Remember to press start when you are ready to play the next game!");
        } catch (RobotError robotError) {
            Logger.logError(robotError.getMessage());
            throw new ExperimentInterruptedException(robotError.getMessage());
        }
        String startTime = ExperimentUtils.getTime();
        Logger.log("Recognize Emotion: " + emotion, log_name);
        Logger.log("Send: " + client.send("recognize " + emotion), log_name);
        Logger.log("Client responded with: " + client.get());
        Logger.log("Show " + emotion + " face " + "and have child RECOGNIZE the emotion", log_name);
        try {
            robot.say("Can you please tell me what face emotion I will try to show you is?");
        } catch (RobotError robotError) {
            Logger.logError(robotError.getMessage());
            throw new ExperimentInterruptedException(robotError.getMessage());
        }

        String promptTime = ExperimentUtils.getTime();
        Logger.log("The child was allowed to select an emotion at " + promptTime, log_name);
        Logger.log("send2: " + client.send("go"));
        String response = client.get();
        String responseTime = ExperimentUtils.getTime();
        Logger.log("Response at " + responseTime + ", response: " + response, log_name);

        String endTime = ExperimentUtils.getTime();
        Logger.log("Next activity at: " + endTime);
        excel_out.writeRecognition(response.contains("right"), emotion, response, promptTime,
                responseTime, startTime, "");

        Logger.log("send4: " + client.send("main"));
        Logger.log("listen5: " + client.get());

        Logger.log("Event complete. Going on to next activity!", log_name);
    }

    /**
     * Asks the child to select an expression corresponding to the given emotion
     * @param emotion The emotion to identify
     * @param client The client which is passed in
     * @param log_name The name of the log file to write to
     * @param excel_out The excel file in use
     * @throws IOException
     */
    private void selectExpressionRB(String emotion, UnityClientIO client, String log_name, ExcelWriter excel_out) throws IOException, ExperimentInterruptedException {
        Logger.log("Select " + emotion, log_name) ;
        String startTime = ExperimentUtils.getTime();
        Logger.log("Started at " + startTime, log_name);
        try {
            robot.say("Remember to press start when you are ready to play the next game!");
        } catch (RobotError robotError) {
            Logger.logError(robotError.getMessage());
            throw new ExperimentInterruptedException(robotError.getMessage());
        }

        //Select two emotions that are not the emotion that we are testing. Do this at random
        String[] emotions = {"happy", "sad", "angry", "surprised", "fear"};
        String incorrectEmotion1;
        String incoreectEmotion2;
        do {
            incorrectEmotion1 = emotions[ExperimentUtils.rand.nextInt(emotions.length)];
            incoreectEmotion2 = emotions[ExperimentUtils.rand.nextInt(emotions.length)];
        } while (incorrectEmotion1.equals(emotion) || incoreectEmotion2.equals(emotion));

        //Select three pictures from the numbers {1,2,3,4} to send to the code
        int img1 = ExperimentUtils.rand.nextInt(4) + 1;
        int img2 = ExperimentUtils.rand.nextInt(4) + 1;
        int img3 = ExperimentUtils.rand.nextInt(4) + 1;


        Logger.log("SEND1: " + client.send("select " + emotion + " " + img1 + " " + incorrectEmotion1 + " " + img2
                + " " + incoreectEmotion2 + " " + img3), log_name);
        Logger.log("Listen2: " + client.get(), log_name);
        try {
            robot.say("Which picture shows " + emotion + "? Touch the correct card on the touch pad.");
        } catch (RobotError robotError) {
            Logger.logError(robotError.getMessage());
            throw new ExperimentInterruptedException(robotError.getMessage());
        }

        String promptTime = ExperimentUtils.getTime();
        Logger.log("Let select: " + promptTime, log_name);
        Logger.log("Sent: " + client.send("go"));

        String response = client.get();
        Logger.log("Response: " + response, log_name);
        String respTime = ExperimentUtils.getTime();
        Logger.log("Response time: " + respTime, log_name);

        Logger.log("Going on to the next experiment.");

        client.send("main");
        String endTime = ExperimentUtils.getTime();
        Logger.log("Finished activity at: " + endTime, log_name);
        excel_out.writeSelect(response.contains("right"), emotion, response, promptTime,
                respTime, startTime, "");
        client.get();
    }

    /**
     * Asks the child to identify the emotion that is specified.
     * @param emotion The emotion to identify
     * @param client The client which is passed in
     * @param log_name The name of the log file to write to
     * @param excel_out The excel file in use
     * @throws IOException
     */
    private void identifyEmotionRB(String emotion, UnityClientIO client, String log_name, ExcelWriter excel_out) throws IOException, ExperimentInterruptedException {
        Logger.log("Identify " + emotion, log_name);
        String startTime = ExperimentUtils.getTime();
        Logger.log(startTime, log_name);

        try {
            robot.say("Remember to press start when you are ready to play the next game!");
        } catch (RobotError robotError) {
            Logger.logError(robotError.getMessage());
            throw new ExperimentInterruptedException(robotError.getMessage());
        }
        Logger.log("Sent: " + client.send("identify " + emotion + " " + Integer.toString(ExperimentUtils.rand.nextInt(4) + 1)), log_name);
        Logger.log("Listen: " + client.get(), log_name);
        String promptTime = ExperimentUtils.getTime();

        JOptionPane.showMessageDialog(null, "Ask child to IDENTIFY which emotion is shown in the picture by" +
                " saying \"What emotion does the picture show? Select the emotion you think.\" Press OK to allow" +
                "the child to select an emotion.", "Message", JOptionPane.INFORMATION_MESSAGE);
        Logger.log("Sent: " + client.send("go"), log_name);

        String response = client.get();
        Logger.log("Response: " + response, log_name);
        String respTime = ExperimentUtils.getTime();
        Logger.log("Response at: " + respTime, log_name);

        Logger.log("The child responded that the emotion was: " + response + " when the true " +
                "emotion was " + emotion + ". Press OK to go on to the next activity.", log_name);

        //Return to the main menu
        client.send("main");
        client.get();

        excel_out.writeIdentify(response.contains("right"), emotion, response, promptTime, respTime, startTime, "");
    }
}
