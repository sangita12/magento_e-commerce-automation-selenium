package ecommerceTesting.pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ecommerceTesting.AbstractComponents.AbstractComponent;

public class LoginPage extends AbstractComponent{
	
	WebDriver driver;
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(id = "email")
	WebElement emailLoginPageField;
	
	@FindBy(id = "pass")
	WebElement passwordLoginPageField;
	
	@FindBy(xpath = "(//*[@id='send2']/span)[1]")
	WebElement signInButtonLoginPage;
	@FindBy(xpath = "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")
	WebElement errorMessage;
	By errorMessageLocator  = By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']");
	
	public void loginApplication(String email, String password) {
		emailLoginPageField.sendKeys(email);
		passwordLoginPageField.sendKeys(password);
		signInButtonLoginPage.click();
		
	}
	
	public boolean isOnLoginPage() {
		String expectedLoginPage = "https://magento.softwaretestingboard.com/customer/account/login/referer/aHR0cHM6Ly9tYWdlbnRvLnNvZnR3YXJldGVzdGluZ2JvYXJkLmNvbS8%2C/";
		String actualUrl = driver.getCurrentUrl();
		
		return actualUrl.equalsIgnoreCase(expectedLoginPage);
		
		
	}
	
	public String getErrorMessage() {
	//	waitForElementToAppear(errorMessage);
		//return errorMessage.getText();
		
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        try {
	            // Wait for the fresh element to appear
	            wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator));

	            // Re-locate it before interacting (avoids stale element)
	            WebElement error = driver.findElement(errorMessageLocator);
	            return error.getText();
	        } catch (StaleElementReferenceException e) {
	            // Retry once in case of stale element
	            WebElement error = driver.findElement(errorMessageLocator);
	            return error.getText();
	        }
	    }
	
	
}
