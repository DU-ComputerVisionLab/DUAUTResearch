/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.zeno_test131117;

import javax.swing.SwingWorker;

/**
 *
 * @author Michelle
 */
public class BrainWorker extends SwingWorker<Integer,String> {

    @Override
    protected Integer doInBackground() throws Exception {
        App.startTalk();
        return 1;
    }
    
}
