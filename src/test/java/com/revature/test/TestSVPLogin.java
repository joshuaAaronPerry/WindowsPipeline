package com.revature.test;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestSVPLogin {
	
	public static WebDriver wd = null;
	
	@Test
	public void testSVPLogin() {
		openChrome();
		wd.get("https://assignforce-client.cfapps.io/login");
		WebElement email = (new WebDriverWait(wd, 10)).
				until(ExpectedConditions.elementToBeClickable(By.name("email")));
		WebElement pass = (new WebDriverWait(wd, 10)).
				until(ExpectedConditions.elementToBeClickable(By.name("password")));
		WebElement submit = (new WebDriverWait(wd, 10)).
				until(ExpectedConditions.elementToBeClickable(By.name("submit")));
		email.sendKeys("svp@revature.com");
		pass.sendKeys("p@$$w0rd");
		submit.click();
		(new WebDriverWait(wd, 10)).
				until(ExpectedConditions.urlMatches("https://assignforce-client.cfapps.io/overview"));
		Assert.assertEquals(wd.getCurrentUrl(), "https://assignforce-client.cfapps.io/overview");	
	}
	
	@Test(dependsOnMethods = {"testSVPLogin"})
	public void clickReportTab() {
		WebElement rTab = (new WebDriverWait(wd, 10)).
				until(ExpectedConditions.elementToBeClickable(By.id("mat-tab-label-0-6")));
		rTab.click();
		(new WebDriverWait(wd, 10)).
		until(ExpectedConditions.urlMatches("https://assignforce-client.cfapps.io/reports"));
		Assert.assertEquals(wd.getCurrentUrl(),"https://assignforce-client.cfapps.io/reports");
	}
	
	@Test(dependsOnMethods = {"clickReportTab"})
	public void testBatchReport() {
		WebElement reports = (new WebDriverWait(wd, 10)).
				until(ExpectedConditions.elementToBeClickable(By.id("mat-expansion-panel-header-8")));
		reports.click();
		
		WebElement change = (new WebDriverWait(wd, 10)).
				until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"cdk-accordion-child-8\"]")));
		String visBefore = change.getCssValue("visibility");
		change.click();
		String visAfter = change.getCssValue("visibility");
		boolean changed = (visBefore == "hidden" && visAfter == "visible") ||
				(visBefore == "visible" && visAfter == "hidden");
		Assert.assertEquals(changed, true);
	}

	public static void openChrome() {
		  System.out.println("Here");
		  File f = new File("src/main/resources/chromedriver.exe");
		  System.setProperty("webdriver.chrome.driver", f.getAbsolutePath());
		  //ChromeOptions options = new ChromeOptions();
		  wd = new ChromeDriver();
		  
	  }

}

