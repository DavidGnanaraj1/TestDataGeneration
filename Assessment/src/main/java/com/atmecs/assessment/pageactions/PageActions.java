package com.atmecs.assessment.pageactions;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.atmecs.assessment.constants.FilePath;
import com.atmecs.assessment.reports.LogReport;
import com.atmecs.assessment.utils.LocatorSeparator;
import com.atmecs.assessment.utils.PropertiesFileReader;

/**
 * This class has methods to perform actions in an element like clicking the
 * element,selecting the dropdown, sending the values to textbox, to check
 * whether an element is displayed or not,moveByOffset,MouseOver
 */

public class PageActions {
	LogReport log;
	WebDriver driver;
	LocatorSeparator separateLocator;
	ExplicitWaitHelpers explicitWaitHelpers;
	Properties properties;

	public PageActions() throws IOException {

		log = new LogReport();
		separateLocator = new LocatorSeparator();
		explicitWaitHelpers = new ExplicitWaitHelpers();
		properties = new PropertiesFileReader().loadingPropertyFile(FilePath.LOCATORS_FILE);
	}

	/**
	 * This method is used for clicking an element
	 */
	public void clickingTheElement(WebDriver driver, String locatorWithType) {

		WebElement elementToBeClicked = driver.findElement(separateLocator.separatingLocators(locatorWithType));
		isDisplayed(driver, locatorWithType);
		explicitWaitHelpers.explicitwaitForElementToBeClickable(driver, elementToBeClicked);
		elementToBeClicked.click();
	}

	/**
	 * This method is used for selecting the dropdown value by using the visisble
	 * text
	 */
	public void selectingTheDropdownvalueByVisibleText(WebElement element, String locatorWithType, String value) {
		isDisplayed(driver, locatorWithType);
		explicitWaitHelpers.explicitwaitForElementToBeClickable(driver, element);
		WebElement dropDownElement = driver.findElement(separateLocator.separatingLocators(locatorWithType));
		Select select = new Select(dropDownElement);
		select.selectByVisibleText(value);
	}

	/**
	 * This method is used for sending the value to an text box
	 */
	public void sendKeys(WebDriver driver, String locatorWithType, String value) {
		isDisplayed(driver, locatorWithType);
		WebElement element = driver.findElement(separateLocator.separatingLocators(locatorWithType));
		element.sendKeys(value);
	}

	/**
	 * This method is used for checking the given element is displayed or not
	 */
	public void isDisplayed(WebDriver driver, String locatorWithType) {

		boolean isDisplayed = driver.findElement(separateLocator.separatingLocators(locatorWithType)).isDisplayed();

		if (isDisplayed == false) {
			log.info("Element not found");
		}
	}

	/**
	 * This method is used to move the mouse and keep it over an element
	 */
	public void moveToAnElement(WebDriver driver, WebElement element) {

		boolean isDisplayed = element.isDisplayed();

		if (isDisplayed == false) {
			log.info("Element not found");
		}

		Actions actions = new Actions(driver);
		actions.moveToElement(element).build().perform();
	}

	/**
	 * This method is used to move the mouse by the given X,Y values
	 */
	public void moveByOffsetHelper(WebDriver driver, String locatorWithType) {

		isDisplayed(driver, locatorWithType);
		WebElement elm = driver.findElement(separateLocator.separatingLocators(locatorWithType));
		Point pt = elm.getLocation();
		int NumberX = pt.getX();
		int NumberY = pt.getY();
		Actions act = new Actions(driver);
		act.moveByOffset(NumberX + 1, NumberY).click().build().perform();
	}

	/**
	 * This method is used the user reached the correct page or not by making the
	 * log statements by providing target page element locator
	 */
	public void checkingPageRedirection(WebDriver driver, String targetPageElementLocator) {

		WebElement targetPageElement = driver
				.findElement(separateLocator.separatingLocators(properties.getProperty(targetPageElementLocator)));
		boolean isDisplayed = targetPageElement.isDisplayed();

		if (isDisplayed == true) {
			log.info("Reached the corrected page");
		} else if (isDisplayed == false) {
			log.info("Not in the correct page");
		}

	}

	/**
	 * This method is used to check whether the element given with type is selected
	 * or not
	 */
	public void isSelected(WebDriver driver, String locatorWithType) {
		WebElement elementToBeCheckedWhetherSelected = driver
				.findElement(separateLocator.separatingLocators(locatorWithType));

		if (elementToBeCheckedWhetherSelected.isSelected()) {
			log.info(elementToBeCheckedWhetherSelected + "is selected ");

		} else if (!(elementToBeCheckedWhetherSelected.isSelected())) {
			log.info(elementToBeCheckedWhetherSelected + "not selected ");
		}
	}

}