package com.teststore.LoginPage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.teststore.PageObject.Add_Product_to_Cart;
import com.teststore.PageObject.Checkout_addressPage;
import com.teststore.PageObject.CreateNewCustomer;
import com.teststore.PageObject.ReviewPaymentPage;
import com.teststore.helper.LoggerHelper;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Applicationlogin {
	CreateNewCustomer objCreateNewCustomer;
	Add_Product_to_Cart objAdd_Product_to_Cart;
	Checkout_addressPage objCheckout_addressPage;
	ReviewPaymentPage objReviewPaymentPage;
	static WebDriver driver;
	private Logger log = LoggerHelper.getlogger(Applicationlogin.class);
	@BeforeTest
	public void dataSetup() throws IOException {
		ExtractuserName JsonData = new ExtractuserName();
		JsonData.extractinformation();
		log.info("Data from the Api got extracted");
	}
	
	@Test
	public void login() throws IOException, InterruptedException {
		
		log.info("Launching the Application on Chrome Browser");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://magento.softwaretestingboard.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		log.info("Application Launch Succesfully");
		objCreateNewCustomer = new CreateNewCustomer(driver);
		objAdd_Product_to_Cart = new Add_Product_to_Cart(driver);
		objCheckout_addressPage = new Checkout_addressPage(driver);
		objReviewPaymentPage = new ReviewPaymentPage(driver);

		objCreateNewCustomer.ClickCreateAnAccountLink();
		log.info("User is clicking on the Create an account Link");

		objCreateNewCustomer.fill_personal_information();
		log.info("User is Filling up the Personal Information for account Creation");

		objCreateNewCustomer.ClickCreateAnAccountButton();
		log.info("Account has been created Succesfully");

		objAdd_Product_to_Cart.navigatetoMensJacketPage();
		log.info("User is Navigate to Mens Jacket Page");

		objAdd_Product_to_Cart.select_the_First_Jacket();
		log.info("User Select the First jacket");

		objAdd_Product_to_Cart.select_the_Size();
		log.info("User Choose the size of the Jacket");

		objAdd_Product_to_Cart.select_the_Color();
		log.info("User select the color of the Jacket");

		objAdd_Product_to_Cart.click_Add_to_Cart_button();
		log.info("User add the jacket in the cart by clicking on the add to cart button");

		objAdd_Product_to_Cart.verify_product_added_in_cart();
		log.info("User validate the product added in the cart");

		objAdd_Product_to_Cart.click_on_Proceed_to_Checkout_button();
		log.info("User click on proceed to the checkout button");

		objCheckout_addressPage.enterShippingAddress();
		log.info("User enter the Shipping Address information");

		objCheckout_addressPage.selectShippingMethod("Fixed");
		log.info("User select the shipping method as Fixed");

		objReviewPaymentPage.verifyInformation();
		log.info("User validate the shipping address os correct");

		objReviewPaymentPage.clickPlaceOrderButton();
		log.info("User place the order");

		objReviewPaymentPage.verifyOrderNumbergenerated();
		log.info("User placed the order successfully and order number is generated");
	}
	
	@AfterTest
	public void teardown() {
		driver.close();
		log.info("Driver Closed Succesfully");
	}
}
