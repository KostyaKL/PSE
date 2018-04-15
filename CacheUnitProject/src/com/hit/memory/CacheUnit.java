package com.hit.memory;

import java.io.IOException;
import java.io.Serializable;

import com.hit.algorithm.IAlgoCache;
import com.hit.dao.IDao;
import com.hit.dm.DataModel;



public class CacheUnit<T> extends Object {

	public CacheUnit(IAlgoCache<Long,DataModel<T>> algo, IDao<Serializable,DataModel<T>> dao) {
		
		
	}
	
	public DataModel<T>[] getDataModels(Long[] ids) throws ClassNotFoundException, IOException{
		
		return null;
	}
}
