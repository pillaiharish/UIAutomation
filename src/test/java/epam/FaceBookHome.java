package epam;

import org.testng.annotations.Test;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;

import pageObject.LandingPage;
import resources.Base;
import resources.ReadExcel;

public class FaceBookHome extends Base{
	public WebDriver driver;
	@Test(dataProvider = "getLoginData")
	public void basePageNavigation(String username, String password) throws IOException {
		driver = initializeDriver();
		driver.get(getURL_1());
		driver.manage().window().maximize();

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(driver.getTitle(),"Facebook – log in or sign up");
		
		LandingPage lp = new LandingPage(driver);
		
		Assert.assertTrue(lp.getEmail().isEnabled());
		lp.getEmail().sendKeys(username);
		
		Assert.assertFalse(lp.getEmail().isSelected());
		lp.getPassword().sendKeys(password);
		
		lp.getLoginButton().click();
		
		//explicit wait is here
		WebDriverWait w = new WebDriverWait(driver, explicitWait());
		w.until(ExpectedConditions.visibilityOf(lp.getErrorLogin()));
		
		softAssert.assertAll();
	}
	
	@DataProvider
	public Object[][] getLoginData(){
		
		ReadExcel re = new ReadExcel("/Users/harish/epam/a/project1/Automation/src/main/java/resources/Test_Data.xlsx");
		int rows = re.getRowCount(0);
		Object[][] data= new Object[rows][2];

		for(int i=0;i<rows;i++)
		{
		data[i][0] = re.getData(0, i, 0);
		data[i][1] = re.getData(0, i, 1);
		}
		return data;
	}
	@AfterMethod
	public void shut() {
		driver.quit();
	}
	
}
