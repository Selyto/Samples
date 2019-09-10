package baseTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import pageobjects.HomePage;

public class BaseTest {

	WebDriver driver;
	Properties props;
	
	String BROWSER_NAME = null;
	String APP_URL = null;
	
	//@BeforeTest
	public void openBrowser(String browser) {
		switch(browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "BrowserDrivers//chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			break;
		}
	}
	
	@BeforeSuite
	public void initiate() {
		readProperties();
		BROWSER_NAME = props.getProperty("browser");
		APP_URL = props.getProperty("url");
		openBrowser(BROWSER_NAME);
		
	}
	
	public HomePage navigateTo() {
		driver.get(APP_URL);
		return PageFactory.initElements(driver, HomePage.class);
	}
	
	public void readProperties() {
		 props = new Properties();
		try {
			props.load(new FileInputStream(new File("config//config.properties")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
	