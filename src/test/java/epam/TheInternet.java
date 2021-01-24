package epam;




import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObject.HerokuPage;
import resources.Base;

public class TheInternet extends Base{
	public WebDriver driver;
	@Test
	public void checkFluentWait() throws IOException {
		driver = initializeDriver();
		driver.get(getURL_2());
		driver.manage().window().maximize();
		
		HerokuPage hp = new HerokuPage(driver);
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(driver.getTitle(),"The Internet");
		
		WebDriverWait w = new WebDriverWait(driver, explicitWait());
		w.until(ExpectedConditions.visibilityOf(hp.getDynamicLoading()));
		hp.getDynamicLoading().click();
		
		w.until(ExpectedConditions.visibilityOf(hp.getLink1()));
		hp.getLink1().click();
		
		w.until(ExpectedConditions.visibilityOf(hp.getStartButton()));
		hp.getStartButton().click();
		
		//Fluent Wait for page to show output
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(getMaxTimeOut()))
				.pollingEvery(Duration.ofSeconds(getPolling()))
				.ignoring(NoSuchElementException.class);
		
		WebElement helloMessage= wait.until(new Function<WebDriver,WebElement>(){
			public WebElement apply(WebDriver driver) {
				if (hp.waitForHello().isDisplayed()) {
					return hp.waitForHello();
				}
				else {
					return null;
				}
			}
		});
		
		Assert.assertFalse(hp.waitForHello().isDisplayed());
		Assert.assertEquals(helloMessage.getText(),"Hello World!");
		softAssert.assertAll();
	}
	
	
	@AfterMethod
	public void shut() {
		driver.quit();
	}
}
