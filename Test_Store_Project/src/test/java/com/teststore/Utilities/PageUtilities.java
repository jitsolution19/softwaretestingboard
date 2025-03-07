package com.teststore.Utilities;

import java.time.Duration;
import java.util.function.Consumer;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.teststore.helper.LoggerHelper;

public class PageUtilities extends WebDriverUtilites {
	private Logger log = LoggerHelper.getlogger(PageUtilities.class);

	private WebDriver driver;

	protected PageUtilities(WebDriver driver) {
		this.driver = driver;
	}

	public boolean clicklink(By webelement) {
		driver.findElement(webelement).click();
		log.info("User clicked on the Link");
		return true;
	}

	public void enterText(By webelement, String information) {
		driver.findElement(webelement).clear();
		driver.findElement(webelement).sendKeys(information);
		log.info("User enter the information :: " + information);
	}

	public String getTextfromUi(By webelement) {
		String text = driver.findElement(webelement).getText();
		log.info("User get the information :: " + text);
		return text;
	}

	public void waitforvisibilityofText(By webelement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(webelement)));

	}

	public void waitforelementToBeClickable(By webelement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(webelement)));
	}

	public void waitforpresenceOfAllElements(By webelement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(webelement));
	}

	public boolean clickButton(By webelement) {
		driver.findElement(webelement).click();
		log.info("User clicked on the Button");
		return true;
	}

	public boolean selectfromdropdown(By webelement, String text) {
		Select select = new Select(driver.findElement(webelement));
		try {
			select.selectByVisibleText(text);
			log.info("User select the [" + text + "] from the dropdown");
		} catch (NoSuchElementException e) {
			select.selectByIndex(5);
		}
		return true;
	}

	public boolean selectradioButton(By webelement) {
		driver.findElement(webelement).click();
		log.info("User clicked on the radio button");
		return true;
	}

	public boolean menu_navigation(By mainmenun, By Submenu, By subsection) {
		boolean flag = false;
		try {
			Actions action = new Actions(driver);
			Consumer<By> hover = (By by) -> {
				action.moveToElement(driver.findElement(by)).perform();
			};
			hover.accept(mainmenun);
			hover.accept(Submenu);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			WebElement webelement = wait.until(ExpectedConditions.visibilityOfElementLocated(subsection));
			hover.accept(subsection);
			webelement.click();
			flag = true;
		} catch (Exception e) {
			System.out.println(e);
			flag = false;
		}
		return flag;
	}
}
