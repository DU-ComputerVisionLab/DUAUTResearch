package com.du.edu.aut.clientio;

import com.du.edu.aut.log.Logger;

import javax.swing.*;
import java.io.IOException;
import java.net.*;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Created by David on 7/24/2015.
 */
//TODO: Add comments and logging
public class UnityClientIO {

    private static String last_address = "";

    private DatagramSocket socketSend;
    private ConcurrentLinkedDeque<String> messageQueue = new ConcurrentLinkedDeque<String>();
    InetAddress address;
    Thread listener;

    public UnityClientIO() throws UnknownHostException {

        Logger.log("Unity client created. Attempting to connect.", "UnityClient");

        //Prompt for the address to connect to
        String client_name = JOptionPane.showInputDialog(
                null,
                "Please enter the client ip/name",
                JOptionPane.PLAIN_MESSAGE);

        last_address = client_name;
        boolean flag = false;
        int tries = 3;

        do {
            try {
                address = InetAddress.getByName(client_name);
                flag = true;
            } catch (UnknownHostException e) {
                JOptionPane.showMessageDialog(null, "Could not resolve hostname - please try again.");
                tries --;
                Logger.logError("Unity client returned with an unknown host: " + address, "UnityClient");
                if (tries == 0)
                    throw new UnknownHostException();
            }
        } while (flag == false);

        try {
            socketSend = new DatagramSocket(4445);
        } catch (SocketException e) {
            e.printStackTrace();
        }

        listener = new Thread(new Listener());
        listener.start();
    }

    public boolean send(String message) throws IOException{
        byte[] buf = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(buf, buf.length, address, 4445);
        try {
            socketSend.send(sendPacket);
        } catch (IOException ex) {
            Logger.logError("Could not send message: " + message, "UnityClient");
            throw new IOException();
        }
        Logger.log("Sent message: " + message, "UnityClient");
        return true;
    }

    //TODO: Add a UI prompt that the client is waiting
    public String get(){
        if (messageQueue.size() > 0){
            Logger.log("Received Message: " + messageQueue.peek(), "UnityClient");
            return messageQueue.pop();
        } else {
            while (messageQueue.size() < 1) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Logger.logError("Client listener was interrupted while sleeping on a get.", "UnityClient");
                }
            }
            Logger.log("Received Message: " + messageQueue.peek());
            return messageQueue.pop();
        }
    }

    public void destroy(){
        listener.interrupt();
        socketSend.close();
    }

    private class Listener implements Runnable {
        @Override
        public void run() {
            byte[] buffer = new byte[1024];
            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                try {
                    socketSend.receive(packet);
                } catch (IOException ex) {
                    socketSend.close();
                    return;
                }
                messageQueue.add(new String(packet.getData(), 0, packet.getLength()));
                if (Thread.interrupted()){
                    break;
                }
            }
        }
    }

}
