package testDriver;


import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import utilities.ConfigFileReader;

public class Driver {
	public static WebDriver driver;
	public static ExtentReports extentReport;
	public static ExtentTest extentTest;
	public ITestResult result;
	
@BeforeTest
	public void init()
	{
	ConfigFileReader cfr=new ConfigFileReader();	
		System.setProperty("webdriver.chrome.driver", cfr.readProperty("setup","ChromeDriverPath"));
		DateFormat dateFormat = new SimpleDateFormat("_yy_MM_dd_HH_mm_ss");
		Date date = new Date();
		System.out.println(System.getProperty("user.dir")+cfr.readProperty("setup","ExtentReportPath")+File.separator+"testReport"+dateFormat.format(date)+".html");
		extentReport= new ExtentReports(System.getProperty("user.dir")+cfr.readProperty("setup","ExtentReportPath")+File.separator+"testReport"+dateFormat.format(date)+".html", false);
		driver= new ChromeDriver();
		Dimension d= new Dimension(1000,600);
		driver.manage().window().setSize(d);;		
		driver.get(	cfr.readProperty("setup","ApplicationURL"));
		System.out.println(System.getProperty("url"));
		System.out.println(System.getProperty("Uname"));
	}
	public void Highlighter(WebElement element)
	{
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void ScrollWindow(int x, int y)
	{
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy("+x+","+y+")","");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@AfterTest
	public void close()
	{
		if(driver != null)
			driver.close();
	}
	
}
