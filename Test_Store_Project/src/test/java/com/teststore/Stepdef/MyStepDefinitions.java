package com.teststore.Stepdef;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.teststore.LoginPage.Applicationlogin;
import com.teststore.LoginPage.ExtractuserName;
import com.teststore.PageObject.Add_Product_to_Cart;
import com.teststore.PageObject.Checkout_addressPage;
import com.teststore.PageObject.CreateNewCustomer;
import com.teststore.PageObject.ReviewPaymentPage;
import com.teststore.helper.LoggerHelper;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class MyStepDefinitions {
	CreateNewCustomer objCreateNewCustomer;
	Add_Product_to_Cart objAdd_Product_to_Cart;
	Checkout_addressPage objCheckout_addressPage;
	ReviewPaymentPage objReviewPaymentPage;
	static WebDriver driver;
	private Logger log = LoggerHelper.getlogger(Applicationlogin.class);

	@Before
	public void dataSetup() throws IOException {
		log.info("Launching the Application on Chrome Browser");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

	}

	@Given("^User information is extracted from the API$")
	public void user_information_is_extracted_from_the_api() throws Throwable {
		ExtractuserName JsonData = new ExtractuserName();
		JsonData.extractinformation();
		log.info("Data from the Api got extracted");
	}

	@Then("^user validate that the jacket order is placed successfully and order number is generated$")
	public void user_validate_that_the_jacket_order_is_placed_successfully_and_order_number_is_generated()
			throws Throwable {
		objReviewPaymentPage.verifyOrderNumbergenerated();
		log.info("User placed the order successfully and order number is generated");
	}

	@And("^user enter the billing address and proceed to place order$")
	public void user_enter_the_billing_address_and_proceed_to_place_order() throws Throwable {
		objCheckout_addressPage.enterShippingAddress();
		log.info("User enter the Shipping Address information");

		objCheckout_addressPage.selectShippingMethod("Fixed");
		log.info("User select the shipping method as Fixed");

		objReviewPaymentPage.verifyInformation();
		log.info("User validate the shipping address os correct");
	}

	@Given("user launch the test store website")
	public void user_launch_the_test_store_website() {
		driver.get("https://magento.softwaretestingboard.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		objCreateNewCustomer = new CreateNewCustomer(driver);
		objAdd_Product_to_Cart = new Add_Product_to_Cart(driver);
		objCheckout_addressPage = new Checkout_addressPage(driver);
		objReviewPaymentPage = new ReviewPaymentPage(driver);
		log.info("Application Launch Succesfully");
	}

	@When("user register in the application with the information received from API")
	public void user_register_in_the_application_with_the_information_received_from_api() throws Throwable {
		objCreateNewCustomer.ClickCreateAnAccountLink();
		log.info("User is clicking on the Create an account Link");

		objCreateNewCustomer.fill_personal_information();
		log.info("User is Filling up the Personal Information for account Creation");

		objCreateNewCustomer.ClickCreateAnAccountButton();
		log.info("Account has been created Succesfully");

	}

	@When("user navigate to jacket section page select the jacket")
	public void user_navigate_to_jacket_section_page_select_the_jacket() throws InterruptedException {
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
	}

	@When("user click on place order button")
	public void user_click_on_place_order_button() {
		objReviewPaymentPage.clickPlaceOrderButton();
		log.info("User place the order");
	}

	@After
	public void teardown() {

		driver.quit();
	}
}
