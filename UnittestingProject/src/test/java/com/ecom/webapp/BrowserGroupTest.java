package com.ecom.webapp;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BrowserGroupTest {
	
	final String amazonURL = "https://www.amazon.in/";
	final String facebookURL ="https://www.facebook.com/";
	
	final String chromePath = "driver/chromedriver.exe";
	final String firefoxPath ="driver/geckodriver.exe";
	
	WebDriver driverOne;
	WebDriver driverTwo;
		
	// test group chrome
	@Test(groups = "ChromeOnly")
	public void lauchChromeTest() {		
		System.setProperty("webdriver.chrome.driver", chromePath);
		driverOne = new ChromeDriver();
		driverOne.get(amazonURL);
	}
	
	@Test(groups = "ChromeOnly", dependsOnMethods = "lauchChromeTest", priority=0 )
	public void testAmazonHomePageTitle() {
		String expected = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
		Assert.assertEquals(expected, driverOne.getTitle());
	}
	
	@Test(groups = "ChromeOnly", dependsOnMethods = "lauchChromeTest" , priority=1)
	public void testAmazonSourceURL() {
		Assert.assertEquals(amazonURL, driverOne.getCurrentUrl());
		driverOne.close();
	}
	
	// test group firefox
	@Test(groups = "FirefoxOnly")
	public void lauchFirefoxTest() {		
		System.setProperty("webdriver.gecko.driver", firefoxPath);
		driverTwo = new FirefoxDriver();
		driverTwo.get(facebookURL);
	}
	
	@Test(groups = "FirefoxOnly", dependsOnMethods = "lauchFirefoxTest", priority=0)
	public void testFaceBookHomePage() {
		String expected ="Facebook - Login or sign up";
		Assert.assertEquals(driverTwo.getTitle(), driverTwo.getTitle());
		driverTwo.close();
	
	}
	
	@Test(groups = "FirefoxOnly", dependsOnMethods = "lauchFirefoxTest", priority=1)
	public void testFaceLoginPage() throws InterruptedException {
		driverTwo.findElement(By.id("email")).sendKeys("xyz@gmail.com");
		driverTwo.findElement(By.id("pass")).sendKeys("admin@123");
		driverTwo.findElement(By.name("login")).submit();
		driverTwo.close();
	}
}
