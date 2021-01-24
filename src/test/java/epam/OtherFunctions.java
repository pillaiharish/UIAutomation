package epam;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import pageObject.HerokuPage;
import resources.Base;

public class OtherFunctions extends Base {
	public WebDriver driver;
	@Test
	public void windowOperations() throws IOException, InterruptedException {
		driver = initializeDriver();
		driver.get(getURL_2());
		driver.manage().window().maximize();
		
		HerokuPage hp = new HerokuPage(driver);
		//for windows
//		String openLinkInNewTab = Keys.chord(Keys.CONTROL,Keys.ENTER);
		//for mac
		String openLinkInNewTab = Keys.chord(Keys.COMMAND,Keys.ENTER);
		hp.getABTesting().sendKeys(openLinkInNewTab);
		String parentHandle = driver.getWindowHandle();
		Set <String> windowHandles = driver.getWindowHandles();
		
		Iterator<String> iter = windowHandles.iterator();
		
		while(iter.hasNext()){

			String child_window=iter.next();
	
	
			if(!parentHandle.equals(child_window)){
				driver.switchTo().window(child_window);
		
				System.out.println(driver.switchTo().window(child_window).getTitle());
		
				driver.close();
			}

		}
		//switch to the parent window
		driver.switchTo().window(parentHandle);
	    System.out.println(windowHandles.size());
	    
	    // JS executor to scroll the page.
	    JavascriptExecutor js = (JavascriptExecutor) driver;  
	    js.executeScript("window.scrollBy(0,300)");
	    // Alert in java
	    hp.getJSAlerts().click();
	    WebDriverWait w = new WebDriverWait(driver, explicitWait());
		w.until(ExpectedConditions.visibilityOf(hp.getAlertPop()));
		hp.getAlertPop().click();
		// switch to alert
		Alert alert1 = driver.switchTo().alert(); 
		// capture alert message
		String alertMessage1= driver.switchTo().alert().getText();
		System.out.println(alertMessage1);
		alert1.accept();
		
		hp.getAlertDismiss().click();
		//switch to alert
		Alert alert2 = driver.switchTo().alert();
		// capture alert message
		String alertMessage2= driver.switchTo().alert().getText();
		System.out.println(alertMessage2);
		alert2.dismiss();
		driver.navigate().back();
		
		hp.getDragDropLink().click();
		
		//Using Action class for drag and drop.		
        Actions act=new Actions(driver);					
        
//        //Building a drag and drop action
//        Action dragAndDrop = act.clickAndHold(hp.getDestination())
//        .moveToElement(hp.getSource())
//        .release(hp.getSource())
//        .build();
//
//        //Performing the drag and drop action
//        dragAndDrop.perform();
        Thread.sleep(5000);
        act.dragAndDrop(hp.getSource(), hp.getDestination()).perform();
        
	    
	}
	@AfterMethod
	public void shut() {
		driver.quit();
	}
}
