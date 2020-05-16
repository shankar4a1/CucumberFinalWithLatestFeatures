package com.rockall.runner;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.rockall.driver.BrowserDriverManager;
import com.rockall.helper.Log;
import com.rockall.helper.ReportGenerator;

import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.TestNGCucumberRunner;

@CucumberOptions(features = "src/test/resources/features", glue = { "com.rockall.stepdefinitions" }, plugin = {
		"pretty", "html:target/cucumber-reports", "json:target/cucumber-reports/CucumberTestReport.json",
		"rerun:target/cucumber-reports/rerun.txt" }, monochrome = true)
public class TestNgRunner extends BrowserDriverManager{
	private TestNGCucumberRunner testNgCucumberRunner;
	private BrowserDriverManager driverManager;
	
	public TestNgRunner() {
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\drivers\\chromedriver.exe");
		System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\drivers\\IEDriverServer.exe");
		System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\drivers\\geckodriver.exe");
		System.setProperty("log4j.configurationFile","log4j.xml");
	}
	
	@BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
		Log.info(String.format("TestNG Before Class running in Thread - %s",Thread.currentThread().getId()));		
		testNgCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }
 
    @Test(groups = "cucumber", description = "Run Scenario", dataProvider = "scenarioData")
    public void scenario(PickleWrapper pickleWrapper,FeatureWrapper featureWrapper) throws Throwable {
    	testNgCucumberRunner.runScenario(pickleWrapper.getPickle());
    }
 
    @DataProvider(parallel=true)
    public Object[][] scenarioData() {
        return testNgCucumberRunner.provideScenarios();
    }
 
    @AfterClass(alwaysRun = true)
    public void tearDown() {
    	testNgCucumberRunner.finish();
    	ReportGenerator.getInstance().run();
    	Log.info(String.format("TestNG After Class running in Thread - %s",Thread.currentThread().getId()));
    }

}
