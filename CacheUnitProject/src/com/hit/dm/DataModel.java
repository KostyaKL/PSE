package com.hit.dm;

import java.io.Serializable;

public class DataModel<T> extends Object implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	Long id;
	T content;

	public DataModel(Long id, T content) {
		this.id = id;
		this.content = content;
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
		
		return id;
	}
	
	public void setDataModelId(Long id) {
		this.id = id;
	}
	
	public T getContent() {
		
		return content;
	}
	
	public void setContent(T content) {
		this.content = content;
		
	}

}
