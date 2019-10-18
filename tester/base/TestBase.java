package com.fresco.tester.base;

public abstract class TestBase implements Runnable {

	static {
		System.setProperty("webdriver.gecko.driver", "F:\\gecko\\geckodriver.exe");
	}

	abstract void startTest() throws Exception;

	public void run() {
		try {
			startTest();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
