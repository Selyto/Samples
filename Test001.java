package testcases;

import org.testng.annotations.Test;

import baseTest.BaseTest;
import pageobjects.HomePage;

public class Test001 extends BaseTest {

	
	@Test
	public void testProductDetails() {
		HomePage hpage = navigateTo();
		hpage.searchProduct("Books", "music");
		hpage.printProductDetails();
	}
	
}
