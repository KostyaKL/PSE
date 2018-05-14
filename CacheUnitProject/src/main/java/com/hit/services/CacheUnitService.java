package com.hit.services;

import java.util.ArrayList;
import java.util.List;

import com.hit.algorithm.IAlgoCache;
import com.hit.algorithm.LRUAlgoCacheImp;
import com.hit.dao.DaoFileImpl;
import com.hit.dao.IDao;
import com.hit.dm.DataModel;
import com.hit.memory.CacheUnit;
import com.hit.server.Request;

public class CacheUnitService<T> extends Object {

	CacheUnit<T> cache;
	IAlgoCache<Long,DataModel<T>> algo;
	IDao<Long,DataModel<T>> dao;
	
	Request<T> request;
	
	public CacheUnitService() {
		algo = new LRUAlgoCacheImp<>(3);
		dao = new DaoFileImpl<>("src/main/resource/datasource.txt");
		cache = new CacheUnit<>(algo, dao);
	}
	
	public boolean update(DataModel<T>[] dataModels) {
	
		return false;
	}
	
	public boolean delete(DataModel<T>[] dataModels) {
		
		return false;
	}
	
	public DataModel<T>[] get(DataModel<T>[] dataModels){
		List<Long> ids = new ArrayList<>();
		int size;
		size = dataModels.length;
		for(int i=0;i<size;i++) {
			ids.add(dataModels[i].getDataModelId());
		}
		
		try {
			cache.getDataModels(ids.toArray((Long[]) new Long[size]));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return dataModels;
	}
}
