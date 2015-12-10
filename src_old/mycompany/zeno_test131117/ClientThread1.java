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
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

//import java.util.*;
//import java.util.Scanner;

/**
 *
 * @author Chemil5000v2
 *  Nathan
 */
public class ClientThread1 {
    
//private DatagramPacket packet = null;
private DatagramSocket socketSend;
private DatagramSocket socketListen;
//private InetAddress address = InetAddress.getByName("192.168.0.255");
//private Listener listen;
private Queue<String> messageQueue = new ConcurrentLinkedQueue<String>();
//InetAddress address = InetAddress.getByName("RobotControl");
InetAddress address = InetAddress.getByName("Chemil5000");
//InetAddress address = InetAddress.getByName("OP9020B");

 public ClientThread1() throws IOException {
 //this("ClientServerThreadThrown?");
     openSocket();
     new Thread(new Listener()).start();
 }   
 
 private void openSocket() {
     try {
         socketSend = new DatagramSocket(4445);
//         socketListen = new DatagramSocket(4446);
     } catch (SocketException e) {
         e.printStackTrace();
         System.exit(1);
     }
 }
 
 public void closeSocket() {
//     socketListen.close();
     socketSend.close();
 }
 
 public String sender(String prompt) {
    byte[] buf = prompt.getBytes();
    DatagramPacket sendPacket = new DatagramPacket(buf, buf.length, address, 4445);
    try {
        socketSend.send(sendPacket);
        System.out.println("Sent: " + prompt);
    } catch (IOException ex) {
        Logger.getLogger(ClientThread1.class.getName()).log(Level.SEVERE, null, ex);
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
             Thread.sleep(100);
            // System.out.println("listen sleep");
         } catch (InterruptedException ex) {
             System.out.println("COULD NOT SLEEP!");
             Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
         } 
     }
     System.out.println("Received: " + messageQueue.peek());
     return messageQueue.poll();
//     return " ";
 }
 
private class Listener implements Runnable {
//    private boolean running = true;
    
    @Override
    public void run() {
//        DatagramSocket socketListen = null;
//        try {
//            socketListen = new DatagramSocket(4446);
//        } catch (SocketException ex) {
//            LogHandler.getLogger(ClientThread1.class.getName()).log(Level.SEVERE, null, ex);
//        }
        byte[] buffer = new byte[1024];
        while (true) {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            try {
                socketSend.receive(packet);
            } catch (IOException ex) {
                Logger.getLogger(ClientThread1.class.getName()).log(Level.SEVERE, null, ex);
                socketSend.close();
                return;
            }
            messageQueue.add(new String(packet.getData(), 0, packet.getLength()));
        }
    }
    
    /*public void interrupt() {
        //socket.
    }*/
}
    
}
