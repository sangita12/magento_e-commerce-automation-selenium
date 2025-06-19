package ecommerceTesting.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecommerceTesting.AbstractComponents.AbstractComponent;

public class CheckOutSuccessPage extends AbstractComponent {
	WebDriver driver;

	public CheckOutSuccessPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//*[contains(text(), 'Thank you for your purchase!')]")
	WebElement thankYouMessage;
	@FindBy(xpath = "//*[contains(text(), 'Continue Shopping')]")
	WebElement continueShoppingButton;
	@FindBy(xpath = "//*[contains(text(), 'Print receipt')]")
	WebElement printReceiptLink;
	

	String expectedUrl = "https://magento.softwaretestingboard.com/checkout/onepage/success/";
	
	public boolean isOnCheckoutSuccessPage() {
		waitForUrl(expectedUrl);
		String actualUrl = driver.getCurrentUrl();
		return actualUrl.equals(expectedUrl);
	}
	
	public String getSuccessfulPurchaseMessage() {
		waitForElementToAppear(thankYouMessage);
		System.out.println(thankYouMessage.getText());
		return thankYouMessage.getText();
	}
	public String isContinueShoppingButtonPresent() {
		waitForElementToAppear(continueShoppingButton);
		System.out.println(continueShoppingButton.getText());
		return continueShoppingButton.getText();
	}
	public String isPrintReceiptLinkPresent() {
		waitForElementToAppear(printReceiptLink);
		System.out.println(printReceiptLink.getText());
		return printReceiptLink.getText();
	}

}
