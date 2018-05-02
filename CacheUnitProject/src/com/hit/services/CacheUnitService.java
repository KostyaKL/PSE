package com.hit.services;

import com.hit.dm.DataModel;

public class CacheUnitService<T> extends java.lang.Object {

	public CacheUnitService() {
		
	}
	
	public boolean update(DataModel<T>[] dataModels) {
		
		return false;
	}
	
	public boolean delete(DataModel<T>[] dataModels) {
		
		return false;
	}
	
	public DataModel<T>[] get(DataModel<T>[] dataModels){
		
		return dataModels;
	}
}
