package ecommerceTesting.pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ecommerceTesting.AbstractComponents.AbstractComponent;

public class WomenJacketsPage extends AbstractComponent {
	WebDriver driver;
	WebElement addToCartButton;

	public WomenJacketsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".item.product.product-item")
	List<WebElement> jacketProducts;

	@FindBy(css = "#option-label-size-143-item-167")
	WebElement jackshirtSizeOptionXL;

	@FindBy(xpath = "//*[contains(text(), 'You added')]")
	WebElement successMessage;

	@FindBy(xpath = "//a[@class='action showcart']//span[@class='counter qty']")
	WebElement cartIcon;

	@FindBy(xpath = "//button[@id='top-cart-btn-checkout']")
	WebElement checkoutButton;

	By productsBy = By.cssSelector(".item.product.product-item");
	By fullProductsBy = By.cssSelector(".products.wrapper.grid.products-grid");

	By loader = By.cssSelector("#checkout-loader");

	public void scrollToElement(WebElement ele) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", ele);
		Thread.sleep(500);
	}

	public void scrollToTopPage() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,0);");
	}

	public List<WebElement> getProductLists() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		System.out.println(jacketProducts);
		return jacketProducts;
	}

	public WebElement getProductByName(String productName) throws InterruptedException {
		List<WebElement> products = getProductLists();

		WebElement product = products.stream().filter(prod -> {
			try {
				WebElement nameElement = prod.findElement(By.xpath(".//a[@class='product-item-link']"));
				return nameElement.getText().trim().equalsIgnoreCase(productName);
			} catch (Exception e) {
				return false;
			}
		}).findFirst().orElseThrow(() -> new RuntimeException("Product not found: " + productName));

		scrollToElement(product);
		new Actions(driver).moveToElement(product).build().perform();

		// Size & color selections
		try {
			WebElement size = waitForElementToBeClickable(
					product.findElement(By.cssSelector("#option-label-size-143-item-167")));
			size.click();

			WebElement color = waitForElementToBeClickable(
					product.findElement(By.cssSelector("#option-label-color-93-item-57")));
			color.click();
		} catch (Exception e) {
			throw new RuntimeException("Size or Color selection failed for product: " + productName, e);
		}

		return product;
	}

	public void addToCart(String productName) throws InterruptedException {
		WebElement prod = getProductByName(productName);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement addToCartBtn = wait.until(
				ExpectedConditions.elementToBeClickable(prod.findElement(By.cssSelector("button.action.tocart"))));

		System.out.println("Displayed: " + addToCartBtn.isDisplayed());
		addToCartBtn.click();

		// Scroll to the top of the page
		scrollToTopPage();

		getSuccessMessage();

		clickToProceedToCheckout();

	}

	public String getSuccessMessage() {
		waitForElementToAppear(successMessage);
		String message = successMessage.getText();
		System.out.println(message);
		return message;
	}

	public void clickToProceedToCheckout() {
		waitForElementToBeClickable(cartIcon);
		cartIcon.click();

		waitForElementToBeClickable(checkoutButton);
		checkoutButton.click();
	//	waitForElementToAppearBy(loader);
	}

}
