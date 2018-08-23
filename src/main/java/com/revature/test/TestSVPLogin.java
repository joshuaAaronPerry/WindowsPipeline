package com.revature.test;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class TestSVPLogin {
	
	public static WebDriver wd = null;
	
	public void testSVPLogin() {
		openChrome();
		wd.get("https://assignforce-client.cfapps.io/login");
		wd.findElement(By.name("email")).sendKeys("svp@revature.com");
		wd.findElement(By.name("password")).sendKeys("p@$$w0rd");
		wd.findElement(By.name("submit")).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertEquals(wd.getCurrentUrl(), "https://assignforce-client.cfapps.io/overview");
	}

	public static void openChrome() {
		  File f = new File("src/main/resources/chromedriver.exe");
		  System.setProperty("webdriver.chrome.driver", f.getAbsolutePath());
		  //ChromeOptions options = new ChromeOptions();
		  wd = new ChromeDriver();
		  
	  }

}

