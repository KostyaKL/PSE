package com.hit.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.hit.services.CacheUnitController;
import com.hit.util.CLI;

public class Server extends Object implements Observer {

	ServerSocket server;
	HandleRequest<String> handle;
	CacheUnitController<String> controller;
	Socket socket;
	Thread client;
	
	List<Thread> clientList;
	
	int flag;
	
	public Server() {
		flag = 1;
		clientList = new ArrayList<Thread>();
	}
	
	public void start()	{
		try {
			server = new ServerSocket(12345);
			while(flag == 1) {
				server.setSoTimeout(60000);
				
				socket = server.accept();
				//ObjectOutputStream output=new ObjectOutputStream(someClient.getOutputStream());
			
				//System.out.println("you are connected to the server");
				//output.writeObject("you are connected to the server");
				//output.flush();
				
				controller = new CacheUnitController<String>();
				handle  = new HandleRequest<String>(socket, controller);
				
				client = new Thread(handle);
				client.start();
				clientList.add(client);
				
			}
		}
		catch (Exception e) {
			  System.out.println("tiered of waiting for connection " + e);
		}
		
		finally {
			try {
				server.close();
				socket.close();
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
					flag = 0;
					int size = clientList.size();
					for (int i=0;i<size;i++) {
						client = clientList.get(i);
						client.interrupt();
					}
					server.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
