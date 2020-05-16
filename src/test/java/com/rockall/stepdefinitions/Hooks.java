package com.rockall.stepdefinitions;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.BeforeClass;

import com.rockall.driver.BrowserDriverManager;
import com.rockall.helper.Log;

import io.cucumber.java8.En;
import io.cucumber.java8.Scenario;

public class Hooks implements En {
	private BrowserDriverManager driverManager;
	
	public Hooks(BrowserDriverManager driverManager) {
		this.driverManager = driverManager;
		Before((Scenario scenario) -> {
			Log.info(String.format("--------Thread ID %s----------",Thread.currentThread().getId()));
			Log.info("Starting Scenario -> " + scenario.getName());
		});

		After((Scenario scenario) -> {
			if (scenario.isFailed()) {
				try {
					byte[] screenshot = ((TakesScreenshot)driverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
				    scenario.embed(screenshot, "image/png", "failure screenshot" + scenario.getId());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			driverManager.quitDriver();
			Log.info("Completed Scenario -> " + scenario.getName());
			Log.info(String.format("--------Thread ID %s----------",Thread.currentThread().getId()));
		});
	}	
}
