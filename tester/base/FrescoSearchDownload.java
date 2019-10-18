package com.fresco.tester.base;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.fresco.tester.Helpers;
import com.fresco.tester.ProjectConstants;

public class FrescoSearchDownload extends TestBase {

	String userName, password;

	public FrescoSearchDownload(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public void startTest() throws Exception {
		WebDriver driver = new FirefoxDriver();
		driver.get(ProjectConstants.TEST_END_POINT);
		driver.manage().window().maximize();
		System.out.println("Successfully opened the website ");
		Thread.sleep(5000);

		// enter email.
		driver.findElement(By.id("email")).sendKeys(userName);
		// enter password.
		driver.findElement(By.id("password")).sendKeys(password);
		// enter captcha.
		String test = driver.findElement(By.xpath("//label[@id='ebcaptchatext']")).getText();
		String ans = String.valueOf(Helpers.decodeCaptcha(test));
		System.out.println("Test =" + test + " Ans =" + ans);
		driver.findElement(By.id("ebcaptchainput")).sendKeys(ans);
		driver.findElement(By.className("checkmark-signup")).click();
		// click on sign in button.
		driver.findElement(By.id("submit")).click();
		Thread.sleep(5000);
		// select state.
		driver.findElement(By.xpath("//div[@id='StateCollapsed']//h4[@class='panel-title collapsed']")).click();
		driver.findElement(By.xpath("//input[@id='1']")).click();
		Thread.sleep(1000);
		// click next
		driver.findElement(By.xpath("//a[contains(text(),'Next Page')]")).click();
		// select industry
		driver.findElement(By.xpath("//div[@id='IndustryCollapsed']//h4[@class='panel-title collapsed']")).click();
		driver.findElement(By.xpath("//div[@id='IndustryCollapse']//input[@type='checkbox'][1]")).click();
		// driver.findElement(By.xpath("//div[@id='IndustryCollapse']//input[@type='checkbox'][2]")).click();
		// click on next
		driver.findElement(By.xpath("//div[@class='col-12 col-md-4 col-sm-4 text-center text-md-right text-sm-right']//a[@class='btn btn-primary btn-md next'][contains(text(),'Next')]")).click();
		// click on job function.

		/*
		 * driver.findElement(By.
		 * xpath("//div[@id='FunctionCollapsed']//h4[@class='panel-title collapsed']")).
		 * click(); driver.findElement(By.xpath(
		 * "//div[@id='FunctionCollapse']//li[3]//label[1]//input[1]")).click();
		 * Thread.sleep(1000);
		 */
		// click on phones.
		driver.findElement(By.xpath("//input[@id='22']")).click();
		Thread.sleep(3000);
		// click on run button.
		driver.findElement(By.xpath("//button[@id='run']")).click();
		Thread.sleep(8000);
		// print count
		System.out.println("fOUND " + driver.findElement(By.xpath("//h6[@class='py-2 px-2 mb-0 display_count']")).getText());
		// click on download
		driver.findElement(By.xpath("//a[@id='download_pop']")).click();
		driver.findElement(By.xpath("//div[@class='modal-dialog']//div[@class='modal-body']")).click();
		// enter desired records to download.
		driver.findElement(By.xpath("//input[@id='records']")).sendKeys("10");
		// select download file format.
		driver.findElement(By.xpath("//select[@id='format']")).click();
		driver.findElement(By.xpath("//option[contains(text(),'Excel')]")).click();
		// click on download button.
		driver.findElement(By.xpath("//input[@id='downloadrec']")).click();
		// creating instance of Robot class
		Robot rb = new Robot();
		// pressing keys with the help of keyPress and keyRelease events
		Thread.sleep(2000);
		rb.keyPress(KeyEvent.VK_DOWN);
		rb.keyRelease(KeyEvent.VK_DOWN);
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(2000);
		Runtime.getRuntime().exec("D:\\SoftwareTestingMaterial\\AutoIt\\DownloadFile.exe");
	}
}
