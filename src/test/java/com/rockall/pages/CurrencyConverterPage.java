package com.rockall.pages;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.rockall.driver.BrowserDriverManager;

public class CurrencyConverterPage extends PageHelper{
	
	public CurrencyConverterPage(BrowserDriverManager driverManager) {
		super(driverManager);
	}
	
	By conversionAmountTextBox = By.id("cc-amount");
	//By sourceCurrencyButton = By.xpath("//button[@class='btn dropdown-toggle btn-input' and @data-id='sourceCurrency']");
	By sourceCurrencyDropDown = By.id("sourceCurrency");
//	By sourceCurrencySearch = By.xpath("//div[@class='bs-searchbox']/input");
//	By sourceCurrencySelected = By.xpath("//ul[@class='dropdown-menu inner']/li[@class='active']");
	//By targetCurrencyButton = By.xpath("//button[@class='btn dropdown-toggle btn-input' and @data-id='targetCurrency']");
	By targetCurrencyDropDown = By.id("targetCurrency");
	By convertButton = By.id("convert");
	By conversionRate = By.xpath("//h3[@class='cc__source-to-target m-t-2']/span[3]");
	
	
	By sourceCurrencyButton = By.xpath("//button[@class='btn dropdown-toggle btn-input' and @data-id='sourceCurrency']");
	By sourceCurrencyDropdown = By.xpath("//button[@data-id='sourceCurrency']/following-sibling::div/ul/li");
	By targetCurrencyButton = By.xpath("//button[@class='btn dropdown-toggle btn-input' and @data-id='targetCurrency']");
	By targetCurrencyDropdown = By.xpath("//button[@data-id='targetCurrency']/following-sibling::div/ul/li");

	public void setSourceAndTargetCurrency(String sourceCurrency,String targetCurrency) {		
		selectDropDown(sourceCurrencyButton,sourceCurrencyDropdown,sourceCurrency);
		selectDropDown(targetCurrencyButton,targetCurrencyDropdown,targetCurrency);
	}
	
	public void setConversionAmount(String amount) {
		inputText(conversionAmountTextBox,amount);
	}
	
	public void clickConvert() {
		click(convertButton);
	}
	
	public Double getConversionRate() {
		return Double.parseDouble(getText(conversionRate).split(" ")[0]);
	}
}
