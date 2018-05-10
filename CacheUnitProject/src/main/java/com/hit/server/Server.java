package com.hit.server;

import java.net.ServerSocket;
import java.util.Observable;
import java.util.Observer;

import com.hit.util.CLI;

public class Server extends Object implements Observer {

	ServerSocket socket;
	
	public Server() {
		try {
			socket = new ServerSocket(12345);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void start()	{
		
	}

	public void update(Observable o, Object arg) {
		
		if(o instanceof CLI) {
			
			if(arg == "start") {
				start();
			}
			else if(arg == "stop") {
			
			}
		}
	}
}
