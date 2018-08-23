package com.revature.test;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
		wd.manage().window().maximize();
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
	public void clickTrainerTab() {
		WebElement trainers = (new WebDriverWait(wd, 10)).
				until(ExpectedConditions.elementToBeClickable(By.id("mat-tab-label-0-4")));
		trainers.click();
		(new WebDriverWait(wd, 10)).
		until(ExpectedConditions.urlMatches("https://assignforce-client.cfapps.io/trainers"));
		Assert.assertEquals(wd.getCurrentUrl(),"https://assignforce-client.cfapps.io/trainers");
	}
	
	@Test(dependsOnMethods = {"clickTrainerTab"})
	public void addTrainer() {
		WebElement btn = (new WebDriverWait(wd, 10)).
				until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/app-trainers/div/mat-toolbar-row[1]/mat-toolbar/button")));
		btn.click();
		WebElement first = (new WebDriverWait(wd, 10)).
				until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"mat-input-3\"]")));
		WebElement last = (new WebDriverWait(wd, 10)).
				until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"mat-input-4\"]")));
		WebElement email = (new WebDriverWait(wd, 10)).
				until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"mat-input-5\"]")));
		first.sendKeys("joe");
		last.sendKeys("smith");
		email.sendKeys("joe@smith.com");
		WebElement netBox = (new WebDriverWait(wd, 10)).
				until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"mat-checkbox-5-input\"]")));
		netBox.click();
		WebElement go = (new WebDriverWait(wd, 10)).
				until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[2]/div/mat-dialog-container/app-trainers-add/form/div[2]/button[1]")));
		go.click();
	}
	
	@Test(dependsOnMethods = {"clickTrainerTab"})
	public void deactivateTrainer() {
		List<WebElement> trainers = (new WebDriverWait(wd, 10)).
				until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("mat-list-content")));
		List<WebElement> buttons = (new WebDriverWait(wd, 10)).
				until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("mac-icon-button")));
		buttons.get(1).click();
	}
	
	@Test(dependsOnMethods = {"clickTrainerTab"})
	public void activateTrainer() {
		JavascriptExecutor js = ((JavascriptExecutor) wd);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		List<WebElement> buttons = (new WebDriverWait(wd, 10)).
				until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("mac-icon-button")));
		buttons.get(1).click();
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
				until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"mat-expansion-panel-header-1\"]")));		
		WebElement change = (new WebDriverWait(wd, 10)).
				until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"cdk-accordion-child-8\"]")));
		String visBefore = change.getCssValue("visibility");
		reports.click();
		String visAfter = change.getCssValue("visibility");
		boolean changed = (visBefore == "hidden" && visAfter == "visible") ||
				(visBefore == "visible" && visAfter == "hidden");
		Assert.assertEquals(changed, true);
	}
	
	@Test(dependsOnMethods = {"clickReportTab"})
	public void testGradSummary() {
		WebElement sum = (new WebDriverWait(wd, 10)).
				until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"mat-expansion-panel-header-2\"]")));
		WebElement change = (new WebDriverWait(wd, 10)).
				until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"cdk-accordion-child-2\"]")));
		String visBefore = change.getCssValue("visibility");
		sum.click();
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

