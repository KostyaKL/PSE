package com.hit.algorithm;

import java.util.ArrayList;
import java.util.HashMap;

public class LRUAlgoCacheImp <K, V> extends AbstractAlgoCache<K,V> {
 	
    public LRUAlgoCacheImp(int capacity) {
    	this.capacity=capacity;
    	map = new HashMap <K,V>();
    	pageArray = new ArrayList<K>();
    }
 
    @Override
    public V getElement(K key) {
		if(pageArray.contains(key)) { //check if key is in memory
			int i;
			int size;
			i= pageArray.indexOf(key);
			pageArray.remove(i);
			size = pageArray.size();
			pageArray.add(size, key);
			return map.get(key);
		}
		return null;
	}
	
    @Override
	public V putElement(K key, V value) {
		int size;
		
		if(map.containsKey(key)) {
			int i;
			V tmp;
			tmp = map.get(key);
			map.remove(key);
			map.put(key, value);
			i= pageArray.indexOf(key);
			pageArray.remove(i);
			size = pageArray.size();
			pageArray.add(size, key);
			return tmp;
		}
		else {
			if(map.size() >= capacity) {
				V tmpV;
				K tmpK;
				int i;
				
				tmpK = pageArray.get(0);
				tmpV = map.get(tmpK);
				map.remove(tmpK);
				i= pageArray.indexOf(tmpK);
				pageArray.remove(i);
				
				map.put(key, value);
				size = pageArray.size();
				pageArray.add(size, key);
				
				return tmpV;
			}
			else {
				map.put(key, value);
				size = pageArray.size();
				pageArray.add(size, key);
				
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
