package com.rockall.helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {
		 
	    private static PropertyManager instance;
	    private static final Object lock = new Object();
	    private static String propertyFilePath = System.getProperty("user.dir")+
	            "\\src\\test\\resources\\config.properties";
	    public static String DEFAULT_ELEMENT_TIMEOUT_IN_SECS;
	    public static String REPORT_OUTPUT_PATH;
	 
	    public static PropertyManager getInstance () {
	        if (instance == null) {
	            synchronized (lock) {
	                instance = new PropertyManager();
	                instance.loadData();
	            }
	        }
	        return instance;
	    }
	 
	    private void loadData() {
	        Properties prop = new Properties();
	        try {
	            prop.load(new FileInputStream(propertyFilePath));
	            //prop.load(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
	        } catch (IOException e) {
	            System.out.println("Configuration properties file cannot be found");
	        }
	 
	        DEFAULT_ELEMENT_TIMEOUT_IN_SECS = prop.getProperty("DEFAULT_ELEMENT_TIMEOUT_IN_SECS");
	        REPORT_OUTPUT_PATH = prop.getProperty("REPORT_OUTPUT_PATH");
	    }
}
