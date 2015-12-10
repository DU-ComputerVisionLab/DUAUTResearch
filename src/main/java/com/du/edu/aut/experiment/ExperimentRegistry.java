package com.du.edu.aut.experiment;

import com.du.edu.aut.log.Logger;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Registry class for experiments. When a new experiment class is added, you have to add it to the initializeRegistry method.
 *
 * Created by David on 7/24/2015.
 */
public class ExperimentRegistry {

    public static HashMap<String, Class<? extends Experiment>> experiments = new HashMap<String, Class<? extends Experiment>>();

    public static void initializeRegistry(){
        //Add all of the experiments to the registry here
        registerExperiment(ExampleExperiment.class);
        registerExperiment(ComputerEyegazeBaseline.class);
        registerExperiment(HumanBaselineInteraction.class);
        registerExperiment(RobotBaseline.class);
    }

    private static void registerExperiment(Class<? extends Experiment> clss){
        Logger.log("Attempting to add experiment: " + clss.getSimpleName(), "Experiment");
        try{
            Field class_name = null;
            for (Field f : clss.getFields()){
                if (f.getName().equals("name") &&
                        f.getDeclaringClass() != Experiment.class)
                    class_name = f;
            }

            if (class_name == null)
                throw new NoSuchFieldException();

            String str_value = (String) class_name.get(null);
            experiments.put(str_value, clss);
            Logger.log("Added experiment: " + clss.getSimpleName(), "Experiment");
        } catch (NoSuchFieldException e) {
           Logger.logError("No name field in the given class: " + clss.getSimpleName(), "Experiment");
           Logger.logError("Failed to add experiment: " + clss.getSimpleName(), "Experiment");
        } catch (IllegalAccessException e) {
            Logger.logError("Illegal access exception while registring: " + clss.getSimpleName(), "Experiment");
            Logger.logError("Failed to add experiment: " + clss.getSimpleName(), "Experiment");
        }

    }
    public static void runExperiment(Class<? extends  Experiment> clss){
        try{
            Experiment to_run = clss.newInstance();
            System.out.println(to_run.getName());
            Logger.log("Initialized experiment: " + to_run.getName(), "Experiment");
            to_run.runExperiment();
            Logger.log("Finished experiment: " + to_run.getName(), "Experiment");
        } catch (InstantiationException e) {
            Logger.logError("Failed to instantiate experiment: " + clss.getSimpleName(), "Experiment");
            Logger.logError("Failed to run experiment: " + clss.getSimpleName(), "Experiment");
        } catch (IllegalAccessException e) {
            Logger.logError("Illegal access error while running experiment: " + clss.getSimpleName(), "Experiment");
            Logger.logError("Failed to run experiment: " + clss.getSimpleName(), "Experiment");
        } catch (ExperimentInterruptedException e) {
            Logger.logError("Experiment failed to run successfully - Check experiment logs for details: " + clss.getSimpleName(),
                    "Experiment");
            Logger.logError(e.getMessage(), "Experiment");
        }
    }
    public static void runExperiment(String experiment_name){
        if (experiments.containsKey(experiment_name)){
            runExperiment(experiments.get(experiment_name));
        } else {
            Logger.logError("Could not run experiment: " + experiment_name + ", " + experiment_name +
                    " does not exist. Did you remember to register the experiment?", "Experiment" );
        }
    }

    public static ArrayList<String> getExperiments(){
        return new ArrayList<String>(experiments.keySet());
    }
}
