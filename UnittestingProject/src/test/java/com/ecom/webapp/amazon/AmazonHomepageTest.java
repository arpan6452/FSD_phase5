package com.ecom.webapp.amazon;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class AmazonHomepageTest {

	final String siteURL = "https://www.amazon.in/";
	final String driverPath = "driver/chromedriver.exe";
	WebDriver driver;

	@BeforeMethod
	void setUp() {
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.get(siteURL);
	}
	
	@AfterMethod
	void tearDown() {
		driver.close();
	}
	
	@Test
	void testForAmazonHomepage() {
		String expectedTitle = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
		String actualTitle = driver.getTitle();
		assertEquals(expectedTitle, actualTitle);
	}
	
	@Test
	void testForAmazonSourceURL() {
		assertEquals(siteURL, driver.getCurrentUrl());
	}
	
	@Test
	void testForAmazonSourceSignIn() {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		// actions
		wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-link-accountList"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("ap_email"))).sendKeys("9836413961");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("continue"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("ap_password"))).sendKeys("arpan@amazon12");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("signInSubmit"))).click();
		String isLoggedIn = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-link-accountList-nav-line-1"))).getText();		
		
		assertEquals(isLoggedIn, "Hello, arpan");
	}
	
	@Test
	void testForAmazonSerachProduct() {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		// actions
		wait.until(ExpectedConditions.elementToBeClickable(By.id("twotabsearchtextbox"))).sendKeys("table");
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
		//wait.until(ExpectedConditions.elementToBeClickable(By.id("s-main-slot s-result-list s-search-results sg-row"))).sendKeys(Keys.RETURN);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'s-result-item') and @data-index='2']//img"))).click();
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs2.get(1));
	    wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-button"))).click();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(), 'Added to Cart')]")));
	    driver.close();
	    driver.switchTo().window(tabs2.get(0));	    	    	  
	}
	

}
