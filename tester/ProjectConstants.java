package com.fresco.tester;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import com.fresco.tester.models.User;

import jxl.Sheet;
import jxl.Workbook;

public class ProjectConstants {

	public static final String TEST_END_POINT = "https://uno.frescodata.com/login.php";
	public static final List<User> UserPass;

	static {
		UserPass = new ArrayList<User>();
		try {
			FileInputStream fi = new FileInputStream("F:\\Bhaumik_Profile\\report\\Testdata.xls");
			Sheet s;
			Workbook w = Workbook.getWorkbook(fi);
			s = w.getSheet(0);
			for (int row = 0; row < s.getRows(); row++) {
				String username = s.getCell(0, row).getContents();
				String password = s.getCell(1, row).getContents();
				User user = new User(username, password);
				UserPass.add(user);
				System.out.println("added user = " + username + " password = " + password);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Unable To read config file");
		}
	}

	public static void init() {

	}

}
