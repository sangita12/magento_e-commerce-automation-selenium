package ecommerceTesting.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import ecommerceTesting.AbstractComponents.AbstractComponent;

public class CheckoutShippingPage extends AbstractComponent {

	private WebDriver driver;

	public CheckoutShippingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@name='company']")
	WebElement company;
	@FindBy(xpath = "//input[@name='street[0]']")
	WebElement streetAddress;
	@FindBy(xpath = "//input[@name='city']")
	WebElement city;
	@FindBy(xpath = "//select[@name='region_id']")
	WebElement stateDropdown;
	@FindBy(xpath = "//input[@name='postcode']")
	WebElement zipCode;
	@FindBy(xpath = "//select[@name='country_id']")
	WebElement countryDropdown;
	@FindBy(xpath = "//input[@name='telephone']")
	WebElement phoneNumber;
	@FindBy(xpath = "(//input[@type='radio'])[1]")
	WebElement shippingMethod;
	@FindBy(css = ".button.action.continue.primary")
	WebElement nextButton;
	@FindBy(xpath = "//div[@class='title']")
	WebElement orderSummaryLink;
	@FindBy(xpath = "//span[contains(text(), 'View Details')]")
	WebElement viewDetails;
	@FindBy(xpath = "//strong[@class='product-item-name']")
	List<WebElement> productNames;
	@FindBy(xpath = "shipping-address-items")
	List<WebElement> shippingAddresForm;

	By loader = By.cssSelector("#checkout-loader");

	public void enterCompany(String companyName) {
		waitForElementToAppear(company);
		company.sendKeys(companyName);
	}

	public void enterStreetAdress(String address) {
		waitForElementToAppear(streetAddress);
		streetAddress.sendKeys(address);
	}

	public void enterCity(String cityName) {
		waitForElementToAppear(city);
		city.sendKeys(cityName);
	}

	public void enterZipCode(String ZipCode) {
		waitForElementToAppear(zipCode);
		zipCode.sendKeys(ZipCode);
	}

	public void enterPhone(String phone) {
		waitForElementToAppear(phoneNumber);
		phoneNumber.sendKeys(phone);
	}

	public void selectState(String state) {
		Select select = new Select(stateDropdown);
		select.selectByVisibleText(state);
	}

	public void selectCountry(String countryName) {
		Select select = new Select(countryDropdown);
		select.selectByVisibleText(countryName);

	}

	public void orderSummary() {
		waitForElementToAppear(orderSummaryLink);
		orderSummaryLink.click();
	}

	public Boolean verifyOrderSummaryCheck(String productName) {
		orderSummary();
		Boolean match = productNames.stream().anyMatch(pName -> pName.getText().equalsIgnoreCase(productName));
		return match;
	}

	private String expectedUrl = "https://magento.softwaretestingboard.com/checkout/#shipping";

	public Boolean isOnCheckoutPage() {
		// Get the actual URL
		waitForElementToInvisibleBy(loader);
		waitForUrl(expectedUrl);
		String actualUrl = driver.getCurrentUrl();
		// Compare the actual URL with the expected URL
		return actualUrl.equals(expectedUrl);
	}

	// Checks if shipping form is visible – it's a first-time shopper or no saved
	// address
	public boolean isShippingFormVisible() {
		return shippingAddresForm.size() > 0;
	}

	public void submitShippingDetails(String companyName, String address, String city, String state, String zipcode,
			String country, String phone) {
		waitForElementToInvisibleBy(loader);
		enterCompany(companyName);
		enterStreetAdress(address);
		enterCity(city);
		selectState(state);
		enterZipCode(zipcode);
		selectCountry(country);
		enterPhone(phone);
		shippingMethod.click();
		orderSummary();
		nextButton.click();
	}

	public void submitIshippingFormViible() {
		waitForElementToInvisibleBy(loader);
		waitForElementToAppear(shippingMethod);
		shippingMethod.click();
		orderSummary();
		waitForElementToAppear(nextButton);
		nextButton.click();
		waitForElementToInvisibleBy(loader);
	}

}
