package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	//private static final String TEST_DATA_SHEET = "./src\\test\\resources\\testdata\\Democart.xlsx";

	private static Workbook book;
	private static Sheet sheet;

	public  Object[][] getTestData(String fileName, String sheetName) {
		Object data[][] = null;
		FileInputStream ip;
		try {
			ip = new FileInputStream(fileName);
			book = WorkbookFactory.create(ip);
			sheet = book.getSheet(sheetName);
			
			// data = new Object[4][5];
					
			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			System.out.println(sheet.getLastRowNum());
			System.out.println("columns of sheet"+sheetName+sheet.getRow(0).getLastCellNum());
			for (int i=0;i<sheet.getLastRowNum();i++)
			{
				for (int j = 0; j<sheet.getRow(0).getLastCellNum();j++)
				{
					System.out.println(sheet.getRow(i+1).getCell(j).toString());
					data[i][j] =  sheet.getRow(i+1).getCell(j).toString();
				}
			}
			
			
		} catch (FileNotFoundException e1) {

			e1.printStackTrace();
		} catch (InvalidFormatException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return data;
	}

}
