package com.teststore.PageObject;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.teststore.Utilities.PageUtilities;
import com.teststore.Utilities.ReadfromJson;
import com.teststore.helper.LoggerHelper;

public class Checkout_addressPage extends PageUtilities {
	private Logger log = LoggerHelper.getlogger(ReviewPaymentPage.class);
	public Checkout_addressPage(WebDriver driver) {
		super(driver);
	}

	private By Street_First_Line = By.xpath(".//input[@name='street[0]']");
	private By Street_Second_Line = By.xpath(".//input[@name='street[1]']");
	private By Street_third_line = By.xpath(".//input[@name='street[2]']");
	private By City = By.xpath(".//input[@name='city']");
	private By State = By.xpath(".//select[@name='region_id']");
	private By PostalCode = By.xpath(".//input[@name='postcode']");
	private By Country = By.xpath(".//select[@name='country_id']");
	private By PhoneNumber = By.xpath(".//input[@name='telephone']");
	private By Shipping_Method_Fixed = By.xpath("(.//input[contains(@name,'ko_unique_')])[1]");
	private By Shipping_Method_Table_Rate = By.xpath("(.//input[contains(@name,'ko_unique_')])[2]");
	private By NextButton = By.xpath(".//button[@data-role='opc-continue']/span");

	public void enterShippingAddress() throws IOException, InterruptedException {
		Thread.sleep(5000);
		waitforvisibilityofText(PhoneNumber);
		ReadfromJson.extractDataFromJson();
		try {
			enterText(Street_First_Line, ReadfromJson.fetchdata("Street_FirstLine_Address"));
			enterText(Street_Second_Line, ReadfromJson.fetchdata("Street_SecondLine_Address"));
			enterText(Street_third_line, ReadfromJson.fetchdata("Street_ThirdLine_Address"));
		} catch (Exception e) {
			log.info("Exception :: "+e);
		}

		try {
			enterText(City, ReadfromJson.fetchdata("City"));

			selectfromdropdown(Country, ReadfromJson.fetchdata("Country"));
			String PostalCodevalue = ReadfromJson.fetchdata("PostalCode");
			if (PostalCodevalue.length() < 5) {
				PostalCodevalue = PostalCodevalue + "1";
			}
			enterText(PostalCode, PostalCodevalue);

			String phonenumbervalue = ReadfromJson.fetchdata("PhoneNumber");
			if (phonenumbervalue.length() < 0) {
				phonenumbervalue = "7058704451";
			}
			enterText(PhoneNumber, phonenumbervalue);
			waitforpresenceOfAllElements(State);
			selectfromdropdown(State, ReadfromJson.fetchdata("State_Prov"));

		} catch (Exception e) {
			log.info("Exception :: "+e);
		}
	}

	public void selectShippingMethod(String method) {
		if (method.matches("Fixed")) {
			selectradioButton(Shipping_Method_Fixed);
		} else {
			selectradioButton(Shipping_Method_Table_Rate);
		}
		clickButton(NextButton);
		try {
			String value = getTextfromUi(By.xpath(".//div[@class='message warning']"));
			if (value.contains("seems to be invalid")) {
				clickButton(NextButton);
			}
		} catch (Exception e) {
			System.out.println("Handling Warning Zip Code Warning message " + e);
			log.info("Exception :: "+e);
		}

	}

}
