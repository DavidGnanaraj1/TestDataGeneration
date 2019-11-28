package com.atmecs.assessment.testscripts;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import com.atmecs.assessment.constants.FilePath;
import com.atmecs.assessment.helpermethods.PhptravelsHelpers;
import com.atmecs.assessment.helpermethods.PolicyEffectiveDateCalculatorHelpers;
import com.atmecs.assessment.pageactions.AssertionHelpers;
import com.atmecs.assessment.pageactions.PageActions;
import com.atmecs.assessment.reports.LogReport;
import com.atmecs.assessment.testbase.TestBase;
import com.atmecs.assessment.utils.ExcelFileReader;
import com.atmecs.assessment.utils.ExcelFileWriter;
import com.atmecs.assessment.utils.LocatorSeparator;
import com.atmecs.assessment.utils.PropertiesFileReader;

public class PolicyEffectiveDateCalculator {

	Properties properties;
	PageActions pageActions = new PageActions();
	Properties testData;
	LocatorSeparator separateLocator = new LocatorSeparator();
	LogReport log = new LogReport();
	AssertionHelpers assertionHelpers = new AssertionHelpers();
	PhptravelsHelpers phpTravelsHelpers = new PhptravelsHelpers();
	ExcelFileReader excelReader;
	ExcelFileWriter excelWriter = new ExcelFileWriter();
	PolicyEffectiveDateCalculatorHelpers policyDateHelpers = new PolicyEffectiveDateCalculatorHelpers();

	public PolicyEffectiveDateCalculator() throws IOException {

		properties = new PropertiesFileReader().loadingPropertyFile(FilePath.LOCATORS_FILE);
		testData = new PropertiesFileReader().loadingPropertyFile(FilePath.EXPECTEDDATA_FILE);
		excelReader = new ExcelFileReader(FilePath.TESTDATA_FILE);

	}

	@Test
	public void policyDateEvaluator() throws IOException {

		int arrayInputIndex = 0;
		String sheetName = testData.getProperty("expdata.inputsheetname");
		int noOfDataToBeValidated = excelReader.countingTheNumberOfRows(sheetName);
		String excelWritingInput[] = new String[noOfDataToBeValidated];

		String inputDetailsForDatemethodString[] = new String[noOfDataToBeValidated];
		int inputDetailsForDateMethod[] = new int[noOfDataToBeValidated];
		inputDetailsForDatemethodString = excelReader.gettingValuesOfAColumn(sheetName,
				testData.getProperty("expdata.columnname"));

		for (int index = 0; index < noOfDataToBeValidated; index++) {

			inputDetailsForDateMethod[index] = Integer.parseInt(inputDetailsForDatemethodString[index]);

		}

		excelWritingInput[arrayInputIndex] = policyDateHelpers
				.subTractsTheNoOfMonths(inputDetailsForDateMethod[arrayInputIndex]);
		excelWritingInput[arrayInputIndex + 1] = policyDateHelpers
				.subTractsTheNoOfMonths(inputDetailsForDateMethod[arrayInputIndex]);
		excelWritingInput[arrayInputIndex + 2] = policyDateHelpers
				.subTractsTheNoOfMonths(inputDetailsForDateMethod[arrayInputIndex]);

		excelWriter.excelFileWriter(excelWritingInput, 2, 1, 4);

	}
}
