package ecommerceTesting.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import ecommerceTesting.pageObjects.CheckOutSuccessPage;
import ecommerceTesting.pageObjects.CheckoutPaymentPage;
import ecommerceTesting.pageObjects.CheckoutShippingPage;
import ecommerceTesting.pageObjects.HomePage;
import ecommerceTesting.pageObjects.LoginPage;
import ecommerceTesting.pageObjects.MyAccountPage;
import ecommerceTesting.pageObjects.WomenJacketsPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	private ChromeOptions options;

	// Page Objects representing various pages in the application.
	public HomePage homePageObject;
	public LoginPage loginPageobject;
	public MyAccountPage myAccountPageobject;
	public WomenJacketsPage womenJacketsPageObject;
	public CheckoutShippingPage checkoutShippingPageObject;
	public CheckoutPaymentPage checkoutPaymentPageObject;
	public CheckOutSuccessPage checkoutSuccessPageObject;


	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties(); // Java util
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\ecommerceTesting\\Resources\\GlobalData.properties");
		prop.load(fis);

		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");
		if (browserName.contains("chrome")) {
			options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if (browserName.contains("headless")) {
				options.addArguments("headless");
			}
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		} else if (browserName.contains("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();

		} else if (browserName.contains("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else {
			System.out.println("Invalid browser name provided");
		}
		return driver;
	}

	@BeforeMethod(alwaysRun = true)
	public void setUp() throws IOException {
		driver = initializeDriver();
		// Initialize PageObjects
		homePageObject = new HomePage(driver);		
		loginPageobject = new LoginPage(driver);
		myAccountPageobject = new MyAccountPage(driver);
		womenJacketsPageObject = new WomenJacketsPage(driver);
		checkoutShippingPageObject = new CheckoutShippingPage(driver);
		checkoutPaymentPageObject = new CheckoutPaymentPage(driver);
		checkoutSuccessPageObject = new CheckOutSuccessPage(driver);
		
	}
	
	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+ "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+ "//reports//" + testCaseName + ".png";
	
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}

}
