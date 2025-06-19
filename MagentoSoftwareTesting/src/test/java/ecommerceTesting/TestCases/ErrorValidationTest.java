package ecommerceTesting.TestCases;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import ecommerceTesting.TestComponents.BaseTest;

public class ErrorValidationTest extends BaseTest{
	@Test
	public void loginErrorvalidation() {
		homePageObject.navigateToHomePage();
		homePageObject.clickOnSignIn();
		loginPageobject.loginApplication("Jane@gmail.com", "Test@12");
				
		String expectedMessage = "Th account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.";
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,0);");
		
		Assert.assertEquals(expectedMessage, loginPageobject.getErrorMessage());

	}

}
