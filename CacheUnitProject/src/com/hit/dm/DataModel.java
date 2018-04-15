package com.hit.dm;

import java.io.Serializable;

public class DataModel<T> extends Object implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataModel(Long id, T content) {
		
	}
	
	public int hashCode() {
		
		return 0;
	}
	
	public boolean equals(Object obj) {
		
		return false;
	}
	
	public String toString() {
		
		return " ";
	}
	
	public Long getDataModelId() {
		
		return null;
	}
	
	public void setDataModelId(Long id) {
		
	}
	
	public T getContent() {
		
		return null;
	}
	
	public void setContent(T content) {
		
		
	}

}
