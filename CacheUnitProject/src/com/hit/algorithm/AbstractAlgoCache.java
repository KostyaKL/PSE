package com.hit.algorithm;

import java.util.ArrayList;
import java.util.HashMap;

public class AbstractAlgoCache<K, V> extends java.lang.Object implements IAlgoCache<K,V> {
	
	public int capacity;
	HashMap <K, V> map;
	ArrayList<K> pageArray;
	
	public AbstractAlgoCache(){
		//capacity = 0;
	}
	
	public AbstractAlgoCache(int capacity){
		this.capacity = capacity;
	}
	
	public V getElement(K key) {
		return null;
	}
	
	public V putElement(K key, V value) {
		return null;
	}
	
	public void removeElement(K key) {
		
	}
	
	public int checkSize() {
		return 0;
	}
}
