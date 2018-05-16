package com.hit.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;


public class PartThreeTest extends Observable implements Runnable {
	
	int port;
	
	public PartThreeTest() {
		port = 12345;
	}
	
	@Override
	public void run() {
		try {
            String serverHostname = new String("127.0.0.1");
 
            System.out.println("Connecting to host " + serverHostname + " on port " + port + ".");
 
            Socket echoSocket = null;
            ObjectOutputStream outC = null;
            
 
            try {
                echoSocket = new Socket(serverHostname, port);
                outC = new ObjectOutputStream(echoSocket.getOutputStream());
            } catch (UnknownHostException e) {
                System.err.println("Unknown host: " + serverHostname);
                System.exit(1);
            } catch (IOException e) {
                System.err.println("Unable to get streams from server");
                System.exit(1);
            }
 
        	BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

 
            while (true) {            	
                System.out.print("client: ");
                String userInput = stdIn.readLine();
                if ("q".equals(userInput)) {
                	echoSocket.close();
                	setChanged();
                    notifyObservers("stop");
                    break;
                }
                outC.writeObject(userInput);
            }
 
            outC.close();
            stdIn.close();           
        }
		catch (Exception e) {
            e.printStackTrace();
        }		
	}
}
