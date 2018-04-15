package com.hit.algorithm;

import java.util.ArrayList;

import java.util.HashMap;

public class MRUAlgoCacheImp<K, V> extends AbstractAlgoCache<K,V> {
 	
    public MRUAlgoCacheImp(int capacity) {
    	this.capacity=capacity;
    	map = new HashMap <K,V>();
    	pageArray = new ArrayList<K>();
    }
 
    @Override
    public V getElement(K key) {
		if(pageArray.contains(key)) {
			int i;
			i= pageArray.indexOf(key);
			pageArray.remove(i);
			pageArray.add(0, key);
			return map.get(key);
		}
		return null;
	}
	
    @Override
	public V putElement(K key, V value) {
		if(map.containsKey(key)) {
			int i;
			V tmp;
			tmp = map.get(key);
			map.remove(key);
			map.put(key, value);
			i= pageArray.indexOf(key);
			pageArray.remove(i);
			pageArray.add(0, key);
			return tmp;
		}
		else {
			if(map.size() >= capacity) {
				V tmpV;
				K tmpK;
				
				tmpK = pageArray.get(0);
				tmpV = map.get(tmpK);
				map.remove(tmpK);
				pageArray.remove(tmpK);
				
				map.put(key, value);
				pageArray.add(0, key);
				
				return tmpV;
			}
			else {
				map.put(key, value);
				pageArray.add(0, key);
				
				return null;
			}
		}
	}
	
    @Override
	public void removeElement(K key) {
		if(map.containsKey(key)) {
			int i;
			i= pageArray.indexOf(key);
			pageArray.remove(i);
			map.remove(key);
		}
	}
    
    public String toString() {
		return pageArray.toString() + " " + map.toString();
	}
}
