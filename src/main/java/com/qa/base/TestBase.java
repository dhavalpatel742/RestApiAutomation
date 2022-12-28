package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
	
	public Properties prop;
	public static int RESPONSE_STATUS_CODE_200 = 200;
	public static int RESPONSE_STATUS_CODE_500 = 500;
	public static int RESPONSE_STATUS_CODE_400 = 400;
	public static int RESPONSE_STATUS_CODE_201 = 201;
	public static int RESPONSE_STATUS_CODE_404 = 404;
	public static int RESPONSE_STATUS_CODE_401 = 401;
	
	public TestBase() {
		
		try {
		prop = new Properties();
		FileInputStream f = new FileInputStream("C:\\Selenium_Workspace\\RestApiAutomation\\src\\main\\java\\com\\qa\\config\\config.properties");
		prop.load(f);
		}
		catch(FileNotFoundException e){
			e.printStackTrace();	
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}

}
