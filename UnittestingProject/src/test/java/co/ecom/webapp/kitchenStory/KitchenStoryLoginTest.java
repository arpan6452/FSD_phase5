package co.ecom.webapp.kitchenStory;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;

public class KitchenStoryLoginTest {

	final String siteURL = "http://kstory-simplilearn.s3-website.us-east-2.amazonaws.com/";
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
	public void invalidUserCredsTest() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		// actions
		driver.findElement(By.xpath("//li[contains(@class,'sign-in')]")).click();// sendKeys("xyz@gmail.com");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("emailId"))).sendKeys("arpan@gmail.com");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("password"))).sendKeys("P@ssword");
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		
		//delay
		Thread.sleep(10000);
		List<WebElement> error = wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//small[text()=' Incorrect Credetials!! ']"), 1));
		assertEquals(" Incorrect Credetials!! ", error.get(0).getText());
	}
	
	@Test
	public void validUserCredsTest() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		// actions
		driver.findElement(By.xpath("//li[contains(@class,'sign-in')]")).click();// sendKeys("xyz@gmail.com");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("emailId"))).sendKeys("arpan@gmail.com");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("password"))).sendKeys("P@ssword");
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		
		//delay
		Thread.sleep(10000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(@class,'sign-in')]")));		
	}
}
