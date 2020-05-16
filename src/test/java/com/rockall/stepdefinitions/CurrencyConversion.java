package com.rockall.stepdefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.rockall.driver.BrowserDriverManager;
import com.rockall.pages.CurrencyConverterPage;

import io.cucumber.java8.En;

public class CurrencyConversion extends BrowserDriverManager implements En{
	private BrowserDriverManager driverManager;
	private WebDriver driver;
	private CurrencyConverterPage currencyConverterPage;
	
	public CurrencyConversion(BrowserDriverManager driverManager) {
		this.driverManager = driverManager;	
		 
		
		Given("The transferwise currency converter {} is launched in {}",(String url,String browserType) -> {
			driverManager.setDriver(browserType);	
			driver = driverManager.getDriver();
			driver.navigate().to(url);
			driver.manage().window().maximize();
			Assert.assertEquals(driver.getTitle(), "Currency Converter | Foreign Exchange Rates | TransferWise");
		});
		
		When("The {} and {} is entered", (String sourceCurrency,String targetCurrency) -> {
			currencyConverterPage = new CurrencyConverterPage(driverManager);
			currencyConverterPage.setSourceAndTargetCurrency(sourceCurrency,targetCurrency);
		});
		
		When("The {double} to be converted is entered", (Double amountForConversion) -> {
			currencyConverterPage.setConversionAmount(amountForConversion.toString());
		});
		
		When("convert button is clicked", () -> {
			currencyConverterPage.clickConvert();
		});

		Then("The converted-to value is as per the given {double}", (Double conversionRate) -> {
			Assert.assertEquals(currencyConverterPage.getConversionRate(),conversionRate);			
		});
	}
}
