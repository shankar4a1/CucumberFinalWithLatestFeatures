package com.rockall.helper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;

public class ReportGenerator {

	private static ReportGenerator reportGenerator = new ReportGenerator();
	private ReportGenerator() {		
	}
	
	public static ReportGenerator getInstance() {
		return reportGenerator;
	}
	
	public void run() {
		File reportOutputDirectory = new File(System.getProperty("user.dir")+"\\target\\");
		List<String> jsonFiles = new ArrayList<>();
		jsonFiles.add(System.getProperty("user.dir") + "\\target\\cucumber-reports\\CucumberTestReport.json");
		
		String projectName = "Rockall_Test";
		Configuration configuration = new Configuration(reportOutputDirectory, projectName);
		configuration.addClassifications("Platform", "Windows");
		
		ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
		reportBuilder.generateReports();
	}
}
