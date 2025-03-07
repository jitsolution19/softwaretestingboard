package com.teststore.PageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.teststore.Utilities.PageUtilities;
import com.teststore.Utilities.ReadfromJson;
import com.teststore.helper.LoggerHelper;

public class ReviewPaymentPage extends PageUtilities {
	private Logger log = LoggerHelper.getlogger(ReviewPaymentPage.class);
	public ReviewPaymentPage(WebDriver driver) {
		super(driver);
	}

	private By billingAddress = By.xpath(".//div[@class='billing-address-details']");
	private By PlaceOrderButton = By.xpath(".//button[@title='Place Order']/span");
	private By purchasemessage = By.xpath(".//span[@data-ui-id='page-title-wrapper']");
	private By orderNumber = By.xpath(".//div[@class='checkout-success']/p/a");

	public boolean verifyInformation() {
		boolean flag = false;
		ReadfromJson.extractDataFromJson();
		String Cityinfo=ReadfromJson.fetchdata("City");
		try {
			String address = getTextfromUi(billingAddress);
			log.info("Billing Address :: "+address);
			if(address.contains(Cityinfo)) {
				flag =true;
			}
		}catch(Exception e) {
			log.info(e);
			flag=false;
		}
		return flag;
	}

	public void clickPlaceOrderButton() {
		clickButton(PlaceOrderButton);
	}

	public void verifyOrderNumbergenerated() throws InterruptedException {
		Thread.sleep(5000);
		waitforvisibilityofText(purchasemessage);
		String getSuccessmessage = getTextfromUi(purchasemessage);
		String GetorderNumber = getTextfromUi(orderNumber);
		log.info("Success message :: "+getSuccessmessage);
		log.info("Order Number is as :: "+GetorderNumber);
	}
}
