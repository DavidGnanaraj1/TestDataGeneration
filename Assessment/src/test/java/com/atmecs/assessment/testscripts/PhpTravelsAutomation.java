package com.atmecs.assessment.testscripts;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.atmecs.assessment.constants.FilePath;
import com.atmecs.assessment.helpermethods.PhptravelsHelpers;
import com.atmecs.assessment.pageactions.AssertionHelpers;
import com.atmecs.assessment.pageactions.PageActions;
import com.atmecs.assessment.reports.LogReport;
import com.atmecs.assessment.testbase.TestBase;
import com.atmecs.assessment.utils.LocatorSeparator;
import com.atmecs.assessment.utils.PropertiesFileReader;

public class PhpTravelsAutomation extends TestBase {

	Properties properties;
	PageActions pageactions;
	Properties testdata;
	LocatorSeparator separatelocator;
	LogReport log;
	AssertionHelpers assertionhelpers;
	PhptravelsHelpers phptravelshelpers;

	public PhpTravelsAutomation() throws IOException {

		pageactions = new PageActions();
		properties = new PropertiesFileReader().loadingPropertyFile(FilePath.LOCATORS_FILE);
		testdata = new PropertiesFileReader().loadingPropertyFile(FilePath.EXPECTEDDATA_FILE);
		separatelocator = new LocatorSeparator();
		assertionhelpers = new AssertionHelpers();
		phptravelshelpers = new PhptravelsHelpers();
		log = new LogReport();
	}

	@Test
	public void bookingAutomation() throws IOException, InterruptedException {

		pageactions.sendKeys(driver, properties.getProperty("loc.usernameinputbox"),
				testdata.getProperty("expdata.username"));
		pageactions.sendKeys(driver, properties.getProperty("loc.password"), testdata.getProperty("expdata.password"));
		pageactions.clickingTheElement(driver, properties.getProperty("loc.userloginbutton"));
		Thread.sleep(3000);
		WebElement bookingsTab = driver
				.findElement(separatelocator.separatingLocators(properties.getProperty("loc.bookingstab")));
		WebElement invoiceElement = driver
				.findElement(separatelocator.separatingLocators(properties.getProperty("loc.invoiceelement")));

		if (invoiceElement.isDisplayed()) {
			log.info("Bookings tab is selected");
		} else {
			log.info("Bookings tabs is not selected by default");
		}
		assertionhelpers.assertingStringTexts(driver, "loc.hiusertext", "expdata.hiusertext");

		Random random = new Random();
		int randomNumber = random.nextInt(35);

		phptravelshelpers.invoiceDetailsValidation(driver, randomNumber);
	}

	@AfterTest
	public void closingTheBrowser() {
		driver.quit();
	}
}
