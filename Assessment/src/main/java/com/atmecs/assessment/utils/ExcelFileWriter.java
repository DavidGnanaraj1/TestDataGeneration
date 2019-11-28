package com.atmecs.assessment.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

import com.atmecs.assessment.constants.FilePath;

public class ExcelFileWriter {

	Properties testData;
	XSSFSheet sheet;
	ExcelFileReader excelReader; 

	public ExcelFileWriter() throws IOException {
		excelReader = new ExcelFileReader(FilePath.TESTDATA_FILE);
		testData = new PropertiesFileReader().loadingPropertyFile(FilePath.EXPECTEDDATA_FILE);
	}

	/**
	 * This method is used to write the data in the excel file by providing the
	 * inputdata in array with initialrownumber ,finalrownumber,column number as an
	 * input
	 */
	public void excelFileWriter( String excelDataInput[], int columnNumber, int initialRowNumber,
			int finalRowNumber) throws IOException {

		File file = new File(FilePath.TESTDATA_FILE);
		FileInputStream fileInput = new FileInputStream(file);
		String sheetName = testData.getProperty("expdata.outputsheetname");
		XSSFWorkbook workBook = new XSSFWorkbook(fileInput);
		sheet = workBook.getSheet(sheetName);

		for (int rowIndex = 1; rowIndex < finalRowNumber; rowIndex++) {

			Row row = sheet.createRow(rowIndex);
			Cell cell = row.createCell(columnNumber);
			cell.setCellValue(excelDataInput[rowIndex - 1]);

		}
		try {
			FileOutputStream fileOut = new FileOutputStream(FilePath.TESTDATA_FILE);
			workBook.write(fileOut);
			fileOut.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}