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
			
			String req = (String)input.readObject();
			
			ref = new TypeToken<Request<DataModel<T>[]>>(){}.getType();
			Request<DataModel<T>[]> request = new Gson().fromJson(req, ref);
			
			/*@SuppressWarnings("unchecked")
			Request<DataModel<T>[]> request = (Request<DataModel<T>[]>) input.readObject();
			*/
			headers = request.getHeaders();
			body = request.getBody();
						
			/**/
			System.out.println("headers: " + headers.values());
			int size = body.length;
			for (int i=0; i< size; i++) {
				System.out.println("body: " + body[i].toString());
			}
			/**/
			
			if(headers.containsValue("UPDATE")) {
				controller.update(body);
			}
			else if(headers.containsValue("GET")) {
				controller.get(body);
			}
			else if(headers.containsValue("DELETE")) {
				controller.delete(body);
			}
			
			
			input.close();
			s.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
