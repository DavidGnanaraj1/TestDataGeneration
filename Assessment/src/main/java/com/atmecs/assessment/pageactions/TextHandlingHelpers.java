package com.atmecs.assessment.pageactions;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.atmecs.assessment.constants.FilePath;
import com.atmecs.assessment.reports.LogReport;
import com.atmecs.assessment.utils.LocatorSeparator;
import com.atmecs.assessment.utils.PropertiesFileReader;

/**
 * 
 *
 */
public class TextHandlingHelpers {
	LogReport log = new LogReport();
	WebDriver driver;
	LocatorSeparator separateHocator = new LocatorSeparator();
	ExplicitWaitHelpers explicitWaitHelpers = new ExplicitWaitHelpers();
	Properties properties;
	PageActions pageActions = new PageActions();

	public TextHandlingHelpers() throws IOException {
		properties = new PropertiesFileReader().loadingPropertyFile(FilePath.LOCATORS_FILE);
	}

	/**
	 * This method is used to get the text of the webelement for the locator given
	 */
	public String gettingTextValue(WebDriver driver, String locatorWithType) {

		pageActions.isDisplayed(driver, locatorWithType);
		String elementText = driver.findElement(separateHocator.separatingLocators(locatorWithType)).getText();
		return elementText;
	}

	/**
	 * This method is used for splitting the sentence into string array and returns
	 * the required index of the array
	 */
	public String splittingStringText(WebDriver driver, String actualMultipleTextLocator, int arrayElementNeeded) {

		String multipleTextArray[] = null;
		String multipleText = driver
				.findElement(separateHocator.separatingLocators(properties.getProperty(actualMultipleTextLocator)))
				.getText();
		multipleTextArray = multipleText.split("\\s");
		return multipleTextArray[arrayElementNeeded - 1];

	}

	/**
	 * This method is used for splitting the sentence into string array and returns
	 * the complete array
	 */
	public String[] returningArrayAfterSplittingText(WebDriver driver, String actualMultipleTextLocator) {

		String multipleTextArray[] = null;
		String multipleText = driver
				.findElement(separateHocator.separatingLocators(properties.getProperty(actualMultipleTextLocator)))
				.getText();
		multipleTextArray = multipleText.split("\\s");
		return multipleTextArray;

	}

	/**
	 * This method gets the value of the element by using the javascript
	 * executor-query selector
	 */
	public void gettingDynamicValues(WebDriver driver, String elementCssLocator) {

		JavascriptExecutor javascript = (JavascriptExecutor) driver;
		String vaalue = javascript.executeScript("return document.querySelector(\"input[name='adults']\").value")
				.toString();
		System.out.println(vaalue);
	}

	/**
	 * This method will get the value attribute of the given attribute
	 */
	public String getAttributeValue(WebDriver driver, String locatorWithType, String value) {
		pageActions.isDisplayed(driver, locatorWithType);
		WebElement element = driver.findElement(separateHocator.separatingLocators(locatorWithType));
		String text = element.getAttribute(value);
		return text;
	}
}
