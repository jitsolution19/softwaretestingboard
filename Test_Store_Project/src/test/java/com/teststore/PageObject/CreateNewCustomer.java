package com.teststore.PageObject;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.teststore.Utilities.PageUtilities;
import com.teststore.Utilities.ReadfromJson;
import com.teststore.helper.LoggerHelper;

public class CreateNewCustomer extends PageUtilities {
	private Logger log = LoggerHelper.getlogger(CreateNewCustomer.class);
	
	public CreateNewCustomer(WebDriver driver) {
		super(driver);
	}

	private By Create_an_account = By.xpath(".//div[@class='panel header']//a[text()='Create an Account'][1]");
	private By First_name = By.id("firstname");
	private By Last_name = By.id("lastname");
	private By Email = By.id("email_address");
	private By password = By.id("password");
	private By confirmpassword = By.id("password-confirmation");
	private By Create_an_account_button = By.xpath(".//button[@type='submit']/span[text()='Create an Account']");
	private By GetAccountCreatedMessage = By.xpath(".//div[@data-ui-id='message-success']/div");

	public void ClickCreateAnAccountLink() {
		clicklink(Create_an_account);
		log.info("Click on Create and Account link");
	}

	public void fill_personal_information() throws IOException {
		log.info("User filling up the personal information");
		ReadfromJson.extractDataFromJson();
		enterText(First_name, ReadfromJson.fetchdata("FirstName"));
		enterText(Last_name, ReadfromJson.fetchdata("LastName"));
		enterText(Email, ReadfromJson.fetchdata("Email"));
		enterText(password, ReadfromJson.fetchdata("Password"));
		enterText(confirmpassword, ReadfromJson.fetchdata("Password"));
	}

	public void ClickCreateAnAccountButton() {

		clicklink(Create_an_account_button);
		waitforvisibilityofText(GetAccountCreatedMessage);
	}

	public void verifyaccountcreated() {
		String ExpectedText = "Thank you for registering with Fake Online Clothing Store.";
		CompareText(ExpectedText, getTextfromUi(GetAccountCreatedMessage));
		log.info("Account created");
	}

}
