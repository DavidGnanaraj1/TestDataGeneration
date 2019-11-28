package com.atmecs.assessment.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.atmecs.assessment.constants.FilePath;

/**
 * This class is used to read the excel file's particular cell data and get the
 * row count and column count of the excel file
 */

public class ExcelFileReader {

	XSSFWorkbook workbook;
	XSSFSheet sheet;
	File file;
	String path;

	/**
	 * This constructor will get the filepath of the testdata file and create the
	 * Xssfworkbook object
	 */
	public ExcelFileReader(String filePath) throws IOException {

		File file = new File(filePath);
		FileInputStream fileInput = new FileInputStream(file);
		workbook = new XSSFWorkbook(fileInput);
	}

	/**
	 * This method will return the value of the particular cell from the excel file
	 */
	public String gettingExcelFileCellValue(int index, int rowNumber, int cellNumber) {

		sheet = workbook.getSheetAt(index);
		String data = sheet.getRow(rowNumber).getCell(cellNumber).getStringCellValue();
		return data;
	}

	/**
	 * This method will return the total number of rows in an excel file
	 */
	public int countingTheNumberOfRows(String sheetName) {

		int rowCount = workbook.getSheet(sheetName).getLastRowNum();
		return rowCount;
	}

	/**
	 * This method will return the total number of column in an excel file
	 */
	public int countingTheNumberOfColumns(String sheetName, int rowNumber) {

		int columnCount = workbook.getSheet(sheetName).getRow(rowNumber).getLastCellNum();
		return columnCount;
	}

	/**
	 * This method is used by testdata provider to provide each row value to the
	 * maximum column limit
	 */
	public String gettingExcelFileData(int sheetIndex, int rowNumber, int columnNumber) {

		int index;
		String[] array = new String[30];
		for (index = rowNumber; index < array.length; index++) {
			array[index - 1] = gettingExcelFileCellValue(sheetIndex, index, columnNumber);
		}
		return array[index - 1];
	}

	/**
	 * This method is used to get the values of the complete column by using the
	 * sheetname,columnname as an input
	 */
	public String[] gettingValuesOfAColumn(String sheetName, String columnName) {
		int columnNumber = 0;
		int rowNumber = 0;
		String excelColumnData[] = new String[countingTheNumberOfRows(sheetName)];
		for (int cellIndex = 1; cellIndex < countingTheNumberOfColumns(sheetName, rowNumber); cellIndex++) {
			if (workbook.getSheet(sheetName).getRow(rowNumber).getCell(cellIndex).getStringCellValue().trim()
					.equals(columnName)) {
				columnNumber = cellIndex;
			}

			for (int rowindex = 1; rowindex <= countingTheNumberOfRows(sheetName); rowindex++) {

				excelColumnData[rowindex - 1] = workbook.getSheet(sheetName).getRow(rowindex).getCell(columnNumber)
						.getStringCellValue();
			}
		}
		return excelColumnData;
	}
}
