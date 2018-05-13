package com.hit.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

import com.hit.services.CacheUnitController;
import com.hit.util.CLI;

public class Server extends Object implements Observer {

	ServerSocket server;
	HandleRequest<String> handle;
	CacheUnitController<String> controller;
	
	public Server() {
		try {
			server = new ServerSocket(12345);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void start()	{
		try {
			//System.out.println("method start");
			while(true) {
				server.setSoTimeout(5000);
				Socket someClient = server.accept();
				ObjectOutputStream output=new ObjectOutputStream(someClient.getOutputStream());
				ObjectInputStream input=new ObjectInputStream(someClient.getInputStream());
				String messageIn;
			
				output.writeObject("you are connected to the server");
				output.flush();
			
				messageIn=(String)input.readObject();
				System.out.println("message from the client: "+messageIn);
				controller = new CacheUnitController<String>();
				handle  = new HandleRequest<String>(someClient, controller);
				new Thread(handle).start();
			
				output.writeObject("bye");
				output.flush();
				output.close();
				input.close();
				someClient.close();
			}
		}
		catch (Exception e) {
			  System.out.println("tiered of waiting for connection " + e);
		}

	}

	public void update(Observable o, Object arg) {
	
		if(o instanceof CLI) {
			
			if(arg == "start") {
				start();
			}
			else if(arg == "stop") {
				try {
					server.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
