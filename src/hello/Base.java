package hello;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base {

	// the below method will launch the browser and return WebDriver
	public static WebDriver launchBrowser() {
		System.setProperty("webdriver.chrome.driver",
				new File("." + File.separator + "driver" + File.separator + "chromedriver.exe").getAbsolutePath());
		return new ChromeDriver();
	}

	// the below method will navigate to a url
	public static void navigateTo(WebDriver driver, String URL) throws IOException {
		driver.get(URL);
	}

	// javascript executor variable returning method
	public static JavascriptExecutor returnJSObject(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		return js;
	}
	// javascript module ends

	// findElement method

	public static WebElement FindElementByXpath(WebDriver driver, String xpath) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver.findElement(By.xpath(xpath));
	}

	// findelement module ends
	// login
	public static void login(WebDriver driver) throws IOException {
		takeScreenshot(driver, "./Screenshot/successful5.jpeg");

		WebElement linkSignIn = FindElementByXpath(driver, "//a[@href='https://www.coolblue.nl/en/login']");

		linkSignIn.click();

		WebElement txtUserName = FindElementByXpath(driver, "//input[@id='header_menu_emailaddress']");
		txtUserName.sendKeys("giridharasugan@gmail.com");

		WebElement txtPassword = FindElementByXpath(driver, "//input[@id='header_menu_password']");
		txtPassword.sendKeys("Ram786");

		WebElement btnLogin = FindElementByXpath(driver, "//button[@class='button button--order button--full-width']");
		btnLogin.click();

		takeScreenshot(driver, "./Screenshot/successful1.jpeg");

	}

	// login module ends
	// take screenshot
	public static void takeScreenshot(WebDriver driver, String filePath) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(filePath));
	}

	// take Screenshot module ends
	// search product
	public static void searchProduct(WebDriver driver) throws AWTException, IOException {
		WebElement txtSearchBox = FindElementByXpath(driver, "//input[@id='search_query']");
		txtSearchBox.sendKeys("lenovo laptops");

		Robot r = new Robot();

		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		takeScreenshot(driver, "./Screenshot/successful10.jpeg");

	}
	// search product ends

	// add to cart
	public static void addToCart(WebDriver driver) throws IOException, InterruptedException {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement linkProduct = FindElementByXpath(driver,
				"//div[@class='is-hidden is-visible-from-size-m js-desktop-order-block']//button[@type='submit']");

		linkProduct.click();

		Wait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='grid-section-xs--gap-2' and .//*[contains(text(),'ready to order')]]")));
		WebElement btnAddToCart = FindElementByXpath(driver, "//div[@class='grid-section-xs--gap-2' and .//*[contains(text(),'ready to order')]]");
		btnAddToCart.click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='button button--order js-shoppingcart-submit-button' and .//*[contains(text(), 'ready to order')]][1]")));
		WebElement btnImReadyToOrder = FindElementByXpath(driver, "//button[@class='button button--order js-shoppingcart-submit-button' and .//*[contains(text(), 'ready to order')]][1]");
		btnImReadyToOrder.click();
		
		Thread.sleep(2000);
		takeScreenshot(driver, "./Screenshot/successfuladdcart.jpeg");

	}

	// add to cart module ends
	// checkout
	public static void checkout(WebDriver driver) throws IOException {
		WebElement btnCheckout = FindElementByXpath(driver,
				"//button[@class='button button--order']");
		btnCheckout.click();
		takeScreenshot(driver, "./Screenshot/successfuladdcart.jpeg");

	}

	public static void prodclick(WebDriver driver) throws IOException {
		WebElement productclick = FindElementByXpath(driver, "//h3[@class='text-color--link'][1]");
		productclick.click();
	}

	// checkout module ends
	public static void main(String[] args) throws IOException, InterruptedException, AWTException {
		WebDriver driver = launchBrowser();

		driver.manage().window().maximize();

		String URL = "https://www.coolblue.nl/en";

		navigateTo(driver, URL);

		// Thread.sleep(5000);

		login(driver);

		searchProduct(driver);

		prodclick(driver);

		addToCart(driver);

		checkout(driver);

		driver.quit();

	}
}
