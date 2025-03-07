package com.teststore.Utilities;

import java.io.IOException;
import java.time.Duration;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.teststore.LoginPage.ExtractuserName;
import com.teststore.helper.LoggerHelper;

import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class AbsractBaseTest {
	protected static WebDriver driver;
	private Logger log = LoggerHelper.getlogger(AbsractBaseTest.class);
	

	@BeforeTest
	public void dataSetup() throws IOException {
		ExtractuserName JsonData = new ExtractuserName();
		JsonData.extractinformation();
		log.info("Data from the Api got extracted");
		
		log.info("Launching the Application on Chrome Browser");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://magento.softwaretestingboard.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		log.info("Application Launch Succesfully");
		
	}
	
	@AfterTest
	public void teardown() {
		driver.quit();
		log.info("Driver Closed Succesfully");
	}

}  