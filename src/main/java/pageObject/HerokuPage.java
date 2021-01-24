package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HerokuPage {
	public WebDriver driver;
	By DynamicLoading= By.linkText("Dynamic Loading");
	By Link1= By.linkText("Example 1: Element on page that is hidden");
	By StartButton = By.cssSelector("[id='start'] button");
	By HelloMessage = By.cssSelector("[id='finish'] h4");
	By ABTesting = By.linkText("A/B Testing");
	By JSAlerts = By.linkText("JavaScript Alerts");
	By AlertPop = By.xpath("//*[text()='Click for JS Alert']");
	By AlertDismiss = By.xpath("//*[text()='Click for JS Confirm']");
	By DragAndDropLink = By.linkText("Drag and Drop");
	By Source = By.id("column-a");
	By Destination = By.id("column-b");
	
	public HerokuPage(WebDriver driverImport) {
		this.driver=driverImport;
	}
	public WebElement getDynamicLoading() {
		return driver.findElement(DynamicLoading);
	}
	public WebElement getLink1() {
		return driver.findElement(Link1);
	}
	public WebElement  getStartButton() {
		return driver.findElement(StartButton);
	}
	public WebElement waitForHello() {
		return driver.findElement(HelloMessage);
	}
	public WebElement getABTesting() {
		return driver.findElement(ABTesting);
	}
	public WebElement getJSAlerts() {
		return driver.findElement(JSAlerts);
	}
	public WebElement getAlertPop() {
		return driver.findElement(AlertPop);
	}
	public WebElement getAlertDismiss() {
		return driver.findElement(AlertDismiss);
	}
	public WebElement getDragDropLink() {
		return driver.findElement(DragAndDropLink);
	}
	public WebElement getSource() {
		return driver.findElement(Source);
	}
	public WebElement getDestination() {
		return driver.findElement(Destination);
	}
}
