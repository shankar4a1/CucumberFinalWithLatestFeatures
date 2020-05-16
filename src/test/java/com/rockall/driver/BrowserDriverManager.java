package com.rockall.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.rockall.helper.BrowserOptions;

public class BrowserDriverManager {

	protected static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();
	
	public void setDriver(String browserName) {
		if(browserName.equalsIgnoreCase("chrome"))
			threadDriver.set(new ChromeDriver(BrowserOptions.getChromeOptions()));
		else if(browserName.equalsIgnoreCase("firefox"))
			threadDriver.set(new FirefoxDriver(BrowserOptions.getFirefoxOptions()));
		else if(browserName.equalsIgnoreCase("ie"))
			threadDriver.set(new InternetExplorerDriver(BrowserOptions.getIeOptions()));
	}
	
	public WebDriver getDriver() {		
        return threadDriver.get();
    }
	
	public void quitDriver() {
		getDriver().quit();
	}
 
}
