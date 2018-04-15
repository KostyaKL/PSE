package com.hit.algorithm;

import java.util.ArrayList;
import java.util.HashMap;

public class SecondChanceAlgoCacheImpl <K, V> extends AbstractAlgoCache<K,V> {
	
	ArrayList<Integer> flagArray;
	Integer flag0;
	Integer flag1;
    
    public SecondChanceAlgoCacheImpl(int capacity) {
        this.capacity = capacity;
        map = new HashMap <K,V>();
        pageArray = new ArrayList<K>();
        flagArray = new ArrayList<Integer>();
        flag0 = Integer.valueOf(0);
    	flag1 = Integer.valueOf(1);
    
    }
    
    @Override
    public V getElement(K key) {
        if(map.containsKey(key)) {
            int i;
            i= pageArray.indexOf(key);
            pageArray.remove(i);
            flagArray.remove(i);
            pageArray.add(0, key);
            flagArray.add(0, flag0);
            return map.get(key);
        }
        
        return null;
    }
    
    @Override
    public V putElement(K key, V value) {        
        if(map.containsKey(key)) {
           V tmpV;
           int i;
             
           tmpV = map.get(key);                     
           map.remove(key);                             
           map.put(key, value);                         
           
           i = pageArray.indexOf(key);                   
           pageArray.remove(i); 
           flagArray.remove(i);
            
           pageArray.add(0, key);
           flagArray.add(0, flag0);                  
           return tmpV;                               
        }
        
        else {
            if(map.size() >= capacity) {
                V tmpV = null;
                K tmpK;
                int i;                        
                
                i = pageArray.size()-1;
                tmpK = pageArray.get(i);               
                
                while (true) {
                	if(flag1 == flagArray.get(i)) {
                		tmpV = map.get(tmpK);                     
                    	map.remove(tmpK);                             
                    	map.put(key, value);
                    	
                    	pageArray.remove(i); 
                        flagArray.remove(i);
                        
                        pageArray.add(0, key);
                        flagArray.add(0, flag0);     
                    
                    	return tmpV;
                	}
                	else {
                		pageArray.remove(i); 
                        flagArray.remove(i); 
                        
                        pageArray.add(0, tmpK);
                        flagArray.add(0, flag1);
                        tmpK = pageArray.get(i);               
                	}
                }    
            }
            
            else {                               
                map.put(key, value);                                   
                pageArray.add(0, key);                
                flagArray.add(0, flag0);
                return null;
            }
        }
    }

    @Override
    public void removeElement(K key) {
        if(map.containsKey(key)) {
            int i;
            i = pageArray.indexOf(key);
            pageArray.remove(i);
            flagArray.remove(i);
            map.remove(key);
        }
    }
    
    
    public String toString() {
		return pageArray.toString() + " " + map.toString();
	}	
}
