package com.hit.memory;

import java.io.IOException;
import java.io.Serializable;

import com.hit.algorithm.IAlgoCache;
import com.hit.dao.IDao;
import com.hit.dm.DataModel;



public class CacheUnit<T> extends Object {
	
	IAlgoCache<Long,DataModel<T>> algo;
	IDao<Serializable,DataModel<T>> dao;

	public CacheUnit(IAlgoCache<Long,DataModel<T>> algo, IDao<Serializable,DataModel<T>> dao) {
		this.algo = algo;
		this.dao = dao;
		
	}
	
	public DataModel<T>[] getDataModels(Long[] ids) throws ClassNotFoundException, IOException {
		int size = ids.length;
		DataModel<T> tmp;
		for(int i=0;i<size;i++) {
			tmp = algo.getElement(ids[i]);
			if(tmp == null) {
				tmp = dao.find(ids[i]);
				algo.putElement(ids[i], tmp);
			}
		}
		
		return null;
	}
}
