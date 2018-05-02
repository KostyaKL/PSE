package com.hit.server;

import java.util.Map;

public class Request<T> extends java.lang.Object implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Request(java.util.Map<java.lang.String,java.lang.String> headers, T body) {
		
	}
	
	public Map<String, String> getHeaders(){
		
		Map<String, String> ret = null;
		return ret;
	}
	
	public void setHeaders(java.util.Map<java.lang.String,java.lang.String> headers) {
		
	}
	
	public T getBody() {
		
		T ret = null;
		return ret;
	}
	
	public void setBody(T body) {
		
	}
	
	public String toString(){
		
		return "ret";
	}
}
