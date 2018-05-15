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
	String command;
	
	public PartThreeTest() {
		port = 12345;
		command = "{ \"headers\":\r\n" + 
				"{\"action\":\"UPDATE\"},\r\n" + 
				"\"body\":\r\n" + 
				"[{\"dataModelId\":111111, \"content\":\"Some String Data\"}]\r\n" + 
				"}";
	}
	
	@Override
	public void run() {
		try {
            String serverHostname = new String("127.0.0.1");
 
            System.out.println("Connecting to host " + serverHostname + " on port " + port + ".");
 
            Socket echoSocket = null;
            ObjectOutputStream outC = null;
            //BufferedReader inC = null;
 
            try {
                echoSocket = new Socket(serverHostname, port);
                outC = new ObjectOutputStream(echoSocket.getOutputStream());
                //inC = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            } catch (UnknownHostException e) {
                System.err.println("Unknown host: " + serverHostname);
                System.exit(1);
            } catch (IOException e) {
                System.err.println("Unable to get streams from server");
                System.exit(1);
            }
 
            /** {@link UnknownHost} object used to read from console */
        	BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

 
            while (true) {            	
                System.out.print("client: ");
                String userInput = stdIn.readLine();
                /** Exit on 'q' char sent */
                if ("q".equals(userInput)) {
                	setChanged();
                    notifyObservers("stop");
                    break;
                }
                //outC.println(userInput);
                outC.writeObject(userInput);
                //System.out.println("server: " + inC.readLine());
            }
 
            /** Closing all the resources */
            outC.close();
            stdIn.close();

            //inC.close();
            
            echoSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }		
	}

}
