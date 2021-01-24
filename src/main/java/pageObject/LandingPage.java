package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage {
	
	public WebDriver driver;
	By Email = By.id("email");
	By Password = By.name("pass");
	By LoginButton = By.name("login");
	By errorLogin = By.id("loginbutton");
	
	public LandingPage(WebDriver driver2) {
		this.driver=driver2;
	}
	public WebElement getEmail() {
		return driver.findElement(Email);
	}
	public WebElement getPassword() {
		return driver.findElement(Password);
	}
	public WebElement getLoginButton() {
		return driver.findElement(LoginButton);
	}
	public WebElement getErrorLogin() {
		return driver.findElement(errorLogin);
	}
}
