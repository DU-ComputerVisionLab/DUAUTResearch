package com.mycompany.zeno_test131117;
//this is the new Brain3

//import java.awt.EventQueue;

import org.robokind.api.animation.Animation;
import org.robokind.api.animation.messaging.RemoteAnimationPlayerClient;
import org.robokind.api.animation.player.AnimationJob;
import org.robokind.api.common.position.NormalizedDouble;
import org.robokind.api.motion.Joint;
import org.robokind.api.motion.Robot;
import org.robokind.api.motion.Robot.RobotPositionHashMap;
import org.robokind.api.motion.messaging.RemoteRobot;
import org.robokind.api.speech.SpeechJob;
import org.robokind.api.speech.messaging.RemoteSpeechServiceClient;
import org.robokind.api.speech.utils.DefaultSpeechJob;
import org.robokind.client.basic.Robokind;
import org.robokind.client.basic.RobotJoints;
import org.robokind.client.basic.UserSettings;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

//import org.apache.commons.lang.ArrayUtils;

/**
 * Michelle Salvador, Kris Douglass, Kendall Weistroffer, and Nathan Saslavsky
 * ZenoProject Phase3
 */
public class App extends JFrame {

    private static boolean enterPressed;

    static void closeEarly() {
        if (excel != null) {
            excel.closeFile();
        }
        closeTextDoc();
    }

    public App() {
        //UserSettings.setCameraAddress(ipAddress);
        //UserSettings.setCameraId("0");

        /* RemoteImageServiceClient<CameraServiceConfig> images =
         // Robokind.connectCameraService();
         ImageMonitor monitor = new ImageMonitor();
         add(monitor);
         images.addImageListener(monitor);*/
    }

    static void threadMessage(String message) {
        String threadName =
                Thread.currentThread().getName();
        System.out.format("%s: %s%n",
                threadName,
                message);
    }

    protected static RemoteRobot myRobot;
    protected static RemoteSpeechServiceClient mySpeaker;
    protected static RemoteAnimationPlayerClient myPlayer;
    protected static String enteredInput;

    public static void main(String[] args) throws InterruptedException {
//Connect to robot  

    }

    public static void timeSleep() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            threadMessage("catched!!! XD");
        }
    }

    protected static int[] intRandomizer(int[] intList) {
        // Implementing Fisher–Yates (or Dirstenfeld?)shuffle (as found in http://stackoverflow.com/questions/1519736/random-shuffling-of-an-array)
        Random rnd = new Random();
        for (int i = intList.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            int a = intList[index];
            intList[index] = intList[i];
            intList[i] = a;
        }
        System.out.println("intlist" + intList);
        return intList;
    }

    protected static String[] randomizer(String[] makeRand) {
        List<String> str = Arrays.asList(Arrays.copyOf(makeRand, makeRand.length));
        Collections.shuffle(str);
        return str.toArray(new String[0]);
    }

    public static String getDate() {
        String timeLog = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        return timeLog;
    }

    public static String getTime() {
        String timeLog = new SimpleDateFormat(" HH:mm:ss ").format(Calendar.getInstance().getTime());
        return timeLog;
    }

    private static Java2ExelFile excel;

    public static void createExcelFile(String fileName, int type) {
        excel = new Java2ExelFile(fileName + ".xlsx", type);
    }

    public static void createTexDoc(String docName) {
        try {
            //create a temporary file

            File logFile = new File(docName);

            // This will output the full path where the file will be written to...
            //writeResponse(logFile.getCanonicalPath());
            writer = new BufferedWriter(new FileWriter(docName));

        } catch (Exception e) {
        }
    }

    static String[] emotions = {"happyT3", "disgustedT1F", "sadT3", "fearT3", "disgustedT3", "angryT3", "neutral", "happyT1", "sadT1", "fearT1", "disgustedT1", "angryT1", "neutral", "happyT2", "sadT2", "surpriseT2", "fearT2", "disgustedT2", "angryT2", "neutral", "happyT3F", "sadT3F", "surpriseT3F", "fearT3F", "disgustedT3F", "angryT3F", "sadT1F", "happyT1F", "fearT1F", "surpriseT3", "angryT1F", "surpriseT1F", "happyT2F", "sadT2F", "surpriseT2F", "fearT2F", "disgustedT2F", "surpriseT1", "angryT2F"};
    static BufferedWriter writer = null;
    static String[] eyeGazes = {"11", "12", "13", "14", "21", "22", "23", "24", "31", "32", "33", "34"};

    protected static void closeTextDoc() {
        try {
            write("\n PROGRAM END:-------------- \n");
            // Close the writer regardless of what happens...
            writer.close();

        } catch (Exception e) {
        }

    }

    public static void writeResponse(String Response) {
        DanceDanceSwingInterface.robotRspns.setText(Response);
    }

    /*       public static void upDateAnimCount(int animCount)
     {
     String count = Integer.toString(13-animCount);
     DanceDanceSwingInterface.animCount.setText(count +" left");
     } */
    protected static void write(String word) {
        try {
            writer.write(word + "\n");
            //System.out.println("wrote");
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void runDanceSession() {
        int danceThis;
        writeResponse("enter 'yes'or 'no' to dance question");
        String doDance = getInputEntered();

        doDance = getInputEntered();
        if (doDance.equals("no")) {
            zenoAnimate("nod2");
            zenoSpeak("Okay. Let's go on.");
        } else {
            zenoSpeak("Yay!! You do want to dance!");
            zenoAnimate("nodUpA1");
            zenoSleep(10);
            zenoSpeak("Let's wait a little bit while they put on the song!");
            zenoAnimate("nod1");

            writeResponse("press ENTER to start dancing");
            doDance = getInputEntered();
            writeResponse("Yay! Dance Dance!! ^_^");
            zenoAnimate("lookUp");
            zenoSpeak("Thanks!");
            zenoSleep(10);
            zenoSpeak("YAY! Let's dance");
            zenoSpeak("I'll follow you with my head!");
            zenoAnimate("happyNeut");
            zenoAnimate("dance1");
            while (!dance.equals("end")) {
                //yup. In the mood for dancing...
                if (DanceDanceSwingInterface.getIfEnterPressed()) {
                    writeResponse("Type in 'end' to end the dance");
                    dance = getInputEntered();
                    //check if still in the mood to dance :D
                }
            }
            //okay dance no more
            zenoSpeak("Haha! That was fun!");
            zenoSpeak("Alright. I guess that was enough dancing.");
            zenoSpeak("Let's keep playing!");
            zenoAnimate("nod1");
            zenoAnimate("happyNeut");
        }
    }

    public static void leftNod() {

        if (!dance.equals("false")) {
            int[] numz = {1, 2, 3};
            int a = numz[new Random().nextInt(numz.length)];
            System.out.println("a" + a);
            if (a == 1) {
                zenoAnimate("nodLeft");
            } else {
                zenoAnimate("nodLeftA1");
            }
        }
    }

    public static void upNod() {
        if (!dance.equals("false")) {
            int[] numz = {1, 2, 3};
            int a = numz[new Random().nextInt(numz.length)];
            System.out.println("a" + a);
            if (a == 1) {
                zenoAnimate("nodUp");
            } else {
                zenoAnimate("nodUpA1");
            }
        }
    }

    public static void rightNod() {
        if (!dance.equals("false")) {
            int[] numz = {1, 2, 3};
            int a = numz[new Random().nextInt(numz.length)];
            if (a == 1) {
                zenoAnimate("nodRight");
            } else {
                zenoAnimate("nodRightA1");
            }
        }
    }

    public static void downNod() {
        if (!dance.equals("false")) {
            int[] numz = {1, 2, 3};
            int a = numz[new Random().nextInt(numz.length)];
            System.out.println("a" + a);
            if (a == 1) {
                zenoAnimate("nodDownA1");
            } else {
                zenoAnimate("nodDown");
            }
        }
    }

    public static void centerNod() {
        if (!dance.equals("false")) {
            zenoAnimate("neutral");
        }
    }

    public static void runSubSession(String[] Sequence, String partName) {
        //Scanner scanner3 = new Scanner (System.in);
        zenoSpeak("Ready " + partName + "?");
        writeResponse("Press ENTER to start the session \n");
        writeInputEntered("");
        getInputEntered();
        // String continueNow = scanner3.nextLine();
        //Scanner scannerLine = new Scanner (System.in);
        String response;
        float wrong = 0;
        float correct = 0;
        //String partID = scanner.next();


        //Zeno will say The session will now start
        writeResponse("The session will now start. Can you please tell me what the emotions I'll try to show you are? \n");
        writeInputEntered("");
        //loop this
        //Zeno "What emotion is this?"

        for (int num = 0; num < 13; num++) {
            // upDateAnimCount(num);
            timeSleep();
            write("\n\n Emotion <<< " + Sequence[num] + " >>>  \n");
            write(getTime());
            //testnorobot Robokind.sleep(200);
            timeSleep();
            writeResponse("Zeno say: What expression is this?");
            String Talk;
            //at the beginning make sure to say this one
            //then afterwards you can say whatever you want...
            if (num == 0) {

                zenoSpeak(partName);
                zenoAnimate(returnOneRandomizer(nodSeq));
                //zenoSpeak("Lets go!");
                //testnorobot Robokind.sleep(1000);
                zenoSpeak(" Can you guess what expression is this?");
                //testnorobot Robokind.sleep(2000);
                timeSleep();
            } else {
                zenoSpeak(returnOneRandomizer(askEmotionSeq));
                timeSleep();
            }

            //writeResponse("Start the talk");
            //testnorobot Robokind.sleep(1000);
            timeSleep();
            //read what is the emotion I am currently looking at then play it
            writeState("emotion: " + Sequence[num]);
            timeSleep();
            //zenoSpeak(Talk);
            zenoAnimate("neutral");
            if (Sequence[num].equals("angryT3")) {
                zenoAnimate(Sequence[num]);
                //testnorobot Robokind.sleep(1000);
                zenoAnimate("angryT3Neutal");
            } else {
                zenoAnimate(Sequence[num]);
                //testnorobot Robokind.sleep(1000);
            }

            //testnorobot Robokind.sleep(1000);
            zenoAnimate("neutral");
            if (num == 0) {
                zenoSpeak("got it?");
            }
            //testnorobot Robokind.sleep(500);

            //Wait for response of child. When I type correct or false then I move on
            //or type repeat to run it again
            //options [true, false,repeat]
            //record all the things I type
            //if I type anything other that that take it as a note:
            response = "";
            while (!(response.equals("true") || response.equals("false"))) {
                timeSleep();
                writeResponse("Type [true][false][repeat][end] or use '*' to animate or '&' to talk\n");
                response = getInputEntered();
                response = response.toLowerCase();

                writeInputEntered("");
                if (response.equals("true")) {
                    timeSleep();
                    writeResponse("recorded true");
                    writeInputEntered("");
                    write("\n           True \n");
                    correct++;
                } else if (response.equals("false")) {
                    timeSleep();
                    writeResponse("recorded false");
                    writeInputEntered("");
                    write("\n           False \n");
                    wrong++;
                } else if (response.equals("repeat")) {
                    timeSleep();
                    writeResponse("will repeat");
                    writeInputEntered("");
                    writeResponse("Zeno do: play the emotion again " + Sequence[num]);
                    write("\n      Played again at time: " + getTime() + "\n");

                    zenoSpeak(returnOneRandomizer(repeatSeq));
                    zenoAnimate("nod2");
                    //zenoAnimate("happy");
                    //testnorobot Robokind.sleep(200);
                    //testnorobot Robokind.sleep(00);

                    if (Sequence[num].equals("angryT3")) {
                        zenoAnimate(Sequence[num]);
                        //testnorobot Robokind.sleep(1000);
                        zenoAnimate("angryT3Neutal");
                    } else {
                        zenoAnimate(Sequence[num]);
                        //testnorobot Robokind.sleep(1000);
                    }
                } else if (response.equals("end")) {
                    writeResponse("END NOW");
                    write("\n Program ended prematurely here \n");

                    zenoSpeak("Okay");
                    zenoAnimate("nod2");
                    zenoSpeak("Lets end this early.");
                    closeTextDoc();
                    zenoDisconnect();
                    System.exit(0);
                } else if (response.indexOf("*") != -1) {
                    response = response.replace("*", "");
                    response = response.replace(" ", "");
                    zenoAnimate(response);
                    //writeResponse("Animate");

                } else if (response.indexOf("&") != -1) {
                    response = response.replace("&", "");
                    response = response.replace(" ", "");
                    zenoSpeak(response);
                    writeResponse("Talking");
                } else if (response.indexOf("#") != -1) {
                    // skip doing anything here since the user wants to end program.
                    // no talking. no animating. 
                } else {
                    writeResponse("Taken as Note:");
                    write("\n        Note: " + response);
                }

            }


            writeResponse("Zeno do: nod");
            //zenoSpeak("I will nod");
            zenoAnimate("happy");
            timeSleep();
            if (num == 7) {
                zenoSpeak(returnOneRandomizer(affirmSeq));
                zenoSpeak("We're almost finished with this round " + partName + "!");
                //testnorobot Robokind.sleep(2000);
                timeSleep();
            } else if (num == 12) {
                zenoSpeak("Great?");
                // zenoAnimate("AZR50_Cheer_FistPump_01.anim");
                zenoSpeak("We are finished?");
                //testnorobot Robokind.sleep(1000);
                float percentCorrect = (correct / 13) * 100;
                int sayPercent = (int) percentCorrect;
                zenoSpeak("We got" + sayPercent + "percent correctly guessed!");
                zenoSpeak("Thanks for playing with me!");
                //zenoAnimate("happy_3");
                //zenoAnimate("AZR50_Dancing_01.anim");   
                timeSleep();
            } else {
                zenoSpeak(returnOneRandomizer(affirmSeq));
                zenoAnimate("nod1");
                writeResponse("Zeno say: okay");
                timeSleep();
            }
            //else
            //{
            //zenoSpeak(returnOneRandomizer(affirmSeq));
            //}

        }

//
        write("SESSION END:");
        float percentCorrect = correct / 13;
        write("% correct:" + percentCorrect);
        write("% correct:" + correct);


    }

    public static void writeState(String state) {
        DanceDanceSwingInterface.writeState(state);
    }

    private static String returnOneRandomizer(String[] sequenceOptions) {
        Random randNum = new Random();
//random permutations of array 

        String randomOne;

        int n = sequenceOptions.length;
        int[] res = new int[n];

        for (int i = 0; i < n; i++) {
            int d = randNum.nextInt(i + 1);
            res[i] = res[d];
            res[d] = i;
        }

        randomOne = sequenceOptions[res[1]];
        return randomOne;
    }

    public static boolean init() {

        String ipAdress = "192.168.0.105";
        UserSettings.setRobotAddress(ipAdress);
        UserSettings.setRobotId("myRobot");
        myRobot = Robokind.connectRobot();

        UserSettings.setSpeechAddress(ipAdress);
        mySpeaker = Robokind.connectSpeechService();

        UserSettings.setAnimationAddress(ipAdress);
        myPlayer = Robokind.connectAnimationPlayer();

        return true;

    }

    public static void callibrateRobot() {
        //Callibrate robot head

        myNeckYaw = new Robot.JointId(myRobot.getRobotId(),
                new Joint.Id(RobotJoints.NECK_YAW));

        myLeftEye = new Robot.JointId(myRobot.getRobotId(),
                new Joint.Id(RobotJoints.LEFT_EYE_YAW));

        myRightEye = new Robot.JointId(myRobot.getRobotId(),
                new Joint.Id(RobotJoints.RIGHT_EYE_YAW));

        Robot.RobotPositionMap posMap = myRobot.getCurrentPositions();
        //double sendNeckYaw = posMap.get(myRightEye).getValue(); 
        //double sendNeckYaw = posMap.get(myLeftEye).getValue();


        double sendNeckYaw = ((NormalizedDouble) posMap.get(myNeckYaw)).getValue();

        System.out.println("the neck yaw to send" + sendNeckYaw);
        DanceDanceSwingInterface.sendNeckYaw(sendNeckYaw);


        writeResponse("Calibrate Zeno's head - Press set when done");
        while (!calibrateButtonPressed) {
            calibrateButtonPressed = DanceDanceSwingInterface.isCalibratePressed();
            calibNeckYaw = DanceDanceSwingInterface.calibNeckYaw();
        }

    }

    protected static void moveHeadThereRobot(double angle) {
        Robot.RobotPositionHashMap goals = new RobotPositionHashMap();
        goals.clear();

        //goals.put(myRightEye,new NormalizedDouble(angle));
        //goals.put(myLeftEye, new NormalizedDouble(angle));
        goals.put(myNeckYaw, new NormalizedDouble(angle));
        myRobot.move(goals, 5);
        System.out.println("Moving to " + angle);
    }

    public static void robotGaze() {
        //generate random eye gaze sequence

        System.out.println("HEERREE");
        //Generate subsession sequence for Zeno Expression
        String[] randEyeGaze = randomizer(eyeGazes);
        System.out.println("HEERREE2 " + randEyeGaze.length);

        String[] eyeOneSequence = new String[12];
        for (int i = 0; i < 12; i++) {
            eyeOneSequence[i] = randEyeGaze[i];
            System.out.println("HEERREE3");
        }

        String[] randEyeGaze2 = randomizer(eyeGazes);
        System.out.println("HEERREE2 " + randEyeGaze2.length);
        String[] eyeTwoSequence = new String[12];
        for (int i = 0; i < 12; i++) {
            eyeTwoSequence[i] = randEyeGaze2[i];
            System.out.println("HEERREE4" + eyeTwoSequence[i]);
        }


        write(">>>>>>>>Playing EyeGaze game<<<<<<<<<<<<<<<<<<<<<<<");

        zenoSpeak("Alright. Let's play a game toghether today! " + partName + " !");


        // zenoSpeak("For this game tell me what dirrection I am looking at");

        // zenoSpeak("I will look either this way. Right");
        // writeResponse("press ENTER to look to the right");
        // String write = getInputEntered();
        // zenoAnimate("nodRight");
        //  zenoSpeak("Or this way. Left");
        // writeResponse("press ENTER to look to the right");
        // write = getInputEntered();
        // zenoAnimate("nodLeft");
        // Robokind.sleep(800);
        // zenoAnimate("nodCenter");

        zenoSpeak("To play the game let me know what direction you think I am looking at.");

        zenoSpeak("Ready " + partName + " ?");
        writeResponse("Type 'yes' if ready");
        String wait = getInputEntered();
        wait = wait.toLowerCase();
        if (wait.equals("yes")) {
            zenoSpeak("Awesome!");

        } else {
            zenoSpeak("Okay. I'll wait.");
            zenoSpeak("Let me know when you are ready.");
            getInputEntered();
        }

        //create array randomly 
        zenoSpeak("Yay! Here is the first one!");
        //randomly show;

        showRobotGaze(eyeOneSequence, 1);
        zenoSpeak("Okay we are going to go faster now!");
        zenoSpeak("I won't say anything between the different head poses so just point to what direction you think I am pointing towards");
        showRobotGaze(eyeTwoSequence, 2);

        endSession4();

    }

    /*
     * public static void robotGaze()
     * {
     * //generate random eye gaze sequence
     * <p/>
     * System.out.println("HEERREE");
     * //Generate subsession sequence for Zeno Expression
     * String[] randEyeGaze= randomizer(eyeGazes);
     * System.out.println("HEERREE2 "+randEyeGaze.length);
     * <p/>
     * String[] eyeOneSequence=new String[12];
     * for(int i=0; i<12; i++)
     * {
     * eyeOneSequence[i] = randEyeGaze[i];
     * System.out.println("HEERREE3");
     * }
     * <p/>
     * String[] randEyeGaze2= randomizer(eyeGazes);
     * System.out.println("HEERREE2 "+randEyeGaze2.length);
     * String[] eyeTwoSequence=new String[12];
     * for(int i=0; i<12; i++)
     * {
     * eyeTwoSequence[i] = randEyeGaze2[i];
     * System.out.println("HEERREE4");
     * }
     * <p/>
     * <p/>
     * write(">>>>>>>>Playing EyeGaze game<<<<<<<<<<<<<<<<<<<<<<<");
     * <p/>
     * zenoSpeak("Alright. We are almost done for today " +partName+ " !");
     * <p/>
     * <p/>
     * zenoSpeak("For this last game. It will be similar to the game you played on the computer before.");
     * <p/>
     * zenoSpeak("I will look either this way. Right");
     * writeResponse("press ENTER to look to the right");
     * String write = getInputEntered();
     * zenoAnimate("nodRight");
     * zenoSpeak("Or this way. Left");
     * writeResponse("press ENTER to look to the right");
     * write = getInputEntered();
     * zenoAnimate("nodLeft");
     * //testnorobot Robokind.sleep(800);
     * zenoAnimate("nodCenter");
     * <p/>
     * zenoSpeak("To play the game let me know what direction you think I am looking at.");
     * //testnorobot Robokind.sleep(200);
     * zenoSpeak("You can point if you'd like");
     * //testnorobot Robokind.sleep(200);
     * <p/>
     * zenoSpeak("Ready?");
     * writeResponse("Type 'yes' if ready");
     * String wait = getInputEntered();
     * wait = wait.toLowerCase();
     * if(wait.equals("yes"))
     * {
     * zenoSpeak("Awesome!");
     * }
     * else
     * {
     * <p/>
     * zenoSpeak("Okay. I'll wait.");
     * zenoSpeak("Let me know when you are ready.");
     * wait = getInputEntered();
     * }
     * <p/>
     * //create array randomly
     * zenoSpeak("Here is the first one!");
     * //randomly show;
     * <p/>
     * showRobotGaze(eyeOneSequence, 1);
     * zenoSpeak("Okay we are going to go faster now!");
     * zenoSpeak("I won't say anything between the different head poses so just point to what direction you think I am pointing towards");
     * showRobotGaze(eyeTwoSequence, 2);
     * <p/>
     * endSession4();
     * <p/>
     * }
     */
    public static void endSession4() {
        zenoAnimate("happyNeut");
        zenoAnimate("nodCenter");
        zenoSpeak("Yay!");
        zenoAnimate("nod1");
        zenoSpeak("We are finished for today " + partName + " !");
        zenoAnimate("nod2");
        zenoSpeak("I loved playing with you today!!!");
        zenoAnimate("nod3");
        zenoAnimate("dance1");
        zenoAnimate("I think it is going to be my brother Nao's turn to play with you now!");
        zenoSpeak("Please come back soon " + partName + "!");
        Robokind.sleep(1000);
        zenoAnimate("waveBye");
        zenoSpeak("I miss you already " + partName + " !");
    }

    public static void moveToEyeGaze(double headAngle, double leftEyeAngle, double rightEyeAngle) {

        Robot.RobotPositionHashMap goals = new RobotPositionHashMap();

        goals.clear();
        goals.put(myLeftEye, new NormalizedDouble(leftEyeAngle));
        goals.put(myRightEye, new NormalizedDouble(rightEyeAngle));
        goals.put(myNeckYaw, new NormalizedDouble(headAngle + calibNeckYaw));
        myRobot.move(goals, 5);

    }

    public static void showRobotGaze(String[] Sequence, int timesDoing) {

        moveToEyeGaze(head82, lEyeNeg25, rEyeNeg25);

        for (int i = 0; i < 12; i++) {
            if ((i != 0) && (timesDoing == 1)) {
                System.out.println("in the show robot gaze");
                zenoSpeak(returnOneRandomizer(askGazeSeq));
            }


            if ((Character.toString(Sequence[i].charAt(0))).equals("1")) {

                if ((Character.toString(Sequence[i].charAt(1))).equals("1")) {
                    //11
                    moveToEyeGaze(head82, lEyeNeg25, rEyeNeg25);
                }

                if ((Character.toString(Sequence[i].charAt(1))).equals("2")) {
                    //12
                    moveToEyeGaze(head82, lEyeNeg5, rEyeNeg5);
                }

                if ((Character.toString(Sequence[i].charAt(1))).equals("3")) {
                    //13
                    moveToEyeGaze(head82, lEyePos5, rEyePos5);
                }

                if ((Character.toString(Sequence[i].charAt(1))).equals("4")) {
                    //14
                    moveToEyeGaze(head82, lEyePos25, rEyePos25);
                }

            }

            if ((Character.toString(Sequence[i].charAt(0))).equals("2")) {

                if ((Character.toString(Sequence[i].charAt(1))).equals("1")) {
                    //21
                    moveToEyeGaze(head90, lEyeNeg25, rEyeNeg25);

                }

                if ((Character.toString(Sequence[i].charAt(1))).equals("2")) {
                    //22
                    moveToEyeGaze(head90, lEyeNeg5, rEyeNeg5);
                }

                if ((Character.toString(Sequence[i].charAt(1))).equals("3")) {
                    //23
                    moveToEyeGaze(head90, lEyePos5, rEyePos5);
                }

                if ((Character.toString(Sequence[i].charAt(1))).equals("4")) {
                    //24
                    moveToEyeGaze(head90, lEyePos25, rEyePos25);
                }

            }

            if ((Character.toString(Sequence[i].charAt(0))).equals("3")) {

                if ((Character.toString(Sequence[i].charAt(1))).equals("1")) {
                    //31
                    moveToEyeGaze(head98, lEyeNeg25, rEyeNeg25);

                }

                if ((Character.toString(Sequence[i].charAt(1))).equals("2")) {
                    //32
                    moveToEyeGaze(head98, lEyeNeg5, rEyeNeg5);
                }

                if ((Character.toString(Sequence[i].charAt(1))).equals("3")) {
                    //33
                    moveToEyeGaze(head98, lEyePos5, rEyePos5);
                }

                if ((Character.toString(Sequence[i].charAt(1))).equals("4")) {
                    //34
                    moveToEyeGaze(head98, lEyePos25, rEyePos25);
                }

            }

            //recode what head and eye position that was
            //zenoSpeak("What direction do you think I am looking at?");
            //record what dirrection the kid thought the robot was looking towards
            recordDirection(Sequence[i]);

        }

    }

    public static void recordDirection(String direction) {
        // 0 is left for participant
        //1 is right for participant
        writeResponse("Press an Arrow to what the kid thought was the dirrection");
        while (DanceDanceSwingInterface.listenForArrow() == false) {
            DanceDanceSwingInterface.listenForArrow();
            System.out.print("");
        }
        int kidDirrection = DanceDanceSwingInterface.getDirrectionOfKid();

        //write("Eyegaze shown: " + direction + "\n");
        //write("kidDirrection:" + kidDirrection + "\n");
        //write(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> \n");

    }

    //THIS IS THE ACTUALL "MAIN of the PROGRAM"
    public static void startTalk() {
        //Callibrate robot head
        //zenoAnimate("nodCenter");
        System.out.println("n Start talk");
        callibrateRobot();

        writeResponse("Enter your name 'the robot controller' \n");
        robotConName = getInputEntered();
        writeInputEntered("");

        writeResponse("Enter the participant's ID or type 'skip' to freestyle control the robot \n");
        partID = getInputEntered();
        writeInputEntered("");

        writeResponse("Enter the participant's name \n");
        partName = getInputEntered();

        String testSkip = partID.toLowerCase();
        testSkip = testSkip.
                replaceAll(" ", "");
        if (testSkip.equals("skip")) {
            writeResponse("Enableing FreeStyle. Type # to exit program.");
            freeStyle();
            //zenoDisconnect();
            System.out.println("Exiting Program from skip!");
            System.exit(0);
        }

        writeInputEntered("");

        writeResponse("Start the talk");
        //  zenoAnimate("happyNeut");

        zenoAnimate("blink");
        zenoSpeak("Hi!");
        zenoSpeak("I am ready to start!");


        boolean selectedType = false;

        while (!selectedType) {
            selectedType = true;
            writeResponse("Is this a baseline,intervention, or eyegaze int? Type 'base', 'int', 'eye' to select.");
            String baseOrInit = getInputEntered();
            writeInputEntered("");

            if (baseOrInit.equals("int")) {

                String interNumb;
                int interNumber;
                //send to the proper session
                boolean passInter = false;

                while (!passInter) {
                    writeResponse("Okay. This is an intervention. What intervention number is it? [1-6]");

                    passInter = true;
                    interNumb = getInputEntered();
                    if (!interNumb.equals("test")) {
                        writeInputEntered("");
                        System.out.println("passed the int numb");
                        interNumber = Integer.parseInt(interNumb);
                        if (0 < interNumber && interNumber < 7) {
                            System.out.println("in reinforcemnt place");
                            //GENARATE LIST
                            writeResponse("Enter the first reinforcement the child prefers (code)");
                            int reinf1 = Integer.parseInt(getInputEntered());
                            writeInputEntered("");
                            writeResponse("Enter the second reinforcement the child prefers (code)");
                            int reinf2 = Integer.parseInt(getInputEntered());
                            writeInputEntered("");
                            writeResponse("Enter the third reinforcement the child prefers (code)");
                            int reinf3 = Integer.parseInt(getInputEntered());
                            writeInputEntered("");
                            int[] sessionActivityOrder = generateActivitySequence();
                            int[] sessionEmotionOrder = generateEmotionSequence();
                            int[] reinfOrder = generateReinfSequence(reinf1, reinf2, reinf3);
                            System.out.println("activity:" + Arrays.toString(sessionActivityOrder));

                            writeResponse("Okay. This is intervention #" + interNumber + ". Let me generate the sequence for the intervention and save it.");
                            wait(2000);
                            writeResponse("Let me generate a file for the session and save the sequence.");
                            //Generate the file
                            //Store Participant ID
                            //Store RobotConductor
                            //Store Date of Session
                            //Store Session Order

                            docTitle = partID + getDate() + " Zeno3";
                            createTexDoc(docTitle);
                            createExcelFile(docTitle, Java2ExelFile.INTERVENTION);

                            writeResponse("Okay Done. Press ENTER when ready to begin intervention.");
                            getInputEntered();
                            //call the 4 different files here....
                            //Go To RunIntervention method
                            runIntervention(sessionActivityOrder, sessionEmotionOrder, reinfOrder);
                        } else {
                            writeResponse("Sorry. That is not a number or that session does not exist... Try again.");
                            passInter = false;
                            try {
                                Thread.sleep(2000);                 //1000 milliseconds is one second.
                            } catch (InterruptedException ex) {
                                Thread.currentThread().interrupt();
                            }
                        }
                    } else {
                        writeInputEntered("");
                        System.out.println("passed the int numb");
                        interNumber = 1;
                        if (0 < interNumber && interNumber < 7) {
                            System.out.println("in reinforcemnt place");
                            //GENARATE LIST
                            writeResponse("Enter the first reinforcement the child prefers (code)");
                            int reinf1 = 2;
                            writeInputEntered("");
                            writeResponse("Enter the second reinforcement the child prefers (code)");
                            int reinf2 = 3;
                            writeInputEntered("");
                            writeResponse("Enter the third reinforcement the child prefers (code)");
                            int reinf3 = 4;
                            writeInputEntered("");
                            int[] sessionActivityOrder = generateActivitySequence();
                            int[] sessionEmotionOrder = generateEmotionSequence();
                            int[] reinfOrder = generateReinfSequence(reinf1, reinf2, reinf3);
                            System.out.println("activity:" + Arrays.toString(sessionActivityOrder));

                            writeResponse("Okay. This is intervention #" + interNumber + ". Let me generate the sequence for the intervention and save it.");
                            //wait(2000);
                            writeResponse("Let me generate a file for the session and save the sequence.");
                            //Generate the file
                            //Store Participant ID
                            //Store RobotConductor
                            //Store Date of Session
                            //Store Session Order

                            docTitle = partID + getDate() + " Zeno3";
//                                    createTexDoc(docTitle);
                            createExcelFile(docTitle, Java2ExelFile.INTERVENTION);

                            writeResponse("Okay Done. Press ENTER when ready to begin intervention.");
                            getInputEntered();
                            //call the 4 different files here....
                            //Go To RunIntervention method
                            runIntervention(sessionActivityOrder, sessionEmotionOrder, reinfOrder);


                            //CLOSE HERE!
                        }
                    }
                }

            } else if (baseOrInit.equals("base")) {
                String baseNumb;
                int baseNumber;

                //send to the correct baseline session
                boolean passBase = false;

                while (!passBase) {
                    writeResponse("Okay. This is a baseline. What baseline Code Number is it? [1-6]");
                    passBase = true;
                    baseNumb = getInputEntered();
                    String holder = null;
                    writeInputEntered("");
                    baseNumber = Integer.parseInt(baseNumb);
                    if (baseNumber == 1 || baseNumber == 6) {
                        writeResponse("This is a Computer Eyegaze Baseline. This computer is not needed");
                        //Wait and terminate program
                        wait(2000);
                        zenoDisconnect();
                        System.exit(0);

                    } else if (baseNumber == 2 || baseNumber == 5) {

                        writeResponse("Okay. This is Human Baseline/Interaction #" + baseNumber + ". Let me generate the sequence for the baseline and save it.");
                        //GENARATE LIST 
                        int[] sessionActivityOrder = generateActivitySequence();
                        int[] sessionEmotionOrder = generateEmotionSequence();

                        wait(3000);
                        writeResponse("Let me generate a file for the baseline and save the sequence.");
                        //Generate the file
                        //Store Participant ID
                        //Store RobotConductor
                        //Store Date of Session
                        //Store Session Order

                        docTitle = partID + getDate() + " Zeno3";
                        createTexDoc(docTitle);
                        createExcelFile(docTitle, Java2ExelFile.BASELINE);

                        write("Participant's ID:" + partID + "\n");
                        write("Time started:" + getTime() + "\n\n");

                        wait(2000);

                        writeResponse("Okay Done. Press ENTER when ready to begin the baseline.");
                        holder = getInputEntered();
                        runHumanBaseline(sessionActivityOrder, sessionEmotionOrder);

                        //CLOSE HERE!

                    } else if (baseNumber == 3) {
                        writeResponse("Okay. This is a Reinforcement Baseline Task.");
                        wait(2000);
                        writeResponse("Let me generareate a file for the Reinforcement Task and save stuff into it.");
                        docTitle = partID + getDate() + " Zeno3_Rewards";
                        createTexDoc(docTitle);

                        write("Participant's ID:" + partID + "\n");
                        write("Time started:" + getTime() + "\n\n");
                        wait(2000);
                        writeResponse("Okay Done. Press ENTER when ready to begin the task.");
                        holder = getInputEntered();
                        runReinforcement();

                    } else if (baseNumber == 4) {
                        writeResponse("Okay. This is a Robot baseline #" + baseNumber + ". Let me generate the sequence for the baseline and save it.");
                        //  writeResponse("Okay. This is Human Baseline/Interaction #" + baseNumber+". Let me generate the sequence for the baseline and save it.");
                        //GENARATE LIST 
                        int[] sessionActivityOrder = generateActivitySequence();
                        int[] sessionEmotionOrder = generateEmotionSequence();

                        wait(2000);
                        writeResponse("Let me generate a file for the baseline and save the sequence.");
                        //Generate the file
                        //Store Participant ID
                        //Store RobotConductor
                        //Store Date of Session
                        //Store Session Order

                        docTitle = partID + getDate() + " Zeno3RobotBaseline";
                        createTexDoc(docTitle);
                        createExcelFile(docTitle, Java2ExelFile.BASELINE);

                        write("Participant's ID:" + partID + "\n");
                        write("Time started:" + getTime() + "\n\n");

                        wait(2000);

                        writeResponse("Okay Done. Press ENTER when ready to begin the baseline.");
                        //wait for enter
                        holder = getInputEntered();
                        zenoAnimate("happy");
                        runRobotBaseline(sessionActivityOrder, sessionEmotionOrder);
                    } else {
                        writeResponse("Sorry. That is not a number or that baseline does not exist... Try again.");
                        passBase = false;
                        wait(2000);
                    }

                    System.out.println("passBase = " + passBase);


                }

                //CLOSE HERE!
                excel.closeFile();
                closeTextDoc();
                writeResponse("Finished Activity!");
                zenoDisconnect();
                System.exit(0);

            } else if (baseOrInit.equals("eye")) {

                String eyeNumb;
                int eyeNumber;

                //send to the correct baseline session
                boolean passEye = false;

                while (!passEye) {
                    writeResponse("Okay. This is an eyegaze intervention. What number is it? [1-4]");
                    passEye = true;
                    eyeNumb = getInputEntered();
                    writeInputEntered("");
                    eyeNumber = Integer.parseInt(eyeNumb);
                    if (0 < eyeNumber && eyeNumber < 5) {
                        writeResponse("Okay. This is eyegaze #" + eyeNumber + ". Let me generate the sequence for the eyegaze and save it.");

                        wait(1000);
                        docTitle = partID + getDate() + " Zeno3Eyegaze_Num_" + eyeNumber;
                        createTexDoc(docTitle);
                        createExcelFile(docTitle, Java2ExelFile.BASELINE);

                        write("Participant's ID:" + partID + "\n");
                        write("Time started:" + getTime() + "\n\n");

                        wait(2000);
                        writeResponse("Let me generate a file for the eygaze and save the sequence.");

                        writeResponse("Okay Done. Press ENTER when ready to begin the eyegaze intervention.");
                        getInputEntered();
                        runEyeIntervention();

                        //CLOSE HERE!
                        excel.closeFile();
                        closeTextDoc();
                        writeResponse("Finished Activity!");
                    } else {
                        writeResponse("Sorry. That is not a number or that eyegaze session does not exist... Try again.");
                        passEye = false;
                        wait(2000);
                    }
                }
            } else {
                selectedType = false;
                writeResponse("Sorry. That is not an option. Please try again...");
                wait(2000);
            }
        }


        zenoDisconnect();
        System.exit(0);
    }

    private static void wait(int time) {
        //wait for time in milliseconds

        try {
            Thread.sleep(time);                 //1000 milliseconds is one second.
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    private static void runIntervention(int[] sessionActivityOrder, int[] sessionEmotionOrder, int[] reinfOrder) {
        System.out.println("inside run intervention" + sessionActivityOrder[2]);

//1 = IdentifyEmotion(String filePath, String emotion, String reinforcement, String notEmotion)
//2 = CopyFace2(String filePath, String Emotion, String Reinforcement)
//3 = RecognizeFaceExpression(String filePath, String emotion, String reinforcement, int NumTimesRecogAcivity)
//4 = SelectExpression(String filePath, String emotion, String reinforcement, String promptHint)

        write("session activity order:" + sessionActivityOrder + "\n");
        write("Emotion order" + sessionEmotionOrder + "\n\n");
        write("Reinf order" + reinfOrder + "\n\n");
        ClientThread1 client = openSocketLocal();
        String emotion = null;
        //String reinf = null;
        for (int i = 0; i < 20; i++) {
            System.out.println("i " + i + ": emotion " + sessionEmotionOrder[i] + "  activity " + sessionActivityOrder[i]);

            if (sessionEmotionOrder[i] == 1) {
                emotion = "happy";
                System.out.println("happy");
            } else if (sessionEmotionOrder[i] == 2) {
                emotion = "sad";
                System.out.println("sad");
            } else if (sessionEmotionOrder[i] == 3) {
                emotion = "surprised";
                System.out.println("surprised");
            } else if (sessionEmotionOrder[i] == 4) {
                emotion = "angry";
                System.out.println("angry");
            } else if (sessionEmotionOrder[i] == 5) {
                emotion = "fear";
                System.out.println("fear");
            } else {
                emotion = "happy";
                System.out.println("what?");
            }


            //IdentifyEmotion(emotion, client, Integer.toString(reinfOrder[i]));
            SelectExpression(emotion, client, Integer.toString(reinfOrder[i]));
            //RecognizeFaceExpression(emotion, client, Integer.toString(reinfOrder[i]));

   /*                      
             if(sessionActivityOrder[i]==1)
             {IdentifyEmotion(emotion, client, Integer.toString(reinfOrder[i]));}
                     
//             else if (sessionActivityOrder[i]==2)
//             {
//             //CopyFace2(emotion, client, Integer.toString(reinfOrder[i]));
//             CopyFace2RB(emotion, client);
//             }

             else if(sessionActivityOrder[i]==3)
             {RecognizeFaceExpression(emotion, client, Integer.toString(reinfOrder[i]));}

             else if (sessionActivityOrder[i]==4) 
             {SelectExpression(emotion, client, Integer.toString(reinfOrder[i]));}
        */
        }
        excel.closeFile();
        closeTextDoc();
        client.sender("exit");
        client.closeSocket();


    }

    protected static ClientThread1 openSocketLocal() {
        ClientThread1 client;
        try {
            client = new ClientThread1();
//            client.openSocket();
            return client;
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private static void runHumanBaseline(int[] sessionActivityOrder, int[] sessionEmotionOrder) {
        //1 = IdentifyEmotion(String filePath, String emotion, String reinforcement, String notEmotion)
//2 = CopyFace2(String filePath, String Emotion, String Reinforcement)
//3 = RecognizeFaceExpression(String filePath, String emotion, String reinforcement, int NumTimesRecogAcivity)
//4 = SelectExpression(String filePath, String emotion, String reinforcement, String promptHint)
        //open socket


        write("session activity order:" + sessionActivityOrder + "\n");
        write("Emotion order" + sessionEmotionOrder + "\n\n");

        ClientThread1 client = openSocketLocal();
        String emotion = null;
        for (int i = 0; i < 3; i++) {
            System.out.println("i " + i + ": emotion " + sessionEmotionOrder[i] + "  activity " + sessionActivityOrder[i]);

            if (sessionEmotionOrder[i] == 1) {
                emotion = "happy";
                System.out.println("happy");
            } else if (sessionEmotionOrder[i] == 2) {
                emotion = "sad";
                System.out.println("sad");
            } else if (sessionEmotionOrder[i] == 3) {
                emotion = "surprised";
                System.out.println("surprised");
            } else if (sessionEmotionOrder[i] == 4) {
                emotion = "angry";
                System.out.println("angry");
            } else if (sessionEmotionOrder[i] == 5) {
                emotion = "fear";
                System.out.println("fear");
            } else {
                emotion = "happy";
                System.out.println("what?");
            }


            if (sessionActivityOrder[i] == 1) {
                IdentifyEmotionHB(emotion, client);
            } else if (sessionActivityOrder[i] == 2) {
                CopyFace2HB(emotion, client);
            } else if (sessionActivityOrder[i] == 3) {
                RecognizeFaceExpressionHB(emotion, client);
            } else if (sessionActivityOrder[i] == 4) {
                SelectExpressionHB(emotion, client);
            }
        }
        excel.closeFile();
        closeTextDoc();
        client.sender("exit");
        client.closeSocket();

    }

    private static void runRobotBaseline(int[] sessionActivityOrder, int[] sessionEmotionOrder) {
        //1 = IdentifyEmotion(String filePath, String emotion, String reinforcement, String notEmotion)
//2 = CopyFace2(String filePath, String Emotion, String Reinforcement)
//3 = RecognizeFaceExpression(String filePath, String emotion, String reinforcement, int NumTimesRecogAcivity)
//4 = SelectExpression(String filePath, String emotion, String reinforcement, String promptHint)
        //open socket
        System.out.println("Inside the robot baseline");

        write("session activity order:" + sessionActivityOrder + "\n");
        write("Emotion order" + sessionEmotionOrder + "\n\n");

        ClientThread1 client = openSocketLocal();
        String emotion = null;
        for (int i = 0; i < 20; i++) {
            System.out.println("i " + i + ": emotion " + sessionEmotionOrder[i] + "  activity " + sessionActivityOrder[i]);

            if (sessionEmotionOrder[i] == 1) {
                emotion = "happy";
                System.out.println("happy");
            } else if (sessionEmotionOrder[i] == 2) {
                emotion = "sad";
                System.out.println("sad");
            } else if (sessionEmotionOrder[i] == 3) {
                emotion = "surprised";
                System.out.println("surprised");
            } else if (sessionEmotionOrder[i] == 4) {
                emotion = "angry";
                System.out.println("angry");
            } else if (sessionEmotionOrder[i] == 5) {
                emotion = "fear";
                System.out.println("fear");
            } else {
                emotion = "happy";
                System.out.println("what?");
            }
//
//RecognizeFaceExpressionHB(emotion, client);
//IdentifyEmotionHB(emotion, client);


            if (sessionActivityOrder[i] == 1) {
                IdentifyEmotionRB(emotion, client);
            } else if (sessionActivityOrder[i] == 2) {
                CopyFace2RB(emotion, client);
            } else if (sessionActivityOrder[i] == 3) {
                RecognizeFaceExpressionRB(emotion, client);
            } else if (sessionActivityOrder[i] == 4) {
                SelectExpressionRB(emotion, client);
            }
        }
        excel.closeFile();
        closeTextDoc();
        client.sender("exit");
        client.closeSocket();

    }

    private static void zenoDoReinf(String index) {
        String[] rewards = {"nod3", "nod2", "dance1", "AZR50_Cheer_FistPump_01.anim", "happy"};
        String[] rewardSay = {"", "Great Job!", "", "", "Hooray!"};
        int[] wait = {1500, 1500, 2000, 2500, 1500};
        //String[] index = {"0","1","2","3","4"};
        int indexNum = Integer.parseInt(index);
        zenoSpeak(rewardSay[indexNum]);
        zenoAnimate(rewards[indexNum]);
        zenoSleep(wait[indexNum]);
    }

    private static void runReinforcement() {
        ClientThread1 client = openSocketLocal();

        String[] rewards = {"nod3", "nod2", "dance1", "AZR50_Cheer_FistPump_01.anim", "happy"};
        String[] rewardSay = {"", "Great Job!", "", "", "Hooray!"};
        int[] wait = {1500, 1500, 2000, 2500, 1500};
        String[] index = {"0", "1", "2", "3", "4"};
        index = randomizer(index);
        // int i=1;
        for (int i = 0; i < 5; i++) {
            System.out.println("Running " + rewards[Integer.parseInt(index[i])]);
            playRewardGame(rewards[Integer.parseInt(index[i])], rewardSay[Integer.parseInt(index[i])], wait[Integer.parseInt(index[i])], client);

            //playRewardGame(rewards("",""))//  wait(4000);
        }
        // get finished
        // listen for noplay


        client.sender("exit");
        client.closeSocket();
        closeTextDoc();
    }

    private static void runEyeIntervention() {
        System.out.println("run eyes");
        robotGaze();
    }

    private static int[] generateActivitySequence() {
        int[] mustBeList = new int[20];
        int index = 0;
        for (int k = 0; k < 5; k++) {
            for (int i = 1; i < 5; i++) {
                mustBeList[index] = i;
                index++;
                System.out.print(i);
            }
        }

        int[] sessionOrder = intRandomizer(mustBeList);
        return sessionOrder;
    }

    private static int[] generateEmotionSequence() {
        int[] mustBeList = new int[20];
        int index = 0;
        for (int k = 0; k < 4; k++) {
            for (int i = 1; i < 6; i++) {
                mustBeList[index] = i;
                index++;
                System.out.print(i);
            }
        }

        int[] sessionEmotionOrder = intRandomizer(mustBeList);
        return sessionEmotionOrder;
    }

    //IdentifyEmotion(String filePath, String emotion, String reinforcement, String notEmotion)
//CopyFace2(String filePath, String Emotion, String Reinforcement)
//RecognizeFaceExpression(String filePath, String emotion, String reinforcement, int NumTimesRecogAcivity)
//SelectExpression(String filePath, String emotion, String reinforcement, String promptHint)
    private static int[] generateReinfSequence(int reinf1, int reinf2, int reinf3) {
        System.out.println("Generating Reinforcement Sequence");
        int[] mustBeList = new int[20];
        int[] reinfList = new int[3];
        reinfList[0] = reinf1;
        reinfList[1] = reinf2;
        reinfList[2] = reinf3;
        int index = 0;

        for (int k = 0; k < 7; k++) {
            for (int i = 0; i < 3; i++) {
                if (index < 20) {
                    mustBeList[index] = reinfList[i];
                    index++;
                    System.out.print(reinfList[i]);
                } else {
                    System.out.print("almost out of bounds");
                }
            }
        }

        int[] sessionReinfOrder = intRandomizer(mustBeList);
        return sessionReinfOrder;
    }

    public static void CopyFace2(String emotion, ClientThread1 client, String reinfIndex) {

        String[] prompts = {"not quite", "try again", "almost. try again"};

        zenoSpeak("Press START when you are ready " + partName);
        //send to unity machine to activate activity
        System.out.println("Send:" + client.sender("copy"));
        write("CopyFace");
        //get back echo
        String startTime = getTime();
        write(startTime);
        wait(100);
        System.out.println("Listen2" + client.listener());
        write("    emotion: " + emotion);

        zenoSpeak("Copy My Face with your face!");
        zenoSpeak("Press the RECORD FACE button when you have it on your face!");
        wait(3000);
        zenoSpeak("Here it is");
        //zenoAnimate("nod3");
        wait(4000);
        wait(2000);
        writeResponse("Show " + emotion + " face and have kid COPY");
        zenoAnimate(emotion);
        wait(2000);
        //wait until face is shown
        //writeInputEntered("Press enter to let kid select the emotion on the touchpad.");
        //getInputEntered();

        //send 'go' to allow touch
        System.out.println("send3" + client.sender("go"));
        write("allowed to select: " + getTime());
        writeInputEntered("wait for child to say they are ready by pressing the button.");
        writeInputEntered("Input the childs correct facial expression features");
        //record the answer
        System.out.println("lsiten4:" + client.listener());
        //writeToExcel(String filePath, int SheetNumber, int row, int col, String Stuff2Write)
        write("entered face at" + getTime());
        writeHumanSay("Press enter to record data go on to next activity");
        String kidAnswer = getInputEntered();

        writeInputEntered("");

        if (!emotion.equals(kidAnswer)) {
            //Prompt 1

            //writeToExcel(String filePath, int SheetNumber, int row, int col, String Stuff2Write)
            String[] randPrompt = randomizer(prompts);
            String prompt = randPrompt[1];
            zenoSpeak(prompt);

            ///Init to try again             
            //go to unity main
            System.out.println("send5" + client.sender("main"));
            //zenoSpeak("lets go to the next activity!");
            //zenoAnimate("happy");
            //recieve 'finished'    
            String endTimeP1 = getTime();
            System.out.println("listen6" + client.listener());
            zenoSpeak("Press START when you are ready " + partName);//////////WANT TO REMOVE
            //send to unity machine to activate activity
            System.out.println("Send:" + client.sender("copy"));
            write("CopyFace");
            //get back echo
            String startTimeP1 = getTime();
            write(startTime);
            wait(100);
            System.out.println("Listen2" + client.listener());
            write("    emotion: " + emotion);

//////Let Try Again
            zenoSpeak("Copy my face with your face");

            zenoSpeak("Press the RECORD FACE button when you have it on your face!");
            wait(3000);
            zenoSpeak("Here it is");
            //zenoAnimate("nod3");
            wait(4000);
            wait(2000);
            writeResponse("Show " + emotion + " face and have kid COPY");
            zenoAnimate(emotion);
            wait(2000);
            //wait until face is shown
            // writeInputEntered("Press enter to let kid select the emotion on the touchpad.");
            //getInputEntered();

            //send 'go' to allow touch
            System.out.println("send3" + client.sender("go"));
            write("allowed to select: " + getTime());
            writeInputEntered("wait for child to say they are ready by pressing the button.");
            writeInputEntered("Input the childs correct facial expression features");
            //record the answer
            System.out.println("lsiten4:" + client.listener());
            //writeToExcel(String filePath, int SheetNumber, int row, int col, String Stuff2Write)
            write("entered face at" + getTime());
            writeHumanSay("Press enter to record data go on to next activity");
            kidAnswer = getInputEntered();

            writeInputEntered("");

            if (!emotion.equals(kidAnswer)) {
                //Prompt 2
                zenoSpeak("Not quite. Try moving " + "Give Hint"/*CopyFace2Hint(Emotion)*/);

                ///Init to try again             
                //go to unity main
                System.out.println("send5" + client.sender("main"));
                //zenoSpeak("lets go to the next activity!");
                //zenoAnimate("happy");
                //recieve 'finished'    
                String endTimeP2 = getTime();
                System.out.println("listen6" + client.listener());
                zenoSpeak("Press START when you are ready " + partName);//////////WANT TO REMOVE
                //send to unity machine to activate activity
                System.out.println("Send:" + client.sender("copy"));
                write("CopyFace");
                //get back echo
                String startTimeP2 = getTime();
                write(startTime);
                wait(100);
                System.out.println("Listen2" + client.listener());
                write("    emotion: " + emotion);

//////Let Try Again

                zenoSpeak("Copy my face.");
                zenoSpeak("Press the RECORD FACE button when you have it on your face!");
                wait(3000);
                zenoSpeak("Here it is");
                //zenoAnimate("nod3");
                wait(4000);
                wait(2000);
                writeResponse("Show " + emotion + " face and have kid COPY");
                zenoAnimate(emotion);
                wait(2000);
                //wait until face is shown
                // writeInputEntered("Press enter to let kid select the emotion on the touchpad.");
                //getInputEntered();

                //send 'go' to allow touch
                System.out.println("send3" + client.sender("go"));
                write("allowed to select: " + getTime());
                writeInputEntered("wait for child to say they are ready by pressing the button.");
                writeInputEntered("Input the childs correct facial expression features");
                //record the answer
                System.out.println("lsiten4:" + client.listener());
                //writeToExcel(String filePath, int SheetNumber, int row, int col, String Stuff2Write)
                write("entered face at" + getTime());
                writeHumanSay("Press enter to record data go on to next activity");
                kidAnswer = getInputEntered();

                writeInputEntered("");


                //Prompt 3
                if (!emotion.equals(kidAnswer)) {
                    zenoSpeak("That was a nice try!");
                    //no reinforcement

                    System.out.println("send5" + client.sender("main"));
                    zenoSpeak("lets go to the next activity!");
                    zenoAnimate("happy");
                    //recieve 'finished'    
                    String endTime = getTime();
                    excel.writeCopy("", emotion, startTime, endTime);
                    System.out.println("listen6" + client.listener());

                    return;
                }

            }
        }

        // did the task before 3rd prompt give reinforcement
        zenoSpeak("Good Job!");
        //zenoAnimate(Reinforcement);
        zenoDoReinf(reinfIndex);

        System.out.println("send5" + client.sender("main"));
        zenoSpeak("lets go to the next activity!");
        zenoAnimate("happy");
        //recieve 'finished'    
        String endTime = getTime();
        excel.writeCopy("", emotion, startTime, endTime);
        System.out.println("listen6" + client.listener());


    }

    public static String hintRecognizeFaceExpression(String emotion) {
        String hint;
        if (emotion.equals("happy")) {
            hint = "Look at the corners of my mouth. They are poining upwards.";
        } else if (emotion.equals("sad")) {
            hint = "Look at the corners of my lips they are pointing downwards.";
        } else if (emotion.equals("angry")) {
            hint = "Look at my eyebrows they are pointing down. I am frowning also.";
        } else if (emotion.equals("fear")) {
            hint = "Look at my eyebrows they are pointing upwards. And my lips are stretched downwards. ";
        } else if (emotion.equals("surprised")) {
            hint = "Look at my mouth. It is wide open.";
        } else {
            hint = "the hints are not working Michelle. Fix it.";
        }

        return hint;
    }

    public static void RecognizeFaceExpression(String emotion, ClientThread1 client, String Reinf) {

        String[] prompts = {"Not quite, try again!", "Not that one.", "Almost, try again!", "Not that one! Try again!"};
        writeResponse("Recognize Initial Prompt");
        // send recognize emotion to unity machine
        zenoSpeak("Remember to press start when you are ready to play the next game!");

        //start the unity game
        System.out.println("Send:" + client.sender("recognize " + emotion));
        //get back echo
        System.out.println("listen" + client.listener());

        //prompt
        String promptTime1 = getTime();
        zenoSpeak("Can you please tell me what face emotion I will try to show you is?");
        SpeechJob sj = zenoSpeak("Here it is");
        waitZenoSpeak(sj);
        wait(3000);
        zenoAnimate(emotion);

        wait(2000);

        zenoAnimate(emotion); //zeno animates emotion
        writeInputEntered("Enter the child's response");

        String wrong_right1 = childInput(client);
        String waitTime1 = getTime();
        String waitTime2 = "", waitTime3 = "", promptTime2 = "", promptTime3 = "", wrong_right2 = "", wrong_right3 = "";

        // Prompt 1.
        if (wrong_right1.contains("wrong")) { //if the kid answers incorrectly
            promptTime2 = getTime();
            String[] randomPrompt = randomizer(prompts);
            String prompt = randomPrompt[1];
            writeResponse("Recognize Prompt 1");
            zenoSpeak(prompt);

            //Prompt 1 Give. 
            sj = zenoSpeak("What facial expression is this?");
            zenoAnimate(emotion);

            //restart the unity game
            System.out.println("Send: " + client.sender("go"));
            waitTime2 = getTime();
            wrong_right2 = childInput(client);

            if (wrong_right2.contains("wrong")) {
                //Prompt 2
                promptTime3 = getTime();
                writeResponse("Recognize Prompt 2");
                zenoSpeak("Not quite " + hintRecognizeFaceExpression(emotion)); //give hint based on 

                sj = zenoSpeak("One more time! What expression is this?");
                waitZenoSpeak(sj);
                zenoAnimate(emotion);
                System.out.println("Send: " + client.sender("go"));
                wrong_right3 = childInput(client);
                waitTime3 = getTime();

                writeInputEntered("");

                if (wrong_right3.contains("wrong")) {
                    //Prompt 3
                    String promptTime4 = getTime();
                    writeResponse("Recognize Prompt 3");
                    zenoSpeak("Not quite, it's a " + emotion + "face!");
                    zenoSpeak("That was a nice try!");
                    zenoSpeak("touch the " + emotion + " card on the screen.");
                    waitZenoSpeak(sj);

                    String wrong_right4 = childInput(client);
                    String waitTime4 = getTime();
                    //end
                    System.out.println("send4" + client.sender("main"));
                    //receive finished
                    System.out.println("liseten5" + client.listener());

                    sj = zenoSpeak("OK. Let's go on.");
                    waitZenoSpeak(sj);
                    zenoAnimate("happy");
                    //call unity main
                    System.out.println("SEND:" + client.sender("main"));
                    //get finished response
                    System.out.println("LISTEN:" + client.listener());
                    String endTime = getTime();
                    write("end to next: " + endTime);
                    excel.writeRecognition(false, emotion, wrong_right1.split(" ")[1], promptTime1, waitTime1, "", "");
                    excel.writeRecognition(false, emotion, wrong_right2.split(" ")[1], promptTime2, waitTime2, "", "");
                    excel.writeRecognition(false, emotion, wrong_right3.split(" ")[1], promptTime3, waitTime3, "", "");
                    excel.writeRecognition(true, emotion, emotion, promptTime4, waitTime4, "", "");
                    return;
                }
            }

        }
        String rewardTime = getTime();
        sj = zenoSpeak("Good Job!");
        waitZenoSpeak(sj);
        zenoDoReinf(Reinf);

        //end 
        System.out.println("send4" + client.sender("main"));
        wait(1500);
        //receive finished
        System.out.println("liseten5" + client.listener());
        wait(1500);
        String endTime = getTime();
        zenoSpeak("OK. Let's go on.");
        zenoAnimate("happy");
        wait(1000);
        if (!wrong_right1.equals("")) {
            boolean correct = !wrong_right1.contains("wrong");
            excel.writeRecognition(correct, emotion, wrong_right1.split(" ")[1], promptTime1, waitTime1, correct ? rewardTime : "", correct ? endTime : "");
        }
        if (!wrong_right2.equals("")) {
            boolean correct = !wrong_right2.contains("wrong");
            excel.writeRecognition(correct, emotion, wrong_right2.split(" ")[1], promptTime2, waitTime2, correct ? rewardTime : "", correct ? endTime : "");
        }
        if (!wrong_right3.equals("")) {
            boolean correct = !wrong_right3.contains("wrong");
            excel.writeRecognition(correct, emotion, wrong_right3.split(" ")[1], promptTime3, waitTime3, correct ? rewardTime : "", correct ? endTime : "");
        }
    }

    private static String childInput(ClientThread1 client) {
        wait(1000);
        System.out.println("send2 " + client.sender("go"));
        //get child response
        String response = client.listener();
        System.out.println("listen3" + response);
        return response;
    }

    public static String hintSelectExpression(String emotion, String person) {
        String hint;
        if (emotion.equals("happy")) {
            hint = "Look for the card with a " + person + "with the corner of their lips turned upward. They are smiling";
        } else if (emotion.equals("sad")) {
            hint = "What card has a" + person + "with of their lips turned down or looking like the " + person + " wants to cry?";
        } else if (emotion.equals("angry")) {
            hint = "Look for a" + person + " with their eyebrows pointed down.";
        } else if (emotion.equals("fear")) {
            hint = "Look for the " + person + " with their eyebrows pointed upwards and eyes wide open.";
        } else if (emotion.equals("surprised")) {
            hint = "What " + person + " has their eyes and mouth wide open?";
        } else {
            hint = "the hints are not working Michelle. Fix it.";
        }

        return hint;
    }

    public static void SelectExpression(String emotion, ClientThread1 client, String Reinf) {
        String[] prompts = {"not quite", "try again", "almost. try again"};

        write("Select " + emotion);
        zenoSpeak("Awesome. Press start when you are ready to begin.");
        writeResponse("Let child know they should press START when they are ready");
        String person = prepareSelectExpression(emotion, client);
        String promptTime = getTime();

        zenoSpeak("Which picture shows " + person + " with a " + emotion + " emotion?");
        SpeechJob sj = zenoSpeak("Touch the correct card on the touch pad.");
        waitZenoSpeak(sj);

        String wrong_right = childInput(client);
        String waitTime = getTime();
        String waitTime1 = "", waitTime2 = "", promptTime1 = "", promptTime2 = "", wrong_right1 = "", wrong_right2 = "";
        //Prompt 1
        //Prompt 2
        if (wrong_right.contains("wrong")) {
            promptTime1 = getTime();
            //read from the excel sheet? O_O
            zenoSpeak("Not quite, " + hintSelectExpression(emotion, person));
            writeResponse("SelectExpression Prompt 2");
            zenoSpeak("One more time! Select the expression from the cards");
            sj = zenoSpeak("Find the " + emotion + " card");
            waitZenoSpeak(sj);
            System.out.println(client.sender("go"));
            waitTime1 = getTime();
            wrong_right1 = childInput(client);

            //Prompt 3
            if (wrong_right1.contains("wrong")) {
                promptTime2 = getTime();
                writeResponse("SelectExpression Prompt 3");
                sj = zenoSpeak("Not quite! It's the green one, touch it!");
                waitZenoSpeak(sj);
                wait(1000);
                System.out.println(client.sender("go"));

                //Zeno do - move hand pointing toward the computer - zenoAnimate();
                //Touch pad disables all other
                //Wait for touch verification from interaction pad
                wrong_right2 = childInput(client);
                waitTime2 = getTime();
                zenoSpeak("That was a nice try!");
                //call unity main
                System.out.println("SEND:" + client.sender("main"));
                //get finished response
                System.out.println("LISTEN:" + client.listener());
                String endTime = getTime();
                write("end to next: " + endTime);
                excel.writeSelect(false, emotion, wrong_right.split(" ")[1], promptTime, waitTime, "", "");
                excel.writeSelect(false, emotion, wrong_right1.split(" ")[1], promptTime1, waitTime1, "", "");
                excel.writeSelect(true, emotion, wrong_right.split(" ")[1], promptTime2, waitTime2, "", "");
                return;
            }
        }
        String rewardTime = getTime();
        sj = zenoSpeak("Nice Job!");
        zenoDoReinf(Reinf);
        waitZenoSpeak(sj);
        //call unity main
        System.out.println("SEND:" + client.sender("main"));
        //get finished response
        System.out.println("LISTEN:" + client.listener());
        String endTime = getTime();
        if (wrong_right.contains("wrong")) {
            excel.writeSelect(false, emotion, wrong_right.split(" ")[1], promptTime, waitTime, "", "");
            excel.writeSelect(true, emotion, emotion, promptTime1, waitTime1, rewardTime, endTime);
        } else {
            excel.writeSelect(true, emotion, emotion, promptTime, waitTime, rewardTime, endTime);
        }
        write("end to next: " + endTime);
    }

    private static String prepareSelectExpression(String emotion, ClientThread1 client) {
        //generate the other two emotions that are not this one
        String[] array = {"happy", "sad", "angry", "surprised", "fear"};
        String[] randEmotions = randomizer(array);
        System.out.println("Made it here?1");
        String notEmotion1 = "";
        String notEmotion2 = "";
        for (int i = 0; i < 5; i++) {
            System.out.println("Made it here?2");
            if (!randEmotions[i].equals(emotion)) {
                System.out.println("i" + i);
                notEmotion1 = randEmotions[i];

            }

        }
        for (int i = 0; i < 5; i++) {
            System.out.println("Made it here?2");
            if (!randEmotions[i].equals(emotion) & !randEmotions[i].equals(notEmotion1)) {
                System.out.println("i" + i);
                notEmotion2 = randEmotions[i];

            }

        }
        System.out.println("Made it here?4");
        //pick one of 5 for each               
        int[] numPick = {1, 2, 3, 4};
        int[] emotionPick = new int[4];
//        for (int i = 0; i < 3; i++) {
        emotionPick = intRandomizer(numPick);
//            emotionPick[i] = numPick[i];
//        }

        //send to the unity machine 
        System.out.println("SEND1:" + client.sender("select " + emotion + " " + emotionPick[0] + " " + notEmotion1 + " " + emotionPick[1] + " " + notEmotion2 + " " + emotionPick[2]));
        wait(1000);
        //get echo
        System.out.println("Listen2:" + client.listener());
        writeResponse("Ask: Which picture shows " + emotion + " ?");

        String person = "";
        if (emotion.equals("fear") || emotion.equals("sad") || emotion.equals("happy")) {
            person = "a child";
        } else if (emotion.equals("angry") || emotion.equals("surprised")) {
            if (emotionPick[0] == 1 || emotionPick[0] == 4 || emotionPick[0] == 3) {
                person = "a child";
            } else {
                person = "an adult";
            }
        }
        return person;
    }

    public static void waitZenoSpeak(SpeechJob sj) {
        while (sj.getStatus() != DefaultSpeechJob.COMPLETE) {
            System.out.print("");
        }
    }

    public static void IdentifyEmotion(String emotion, ClientThread1 client, String reinf) {

        String[] prompts = {"Not quite!", "Almost!", "Not that one!"};
        writeResponse("Identify Emotion Initial Prompt");

        //send to unity machine
        write("Identify " + emotion);

        //Start Prompt
        String promptTime1 = getTime();
        write(promptTime1);

        writeResponse("Let child know they should press START when they are ready");
        SpeechJob sj = zenoSpeak("Press START when you are ready.");
        waitZenoSpeak(sj);
        String person = prepareIdentifyEmotion(emotion, client);

        sj = zenoSpeak("What emotion does the " + person + " in the picture show? Press the emotion you think");
        System.out.println("Job COmplete" + DefaultSpeechJob.COMPLETE);
        System.out.println("sJ" + sj.getStatus());
        // wait until its done talking
        waitZenoSpeak(sj);

        String wrong_right1 = childInput(client);
        String waitTime1 = getTime();
        String waitTime2 = "", waitTime3 = "", promptTime2 = "", promptTime3 = "", wrong_right2 = "", wrong_right3 = "";
        System.out.println("Job COmplete" + DefaultSpeechJob.COMPLETE);

        //Prompt 1
        if (wrong_right1.contains("wrong")) {
            promptTime2 = getTime();
            String[] randomPrompt = randomizer(prompts);
            String prompt = randomPrompt[1];
            zenoSpeak(prompt);
            writeResponse("Identify Emotion Prompt 1");
            sj = zenoSpeak("Let's try again! What emotion do you think the card shows?");
            waitZenoSpeak(sj);

            System.out.println(client.sender("go"));
            waitTime2 = getTime();
            wrong_right2 = childInput(client);

            //Prompt 2
            if (wrong_right2.contains("wrong")) {
                promptTime2 = getTime();
                //  random so that the correct answer is not always the first or last one
                writeResponse("Identify Emotion Prompt 2");
                prompt = randomPrompt[1];
                zenoSpeak(prompt);
                sj = zenoSpeak("One more time! What emotion is on the card?");
                waitZenoSpeak(sj);

                String[] array = {"happy", "sad", "angry", "surprised", "fear"};
                String[] randEmotions = randomizer(array);
                //  List<String> rand = Collections.shuffle(new ArrayList<String>(array));
                String notEmotion = "";
                //  for (int i = 0; i < 5; i++) {
                String[] words = wrong_right1.split(" ");
                String kidResponse1 = words[1];
                System.out.println(kidResponse1);


                words = wrong_right2.split(" ");
                String kidResponse2 = words[1];
                System.out.println(kidResponse2);
                ArrayList<String> left = new ArrayList<String>();
                for (String word : array) {
                    if (!word.equalsIgnoreCase(kidResponse1) && !word.equalsIgnoreCase(kidResponse2) && !word.equalsIgnoreCase(emotion)) {
                        left.add(word);
                    }
                }
                Collections.shuffle(left);
                notEmotion = left.get(0);

                if (Math.random() < 0.5) {
                    sj = zenoSpeak("Is this " + notEmotion + " or " + emotion);
                    waitZenoSpeak(sj);
                } else {
                    sj = zenoSpeak("Is this " + emotion + " or " + notEmotion);
                    waitZenoSpeak(sj);
                }
                wait(3000);
                wrong_right3 = childInput(client);
                waitTime3 = getTime();
                //Prompt 3
                if (wrong_right3.contains("wrong")) {
                    String promptTime4 = getTime();
                    sj = zenoSpeak("That was a nice try!");
                    waitZenoSpeak(sj);
                    sj = zenoSpeak("It's " + emotion + "!" + " Touch it and say " + emotion + "!");
                    waitZenoSpeak(sj);

                    childInput(client);
                    String waitTime4 = getTime();
                    //wait for signal back that the kid touched the button
                    sj = zenoSpeak("Lets go on");
                    waitZenoSpeak(sj);
                    //call unity main
                    System.out.println("SEND:" + client.sender("main"));
                    //get finished response
                    System.out.println("LISTEN:" + client.listener());
                    String endTime = getTime();
                    write("end to next: " + endTime);
                    excel.writeIdentify(false, emotion, wrong_right1.split(" ")[1], promptTime1, waitTime1, "", "");
                    excel.writeIdentify(false, emotion, wrong_right2.split(" ")[1], promptTime2, waitTime2, "", "");
                    excel.writeIdentify(false, emotion, wrong_right3.split(" ")[1], promptTime3, waitTime3, "", "");
                    excel.writeIdentify(true, emotion, emotion, promptTime4, waitTime4, "", "");
                    return;
                }
            }
        }
        String rewardTime = getTime();
        sj = zenoSpeak("Good Job! You are doing great!");
        waitZenoSpeak(sj);
        zenoDoReinf(reinf);
        //call unity main
        System.out.println("SEND:" + client.sender("main"));
        //get finished response
        System.out.println("LISTEN:" + client.listener());
        String endTime = getTime();
        write("end to next: " + endTime);
        if (!wrong_right1.equals("")) {
            boolean correct = !wrong_right1.contains("wrong");
            excel.writeIdentify(correct, emotion, wrong_right1.split(" ")[1], promptTime1, waitTime1, correct ? rewardTime : "", correct ? endTime : "");
        }
        if (!wrong_right2.equals("")) {
            boolean correct = !wrong_right2.contains("wrong");
            excel.writeIdentify(correct, emotion, wrong_right2.split(" ")[1], promptTime2, waitTime2, correct ? rewardTime : "", correct ? endTime : "");
        }
        if (!wrong_right3.equals("")) {
            boolean correct = !wrong_right3.contains("wrong");
            excel.writeIdentify(correct, emotion, wrong_right3.split(" ")[1], promptTime3, waitTime3, correct ? rewardTime : "", correct ? endTime : "");
        }
    }

    private static String prepareIdentifyEmotion(String emotion, ClientThread1 client) {
        int[] numPick = {1, 2, 3, 4};
        numPick = intRandomizer(numPick);

        System.out.println("Send:" + client.sender("identify " + emotion + " " + numPick[1]));

        String person = "";

        if (emotion.equals("fear") || emotion.equals("sad") || emotion.equals("happy")) {
            person = "child";
        } else if (emotion.equals("angry")) {
            if (numPick[1] == 1 || numPick[1] == 4 || numPick[1] == 3) {
                person = "child";
            } else {
                person = "adult";
            }
        } else if (emotion.equals("surprised")) {
            if (numPick[1] == 3) {
                person = "adult";
            } else {
                person = "child";
            }
        }

        //get echo

        System.out.println("LIsten:" + client.listener());
        String promptTime = getTime();

        return person;

    }

    ////////////////////////////ROBOT BASELINE
    public static void CopyFace2RB(String emotion, ClientThread1 client) {
        zenoSpeak("Press START when you are ready " + partName);
        //send to unity machine to activate activity
        System.out.println("Send:" + client.sender("copy"));
        write("CopyFace");
        //get back echo
        String startTime = getTime();
        write(startTime);
        wait(100);
        System.out.println("Listen2" + client.listener());
        write("    emotion: " + emotion);

        zenoSpeak("Copy My Face with your face!");
        zenoSpeak("Press the RECORD FACE button when you have it on your face!");
        wait(3000);
        zenoSpeak("Here it is");
        //zenoAnimate("nod3");
        wait(4000);
        wait(2000);
        writeResponse("Show " + emotion + " face and have kid COPY");
        zenoAnimate(emotion);
        wait(2000);
        //wait until face is shown
        writeInputEntered("Press enter to let kid select the emotion on the touchpad.");
        //getInputEntered();

        //send 'go' to allow touch
        System.out.println("send3" + client.sender("go"));
        write("allowed to select: " + getTime());
        writeInputEntered("wait for child to say they are ready by pressing the button.");
        writeInputEntered("Input the childs correct facial expression features");
        //record the answer
        System.out.println("lsiten4:" + client.listener());
        //writeToExcel(String filePath, int SheetNumber, int row, int col, String Stuff2Write)
        write("entered face at" + getTime());
        writeHumanSay("Press enter to record data go on to next activity");
        getInputEntered();
        //go to unity main
        System.out.println("send5" + client.sender("main"));
        zenoSpeak("lets go to the next activity!");
        zenoAnimate("happy");
        //recieve 'finished'    
        String endTime = getTime();
        excel.writeCopy("", emotion, startTime, endTime);
        System.out.println("listen6" + client.listener());

        wait(1500);
    }

    public static void RecognizeFaceExpressionRB(String emotion, ClientThread1 client) {
        // send recognize emotion to unity machine
        zenoSpeak("Remember to press start when you are ready to play the next game!");
        String startTime = getTime();
        write("Recognize:" + emotion);
        System.out.println("Send:" + client.sender("recognize " + emotion));
        //get back echo
        System.out.println("lsiten" + client.listener());

        writeResponse("Show " + emotion + " face " + "and have child RECOGNIZE the emotion");
        zenoSpeak("Can you please tell me what face emotion I will try to show you is?");
        zenoSpeak("Here it is");
        wait(5000);
        wait(1000);
        zenoAnimate(emotion);
        wait(2000);
        writeInputEntered("Press ENTER to let the child select the emotion on the touchpad");
        // getInputEntered();
        //send 'go'
        String promptTime = getTime();
        write("allowed to select at" + promptTime);
        wait(1000);
        System.out.println("send2 " + client.sender("go"));
        //get child response
        String response = client.listener();
        String responseTime = getTime();
        write("response at" + responseTime);
        write("    response: " + response);
        //record child response
        System.out.println("listen3" + response);
        //Java2ExelFile.writeToExcel(filePath,0,NumTimesRecogAcivity2+2, 2, kidsResponse); 

        writeHumanSay(" go on to next activity");

        String endTime = getTime();
        write("next activity at :" + endTime);
        excel.writeRecognition(isResponseRight(response), emotion, response, promptTime, responseTime, startTime, "");
        // getInputEntered();
        //send to unity main
        System.out.println("send4" + client.sender("main"));
        wait(1500);
        //receive finished
        System.out.println("liseten5" + client.listener());
        wait(1500);

        zenoSpeak("OK. Let's go on.");
        zenoAnimate("happy");
        wait(1000);

        //Java2ExelFile.writeToExcel(filePath,0,NumTimesRecogAcivity2+1, 2, "1");

    }



    public static void SelectExpressionRB(String emotion, ClientThread1 client) {

        write("Select " + emotion);
        String startTime = getTime();
        write("start at" + startTime);
        zenoSpeak("Awesome. Press start when you are ready to begin.");
        writeResponse("Let child know they should press START when they are ready");
        //generate the other two emotions that are not this one
        String[] array = {"happy", "sad", "angry", "surprised", "fear"};
        String[] randEmotions = randomizer(array);
        System.out.println("Made it here?1");
        String notEmotion1 = "";
        String notEmotion2 = "";
        for (int i = 0; i < 5; i++) {
            System.out.println("Made it here?2");
            if (!randEmotions[i].equals(emotion)) {
                System.out.println("i" + i);
                notEmotion1 = randEmotions[i];

            }

        }
        for (int i = 0; i < 5; i++) {
            System.out.println("Made it here?2");
            if (!randEmotions[i].equals(emotion) & !randEmotions[i].equals(notEmotion1)) {
                System.out.println("i" + i);
                notEmotion2 = randEmotions[i];

            }

        }
        System.out.println("Made it here?4");
        //pick one of 5 for each               
        int[] numPick = {1, 2, 3, 4};
        int[] emotionPick = new int[3];
        for (int i = 0; i < 3; i++) {
            numPick = intRandomizer(numPick);
            emotionPick[i] = numPick[i];
        }

        //send to the unity machine 
        System.out.println("SEND1:" + client.sender("select " + emotion + " " + emotionPick[0] + " " + notEmotion1 + " " + emotionPick[1] + " " + notEmotion2 + " " + emotionPick[2]));
        wait(1000);
        //get echo
        System.out.println("Listen2:" + client.listener());
        writeResponse("Ask: Which picture shows " + emotion + " ?");

        String person = "";
        if (emotion.equals("fear") || emotion.equals("sad") || emotion.equals("happy")) {
            person = "a child";
        } else if (emotion.equals("angry") || emotion.equals("surprised")) {
            if (numPick[1] == 1 || numPick[1] == 4 || numPick[1] == 3) {
                person = "a child";
            } else {
                person = "an adult";
            }
        }

        zenoSpeak("Which picture shows " + person + " with a " + emotion + " emotion?");
        wait(2000);
        writeHumanSay("Say: Touch the correct card on the touch pad.");
        zenoSpeak("Touch the correct card on the touch pad.");
        wait(2000);
        writeInputEntered("Press enter to let the child select the picture on the Touchpad.");
        //getInputEntered();
        String promptTime = getTime();
        write("let select" + promptTime);
        //send "go"   
        System.out.println("SEND3" + client.sender("go"));

        writeInputEntered("allow child to enter their choice");
        //wait for the child response
        wait(1000);
        String wrong_right = client.listener();
        write("response" + wrong_right);
        String respTime = getTime();
        write(respTime);
        System.out.println("LISTEN4" + wrong_right);


        // writeResponse("Press enter to go on to next activity");
        //  getInputEntered();
        //go to unity main    
        System.out.println("SEND5" + client.sender("main"));
        String endTime = getTime();
        write(endTime);

        zenoSpeak("Yeay. Lets go on.");
        zenoAnimate("happy");

        excel.writeSelect(isResponseRight(wrong_right), emotion, wrong_right, promptTime, respTime, startTime, "");
        System.out.println("Listen6" + client.listener());
        wait(1000);
        // String response = client.listener();
        // System.out.println("LISTEN5"+response);

    }

    public static void IdentifyEmotionRB(String emotion, ClientThread1 client) {

        //send to unity machine
        write("Identify " + emotion);
        String startTime = getTime();
        write(startTime);
        writeResponse("Let child know they should press START when they are ready");
        zenoSpeak("Press START when you are ready.");
        int[] numPick = {1, 2, 3, 4};
        numPick = intRandomizer(numPick);

        System.out.println("Send:" + client.sender("identify " + emotion + " " + numPick[1]));

        String person = "";

        if (emotion.equals("fear") || emotion.equals("sad") || emotion.equals("happy")) {
            person = "child";
        } else if (emotion.equals("angry")) {
            if (numPick[1] == 1 || numPick[1] == 4 || numPick[1] == 3) {
                person = "child";
            } else {
                person = "adult";
            }
        } else if (emotion.equals("surprised")) {
            if (numPick[1] == 1) {
                person = "adult";
            } else {
                person = "child";
            }
        }


        //get echo

        System.out.println("LIsten:" + client.listener());
        String promptTime = getTime();

        writeResponse("Ask child to IDENTIFY which emotion is shown on the picture");
        writeHumanSay("Say: What emotion does the picture show? Select an emotion you think the" + "is feeling");
        zenoSpeak("What emotion does the " + person + " in the picture show? Press the emotion you think");

        writeInputEntered("Press ENTER to allow child to select an emotion");
        // getInputEntered();
        //send go
        wait(2000);
        wait(1000);
        System.out.println("SEND:" + client.sender("go"));
        //listen to begin

        //System.out.println("LISTEN:"+client.listener());
        //listen to get child response
        String response = client.listener();
        write("response " + response);
        String respTime = getTime();
        write("Response at " + respTime);
        System.out.println("LISTEN:" + response);

        writeInputEntered("");
        // writeInputEntered("Press ENTER to go on to next activity.");
        // getInputEntered();
        //call unity main
        System.out.println("SEND:" + client.sender("main"));
        //get finished response
        System.out.println("LISTEN:" + client.listener());
        String endTime = getTime();
        write("end to next: " + endTime);
        zenoSpeak("Great. Let's move on " + partName);
        excel.writeIdentify(isResponseRight(response), emotion, response, promptTime, respTime, startTime, "");
        wait(2000);
        //System.out.println("LISTEN:"+client.listener());
    }

    ////////////////////////////HUMAN BASELINE
    public static void CopyFace2HB(String emotion, ClientThread1 client) {

        //send to unity machine to activate activity
        System.out.println("Send:" + client.sender("copy"));
        write("CopyFace");
        //get back echo
        String startTime = getTime();
        write(startTime);
        wait(100);
        System.out.println("Listen2" + client.listener());
        write("    emotion: " + emotion);
        writeResponse("Show " + emotion + " face and have kid COPY");
        writeHumanSay("Copy My Face with your face!");
        writeInputEntered("Press enter to let kid select the emotion on the touchpad.");
        getInputEntered();

        //send 'go' to allow touch
        System.out.println("send3" + client.sender("go"));
        write("allowed to select: " + getTime());
        writeInputEntered("wait for child to say they are ready by pressing the button.");
        writeInputEntered("Input the childs correct facial expression features");
        //record the answer
        System.out.println("lsiten4:" + client.listener());
        //writeToExcel(String filePath, int SheetNumber, int row, int col, String Stuff2Write)
        write("entered face at" + getTime());
        writeHumanSay("Press enter to record data go on to next activity");
        getInputEntered();
        //go to unity main
        System.out.println("send5" + client.sender("main"));
        //recieve 'finished'    
        String endTime = getTime();
        excel.writeCopy("", emotion, startTime, endTime);
        System.out.println("listen6" + client.listener());
        write("to next activity");
        wait(1500);
    }

    public static void RecognizeFaceExpressionHB(String emotion, ClientThread1 client) {
        // send recognize emotion to unity machine
        String startTime = getTime();
        write("Recognize:" + emotion);
        System.out.println("Send:" + client.sender("recognize " + emotion));
        //get back echo
        System.out.println("lsiten" + client.listener());

        writeResponse("Show " + emotion + " face " + "and have child RECOGNIZE the emotion");
        writeHumanSay("Can you please tell me what face emotion I will try to show you is?");
        writeInputEntered("Press ENTER to let the child select the emotion on the touchpad");
        getInputEntered();
        //send 'go'
        String promptTime = getTime();
        write("allowed to select at" + promptTime);
        wait(1000);
        System.out.println("send2 " + client.sender("go"));
        //get child response
        String response = client.listener();
        String responseTime = getTime();
        write("response at" + responseTime);
        write("    response: " + response);
        //record child response
        System.out.println("listen3" + response);
        //Java2ExelFile.writeToExcel(filePath,0,NumTimesRecogAcivity2+2, 2, kidsResponse); 

        writeHumanSay("Press enter to go on to next activity");
        String endTime = getTime();
        write("next activity at :" + endTime);
        excel.writeRecognition(isResponseRight(response), emotion, response, promptTime, responseTime, startTime, "");
        // getInputEntered();
        //send to unity main
        System.out.println("send4" + client.sender("main"));
        wait(1500);
        //receive finished
        System.out.println("liseten5" + client.listener());
        wait(1500);
        //Java2ExelFile.writeToExcel(filePath,0,NumTimesRecogAcivity2+1, 2, "1");

    }

    private static boolean isResponseRight(String response) {
        if (response.contains("right")) {
            return true;
        } else return !response.contains("wrong");
    }

    public static void SelectExpressionHB(String emotion, ClientThread1 client) {

        write("Select " + emotion);
        String startTime = getTime();
        write("start at" + startTime);
        writeResponse("Let child know they should press START whent they are ready");
        //generate the other two emotions that are not this one
        String[] array = {"happy", "sad", "angry", "surprised", "fear"};
        String[] randEmotions = randomizer(array);


        System.out.println("Made it here?1");
        String notEmotion1 = "";
        String notEmotion2 = "";
        for (int i = 0; i < 5; i++) {
            System.out.println("Made it here?2");
            if (!randEmotions[i].equals(emotion)) {
                System.out.println("i" + i);
                notEmotion1 = randEmotions[i];

            }

        }
        for (int i = 0; i < 5; i++) {
            System.out.println("Made it here?2");
            if (!randEmotions[i].equals(emotion) & !randEmotions[i].equals(notEmotion1)) {
                System.out.println("i" + i);
                notEmotion2 = randEmotions[i];

            }

        }
        System.out.println("Made it here?4");


        //pick one of 5 for each
        int[] numPick = {1, 2, 3, 4};
        int[] emotionPick = new int[3];
        for (int i = 0; i < 3; i++) {
            numPick = intRandomizer(numPick);
            emotionPick[i] = numPick[i];
        }

        //send to the unity machine 
        System.out.println("SEND1:" + client.sender("select " + emotion + " " + emotionPick[0] + " " + notEmotion1 + " " + emotionPick[1] + " " + notEmotion2 + " " + emotionPick[2]));

        //get echo
        System.out.println("Listen2:" + client.listener());
        writeResponse("Ask: Which picture shows " + emotion + " ?");
        writeHumanSay("Say: Touch the correct card on the touch pad.");

        writeInputEntered("Press enter to let the child select the picture on the Touchpad.");
        getInputEntered();
        String promptTime = getTime();
        write("let select" + promptTime);
        //send "go"   
        System.out.println("SEND3" + client.sender("go"));

        writeInputEntered("allow child to enter their choice");
        //wait for the child response
        wait(1000);
        String wrong_right = client.listener();
        write("response" + wrong_right);
        String respTime = getTime();
        write(respTime);
        System.out.println("LISTEN4" + wrong_right);


        // writeResponse("Press enter to go on to next activity");
        //  getInputEntered();
        //go to unity main    
        System.out.println("SEND5" + client.sender("main"));
        String endTime = getTime();
        write(endTime);
        excel.writeSelect(isResponseRight(wrong_right), emotion, wrong_right, promptTime, respTime, startTime, "");
        System.out.println("Listen6" + client.listener());
        wait(1000);
        // String response = client.listener();
        // System.out.println("LISTEN5"+response);

    }

    public static void IdentifyEmotionHB(String emotion, ClientThread1 client) {

        //send to unity machine
        write("Identify " + emotion);
        String startTime = getTime();
        write(startTime);
        writeResponse("Let child know they should press START whent they are ready");
        int[] numPick = {1, 2, 3, 4};
        numPick = intRandomizer(numPick);

        System.out.println("Send:" + client.sender("identify " + emotion + " " + numPick[1]));
        //get echo
        System.out.println("LIsten:" + client.listener());
        String promptTime = getTime();

        writeResponse("Ask child to IDENTIFY which emotion is shown on the picture");
        writeHumanSay("Say: What emotion does the picture show? Select an emotion you think");
        writeInputEntered("Press ENTER to allow child to select an emotion");
        getInputEntered();
        //send go
        wait(2000);
        System.out.println("SEND:" + client.sender("go"));
        //listen to begin

        //System.out.println("LISTEN:"+client.listener());
        //listen to get child response
        String response = client.listener();
        write("response " + response);
        String respTime = getTime();
        write("Response at " + respTime);
        System.out.println("LISTEN:" + response);

        writeInputEntered("");
        // writeInputEntered("Press ENTER to go on to next activity.");
        // getInputEntered();
        //call unity main
        System.out.println("SEND:" + client.sender("main"));
        //get finished response
        System.out.println("LISTEN:" + client.listener());
        String endTime = getTime();
        write("end to next: " + endTime);
        excel.writeIdentify(isResponseRight(response), emotion, response, promptTime, respTime, startTime, "");
        wait(1000);
        //System.out.println("LISTEN:"+client.listener());
    }

    /////////////////////////////Rewards Activity///////////////////////////////////////
    public static int playRewardGame(String activity, String Say, int wait, ClientThread1 client) {
        // zenoAnimate("happyNeut");
        write(activity + " started at: " + getTime());
        client.sender("play");
        String response1 = client.listener();
        System.out.println("ListenStart:" + response1);
        client.sender("main");
        String response2 = client.listener();
        System.out.println("ListenStart:" + response2);
        //writeInputEntered("Press enter to begin next activity");
        //getInputEntered();
        zenoSpeak("press play if you would like me to do this");
        wait(1000);
        zenoSpeak(Say);
        zenoAnimate(activity);
        zenoSleep(2000);
        zenoAnimate("happy");
        //wait(2000);
        zenoSpeak("Or press no play if you would like me to do nothing.");
        zenoSleep(2000);
        wait(2000);
        client.sender("play");
        write("activity allwoed:" + getTime());
        int playCount = 0;
        int Count = 0;
        String response = client.listener();
        System.out.println("ListenStart:" + response);
        wait(2000);
        client.sender("go");
        while (!(response).equals("finished")) {
            wait(1000);
            response = client.listener();
            System.out.println("Listen" + Count + " " + response);
            if (response.equals("playpressed")/*& ((Count%2)==0)*/) {
                write("activity played:" + activity + getTime() + "\n");
                write("play pressed");

                System.out.println("play pressed");
                //wait(2000);
                zenoSpeak(Say);
                zenoAnimate(activity);
                zenoSleep(wait);
                playCount++;
                Count++;

                wait(1000 + wait);
                client.sender("go");
                response = "";
            } else if (response.equals("noplaypressed")/*& ((Count%2)==0)*/) {
                System.out.println("noplay pressed");

                // zenoSpeak(Say);
                // zenoAnimate(activity);
                zenoSleep(wait);
                // playCount++;
                Count++;
                wait(1000 + wait);
                // wait(1000+50);
                client.sender("go");
                response = "";
            } else {
                Count++;
                if (response.contains("noplay")) {
                    write("    " + response);
                    System.out.println("WROTE IN THE FILE: " + response);
                }

            }

        }

        response = client.listener();
        System.out.println("ListenEnd:" + response);
        write("activity" + activity + "said " + Say);
        write("play pressed" + playCount + "\n------END Activity------    \n\n\n ");

        //  response = client.listener();
        //  System.out.println("ListenEnd2:" +response);
        wait(2000);
        //zenoAnimate("happyNeut");
        System.out.println("playCount" + playCount);

        return playCount;

    }

    //////////////////////////OLD Version of Protocol Below///////////////////////////////////
    public static void BaselineS1() {

        zenoSpeak("Hey lets play another game now?");
        zenoAnimate("nod3");

        // writeResponse("End Configuration");
        writeResponse("Enter the participant's Name \n");
        partName = getInputEntered();
        writeInputEntered("");


        writeResponse("Type to speak, * to animate, or # to go on. \n");

        //Create the Document with participants ID and todays time and date as the title
        docTitle = partID + getDate();
        createTexDoc(docTitle);

        write("Participant's ID:" + partID + "\n");
        write("Time started:" + getTime() + "\n\n");
        //go to short conversation
        conversation();

        //Done generating and writing subsessions! 
        //Now run them
        if (sessionNumber == 1) {

            zenoSpeak("Do you remember how to play the game Sophie told you about?");
            writeResponse("enter 'yes' or 'no'");
            boolean answerGot = false;
            while (!answerGot) {
                if (DanceDanceSwingInterface.getIfEnterPressed()) {
                    String doTalk = "";
                    doTalk = getInputEntered();
                    if (doTalk.equals("no")) {
                        answerGot = true;
                        zenoAnimate("nod2");
                        zenoSpeak("Okay. Let me explain it to you.");
                        zenoAnimate("nodRight");
                        zenoSpeak("I'm going to try to make different emotion expreessions with my face and body. When I make one,try to guess what emotion it is.");
                        zenoSpeak("Use the choices written on the cards");
                        zenoAnimate("nod3");
                        zenoSpeak("I might repeat the expressions more than once with my face or body so it is okay if you have to say the same emotion choice more than once.");
                        zenoAnimate("nod1");
                        zenoSpeak("And also remember to tell me your guess.");
                        zenoAnimate("happyNeut");
                        zenoSpeak("Oh. And if you need to see an expression again, you can ask me to show it to you again.");
                        zenoSpeak("Let's start!");
                        zenoSleep(50);
                        zenoAnimate("nod2");
                    } else {
                        answerGot = true;
                        zenoSpeak("Awesome!");
                        //zenoAnimate("nod2");
                        zenoSpeak("Just remember to use the choices written on the cards");
                        zenoAnimate("nod3");
                        zenoSpeak("and I might repeat the expressions more than once with my face or body so it is okay if you have to say the same emotion choice more than once.");

                        zenoSpeak("And also remember to tell me your guess.");
                        zenoSleep(20);
                        zenoAnimate("nodLeft");
                        zenoSpeak("Oh. And if you need to see an expression again, you can ask me to show it to you again.");
                        zenoSleep(20);
                        zenoAnimate("nodLeft");
                        zenoAnimate("happyNeut");
                        zenoSpeak("Let's start!");
                        zenoSleep(50);
                        zenoAnimate("nod2");
                    }

                }
            }

            System.out.println("HEERREE");
            //Generate subsession sequence for Zeno Expression
            String[] randEmotions = randomizer(emotions);
            System.out.println("HEERREE2" + randEmotions.length);

            String[] OneSequence = new String[13];
            for (int i = 0; i < 13; i++) {
                OneSequence[i] = randEmotions[i];
            }

            String[] TwoSequence = new String[13];
            for (int i = 13; i < 26; i++) //now that i look at it i'm not sure why i even offset this....??
            {
                TwoSequence[i - 13] = randEmotions[i];
            }
            String[] ThreeSequence = new String[13];
            for (int i = 26; i < 39; i++) {
                ThreeSequence[i - 26] = randEmotions[i];
            }

            write("     Subsession One: " + Arrays.toString(OneSequence) + "\n \n");
            //writeResponse("Subsession One: "+Arrays.toString(OneSequence)+"\n ");
            System.out.println("Subsession One: " + Arrays.toString(OneSequence) + "\n \n");

            write("     Subsession Two: " + Arrays.toString(TwoSequence) + "\n \n");
            //writeResponse("Subsession Two: "+Arrays.toString(TwoSequence)+"\n ");
            System.out.println("Subsession Two: " + Arrays.toString(TwoSequence) + "\n \n");
            //Generate subsession sequence for Zeno Pose+Expression

            write("     Subsession Three: " + Arrays.toString(ThreeSequence) + "\n \n");
            //writeResponse("Subsession Three: "+Arrays.toString(ThreeSequence)+"\n ");
            System.out.println("Subsession Three: " + Arrays.toString(ThreeSequence) + "\n \n");


            write("Session ONE--------------");
            runSubSession(OneSequence, partName);
            //Dance?
            zenoSpeak("Now that we are finished would you like to dance, take a break, or continue playing?");
            zenoAnimate("dance1");
            runDanceSession();
            //Break
            writeResponse("Press ENTER to End Break");
            String pause = getInputEntered();
            //Greating to end Break
            zenoSpeak("Yay! Are you ready to continue?");
            writeResponse("Press ENTER to nod. Type 'yes' or 'no'.");
            pause = getInputEntered();
            zenoAnimate("nod2");
            if (pause.equals("yes")) {

            } else {
                zenoSpeak("Okay. Tell me when you are ready.");
                writeResponse("Press ENTER to End Break");
                pause = getInputEntered();
            }

            zenoSpeak("Alright");
            zenoAnimate("nod2");
            zenoSpeak("Let's play that again then.");
            zenoAnimate("nod3");
            zenoSpeak("Remember to use the options on the cards.");
            zenoAnimate("nodRight");
            zenoSpeak("and also that you can ask me to see the emotion again if you dont see it the first time.");

            write("Session TWO--------------");
            runSubSession(TwoSequence, partName);

            //Dance?
            zenoSpeak("Now that we are finished " + partName + " would you like to dance with me, take a short break, or just continue?");
            zenoAnimate("dance1");
            runDanceSession();
            zenoSpeak("We only have one more game to do and we will be done!");
            zenoAnimate("dance1");
            //Break
            writeResponse("Press ENTER to End Break");
            pause = getInputEntered();
            //Greating to end Break
            zenoSpeak("Yay! Are you ready to continue?");
            writeResponse("Press ENTER to nod. Type 'yes' or 'no'.");
            pause = getInputEntered();
            zenoAnimate("nod2");
            if (pause.equals("yes")) {

            } else {
                zenoSpeak("Alright " + partName + ". Tell me when you are ready.");
                writeResponse("Press ENTER to End Break");
                pause = getInputEntered();
            }

            zenoSpeak("Alright");
            zenoAnimate("nod2");
            zenoSpeak("Let's play the game one last time");
            zenoAnimate("nod3");
            zenoSpeak("I think we are doing pretty good!");
            zenoAnimate("dance1");

            write("Session THREE--------------");
            runSubSession(ThreeSequence, partName);


            zenoSpeak("Well " + partName);
            zenoSleep(20);
            zenoAnimate("nodDown");
            zenoAnimate("happyNeut");
            zenoSpeak("I think we are finished for today!");
            zenoAnimate("dance1");
            zenoAnimate("nodDown");
            zenoSpeak("I hope you liked to play with me");
            zenoAnimate("happyNeut");
            zenoSpeak("It was really nice to meet you " + partName);
            zenoAnimate("waveBye");
            zenoSpeak("Bye!");
            zenoAnimate("happyNeut");

        }
    }

    public static void Break() {
        zenoSpeak("Yay!");
//testnorobot Robokind.sleep(1000);
        zenoSpeak("we are finished with this game!");
        zenoSpeak("Let's take a break for 5 minutes and then we can play the next game!");

        writeResponse("Type 'yes' to end the break or use '&' to talk to kid");
        String response = getInputEntered();


        while (!response.equals("yes")) {

            // response= getInputEntered();            

            if (response.equals("end")) {
                writeResponse("END NOW");
                write("\n Program ended prematurely here \n");

                zenoSpeak("Okay");
                zenoAnimate("nod2");
                zenoSpeak("Lets end this early.");
                excel.closeFile();
                closeTextDoc();
                zenoDisconnect();
                System.exit(0);
            } else if (response.indexOf("*") != -1) {
                response = response.replace("*", "");
                response = response.replace(" ", "");
                zenoAnimate(response);
                //writeResponse("Animate");
            } else if (response.indexOf("&") != -1) {
                response = response.replace("&", "");
                response = response.replace(" ", "");
                zenoSpeak(response);
                writeResponse("Talking");
                write(response);
            } else if (response.equals("yes")) {
                // skip doing anything here since the user wants to end program.
                // no talking. no animating. 
                //   closeTextDoc(docTitle);
                //   zenoDisconnect();
                //   System.exit(0); 
            }

            response = getInputEntered();
        }
    }

    public static void RobotGameS4() {


        String filepath = "C:/Users/Michelle/Desktop/TestExcelSheet.xlsx";

        /*
         // writeResponse("Enter the participant's Name \n");  
         //                partName = getInputEntered();
         //                writeInputEntered("");
                
         zenoSpeak("Hi "+ partName+ " I'm so glad you are back!");
         zenoAnimate("nod2");//check
              
                 
         /*zenoSpeak("I am so happy to see you again. I really like playing with you!");
         zenoAnimate("nod3");//check
 
         zenoSpeak("How are you?");
         zenoAnimate("nod3");//check
         */
        /*
         writeResponse("Type to speak, * to animate, or # to go on. \n"); 
        
         //Create the Document with participants ID and todays time and date as the title
         docTitle = partID+getDate();
         createTexDoc(docTitle);
         write("Participant's ID:"+partID+"\n");
         write("Time started:"+getTime()+"\n\n");
         //go to short conversation
         conversation();
                
         zenoSpeak("All right! Let's get started");
  
         //storyTell(4);
         // Break();
         // copyFace();
         // Break();
         // storyListen(4); 
         // Break();
         //robotGaze();
         //  BaselineS1();    */
    }

    public static void freeStyle() {
        String doThis = "";
        while (doThis.indexOf("#") == -1) {
            if (DanceDanceSwingInterface.getIfEnterPressed()) {
                doThis = getInputEntered();
                if (doThis.indexOf("*") != -1) {
                    writeResponse("Animate");
                    doThis = doThis.replace("*", "");
                    doThis = doThis.replace(" ", "");
                    zenoAnimate(doThis);
                } else if (doThis.indexOf("#") != -1) {
                    // skip doing anything here since the user wants to end program.
                    // no talking. no animating. 
                } else {
                    zenoSpeak(doThis);
                    writeResponse("Talking");
                }
            }
        }
    }

    public static void conversation() {
//zenoSpeak("That's a nice name "+partName);
//zenoAnimate("nod2");
//zenoAnimate("nodRight");

//zenoSpeak("How was your day today "+partName+" ?");
        String doTalk = "";

        writeResponse("Talk with the Kid a little with the robot then type # to continue");
        while (doTalk.indexOf("#") == -1) {
            if (DanceDanceSwingInterface.getIfEnterPressed()) {
                doTalk = getInputEntered();

                if (doTalk.indexOf("*") != -1) {
                    writeResponse("Animate");
                    doTalk = doTalk.replace("*", "");
                    doTalk = doTalk.replace(" ", "");
                    zenoAnimate(doTalk);
                } else if (doTalk.indexOf("#") != -1) {
                } else {
                    zenoSpeak(doTalk);
                    writeResponse("Talking");
                }
            }
        }

    }

    public static void zenoDisconnect() {
        Robokind.disconnect();
    }

    public static void zenoSleep(int time) {
        Robokind.sleep(time);
    }

    public static void setInputEntered(String Input) {
        enteredInput = Input;
        writeResponse("you typed: " + enteredInput);
    }

    public static void setEnterPressed() {
        enterPressed = true;
    }

    private static String getInputEntered() {
        System.out.print("IN HERE!");
        return DanceDanceSwingInterface.getInputEntered();
    }

    private static void writeHumanSay(String writeThis) {
        DanceDanceSwingInterface.writeHumanSay(writeThis);
    }

    private static void writeInputEntered(String writeThis) {
        DanceDanceSwingInterface.writeInputEntered(writeThis);
    }

    public static void zenoAnimate(String Animation) {
        writeResponse("Start the Animation");
        //Create animPlayer and load animation

        String animationPath = "C:\\Users\\RobotController\\Desktop\\ZenoBearCompareTrue\\" + Animation + ".xml";
        // String animationPath ="C:\\Users\\Michelle\\Desktop\\ZenoBearCompareTrue\\"+Animation+".xml";
        //String animationPath ="C:\\Users\\Kris.Timcampy2\\Desktop\\Autism Project Fall 2014\\ZenoBearCompare"+Animation+".xml";

        File fPath = new File(animationPath);
        if (fPath.exists()) {
            Animation introAnim = Robokind.loadAnimation(animationPath);

            //Create introJob, tell it to play introAnim with animPlayer, get the length of the animation and tell the thread to wait for that length of time plus 500 milliseconds (so the next command is issued AFTER the animation finishes playing.)
            //testnorobot
            AnimationJob introJob = myPlayer.playAnimation(introAnim);
            Robokind.sleep(50 + introJob.getAnimationLength());

            System.out.println("playing " + Animation);
            writeResponse("playing " + Animation);
        } else {
            writeResponse("Animation does not exist");
            System.out.println("can not play " + Animation);

        }

    }

    public static void zenoAnimateP(String Animation) {
        //writeResponse("Start the Animation");
        //Create animPlayer and load animation

        String animationPath = "C:/Users/RobotController/Desktop/ZenoBearCompare/" + Animation + ".xml";
        //String animationPath ="C:/Users/Michelle/Desktop/ZenoBearCompare"+Animation+".xml";
        //String animationPath ="C:\\Users\\Kris.Timcampy2\\Desktop\\Autism Project Fall 2014\\ZenoBearCompare"+Animation+".xml";
        File fPath = new File(animationPath);
        if (fPath.exists()) {
            Animation introAnim = Robokind.loadAnimation(animationPath);

            //Create introJob, tell it to play introAnim with animPlayer, get the length of the animation and tell the thread to wait for that length of time plus 500 milliseconds (so the next command is issued AFTER the animation finishes playing.)

            AnimationJob introJob = myPlayer.playAnimation(introAnim);
            Robokind.sleep(50);

            System.out.println("playingP " + Animation);
            writeResponse("playingP " + Animation);
        } else {
            writeResponse("Animation does not exist");
        }
    }

    public static SpeechJob zenoSpeak(String Speech) {
        //testnorobot
        System.out.println("saying: " + Speech);
        return mySpeaker.speak(Speech);
    }

    public static List<String> getHints(int ans, int resp) {
        String[] hintArray = {"Raise your inner eyebrows like this.", "Lower your inner eyebrows like this.", "Close your eyelids more, like I am doing.", "Move the corner of your lips up like me.", "Move the corner of your lips down like I am doing.", "Open your mouth just like me"};
        int hint = (ans & resp) ^ ans;
        ArrayList<String> hints = new ArrayList<String>();
        for (int i = 0; hint > 0; i++, hint >>= 1) {
            if ((hint & 0x1) == 0x1) {
                hints.add(hintArray[i]);
            }
        }
        return hints;
    }

    public static boolean checkCopyFace(int ans, int resp) {
        return ((ans & resp) ^ ans) == 0;
    }

    static String docTitle;
    //the library of what zeno "knows and chooses" from
    static String[] nodSeq = {"nod1", "nod2", "nod3"};
    static String[] askEmotionSeq = {"how about this one?", "this one?", "what expression is this one?", "here is another one.", "now this one.", "now what expression is this?"};
    static String[] askGazeSeq = {"how about this one?", "this one?", "Awesome. Just remember to look at both my head and my eyes when you guess.", "Okay. Look at my eyes and head for this one.", "What direction do you think I am looking now?", "Remember to look at my eyes and head for the next one.", "Cool. Look at my head and eyes on this one.", "Here is the next one"};
    static String[] affirmSeq = {"okay!", "cool!", "awesome", "super", "super epic", "Nice job!", "Yes"};
    static String[] repeatSeq = {"okay?", "here it is again", "Okay. Again", "Okay. Let me say it again for you.", "Yup. Here it is again."};
    static String dance = "true";
    private static String partName;
    private static String robotConName;
    private static String partID;
    private static int sessionNumber;
    private static Robot.JointId myNeckYaw;
    private static Robot.JointId myRightEye;
    private static Robot.JointId myLeftEye;
    private static boolean calibrateButtonPressed = false;
    private static double calibNeckYaw;
    //find the values for these angles
    private static double head82 = -0.075;
    private static double head90 = 0;
    private static double head98 = 0.075;
    private static double lEyeNeg25 = 0.29895833;
    private static double lEyeNeg5 = 0.42395833;
    private static double lEyePos5 = 0.67395833;
    private static double lEyePos25 = 0.77395833;
    private static double rEyeNeg25 = 0.24895833;
    private static double rEyeNeg5 = 0.39895833;
    private static double rEyePos5 = 0.57395833;
    private static double rEyePos25 = 0.79898533;
    private static String filePath;
    private static String[] reinforce;
}
