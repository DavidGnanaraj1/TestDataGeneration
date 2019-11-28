package com.atmecs.assessment.pageactions;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.atmecs.assessment.constants.FilePath;
import com.atmecs.assessment.utils.LocatorSeparator;
import com.atmecs.assessment.utils.PropertiesFileReader;

/**
 * This class has methods for various assertion like asserting two string text,
 * verifying the expected page title with the actual page title
 */

public class AssertionHelpers {
	LocatorSeparator separatingLocators = new LocatorSeparator();
	Properties properties;
	PageActions pageActions = new PageActions();
	Properties testData;

	/**
	 * This constructor loads the locators.properties file
	 */
	public AssertionHelpers() throws IOException {

		properties = new PropertiesFileReader().loadingPropertyFile(FilePath.LOCATORS_FILE);
		testData = new PropertiesFileReader().loadingPropertyFile(FilePath.EXPECTEDDATA_FILE);
	}

	/**
	 * This method gets the actual string text from webpage and get the expected
	 * string text from the properties assert whether actual string texts and
	 * expected string texts are equal or not
	 */
	public void assertingStringTexts(WebDriver driver, String actualElementLocator, String expectedElementLocator)
			throws IOException {

		WebElement actualTextElement = driver
				.findElement(separatingLocators.separatingLocators(properties.getProperty(actualElementLocator)));
		String actualTextValue = actualTextElement.getText();
		String expectedTextValue = testData.getProperty(expectedElementLocator);

		Assert.assertEquals(actualTextValue, expectedTextValue);

	}

	/**
	 * This method gets the actual pagetitle from webpage and get the expected page
	 * title from properties file assert whether actual pagetitle and expected
	 * pagetitle are equal or not
	 */
	public void assertingPageTitle(WebDriver driver, String expectedTitleLocator) {

		String actualPageTitle = driver.getTitle();
		String expectedPageTitle = properties.getProperty(expectedTitleLocator);
		Assert.assertEquals(actualPageTitle, expectedPageTitle);
	}

	/**
	 * This method is used for getting the actual multiple text values and asserting
	 * it with the given expected array values
	 */
	public String assertingMultipleValues(WebDriver driver, String expectedDataArray[], int iterationNumber,
			int numberOfProducts, String actualCommonElementLocator) {

		String returnStatement = null;

		for (int index = 0; index < iterationNumber; index++) {

			List<WebElement> actualdata = driver.findElements(
					separatingLocators.separatingLocators(properties.getProperty(actualCommonElementLocator)));
			String actualdataarray[] = new String[numberOfProducts];
			actualdataarray[index] = actualdata.get(index).getText();
			try {
				Assert.assertEquals(actualdataarray[index], expectedDataArray[index]);
				return returnStatement = "Validation completed for " + expectedDataArray[index];
			} catch (Exception e) {
				e.printStackTrace();
				return returnStatement = "Assertion failed" + actualdataarray[index] + "," + expectedDataArray[index]
						+ " not matching";

			}
		}
		return returnStatement;

	}
}
