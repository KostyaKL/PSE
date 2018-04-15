package com.hit.algorithm;

import org.junit.jupiter.api.Test;
import org.junit.Assert;


class IAlgoCacheTest {

	Integer key;
	Integer value;
	
	IAlgoCache<Integer, Integer> AlgoTest;
	
	@Test
	public void testLRUAlgoCacheImp() {
		AlgoTest = new LRUAlgoCacheImp<>(3);
		
		exec(2);
		exec(3);
		exec(4);
		
		exec(2);
		exec(1);
		exec(3);
		
		exec(7);
		exec(5);
		exec(4);
		
		exec(3);
		
		System.out.println("LRU result " + AlgoTest.toString());
		
		Assert.assertEquals("[5, 4, 3] {3=8, 4=9, 5=10}", AlgoTest.toString());
	}
	
	@Test
	public void testMRUAlgoCacheImp() {
		AlgoTest = new MRUAlgoCacheImp<>(3);
		
		exec(2);
		exec(3);
		exec(4);
		
		exec(2);
		exec(1);
		exec(3);
		
		exec(7);
		exec(5);
		exec(4);
		
		exec(3);
		
		System.out.println("MRU result " + AlgoTest.toString());
		
		Assert.assertEquals("[3, 5, 1] {1=6, 3=8, 5=10}", AlgoTest.toString());
	}
	
	@Test
	public void testRandomAlgoCacheImp() {
		AlgoTest = new RandomAlgoCacheImp<>(3);
		
		exec(2);
		exec(3);
		exec(4);
		
		exec(2);
		exec(1);
		exec(3);
		
		exec(7);
		exec(5);
		exec(4);
		
		exec(3);
		
		System.out.println("Random result " + AlgoTest.toString());
		
		Assert.assertEquals(6, AlgoTest.checkSize());
	}
	
	@Test
	public void testSecondChanceAlgoCacheImpl() {
		AlgoTest = new SecondChanceAlgoCacheImpl<>(3);
		
		exec(2);
		exec(3);
		exec(4);
		
		exec(2);
		exec(1);
		exec(3);
		
		exec(7);
		exec(5);
		exec(4);
		
		exec(3);
		
		System.out.println("SC result " + AlgoTest.toString());
		
		Assert.assertEquals("[3, 4, 5] {3=8, 4=9, 5=10}", AlgoTest.toString());
	}
	
	void exec(int i) {
		try {
			key = new Integer(i);
			value = new Integer(i+5);
		
		
			AlgoTest.getElement(key);
			AlgoTest.putElement(key, value);
		}
		catch(Exception e) {
			System.out.println("error: " + e);
		}
		
		//System.out.println("result " + i + ": " + AlgoTest.toString());
	}

}
