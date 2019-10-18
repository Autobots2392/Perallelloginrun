package com.fresco.tester;

import java.lang.reflect.Constructor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.fresco.tester.base.FrescoSearchDownload;
import com.fresco.tester.base.TestBase;
import com.fresco.tester.models.User;

public class TestDriver {

	private int maxInstance;
	private int parllelRunner;
	private Class<? extends TestBase> className;

	ExecutorService executorService = null;

	public TestDriver(int maxInstance, int parellel, Class<? extends TestBase> className) {
		super();
		this.maxInstance = maxInstance;
		this.parllelRunner = parellel;
		executorService = Executors.newFixedThreadPool(parellel);
		this.className = className;
	}

	public static void main(String[] args) throws Exception {
		ProjectConstants.init();
		TestDriver driver = new TestDriver(3, 3, FrescoSearchDownload.class);
		driver.start();
	}

	private void start() throws Exception {
		for (int i = 0; i < maxInstance; i++) {
			Constructor constructor = className.getConstructor(new Class[] { String.class, String.class });
			Pair<String, String> pair = getUserNamePasword(i, maxInstance);
			executorService.submit((Runnable) constructor.newInstance(pair.frist, pair.secoend));
		}
		executorService.shutdown();
	}

	private Pair<String, String> getUserNamePasword(int current, int max) {
		if (current > ProjectConstants.UserPass.size()) {
			User user = ProjectConstants.UserPass.get(current % ProjectConstants.UserPass.size());
			return new Pair<String, String>(user.getUser(), user.getPassword());
		} else {
			User user = ProjectConstants.UserPass.get(current);
			return new Pair<String, String>(user.getUser(), user.getPassword());
		}
	}

}
