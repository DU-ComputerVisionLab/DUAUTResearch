package com.du.edu.aut.robots;

import com.du.edu.aut.log.Logger;
import org.robokind.api.animation.Animation;
import org.robokind.api.animation.messaging.RemoteAnimationPlayerClient;
import org.robokind.api.animation.player.AnimationJob;
import org.robokind.api.motion.messaging.RemoteRobot;
import org.robokind.api.speech.SpeechJob;
import org.robokind.api.speech.messaging.RemoteSpeechServiceClient;
import org.robokind.api.speech.utils.DefaultSpeechJob;
import org.robokind.client.basic.Robokind;
import org.robokind.client.basic.UserSettings;

import java.io.File;

/**
 * Implementation for the Robotkind R50
 * Created by David on 7/24/2015.
 */
public class RobokindR50 extends Robot{


    private boolean connected = false;
    private RemoteRobot robot = null;
    private RemoteSpeechServiceClient speaker = null;
    private RemoteAnimationPlayerClient player = null;

    public RobokindR50(String ip, String name){
        super(ip, name, "Robokind R50");
    }

    public boolean connect(){

        //Setup the address and robot ID
        UserSettings.setRobotAddress(this.ip_address);
        UserSettings.setRobotId(this.name);
        Logger.log("Set RobokindR50 ip_address to: " + this.ip_address, "Robot");
        Logger.log("Set RobokindR50 name to: " + this.name, "Robot");


        //Attempt to connect to the robot
        Logger.log("Attempting to connect to RobokindR50 named " + this.name + " at " + this.ip_address, "Robot");
        robot = Robokind.connectRobot();

        //Check to make sure the connection worked
        if (robot == null){
            Logger.logError("Could not connect to robot at: " + this.ip_address, "Robot");
            Robokind.disconnect();
            return false;
        }
        Logger.log("Connected to RobokindR50 named " + this.name + " at " + this.ip_address, "Robot");

        //Setup and connect to the robot's speaker
        Logger.log("Attempting to connect to RobokindR50 speaker at " + this.ip_address, "Robot");
        UserSettings.setSpeechAddress(this.ip_address);
        speaker = Robokind.connectSpeechService();

        //Check to make sure the connection worked
        if (speaker == null){
            Logger.logError("Could not connect to robot speaker at: " + this.ip_address, "Robot");
            Robokind.disconnect();
            return false;
        }

        Logger.log("Connected to RobokindR50 speaker at " + this.ip_address, "Robot");

        //Setup and connect to the robot's player
        Logger.log("Attempting to connect to RobokindR50 player at " + this.ip_address, "Robot");
        UserSettings.setAnimationAddress(this.ip_address);
        player = Robokind.connectAnimationPlayer();

        //Check to make sure the connection worked
        if (player == null){
            Logger.logError("Could not connect to robot player at: " + this.ip_address, "Robot");
            Robokind.disconnect();
            return false;
        }
        Logger.log("Connected to RobokindR50 speaker at " + this.ip_address, "Robot");

        connected = true;
        return true;
    }

    public boolean disconnect(){
        Logger.log("Disconnecting from robot on " + this.ip_address, "Robot");
        Robokind.disconnect();
        Logger.log("Successfully disconnected from robot on " + this.ip_address, "Robot");
        return true;
    }

    public void sleep(long time){
        Logger.log("Robot sleeping for " + time + "ms", "Robot");
        Robokind.sleep(time);
        Logger.log("Robot finished sleeping", "Robot");
    }

    public void say(String message) throws RobotError{

        Logger.log("Beginning speech job - " + message, "Robot");
        SpeechJob job = speaker.speak(message);

        //Does a blocking call until the job is complete or cancelled.
        while(job.getStatus() != DefaultSpeechJob.COMPLETE){
            if(job.getStatus() == DefaultSpeechJob.CANCELED){
                Logger.logError("Robot speech job canceled - " + message, "Robot");
                throw new RobotError("Speech job canceled - " + message);
            }
        }
        Logger.log("Finished speech job - " + message, "Robot");
    }


    //TODO: Implement the animation code fully
    public void animate(String animation) throws RobotError {

        //TODO: Make the animation path relative
        String animationPath = "C:/Users/RobotController/Desktop/ZenoBearCompare/" + animation + ".xml";

        Logger.log("Playing animation " + animation, "Robot");

        File fPath = new File(animationPath);
        if (fPath.exists()) {
            Animation anim = Robokind.loadAnimation(animationPath);

            //Create introJob, tell it to play introAnim with animPlayer, get the length of the animation and
            // tell the thread to wait for that length of time plus 500 milliseconds
            // (so the next command is issued AFTER the animation finishes playing.
            AnimationJob job = player.playAnimation(anim);

            //TODO: Add blocking to the animation call

        } else {
            Logger.logError("Robot animation does not exit - " + fPath.getAbsolutePath(), "Robot");
            return;
        }

        Logger.log("Finished animation " + animation, "Robot");
    }

}
