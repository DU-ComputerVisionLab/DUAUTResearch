/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.zeno_test131117;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

//import java.util.*;
//import java.util.Scanner;

/**
 *
 * @author Chemil5000v2
 *  Nathan
 */
public class ClientThread {
    
//private DatagramPacket packet = null;
private DatagramSocket socketSend;
private DatagramSocket socketListen;
//private InetAddress address = InetAddress.getByName("192.168.0.104");
//private Listener listen;
private Queue<String> messageQueue = new ArrayDeque<String>();
private Thread listThread;

//InetAddress address = InetAddress.getByName("RobotControl");
InetAddress address = InetAddress.getByName("Chemil5000");


 public ClientThread() throws IOException {
 //this("ClientServerThreadThrown?");
     openSocket();
    
 }   
 
 private void openSocket() {
     try {
         socketSend = new DatagramSocket(4445);
         socketListen = new DatagramSocket(4446);
     } catch (SocketException e) {
         Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, e);
     }
     //;(new Thread(new Listener())).start()
     //Thread newThread = new Thread(new Listener())).start();
Listener listenThread = new Listener();
listenThread.start();
 }
 
 public void closeSocket() {
    // socketListen.close();
     socketSend.close();
 }
 
 public String sender(String prompt) {
     System.out.println("Sending message " + prompt);
    byte[] buf = prompt.getBytes();
    DatagramPacket sendPacket = new DatagramPacket(buf, buf.length, address, 4445);
    try {
        socketSend.send(sendPacket);
    } catch (IOException ex) {
        Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
    } 
    return prompt;
 }
 
 public String listener() {
//     byte[] buf = new byte[1000];
//     packet = new DatagramPacket(buf, buf.length);
//     try {
//         socket.receive(packet);
//     } catch (IOException e) {
//         LogHandler.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, e);
//     }
//     String received = new String(packet.getData(), 0, packet.getLength());
//     return received;
     
     while (messageQueue.isEmpty()) {
         try {
             Thread .sleep(100);
            // System.out.println("listen sleep");
         } catch (InterruptedException ex) {
             System.out.println("COULD NOT SLEEP!");
             Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
         } 
     }
     return messageQueue.poll();
//     return " ";
 }
 
public class Listener implements Runnable {
//    private boolean running = true;
    
   // @Override
    
    
    public void run() {
        DatagramSocket sendSocket = null;
        System.out.println("Started the listener");
        /*
         * try {
           // socketListen = new DatagramSocket(4446);
        } catch (SocketException ex) {
            LogHandler.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        byte[] buffer = new byte[1024];
        System.out.println("new byte?");
        while (true) {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            System.out.println("get datagram packet?");
            try {        
                System.out.println("trying to recieve?");
                socketListen.setSoTimeout(100);
                               try{socketListen.receive(packet);
                 messageQueue.add(new String(packet.getData(), 0, packet.getLength()));
                System.out.println("recieved a packet in listener");
            } catch (IOException ex) {
                //LogHandler.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("io exception in receving packet");
            }
            } catch (SocketException ex) {
                //LogHandler.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            }
          
           System.out.println("added to queue");
            try {
                Thread.sleep(500);
                System.out.println("sleep 50");
            } catch (InterruptedException ex) {
                //LogHandler.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                System.out.print("list not sleep");
            }
        }
    }
    
    public void start ()
    {
        listThread =new Thread();
        System.out.println("started listen");
       // run();
    }
    /*public void interrupt() {
        //socket.
    }*/
}
    
}
