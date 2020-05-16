package com.rockall.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.rockall.driver.BrowserDriverManager;
import com.rockall.helper.Log;
import com.rockall.helper.PropertyManager;

public class PageHelper extends BrowserDriverManager {

	private BrowserDriverManager driverManager;
	private WebDriver driver;

	public PageHelper(BrowserDriverManager driverManager) {
		this.driverManager = driverManager;
		driver = driverManager.getDriver();
		PageFactory.initElements(driver, this);
		PropertyManager.getInstance();
	}

	public WebElement waitAndFindElement(By by) {
		return this.waitForExpectedElement(by, Long.parseLong(PropertyManager.DEFAULT_ELEMENT_TIMEOUT_IN_SECS));
	}

	public WebElement waitAndFindElement(By by, long timeoutInSeconds) {
		return this.waitForExpectedElement(by, timeoutInSeconds);
	}

	public void waitForLoad() {
		new WebDriverWait(driver, Long.parseLong(PropertyManager.DEFAULT_ELEMENT_TIMEOUT_IN_SECS))
				.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd)
						.executeScript("return document.readyState").equals("complete"));
	}

	public WebElement waitForExpectedElement(By by, long timeoutInSeconds) {
		Wait<WebDriver> wait = new WebDriverWait(driver, timeoutInSeconds);
		waitForLoad();
		wait.until(visibilityOfElementLocated(by));
		return driver.findElement(by);
	}

	public ExpectedCondition<WebElement> visibilityOfElementLocated(By by) {
		return new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver driver) {
				try {
					Thread.sleep(1000L);
				} catch (InterruptedException ex) {
					Log.error(ex.getMessage());
				}

				WebElement element = driver.findElement(by);
				return element.isEnabled() ? element : null;
			}
		};
	}

	public WebElement waitForElementToBeClickable(By by) {
		Wait<WebDriver> wait = new WebDriverWait(driver,
				Long.parseLong(PropertyManager.DEFAULT_ELEMENT_TIMEOUT_IN_SECS));
		return wait.until(ExpectedConditions.elementToBeClickable(by));
	}

	public void inputText(By by, String input) {
		waitAndFindElement(by).sendKeys(Keys.chord(Keys.CONTROL, "a"));
		driver.findElement(by).sendKeys(input);
	}

	public void click(By by) {
		waitAndFindElement(by).click();
	}

	public void selectDropDown(By btn, By dropDown, String input) {
		waitForElementToBeClickable(btn).click();
		List<WebElement> options = driver.findElements(dropDown);
		for (WebElement opt : options) {
			if (opt.getText().equals(input)) {
				opt.click();
				return;
			}
		}
		throw new NoSuchElementException("Can't find " + input + " in dropdown");
	}

	public String getText(By by) {
		return waitAndFindElement(by).getText();
	}
}
