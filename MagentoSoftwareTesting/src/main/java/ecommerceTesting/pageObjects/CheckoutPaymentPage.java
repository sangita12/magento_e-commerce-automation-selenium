package ecommerceTesting.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecommerceTesting.AbstractComponents.AbstractComponent;

public class CheckoutPaymentPage extends AbstractComponent {
	WebDriver driver;

	public CheckoutPaymentPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[@title='Place Order']")
	WebElement placeOrderButton;
	
	@FindBy(xpath = "//div[@class='minicart-items-wrapper overflowed']//span[@class='price']")
	List<WebElement> productsPrice;
	
	@FindBy(xpath = "//table[@class='data table table-totals']//tr[@class='grand totals']//span[@class='price']")
	WebElement orderTotal;
	
	@FindBy(xpath = "//table[@class='data table table-totals']//tr[@class='totals discount']//span[@class='price']")
	List<WebElement> discountTotal;
	
	@FindBy(xpath = "//*[@class='ship-to']//*[@class='shipping-information-content']")
	WebElement shipTo;
	
	@FindBy(xpath = "//div[@class='billing-address-same-as-shipping-block field choice']//input[@type='checkbox']")
	WebElement shippingAddressCheckBox;
	
	By loader = By.cssSelector("#checkout-loader");

	// Scroll to the element
	public void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
	}

	// Method to compare the current URL with the expected URL

	String expectedUrl = "https://magento.softwaretestingboard.com/checkout/#payment";

	public Boolean isOnPaymentPage() {
		waitForElementToInvisibleBy(loader);
		waitForUrl(expectedUrl);
		String actualUrl = driver.getCurrentUrl();
		return actualUrl.equals(expectedUrl);
	}

	public void deSelectCheckBox() {
		if (shippingAddressCheckBox.isSelected()) {
			shippingAddressCheckBox.click();
		}

	}

	double cartTotal = 0;
	double discount;

	public double getSubTotal() {

		// Cart subtotal
		for (WebElement ele : productsPrice) {
			waitForElementToAppear(ele);			
			cartTotal = cartTotal + Double.parseDouble(ele.getText().split("\\$")[1].trim());
		}
		System.out.println("Cart Total: " + cartTotal);
		return cartTotal;
	}

	public double getDiscount() throws InterruptedException {
		// waitForElementToAppear(discountTotal);
		// Check if discount row present
		Thread.sleep(1000);
		if (!discountTotal.isEmpty()) {
			for (WebElement ele : discountTotal) {
				discount = Double.parseDouble(ele.getText().split("\\$")[1].trim());
			}
			System.out.println("Discount price: " + discount);

		} else {
			System.out.println("No discount applied.");

		}
		return discount;
	}

	public double getOrderTotal() {
		// Order Total
		waitForElementToAppear(orderTotal);
		double orderTotalAmount = Double.parseDouble(orderTotal.getText().split("\\$")[1].trim());
		System.out.println("Order Total: " + orderTotalAmount);
		return orderTotalAmount;

	}

	public boolean isOrderTotalCorrect() throws InterruptedException {
		waitForElementToInvisibleBy(loader);
		double subTotalPrice = getSubTotal();
		double discountPrice = getDiscount();
		double orderTotal = getOrderTotal();
		double expectedTotal = subTotalPrice - discountPrice;
		System.out.println("Expected Total: " + expectedTotal);
		// Math.abs(...) ensures it works even if the actual is slightly above or below.
		// 0.01 is the tolerance threshold (you can adjust it if needed).
		return Math.abs(orderTotal - expectedTotal) < 0.01;
	}

	public String getShipToinformation() {
		scrollToElement(shipTo);
		waitForElementToAppear(shipTo);
		String shipAddress = shipTo.getText();
		System.out.println(shipAddress);
		return shipAddress;
	}

	public void clickOnPlaceOrder() {
		waitForElementToInvisibleBy(loader);
		waitForElementToAppear(placeOrderButton);
		placeOrderButton.click();
	}

}
