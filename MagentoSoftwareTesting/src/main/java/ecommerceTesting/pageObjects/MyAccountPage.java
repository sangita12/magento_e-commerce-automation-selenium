package ecommerceTesting.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecommerceTesting.AbstractComponents.AbstractComponent;

public class MyAccountPage extends AbstractComponent {

	WebDriver driver;
	String homePageUrl = "https://magento.softwaretestingboard.com/";

	public MyAccountPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//span[@class='logged-in'])[1]")
	WebElement loggedInMessage;
	


	public Boolean isOnHomePage() {
		String actualUrl = driver.getCurrentUrl();
		return actualUrl.equalsIgnoreCase(homePageUrl);
	}

	public String checkWelcomeMessage() throws InterruptedException {
		Thread.sleep(1000);
		String actualLoggedInMessage = waitForElementToBeClickable(loggedInMessage).getText();
		System.out.println(actualLoggedInMessage);
		return actualLoggedInMessage;

	}


}
