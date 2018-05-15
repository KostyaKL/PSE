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
	
	@Override
	public int hashCode() {
		
		return id.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(id.hashCode() == obj.hashCode()) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		
		return "dataModelId:" + id.toString() + ",content:" + content.toString();
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
