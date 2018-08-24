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

public class TestAll {
	
	public static WebDriver wd = null;
	
	@Test(priority = 0)
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
	
	@Test(priority = 1)
	public void clickSettingsTab() {
		WebElement trainers = (new WebDriverWait(wd, 10)).
				until(ExpectedConditions.elementToBeClickable(By.id("mat-tab-label-0-5")));
		trainers.click();
		(new WebDriverWait(wd, 10)).
		until(ExpectedConditions.urlMatches("https://assignforce-client.cfapps.io/profile/svp@revature.com"));
		Assert.assertEquals(wd.getCurrentUrl(),"https://assignforce-client.cfapps.io/profile/svp@revature.com");
	}
	
	
	@Test(priority = 2)
	public void testProfileBtn() {
		WebElement btn = (new WebDriverWait(wd, 10)).until(ExpectedConditions.elementToBeClickable(By.cssSelector(".mat-icon-button")));
		String contentBefore = wd.findElement(By.id("profilecontent")).getAttribute("innerhtml");
		btn.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String contentAfter = wd.findElement(By.id("profilecontent")).getAttribute("innerhtml");
		boolean change = contentBefore.equals(contentAfter);
		Assert.assertEquals(change, true);
	}	
	
	@Test(priority = 3)
	public void clickTrainerTab() {
		WebElement trainers = (new WebDriverWait(wd, 10)).
				until(ExpectedConditions.elementToBeClickable(By.id("mat-tab-label-0-4")));
		trainers.click();
		(new WebDriverWait(wd, 10)).
		until(ExpectedConditions.urlMatches("https://assignforce-client.cfapps.io/trainers"));
		Assert.assertEquals(wd.getCurrentUrl(),"https://assignforce-client.cfapps.io/trainers");
	}
	
	@Test(priority = 4)
	public void addTrainer() {
		WebElement btn = (new WebDriverWait(wd, 10)).
				until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.mat-icon-button:nth-child(3)")));
		btn.click();
		WebElement first = (new WebDriverWait(wd, 10)).
				until(ExpectedConditions.elementToBeClickable(By.cssSelector("#mat-input-0")));
		WebElement last = (new WebDriverWait(wd, 10)).
				until(ExpectedConditions.elementToBeClickable(By.cssSelector("#mat-input-1")));
		WebElement email = (new WebDriverWait(wd, 10)).
				until(ExpectedConditions.elementToBeClickable(By.cssSelector("#mat-input-2")));
		first.sendKeys("joe");
		last.sendKeys("smith");
		email.sendKeys("joe@smith.com");
//		WebElement netBox = (new WebDriverWait(wd, 10)).
//				until(ExpectedConditions.elementToBeClickable(By.cssSelector("#mat-checkbox-2-input")));
//		netBox.click();
		WebElement go = (new WebDriverWait(wd, 10)).
				until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.mat-button:nth-child(1)")));
		go.click();
	}
	
	@Test(priority = 5)
	public void deactivateTrainer() {
//		List<WebElement> trainers = (new WebDriverWait(wd, 10)).
//				until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("mat-list-content")));
		List<WebElement> buttons = (new WebDriverWait(wd, 10)).
				until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("mat-card.mat-card:nth-child(2) > mat-card-content:nth-child(1) > div:nth-child(1) > mat-list:nth-child(1) > app-trainer-item:nth-child(1) > mat-list-item:nth-child(1) > div:nth-child(1) > button:nth-child(6)")));
		buttons.get(1).click();
	}
	
	@Test(priority = 6)
	public void activateTrainer() {
		JavascriptExecutor js = ((JavascriptExecutor) wd);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		List<WebElement> buttons = (new WebDriverWait(wd, 10)).
				until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("mac-icon-button")));
		buttons.get(1).click();
		js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
	}
	
	@Test(priority = 7)
	public void clickReportTab() {
		WebElement rTab = (new WebDriverWait(wd, 10)).
				until(ExpectedConditions.elementToBeClickable(By.id("mat-tab-label-0-6")));
		rTab.click();
		(new WebDriverWait(wd, 10)).
		until(ExpectedConditions.urlMatches("https://assignforce-client.cfapps.io/reports"));
		Assert.assertEquals(wd.getCurrentUrl(),"https://assignforce-client.cfapps.io/reports");
	}
	
	@Test(priority = 8)
	public void testBatchReport() {
		WebElement reports = (new WebDriverWait(wd, 10)).
				until(ExpectedConditions.elementToBeClickable(By.cssSelector("#mat-expansion-panel-header-1 > span:nth-child(1) > mat-panel-title:nth-child(1)")));		
		reports.click();
		WebElement change = (new WebDriverWait(wd, 10)).
				until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#cdk-accordion-child-1 > div:nth-child(1)")));
		String visBefore = change.getCssValue("visibility");
		change = wd.findElement(By.cssSelector("#cdk-accordion-child-1 > div:nth-child(1)"));
		reports.click();
		String visAfter = change.getCssValue("visibility");
		boolean changed = (visBefore == "hidden" && visAfter == "visible") ||
				(visBefore == "visible" && visAfter == "hidden");
		Assert.assertEquals(changed, true);
	}
	
	@Test(priority = 9)
	public void testGradSummary() {
		WebElement sum = (new WebDriverWait(wd, 10)).
				until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"mat-expansion-panel-header-2\"]")));
		WebElement change = (new WebDriverWait(wd, 10)).
				until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"cdk-accordion-child-2\"]")));
		String visBefore = change.getCssValue("visibility");
		sum.click();
		change = wd.findElement(By.xpath("//*[@id=\\\"cdk-accordion-child-2\\\"]"));
		String visAfter = change.getCssValue("visibility");
		boolean changed = (visBefore == "hidden" && visAfter == "visible") ||
				(visBefore == "visible" && visAfter == "hidden");
		Assert.assertEquals(changed, true);
	}
	
	private static void createBatch() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wd.findElement(By.id("mat-select-3")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		wd.findElement(By.xpath("//*[@id=\'mat-option-1\']")).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		wd.findElement(By.id("mat-input-3")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		wd.findElement(By.className("mat-datepicker-toggle/button")).click();
		try {
	Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wd.findElement(By.xpath("//*[@id=\'cdk-accordion-child-2\']/div/form/div[5]/button[1]")).click();
		
	}
	
	private static void editBatch() {
		wd.findElement(By.xpath("//*[@id=\'cdk-accordion-child-3\']/div/form/div[2]/mat-form-field[1]/div/div[1]/div[1]/mat-datepicker-toggle/button")).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wd.findElement(By.xpath("//*[@id='mat-datepicker-2']/div[2]/mat-month-view/table/tbody/tr[4]/td[3]/div")).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wd.findElement(By.xpath("//*[@id=\'cdk-accordion-child-3\']/div/form/div[2]/mat-form-field[2]/div/div[1]/div[2]/mat-datepicker-toggle/button")).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wd.findElement(By.xpath("//*[@id=\'mat-datepicker-3\']/div[2]/mat-month-view/table/tbody/tr[4]/td[4]/div")).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wd.findElement(By.xpath("//*[@id=\'cdk-accordion-child-3\']/div/form/div[5]/button[1]")).click();
	}
	
	@Test(priority=10)
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
	
	@Test(priority = 11)
	public void testCreateBatch() {
		createBatch();
	}
	
	@Test (priority = 12)
	public void testEditBatch() {
		editBatch();
	}
	
	@Test(priority = 13)
	public void testMoveToLocationBar() {
		moveToLocationTab();
	}

	@Test(priority = 14)
	public void testCollapseLocation() {
		collapseFirstLocation();
	}

	@Test(priority = 15)
	public void testExpandLocation() {
		expandFirstLocation();
	}


	@Test (priority = 16)
	public void testLogout() {
		logout();
	}
	
	@Test(priority = 17)
	public static void trainerLogin() {
		openChrome();
		wd.get("https://assignforce-client.cfapps.io/login");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement elEmail = wd.findElement(By.name("email"));
		elEmail.click();
		WebElement elPass = wd.findElement(By.name("password"));
		WebElement elSubmit = wd.findElement(By.name("submit"));
		elEmail.sendKeys("test.trainer@revature.com");
		elPass.sendKeys("p@$$w0rd");
		elSubmit.click();
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String newUrl = wd.getCurrentUrl();
		Assert.assertEquals(newUrl, "https://assignforce-client.cfapps.io/overview");
	}
	
	@Test(priority = 18)
	public void clickTrainSettings() {
		WebElement trainers = (new WebDriverWait(wd, 10)).
				until(ExpectedConditions.elementToBeClickable(By.id("mat-tab-label-0-7")));
		trainers.click();
		(new WebDriverWait(wd, 10)).
		until(ExpectedConditions.urlMatches("https://assignforce-client.cfapps.io/settings"));
		Assert.assertEquals(wd.getCurrentUrl(),"https://assignforce-client.cfapps.io/settings");
	}
	
	@Test(priority = 19)
	public void resetTrainerSettings() {
		WebElement we = wd.findElement(By.id("mat-input-0"));
		String val1 = we.getAttribute("value");
		we.clear();
		we.sendKeys("2");
		we = wd.findElement(By.id("mat-input-1"));
		String val2 = we.getAttribute("value");
		we.clear();
		we.sendKeys("20");
		we = wd.findElement(By.id("mat-input-2"));
		String val3 = we.getAttribute("value");
		we.clear();
		we.sendKeys("30");
		we = wd.findElement(By.id("mat-input-3"));
		String val4 = we.getAttribute("value");
		we.clear();
		we.sendKeys("15");
		WebElement btn = wd.findElement(By.xpath("/html/body/app-root/app-settings/form/mat-grid-list/div/mat-grid-tile[19]/figure/button"));
		btn.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String newVal1 = wd.findElement(By.id("mat-input-0")).getAttribute("value");
		String newVal2 = wd.findElement(By.id("mat-input-1")).getAttribute("value");
		String newVal3 = wd.findElement(By.id("mat-input-2")).getAttribute("value");
		String newVal4 = wd.findElement(By.id("mat-input-3")).getAttribute("value");
		boolean resetWorked = val1.equals(newVal1) && val2.equals(newVal2) && 
				val3.equals(newVal3) && val4.equals(newVal4);
		Assert.assertEquals(resetWorked, true);
	}
	
	
	
	private static void logout() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		wd.findElement(By.id("logoutBTN")).click();
		wd.quit();
}
	
	public static void moveToLocationTab() {

		boolean expand = true;
		while (expand) {
			try {
				Thread.sleep(500);
				wd.findElement(By.id("mat-tab-label-0-2")).click();
				System.out.println("Finally moved!");
				expand = false;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("Nope");
			} catch (Exception e) {
				System.out.println("Naw");

			}
		}

	}

	public static void clickLocationBar() {

		boolean expand = true;
		while (expand) {
			try {
				Thread.sleep(500);
				wd.findElement(By.id("mat-expansion-panel-header-1")).click();
				System.out.println("Finally collapse");
				expand = false;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("Nope");
			} catch (Exception e) {
				System.out.println("Naw");

			}
		}
		while (!expand) {
			try {
				Thread.sleep(500);
				wd.findElement(By.id("mat-expansion-panel-header-1")).click();
				System.out.println("Finally expand");
				expand = true;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("Nope");
			} catch (Exception e) {
				System.out.println("Naw");

			}
		}

	}

	public static void expandFirstLocation() {
		wd.findElement(By.id("mat-expansion-panel-header-2")).click();
	}

	public static void collapseFirstLocation() {
		wd.findElement(By.id("mat-expansion-panel-header-2")).click();
	}

	public static void addLocation() {

		try {
			Thread.sleep(500);
			wd.findElement(By.className("cdk-overlay-container")).click();
//		d.findElement(By.id("cdk-overlay-23")).click();
//		d.findElement(By.id("mat-input-3")).sendKeys("FarAWAY");
			System.out.println("Finally Clicked Add Location");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}

}
	

	
	public static void openChrome() {
		  System.out.println("Here");
		  File f = new File("src/main/resources/chromedriver.exe");
		  System.setProperty("webdriver.chrome.driver", f.getAbsolutePath());
		  //ChromeOptions options = new ChromeOptions();
		  wd = new ChromeDriver();
		  
	  }

}

