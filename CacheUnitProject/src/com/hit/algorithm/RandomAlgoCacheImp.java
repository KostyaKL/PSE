package com.hit.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class RandomAlgoCacheImp<K, V> extends AbstractAlgoCache<K,V> {
	
    public RandomAlgoCacheImp(int capacity) {
    	this.capacity=capacity;
    	map = new HashMap <K,V>();
    	pageArray = new ArrayList<K>();
    }
 
    @Override
    public V getElement(K key) {
		if(map.containsKey(key)) {
			return map.get(key);
		}
		return null;
	}
	
    @Override
	public V putElement(K key, V value) {
		if(map.containsKey(key)) {
			V tmp;
			tmp = map.get(key);
			map.remove(key);
			map.put(key, value);
			return tmp;
		}
		else {
			if(map.size() >= capacity) {
				V tmpV;
				K tmpK;
				int i;
				Random rand;
				
				rand = new Random();
				i = rand.nextInt(pageArray.size());
				
				tmpK = pageArray.get(i);
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
	
	@Override
	public String toString() {
		return pageArray.toString() + " " + map.toString();
	}

	public int checkSize() {
		return pageArray.size() + map.size();
	}	
}