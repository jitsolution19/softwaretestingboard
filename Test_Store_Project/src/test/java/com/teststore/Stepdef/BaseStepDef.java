package com.teststore.Stepdef;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.teststore.LoginPage.Applicationlogin;
import com.teststore.helper.LoggerHelper;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class BaseStepDef {
	static WebDriver driver;
	private Logger log = LoggerHelper.getlogger(Applicationlogin.class);

	@Before
	public void dataSetup() throws IOException {
//		log.info("Launching the Application on Chrome Browser");
//		String Path = "C:\\Users\\jeete\\Downloads\\chromedriver-win32\\chromedriver.exe";
//		System.setProperty("webdriver.chrome.driver",Path);
//		driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}


	@After
	public void teardown() {

//		driver.quit();
	}
}
