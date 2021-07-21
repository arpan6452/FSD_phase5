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

public class KitchenStoryRegistrationTest {

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
		WebDriverWait wait = new WebDriverWait(driver, 5);
		// actions
		driver.findElement(By.xpath("//li[contains(@class,'sign-up')]")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("email"))).sendKeys("");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h5[text()='Sign Up']/..//following-sibling::form//input[@id='password']"))).sendKeys("P@ssword");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("address1"))).sendKeys("105, Ideal Star");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("address2"))).sendKeys("Rajarhat Main Road");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("city"))).sendKeys("kolkata");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("state"))).sendKeys("West Bengal");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("pincode"))).sendKeys("560098");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Register']"))).click();
		
		//delay
		Thread.sleep(10000);
		List<WebElement> error = null;
		String log ="";
		try {
			error = wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//small[text()=' Incorrect Credetials!! ']"), 1));
			log = error.get(0).getText();
		}catch (Exception e) {
				// TODO: handle exception
			}
		assertEquals(" Incorrect Credetials!! ", log);
	}
	
	@Test
	public void validUserCredsTest() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		// actions
		driver.findElement(By.xpath("//li[contains(@class,'sign-up')]")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("email"))).sendKeys("arpan@gmail.com");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h5[text()='Sign Up']/..//following-sibling::form//input[@id='password']"))).sendKeys("P@ssword");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("address1"))).sendKeys("105, Ideal Star");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("address2"))).sendKeys("Rajarhat Main Road");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("city"))).sendKeys("kolkata");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("state"))).sendKeys("West Bengal");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("pincode"))).sendKeys("560098");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Register']"))).click();
		
		//delay
		Thread.sleep(10000);
		List<WebElement> error = null;
		String log ="";
		try {
			error = wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//small[text()=' Incorrect Credetials!! ']"), 1));
			log = error.get(0).getText();
		}catch (Exception e) {
				// TODO: handle exception
			}
		assertEquals(" Incorrect Credetials!! ", log);
	}
}
