package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	// DataProvider 1
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException{
		String path=".\\testData\\Opencart_LoginData.xlsx"; // taking xl file from testData
		
		ExcelUtility xlutil = new ExcelUtility(path);	// creating an object from XLUtility
		
		int totalRows = xlutil.getRowCount("Sheet1");
		int totalCols = xlutil.getCellCount("Sheet1", 1);
		
		String logindata[][] = new String[totalRows][totalCols];	// Created for two dimension array which can store
		
		for(int i=1; i<=totalRows; i++) {
			for(int j=0; j<totalCols; j++) {
				logindata[i-1][j] = xlutil.getCellData("Sheet1", i, j);  // 1,0
			}
		}
		
		return logindata;
			
	}

}
