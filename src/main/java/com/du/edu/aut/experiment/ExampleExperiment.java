package com.du.edu.aut.experiment;

/**
 * Example experiment to show how to add an experiment to the project
 *
 * Created by David on 7/24/2015.
 */
class ExampleExperiment extends Experiment {

    /**
     * This is the name that the experiment will use. THE EXPERIMENT MUST HAVE THIS FIELD
     */
    public static final String name = "Example Experiment";

    /**
     * A constructor passing the name up to the experiment. If you want logging to be implemented
     * properly, you have to have this constructor.
     */
    public ExampleExperiment(){
        super(name);
    }

    /**
     * Method that contains the code for the experiment that is actually going to be run.
     */
    @Override
    public void runExperiment() throws ExperimentInterruptedException{
        //Do nothing really important....
        System.out.println("Hello World!");
    }

}
