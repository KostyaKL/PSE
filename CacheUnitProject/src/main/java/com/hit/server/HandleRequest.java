package com.hit.server;

import java.io.ObjectInputStream;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hit.dm.DataModel;
import com.hit.services.CacheUnitController;

public class HandleRequest<T> extends Object implements Runnable {
	
	Socket s;
	CacheUnitController<T> controller;
	Request<T> request;

	Type ref;
	
	Map<String, String> headers;
	DataModel<T>[] body;

	public HandleRequest(Socket s, CacheUnitController<T> controller) {
		this.s = s;
		this.controller = controller;		
		
	}
	
	public void run() {
		try {
			ObjectInputStream input=new ObjectInputStream(s.getInputStream());
			String messageIn=(String)input.readObject();
			
			ref = new TypeToken<Request<DataModel<T>[]>>(){}.getType();
			Request<DataModel<T>[]> request = new Gson().fromJson(messageIn, ref);
			
			headers = request.getHeaders();
			body = request.getBody();
			
			while(!headers.isEmpty()) {
				if(headers.containsKey("update")) {
					headers.remove("update");
					controller.update(body);
				}
				else if(headers.containsKey("get")) {
					headers.remove("get");
					controller.get(body);
				}
				else if(headers.containsKey("delete")) {
					headers.remove("delete");
					controller.delete(body);
				}
			}
			
			input.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
