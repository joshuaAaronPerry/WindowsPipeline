package com.revature.test;

import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TrainerBatches {
	static WebDriver wd = null;
	private static void logout() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		wd.findElement(By.id("logoutBTN")).click();
		wd.quit();
	}
private static void loginTrainer() {
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		wd.findElement(By.name("email")).sendKeys("test.trainer@revature.com");
		wd.findElement(By.name("password")).sendKeys("p@$$w0rd");
		wd.findElement(By.name("submit")).click();

	}


private static void launchApplication() {
		File chrome = new File("src/main/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", chrome.getAbsolutePath());
		wd = new ChromeDriver();
		wd.get("https://assignforce-client.cfapps.io");		
	}

private void cycleSorts() {
	try {
		Thread.sleep(3000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	List<WebElement> elements = wd.findElements(By.cssSelector(".mat-sort-header-button"));
	for (int i = 0;i<elements.size();i++) {
			try {
				Thread.sleep(1000);
				} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			elements.get(i).click();
			try {
				Thread.sleep(1000);
				} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
}
	@Test (priority=0)
	public void testAppLaunch() {
		launchApplication();
	}
	
	@Test (priority=1)
	public void testLoginTrainer(){
		loginTrainer();
	}
	
	@Test (priority=2)
	public void selectBatchesTab() {
	boolean expand = true;
	while (expand) {
		try {
			Thread.sleep(500);
			wd.findElement(By.id("mat-tab-label-0-1")).click();
			expand = false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	}
	
	@Test (priority = 3)
	public void testCycleSorts() {
		cycleSorts();
	}
	
	@Test (dependsOnMethods="testCycleSorts")
	public void testLogout() {
		logout();
	}
}