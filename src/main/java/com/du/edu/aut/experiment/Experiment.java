package com.du.edu.aut.experiment;

/**
 * An abstract example of an experiment class. The experiment will run the "runExperiment" method
 * when it is called.
 *
 * Created by David on 7/24/2015.
 */
abstract class Experiment {
    public static String name = ""; //The name that will be used to run the experiment

    public Experiment(String n){
        name = n;
    }

    abstract void runExperiment() throws ExperimentInterruptedException; //The method that will be called when the experiment will be run
    public String getName(){
        return name;
    }
}
