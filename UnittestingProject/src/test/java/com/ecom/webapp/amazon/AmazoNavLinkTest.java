package com.ecom.webapp.amazon;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

public class AmazoNavLinkTest {

	final String siteURL = "https://www.amazon.in/";
	final String driverPath = "driver/chromedriver.exe";
	WebDriver driver;

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.get(siteURL);
	}

	@AfterMethod
	public void afterMethod() {
		driver.close();
	}

	@Test
	public void testMobileLinkNavigation() {
		// find mobile
		WebElement mobileLink = driver.findElement(By.cssSelector("#nav-xshop > a:nth-child(3)"));
		// test web element exist
		assertTrue(mobileLink.isDisplayed());
		assertTrue(mobileLink.isEnabled());
		// click
		mobileLink.click();
		String expected = "Mobile Phones: Buy New Mobiles Online at Best Prices in India | Buy Cell Phones Online - Amazon.in";
		assertEquals(expected, driver.getTitle());
	}

	@Test
	void testFashionLinkNavigation() {
		WebElement fashinLink = driver.findElement(By.cssSelector("#nav-xshop > a:nth-child(4)"));
		// test web element exist
		assertTrue(fashinLink.isDisplayed());
		assertTrue(fashinLink.isEnabled());
		// click
		fashinLink.click();
		assertEquals("Amazon Fashion: Clothing, Footwear and Accessories online for Men, Women and Kids", driver.getTitle());
	}
}
