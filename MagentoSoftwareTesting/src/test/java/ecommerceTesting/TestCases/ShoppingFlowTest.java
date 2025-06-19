package ecommerceTesting.TestCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ecommerceTesting.Data.DataReader;
import ecommerceTesting.TestComponents.BaseTest;

public class ShoppingFlowTest extends BaseTest {

	// Test 1: End-to-End Test of the Entire Flow
	@Test(dataProvider = "getData")
	public void addProductsToCart(HashMap<String, String> inputData) throws InterruptedException {
		homePageObject.navigateToHomePage();
		homePageObject.clickOnSignIn();
		// Assert User is navigated to 'Customer LoginPage' Page.
		Assert.assertTrue(loginPageobject.isOnLoginPage(), "User is not on 'Customer LoginPage' page.");

		loginPageobject.loginApplication(inputData.get("email"), inputData.get("password"));

		// Assert that the User is redirected to the 'HomePage' after successful login.
		Assert.assertTrue(myAccountPageobject.isOnHomePage(),
				"The user was not redirected to the 'HomePage' page after successful loginPage.");

		// Assert Welcome message and the customer Full Name on User Account Menu
		// Element.
		Assert.assertEquals(myAccountPageobject.checkWelcomeMessage(), "Welcome, " + inputData.get("userName") + "!",
				"The welcome message and customer name on the User Account Menu do not match the expected values.");

		// Hover to Women menu
		homePageObject.hoverToWomenMenu();
		homePageObject.hoverToWomenTopMenu();
		homePageObject.clickOnJackets();

		womenJacketsPageObject.addToCart(inputData.get("productName"));

		// Assert that the User is redirected to the 'Checkout Shipping' page.
		Assert.assertTrue(checkoutShippingPageObject.isOnCheckoutPage(),
				"The user was not redirected to the 'Checkout shipping' page.");

		// Form is visible – it's a first-time shopper or no saved address
		if(checkoutShippingPageObject.isShippingFormVisible()) {
			checkoutShippingPageObject.submitShippingDetails(inputData.get("company"), inputData.get("street"),
					inputData.get("city"), inputData.get("state"), inputData.get("zipcode"), inputData.get("country"),
					inputData.get("phonenumber"));
			
		} else {
			System.out.println("Using previously saved shipping address.");
			checkoutShippingPageObject.submitIshippingFormViible();
		}
		
		System.out.println("hello ooooo");
		

	//	Boolean match = checkoutShippingPageObject.verifyOrderSummaryCheck(inputData.get("productName"));
	//	Assert.assertTrue(match, "Your product not present in the cart");

		// Assert that the User is redirected to the 'Checkout Payment' page.
		Assert.assertTrue(checkoutPaymentPageObject.isOnPaymentPage(),
				"The user was not redirected to the 'Checkout Payment' page.");

		// Assert the Order and Expected price are match.
		Assert.assertTrue(checkoutPaymentPageObject.isOrderTotalCorrect(),
				"The 'Order Total' price does not match with the 'Expected Total' price");

		String shipAddress = checkoutPaymentPageObject.getShipToinformation();

		String expectedShipAddress = inputData.get("userName") + "\n" + inputData.get("street") + "\n"
				+ inputData.get("city") + ", " + inputData.get("state") + " " + inputData.get("zipcode") + "\n"
				+ inputData.get("country") + "\n" + inputData.get("phonenumber");
		System.out.println(expectedShipAddress);

		// Assert Shipping Address is as per the requirement
		Assert.assertEquals(shipAddress, expectedShipAddress, "Shipping address not correct.");
	//	checkoutPaymentPageObject.deSelectCheckBox();
		
		// Click on 'Place Order' button
		checkoutPaymentPageObject.clickOnPlaceOrder();
		
	
		// Assert that the User is directed to the 'Checkout Success' page.
		Assert.assertTrue(checkoutSuccessPageObject.isOnCheckoutSuccessPage(),
				"The user was not redirected to the 'Checkout Success' page.");
		
	
		
		// Assert successful message
		Assert.assertEquals(checkoutSuccessPageObject.getSuccessfulPurchaseMessage(),
				"Thank you for your purchase!",
				"The successful purchase message does NOT match the expected message.");
		
		//Assert 'Continue Shopping' button is present.
		Assert.assertEquals(checkoutSuccessPageObject.isContinueShoppingButtonPresent(),
				"Continue Shopping",
				"The 'Continue Shopping' is not present.");
		
		
		//Assert 'Print receipt' link is present.
				Assert.assertEquals(checkoutSuccessPageObject.isPrintReceiptLinkPresent(),
						"Print receipt",
						"The 'Print receipt' is not present.");

	}

	@DataProvider
	public Object[][] getData() throws IOException {
		DataReader dataReaderObject = new DataReader();

		List<HashMap<String, String>> loginData = dataReaderObject.getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\ecommerceTesting\\Data\\ShoppingData.json");
		return new Object[][] { { loginData.get(0) } };

	}
}
