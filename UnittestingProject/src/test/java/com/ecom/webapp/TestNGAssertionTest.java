package com.ecom.webapp;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestNGAssertionTest {

	// hard assert : Hard Assert throws an AssertException immediately when an
	// assert statement fails and test suite continues with next @Test
	@Test
	void hardAsserTest() {
		System.out.println("--- Hard Assert methods was started ----");
		// assertEquals(true, false); // Assertion Error.
		System.out.println("--- Hard Assert is completed. ----");
		assertTrue(true);
	}

	// soft assert :- Soft Assert collects errors during @Test.
	// Soft Assert does not throw an exception when an assert fails
	
	@Test
	void softAssert() {
		SoftAssert softAssert = new SoftAssert();
		System.out.println("--- Soft Assert methods was started ----");
		softAssert.assertEquals(true, false);
		System.out.println("--- Soft Assert is completed. ----");
		
	}
}
