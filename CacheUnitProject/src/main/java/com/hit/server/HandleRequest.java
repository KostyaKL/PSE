package com.hit.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
			ObjectOutputStream output=new ObjectOutputStream(s.getOutputStream());
			
			String req = (String)input.readObject();
			
			ref = new TypeToken<Request<DataModel<T>[]>>(){}.getType();
			Request<DataModel<T>[]> request = new Gson().fromJson(req, ref);
			
			headers = request.getHeaders();
			body = request.getBody();
			
			if(headers.containsValue("UPDATE")) {
				Boolean ret = controller.update(body);
				output.writeObject(ret);
			}
			
			else if(headers.containsValue("GET")) {
				body = controller.get(body);
				request.setBody(body);
				output.writeObject(request);
			}
			
			else if(headers.containsValue("DELETE")) {
				Boolean ret = controller.delete(body);
				output.writeObject(ret);
			}
			
			
			input.close();
			s.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
