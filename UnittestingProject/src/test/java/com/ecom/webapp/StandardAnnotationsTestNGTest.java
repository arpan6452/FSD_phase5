package com.ecom.webapp;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class StandardAnnotationsTestNGTest {
	
	@BeforeMethod
	void beforeMethod() {
		System.out.println("--- Before Test Method ---");
	}
	
	@AfterMethod
	void afterMethod() {
		System.out.println("--- After Test Method ---");
	}
	
	
	@BeforeClass
	void beforeClass() {
		System.out.println("--- Before class ---");
	}
	
	@AfterClass
	void afterClass() {
		System.out.println("--- After class ---");
	}
	
	@BeforeSuite
	void beforeSuite() {
		System.out.println("--- BeforeTest Suit class ---");
	}
	
	
	@AfterSuite
	void afterSuite() {
		System.out.println("--- BeforeTest Suit class ---");
	}
	
	
	@Test
	void testOne() {
		System.out.println(" Test 1 is executed !");
	}
	
	@Test
	void testTwo() {
		System.out.println(" Test 2 is executed !");
	}
}
