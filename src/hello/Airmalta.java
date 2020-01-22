package hello;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Airmalta {
	static WebDriver driver;

	@BeforeClass

	public static void Beforeclass() {

		// Configure Browers
		System.setProperty("webdriver.chrome.driver",
				"E:\\Selenium\\Travelairmalta\\driver\\chromedriver.exe");

		driver = new ChromeDriver();
		// launching Browers
		driver.get("https://www.airmalta.com/");
		driver.manage().window().maximize();
	}

	

}
