package com.du.edu.aut.gui;

import com.du.edu.aut.experiment.ExperimentOptions;
import com.du.edu.aut.log.Logger;
import com.du.edu.aut.robots.RobotRegistry;
import org.pushingpixels.substance.api.skin.SubstanceGraphiteLookAndFeel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

/**
 *
 *  Main menu class
 *
 * Created by David on 7/24/2015.
 */
public class MainMenu {
    private JButton connectARobotButton;
    private JButton runAnExperimentButton;
    private JButton openLogFilesButton;
    private JPanel MainPanel;
    private JList list1;
    private JButton disconnectARobotButton;
    private JLabel partName;
    private JLabel partID;

    private boolean robot_connect = false;

    public void setRobotConnect(boolean set){
        robot_connect = set;
        if (robot_connect){
            connectARobotButton.setEnabled(true);
            disconnectARobotButton.setEnabled(true);
        } else {
            connectARobotButton.setEnabled(true);
            disconnectARobotButton.setEnabled(false);
        }
    }

    @SuppressWarnings("unchecked")
    public MainMenu() {

        connectARobotButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if(connectARobotButton.isEnabled()) {
                    ConnectRobot.INIT();
                    refreshData();
                }
            }
        });
        runAnExperimentButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                RunExperiment.INIT();
                refreshData();
            }
        });
        openLogFilesButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                try {
                    Desktop desktop = Desktop.getDesktop();
                    File dirToOpen = new File("Logs");
                    desktop.open(dirToOpen);
                    refreshData();
                } catch (IllegalArgumentException iae) {
                    System.out.println("File Not Found");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        MainPanel.addContainerListener(new ContainerAdapter() {
        });

        disconnectARobotButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (disconnectARobotButton.isEnabled()) {
                    DisconnectRobot.INIT();
                    refreshData();
                }
            }
        });

        //Initialization
        setRobotConnect(false);
        DefaultListModel m = (DefaultListModel) list1.getModel();
        for (com.du.edu.aut.robots.Robot r : RobotRegistry.listConnectedRobots()){
            m.addElement(r);
        }

        partID.setText(ExperimentOptions.participantID);
        partName.setText(ExperimentOptions.participantName);
    }

    public void refreshData(){
        setRobotConnect(false);
        DefaultListModel m = (DefaultListModel) list1.getModel();
        m.clear();
        for (com.du.edu.aut.robots.Robot r : RobotRegistry.listConnectedRobots()){
            m.addElement(r);
        }
        if (RobotRegistry.listConnectedRobots().size() > 0){
            setRobotConnect(true);
        }
    }

    public static void INIT() {
        try {
            UIManager.setLookAndFeel(new SubstanceGraphiteLookAndFeel());
        } catch (Exception e) {
            Logger.logError("Substance Graphite failed to initialize", "UI");
        }


        Logger.log("Initializing the experiment options");
        ExperimentOptions.initialize();

        final JFrame frame = new JFrame("MainMenu");
        frame.setContentPane(new MainMenu().MainPanel);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        //Flush logs when closing the window
        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                //NOT IMPLEMENTED
            }

            @Override
            public void windowClosing(WindowEvent e) {
                if (JOptionPane.showConfirmDialog(frame, "Are you sure ?", "Exit?", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                    Logger.flushLogs();
                    frame.setVisible(false);
                    frame.dispose();
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {
                //NOT IMPLEMENTED
            }

            @Override
            public void windowIconified(WindowEvent e) {
                //NOT IMPLEMENTED
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                //NOT IMPLEMENTED
            }

            @Override
            public void windowActivated(WindowEvent e) {
                //NOT IMPLEMENTED
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                //NOT IMPLEMENTED
            }
        });

        frame.setSize(800, 600);
        frame.setVisible(true);

    }

    @SuppressWarnings("unchecked")
    private void createUIComponents() {
        DefaultListModel model = new DefaultListModel();
        list1 = new JList(model);
        list1.setSelectionModel(new DisabledItemSelectionModel());
        list1.setDragEnabled(false);
        for (MouseMotionListener ml : list1.getMouseMotionListeners()){
            list1.removeMouseMotionListener(ml);
        }
    }

    private class DisabledItemSelectionModel extends DefaultListSelectionModel {
        public DisabledItemSelectionModel(){
            super();
        }
        @Override
        public void setSelectionInterval(int index0, int index1) {
            super.setSelectionInterval(-1, -1);
        }
    }
}


