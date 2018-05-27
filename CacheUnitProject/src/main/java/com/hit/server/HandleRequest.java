package com.hit.server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.hit.dm.DataModel;
import com.hit.services.CacheUnitController;

public class HandleRequest<T> extends Object implements Runnable {
	
	Socket s;
	CacheUnitController<T> controller;
	
	Type ref;
	
	Map<String, String> headers;
	DataModel<T>[] body;
	
	ObjectInputStream input;
	ObjectOutputStream output;
	
	String req;
	
	
	public HandleRequest(Socket s, CacheUnitController<T> controller) {
		this.s = s;
		this.controller = controller;
		ref = new TypeToken<Request<DataModel<T>[]>>(){}.getType();
	}
	
	public void run() {
		try {
			input=new ObjectInputStream(s.getInputStream());
			output=new ObjectOutputStream(s.getOutputStream());
			
			while(!s.isClosed()) {
				
				req = (String)input.readObject();
					
				Request<DataModel<T>[]> request = null;
				try {
					request = new Gson().fromJson(req, ref);
				}
				catch(JsonSyntaxException e) {
					output.writeObject("json error");
					output.flush();	
					request = null;
				}
			
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
		catch(EOFException e) {
		//	e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally{
			try {
				input.close();
				output.close();
				if(s != null && !s.isClosed()) {
					s.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
