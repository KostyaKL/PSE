package com.hit.server;

import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import com.hit.services.CacheUnitController;

public class HandleRequest<T> extends Object implements Runnable {
	
	Socket s;
	CacheUnitController<T> controller;
	Request<T> request;
	Map<String, String> headers;
	T body;

	public HandleRequest(Socket s, CacheUnitController<T> controller) {
		this.s = s;
		this.controller = controller;
		
		headers = new HashMap<String, String>();
		
		request = new Request<T>(headers, body);
	}
	
	public void run() {
		try {
			ObjectInputStream input=new ObjectInputStream(s.getInputStream());
			String messageIn=(String)input.readObject();
			
			input.close();
		}
		catch(Exception e) {
			
		}
	}
}
