package com.hit.server;

import java.util.concurrent.TimeUnit;

import com.hit.util.CLI;

public class CacheUnitServerDriver extends Object {

	public CacheUnitServerDriver() {
		
	}
	
	public static void main(String[] args) {
		CLI cli = new CLI(System.in, System.out);
		Server server = new Server();
		cli.addObserver(server);
		new Thread(cli).start();
		
		try {
			TimeUnit.SECONDS.sleep(7);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PartThreeTest test = new PartThreeTest();
		test.addObserver(server);
		new Thread(test).start();
	}
}
