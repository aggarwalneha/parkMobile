package com.company.appl.Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.company.appl.Config.*;

public class ExcelUtils {
	
	private static XSSFSheet ExcelWsheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	
		
	public static Object getCellData(int Row, int Col)
	{
		Cell = ExcelWsheet.getRow(Row).getCell(Col);
		Object CellValue = GetDataFromExcelCell(Cell);
		return CellValue;
		
	}
	
	
	public static Object GetDataFromExcelCell(XSSFCell cell){
		
		switch (cell.getCellType()) {
        case 1: //cell.CELL_TYPE_STRING:
            //System.out.println(cell.getRichStringCellValue().getString());
        	return cell.getRichStringCellValue().getString();
            //break;
        case 0: //cell.CELL_TYPE_NUMERIC:
            if (DateUtil.isCellDateFormatted(cell)) {
                return cell.getDateCellValue();
            } else {
            	return cell.getNumericCellValue();
            }
          
        case 4: // cell.CELL_TYPE_BOOLEAN:
        	return cell.getBooleanCellValue();
            
        case 2: //cell.CELL_TYPE_FORMULA:
        	return cell.getCellFormula();
            
        default:
            return null;
    }
	}

	public static void setExcelFile(String filePath, String sheetName) throws IOException {
		// TODO Auto-generated method stub
		InputStream fis = new FileInputStream(filePath);
		ExcelWBook = new XSSFWorkbook(fis);
		ExcelWsheet = ExcelWBook.getSheet(sheetName);
	}
	
	
	public static int getLastRowNumber() {
		// TODO Auto-generated method stub
		int LastRowNum = ExcelWsheet.getLastRowNum();
		return LastRowNum;
	}
	
	public static int getLastColCount(int row){
		int LastColumnCount = ExcelWsheet.getRow(row).getLastCellNum();
		return LastColumnCount;
		
	}
	
	public static String ConvertDoubleToIntToString(Object var){
		Double D = (Double) var;
		int i = Integer.valueOf(D.intValue());
		return String.valueOf(i);			
	}
	
	

}
