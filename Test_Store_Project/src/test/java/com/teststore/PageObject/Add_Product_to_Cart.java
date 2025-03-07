package com.teststore.PageObject;


import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.teststore.Utilities.PageUtilities;
import com.teststore.helper.LoggerHelper;

public class Add_Product_to_Cart extends PageUtilities {
	private Logger log = LoggerHelper.getlogger(Add_Product_to_Cart.class);
	public Add_Product_to_Cart(WebDriver driver) {
		super(driver);
	}

	private By FirstJacket = By.xpath(".//ol[@class='products list items product-items']/li[1]/div//img");
	private By Jacketsize = By.xpath(".//div[@class='swatch-opt']/div[1]/div/div[1]");
	private By JacketColor = By.xpath(".//div[@class='swatch-opt']/div[2]/div/div[1]");
	private By addtocartButton = By.xpath(".//button[@id='product-addtocart-button']/span");
	private By CheckProductaddedinCartMessage = By.xpath(".//div[@class='page messages']");
	private By minicartbutton = By.xpath(".//div[@data-block='minicart']");
	private By ProceedtoCheckoutButton = By.id("top-cart-btn-checkout");
	private By mainmenun = By.xpath(".//div[@id='store.menu']//a[contains(@href,'/men.html')]");
	private By Submenu = By.xpath(".//div[@id='store.menu']//a[contains(@href,'/tops-men.html')]");
	private By subsection = By.xpath(".//div[@id='store.menu']//a[contains(@href,'/jackets-men.html')]");

	public void navigatetoMensJacketPage() {
		waitforvisibilityofText(mainmenun);
		menu_navigation(mainmenun, Submenu, subsection);
	}

	public void select_the_First_Jacket() {
		clicklink(FirstJacket);

	}

	public void select_the_Size() {

		waitforvisibilityofText(Jacketsize);
		clickButton(Jacketsize);
	}

	public void select_the_Color() {
		clickButton(JacketColor);
	}

	public void click_Add_to_Cart_button() {
		clickButton(addtocartButton);
	}

	public boolean verify_product_added_in_cart() {
		boolean flag = false;
		waitforvisibilityofText(CheckProductaddedinCartMessage);
		try {
			String gettext = getTextfromUi(CheckProductaddedinCartMessage);
			log.info("Check Order Status Message :: "+gettext);
			if (gettext.contains("added")) {
				flag = true;
			}
			System.out.println(gettext);
		} catch (Exception e) {
			System.out.println(e);
			flag = false;
		}
		return flag;

	}

	public void click_on_Proceed_to_Checkout_button() throws InterruptedException {
		Thread.sleep(5000);
		waitforelementToBeClickable(minicartbutton);
		clickButton(minicartbutton);
		waitforelementToBeClickable(ProceedtoCheckoutButton);
		clickButton(ProceedtoCheckoutButton);

	}

}
