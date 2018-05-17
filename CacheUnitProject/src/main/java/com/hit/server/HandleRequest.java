package com.hit.server;

import java.io.IOException;
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
	
	ObjectInputStream input;
	ObjectOutputStream output;
	
	
	public HandleRequest(Socket s, CacheUnitController<T> controller) {
		this.s = s;
		this.controller = controller;	
	}
	
	public void run() {
		try {
			input=new ObjectInputStream(s.getInputStream());
			output=new ObjectOutputStream(s.getOutputStream());
			
			while(!s.isClosed()) {
				String req = (String)input.readObject();
			
				ref = new TypeToken<Request<DataModel<T>[]>>(){}.getType();
				Request<DataModel<T>[]> request = new Gson().fromJson(req, ref);
			
				if(request != null) {				
					headers = request.getHeaders();
					body = request.getBody();
			
					if(headers.containsValue("UPDATE")) {
						Boolean ret = new Boolean(controller.update(body));
						output.writeObject(ret);
						output.flush();
					}
			
					else if(headers.containsValue("GET")) {
						body = controller.get(body);
						request.setBody(body);
						output.writeObject(request);
						output.flush();
					}
			
					else if(headers.containsValue("DELETE")) {
						Boolean ret = new Boolean(controller.delete(body));
						output.writeObject(ret);
						output.flush();					
					}
				}
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally{
			try {
				input.close();
				output.close();
				s.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
