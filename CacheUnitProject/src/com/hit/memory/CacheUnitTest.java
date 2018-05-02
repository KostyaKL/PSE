package com.hit.memory;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class CacheUnitTest {
	String test;
	
	public CacheUnitTest() {
		
	}
	
	@Test
	public void getDataModelsTest() {
		
		Assert.assertEquals("[5, 4, 3] {3=8, 4=9, 5=10}", test);
	}
}
