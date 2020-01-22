package hello;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
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

public class Airmalta {
	static WebDriver driver;
	static Wait wait;

	@BeforeClass
	public static void Beforeclass() {

		// Configure Browers
		System.setProperty("webdriver.chrome.driver",
				new File("." + File.separator + "driver" + File.separator + "chromedriver.exe").getAbsolutePath());

		driver = new ChromeDriver();
		driver.manage().window().maximize();

		wait = new WebDriverWait(driver, 60);
	}

	@Test
	public void bookFlight() throws InterruptedException {
		navigateToHomePage();
		selectFromLocation();
		Thread.sleep(3000);
//		selectToLocation();
		Thread.sleep(3000);
		selectDepartureDate();
		Thread.sleep(3000);
		selectReturnDate();
		Thread.sleep(3000);
		findFlights();
	}

	public void navigateToHomePage() throws InterruptedException {
		// launching Browers
		driver.get("https://www.airmalta.com/");
		Thread.sleep(3000);
		Assert.assertEquals("Malta Flights | Book Your Flights to Malta with Air Malta", driver.getTitle());
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='cc_btn cc_btn_accept_all']")));
		driver.findElement(By.xpath("//a[@class='cc_btn cc_btn_accept_all']")).click();
	}

	public void selectFromLocation() {
		String fromId = "fromAirports_txtSearch";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(fromId)));
		driver.findElement(By.id(fromId)).sendKeys("Adana");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@airport='adana']")));
		driver.findElement(By.xpath("//div[@airport='adana']")).click();
	}

	public void selectToLocation() {
		String toId = "toAirports_txtSearch";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(toId)));
		driver.findElement(By.id(toId)).click();
		driver.findElement(By.id(toId)).clear();
		driver.findElement(By.id(toId)).sendKeys(" Abu Dhabi International");
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[@airport='abu dhabi international']")));
		driver.findElement(By.xpath("//div[@airport='abu dhabi international']")).click();
	}

	public void selectDepartureDate() {
		String startDateXpath= "//td[a[@class='ui-state-default']][1]";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(startDateXpath)));
		driver.findElement(By.xpath(startDateXpath)).click();
	}

	public void selectReturnDate() {
		String returnDateXpath = "//td[a[@class='ui-state-default']][2]";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(returnDateXpath)));
		driver.findElement(By.xpath(returnDateXpath)).click();
	}

	public void findFlights() {
		String findFlightsId = "btnBookingSubmit";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(findFlightsId)));
		driver.findElement(By.id(findFlightsId)).click();

	}

	@AfterClass
	public static void teardown() {
		driver.quit();
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

	// login module ends
	// take screenshot
	public static void takeScreenshot(WebDriver driver, String filePath) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(filePath));
	}

}
