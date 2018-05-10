package com.hit.services;

import com.hit.dm.DataModel;

public class CacheUnitController<T> extends java.lang.Object {

	CacheUnitService<T> service;
	
	public CacheUnitController() {
		service = new CacheUnitService<T>();
	}
	
	public boolean update(DataModel<T>[] dataModels) {
		
		return service.update(dataModels);
	}
	
	public boolean delete(DataModel<T>[] dataModels) {
		
		return service.delete(dataModels);
	}
	
	public DataModel<T>[] get(DataModel<T>[] dataModels){
		
		return service.get(dataModels);
	}
}
