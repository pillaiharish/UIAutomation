package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Base {
	public WebDriver driver;
	public int EXPLICIT_WAIT;
	public int MAX_TIMEOUT;
	public int POLLING;
	public String URL_1;
	public String URL_2;
	
	public WebDriver initializeDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fs = new FileInputStream("/Users/harish/epam/a/project1/Automation/src/main/java/resources/data.properties");
		prop.load(fs);
		String browserName = prop.getProperty("browser");
		EXPLICIT_WAIT = Integer.parseInt(prop.getProperty("EXPLICIT_WAIT"));
		MAX_TIMEOUT = Integer.parseInt(prop.getProperty("MAX_TIMEOUT"));
		POLLING = Integer.parseInt(prop.getProperty("POLLING"));
		URL_1 = prop.getProperty("URL_1");
		URL_2 = prop.getProperty("URL_2");
		
		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver","/Users/harish/Downloads/chromedriver/chromedriver");
			driver =new ChromeDriver();
		}
		else if (browserName.equals("firefox")) {
			// here code for firefox browser 
		}
		else if (browserName.equals("IE")) {
			// here code for internet explorer browser
		}
		// implicit wait is invoked from here
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		return driver;
	}
	public int explicitWait() {
		return EXPLICIT_WAIT;
	}
	public int getMaxTimeOut() {
		return MAX_TIMEOUT;
	}
	public int getPolling() {
		return POLLING;
	}
	public String getURL_1() {
		return URL_1;
	}
	public String getURL_2() {
		return URL_2;
	}
	
	public void getScreenShotPath( String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// "user.dir" is current project path for windows "\\reports\\"
		// "user.home" for mac with "/reports/"
		String destinationFile = System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
		FileUtils.copyFile(source, new File(destinationFile));
	}

}
