package test.HomePage;

import java.text.ParseException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;

import pageObjects.HomePage;
import testDriver.Driver;
public class HomePageTestCases extends Driver{
	HomePage hp;
	
	
	
	@Test()
	public void TC_001SourceDropdown() throws ParseException
	{
		hp=new HomePage(driver);
		Assert.assertTrue(hp.EnterSourceTrip("round","A"));
	}
	
	
	@Test()
	public void TC_002VerifySearchResults() throws ParseException
	{
		hp=new HomePage(driver);
		Assert.assertTrue(hp.formFill("round","D", "G","10/07/2017","11/21/2017"));
	}
	
}
