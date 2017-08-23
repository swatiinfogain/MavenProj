package pageObjects;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import testDriver.Driver;

public class HomePage extends Driver {
	public WebDriver driver;
	@FindBy(xpath=".//*[@id='gi_roundtrip_label']")
	WebElement roundTripRadio;
	@FindBy(xpath=".//*[@id='gi_oneway_label']")
	WebElement onewayRadio;
	@FindBy(xpath=".//*[@id='gi_multicity_label']")
	WebElement multiCityRadio;
	@FindBy(xpath=".//*[@id='gi_oneway_label']")
	WebElement oneWayRadio;
	@FindBy(xpath=".//*[@id='gosuggest_inputSrc']")
	WebElement srcCity;
	@FindBy(xpath="//*[@id='react-autosuggest-1-suggestion--1']")
	WebElement drop1;
	@FindBy(xpath="//*[@id='react-autosuggest-1-suggestion--2']")
	WebElement drop2;
	@FindBy(css=".DayPicker-NavButton.DayPicker-NavButton--next")
	WebElement nextArrowCal;
	@FindBy(xpath=".//*[@id='searchWidgetCommon']/div/div[3]/div[1]/div[1]/div/input")
	WebElement calenderFrom;
	@FindBy(xpath=".//*[@id='searchWidgetCommon']/div/div[3]/div[1]/div[2]/div/input[1]")
	WebElement calenderTo;
	@FindBy(xpath=".//*[@id='searchWidgetCommon']/div/div[3]/div[1]/div[1]/div/div/div/div[2]/div[1]")
	WebElement calenderToMONYY;
	@FindBy(xpath=".//*[@id='searchWidgetCommon']/div/div[3]/div[1]/div[2]/div/div/div/div[2]/div[1]")
	WebElement calenderFromMONYY;
	@FindBy(xpath=".//*[@id='gosuggest_inputDest']")
	WebElement destCity;
	@FindBy(xpath=".//*[@id='gi_search_btn']")
	WebElement SearchButton;
	@FindBy(xpath=".//*[@id='onwFltContainer']/div[3]")
	WebElement SearchResults;
	public HomePage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	void EnterTrip(String trip){
		if(trip.equals("round")){
			Highlighter(roundTripRadio);		
			roundTripRadio.click();
		}
		else
		{
			if(trip.equals("oneway"))
			{
				Highlighter(onewayRadio);		
				onewayRadio.click();
			}
			else{
				Highlighter(multiCityRadio);		
				multiCityRadio.click();
			}
		}
	}
	void EnterSrcCity(String sourceCity){
		Highlighter(srcCity);
		srcCity.clear();
		srcCity.click();
		srcCity.sendKeys(sourceCity);
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='react-autosuggest-1-suggestion--1']")));
		Highlighter(drop1);
		drop1.click();
		
	}
	void EnterDestCity(String destinationCity)
	{
		Highlighter(destCity);
		destCity.clear();

		destCity.click();
		destCity.sendKeys(destinationCity);
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='react-autosuggest-1-suggestion--2']")));
		Highlighter(drop2);
		drop2.click();
	}
	void SelectFromDate(String fromDate) throws ParseException{
		Highlighter(calenderFrom);
		calenderFrom.click();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date fromDt = sdf.parse(fromDate);
		SimpleDateFormat sdf1 = new SimpleDateFormat("MMMM/dd/yyyy");
		int mon=fromDt.getMonth()+1;
		String changedDate=sdf1.format(fromDt);
		String changedDateTo[]=changedDate.split("/");
		while(true)
		{
			if((changedDateTo[0]+" "+changedDateTo[2]).equals(calenderToMONYY.getText()))
			{
				WebElement date=driver.findElement(By.xpath(".//*[@id='fare_"+changedDateTo[2]+mon+changedDateTo[1]+"']"));
				Highlighter(date);
				date.click();
				break;

			}
			else
				Highlighter(nextArrowCal);
			nextArrowCal.click();	
		}
	}
	void SelectToDate(String toDate) throws ParseException{
		Highlighter(calenderTo);
		calenderTo.click();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date toDt = sdf.parse(toDate);
		SimpleDateFormat sdf1 = new SimpleDateFormat("MMMM/dd/yyyy");
		int mon=toDt.getMonth()+1;
		String tDate=sdf1.format(toDt);
		String DateTo[]=tDate.split("/");
		while(true)
		{
			if((DateTo[0]+" "+DateTo[2]).equals(calenderFromMONYY.getText()))
			{
				WebElement date=driver.findElement(By.xpath(".//*[@id='fare_"+DateTo[2]+mon+DateTo[1]+"']"));
				Highlighter(date);
				date.click();
				break;

			}
			else
				Highlighter(nextArrowCal);
			nextArrowCal.click();	
		}
	}
	void Search()
	{
		Highlighter(SearchButton);
		SearchButton.click();
		

	}
	public Boolean SearchResults()
	{
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='onwFltContainer']/div[3]")));
		if(SearchResults.isDisplayed()){
		Highlighter(SearchResults);
		return true;
	}
	return false;
	
	}
	public Boolean EnterSourceTrip(String trip, String sourceCity)
	{
		EnterTrip(trip);
		EnterSrcCity(sourceCity);
		System.out.println(srcCity.getText());
		if(!"From: City or Airport".equals(srcCity.getText()))
		return true;
		
		else
			return false;
	}
	public Boolean formFill(String trip,String sourceCity, String destinationCity,String fromDate,String toDate) throws ParseException{
		EnterTrip(trip);
		EnterSrcCity(sourceCity);
		EnterDestCity(destinationCity);
		ScrollWindow(0,250);
		SelectFromDate(fromDate);
		SelectToDate(toDate);
		Search();
		return SearchResults();
	}

}
