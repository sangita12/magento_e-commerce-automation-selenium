package ecommerceTesting.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecommerceTesting.AbstractComponents.AbstractComponent;

public class HomePage extends AbstractComponent{
	
	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@class='panel header']//a[contains(text(),'Sign In')]")
	WebElement signInLink;
	
	@FindBy(xpath = "//div[@class='panel header']//a[contains(text(),'Create an Account')]")
	WebElement createAccountLink;
	
	@FindBy(xpath="//a[@id='ui-id-4']/span[2]")
	WebElement womenNavigationMenuLink;
	
	@FindBy(xpath="//*[@id='ui-id-9']/span[2]")
	WebElement topsWomenCategory;
	
	@FindBy(xpath="//*[@id='ui-id-11']")
	WebElement jacketsWomenCategory;
	
	
	
	public void navigateToHomePage() {
		driver.get("https://magento.softwaretestingboard.com/");
	}
	
	public void clickOnSignIn() {
		waitForElementToAppear(signInLink);
		signInLink.click();
		}
	
	public void hoverToWomenMenu() {
		Actions action = new Actions(driver);
		action.moveToElement(womenNavigationMenuLink).build().perform();
	}
	
	public void hoverToWomenTopMenu() {
		Actions action = new Actions(driver);
		action.moveToElement(topsWomenCategory).perform();
	}
	
	public void clickOnJackets() {
		Actions action = new Actions(driver);
		action.moveToElement(jacketsWomenCategory).click().perform();
	}
	
	
	
	
	
	
	

}
