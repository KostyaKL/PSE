package com.hit.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

import com.hit.services.CacheUnitController;
import com.hit.util.CLI;

@SuppressWarnings("rawtypes")
public class Server extends Object implements Observer {

	ServerSocket server;
	HandleRequest handle;
	CacheUnitController controller;
	Socket someClient;
	
	public Server() {
	
	}
	
	@SuppressWarnings("unchecked")
	public void start()	{
		try {
			server = new ServerSocket(12345);
			while(true) {
				server.setSoTimeout(60000);
				
				someClient = server.accept();
				//ObjectOutputStream output=new ObjectOutputStream(someClient.getOutputStream());
			
				System.out.println("you are connected to the server");
				//output.writeObject("you are connected to the server");
				//output.flush();
				
				controller = new CacheUnitController();
				handle  = new HandleRequest(someClient, controller);
				
				new Thread(handle).start();
				
			}
		}
		catch (Exception e) {
			  System.out.println("tiered of waiting for connection " + e);
		}
		
		finally {
			try {
				server.close();
				someClient.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
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
		else if(o instanceof PartThreeTest) {
			if(arg == "stop") {
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
