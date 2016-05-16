package com.company.appl.Config;

//import com.company.appl.Utility.ExcelUtils;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.map.HashedMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.company.appl.Utility.ExcelUtils;



public class libraryFunctions {
	
	public static WebDriver driver = null;
	public static Properties OR = null;
	public static Properties Prop = null;
	public static HashMap<String, HashMap<String, String>> data_map = null;
	public static HashMap<Object,Object> map = null;
	public static ArrayList<HashMap<String,String>> al = null;
	
	public static int MatchExcelColumnData(int ColumnCount,String TestMethodName,String FilePath, String SheetName){
		try {
			
			ExcelUtils.setExcelFile(FilePath, SheetName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int LastRowNum = ExcelUtils.getLastRowNumber();
		int Rownum=-1;
		for (int iRow=1;iRow<=LastRowNum;iRow++)
		{
			if(TestMethodName.equalsIgnoreCase(ExcelUtils.getCellData(iRow,ColumnCount).toString())){
				Rownum =iRow;
				break;
			}
		}
		
		return Rownum;
		
	}
	
	
	public static HashMap<Object,Object> ReadExcelDataToHashMapForSpecificRow(int rowCount,String FilePath, String SheetName){
		try {
			
			ExcelUtils.setExcelFile(FilePath, SheetName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Object RowData = null;
		Object ColData = null;
		int LastColNum = ExcelUtils.getLastColCount(rowCount);
		map = new HashMap<Object,Object>();
		
		
		for (int iCol=1;iCol<LastColNum;iCol++)
		{
			ColData = ExcelUtils.getCellData(0, iCol);
   			RowData = ExcelUtils.getCellData(rowCount, iCol);
   			if(!(map.containsKey(ColData)))
   				{
   				map.put(ColData, RowData);
   				}
		}
		return map;
		
	}
	
	
	
	
	
	
	
	public static void IterateNestedHashmap(HashMap<String, HashMap<String, String>> data){
		
		for (Entry<String, HashMap<String, String>> map : data.entrySet()) {
		    String RowID = map.getKey();
		    // ...
		    for (Entry<String, String> data_map : map.getValue().entrySet()) {
		        String colName = data_map.getKey();
		        String value = data_map.getValue();
		        // ...
		        //System.out.println("RowID is: "+RowID);
		        //System.out.println("colName is: "+colName);
		        //System.out.println("value is: "+value);
		    }
		}
		
	}
	
	public static WebDriver IntializeDriver(){
		//FirefoxProfile fp = new FirefoxProfile();
	
		driver = new FirefoxDriver();
		
		//System.setProperty("webdriver.ie.driver", "D:\\Utilities\\IEDriverServer_x64_2.52.0\\IEDriverServer.exe");
		//driver = new InternetExplorerDriver();
		return driver;
	}
	public static void navigateToUrl(String url){
		driver.get(url);
		
		
	}
	public static void implicitWait(){
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	public static void explicitwait(String obj,Integer WaitTime){
		try
		{
			WebDriverWait wait = new WebDriverWait(driver,WaitTime);
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(obj))));
		    
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		
	}
	public static void clearText(String obj){
		//name of obj parameter should be same as the name of locator key for the WebElement in OR file
		driver.findElement(By.xpath(OR.getProperty(obj))).clear();
	}
	public static void enterText(String obj, String data){
		//name of obj parameter should be same as the name of locator key for the WebElement in OR file
		driver.findElement(By.xpath(OR.getProperty(obj))).sendKeys(data);
	}
	public static void click(String obj){
		//name of obj parameter should be same as the name of locator key for the WebElement in OR file
		driver.findElement(By.xpath(OR.getProperty(obj))).click();
	}
	
	public static void selectDropdown(String obj, String value){
		WebElement ele = driver.findElement(By.xpath(OR.getProperty(obj)));
		Select sel = new Select(ele);
		sel.deselectByVisibleText(value);
		
	}
	public static void SetTextInInputElement(String obj, String value){
		WebElement ele = driver.findElement(By.xpath(OR.getProperty(obj)));
		ele.sendKeys(value);
	}
	public static void selectCheckbox(String obj){
		WebElement ele = driver.findElement(By.xpath(OR.getProperty(obj)));
		ele.click();
	}
	
	public static void clickLink(String obj){
		libraryFunctions.implicitWait();
		List<WebElement> linkList = driver.findElements(By.tagName("a"));
		A: for(WebElement link : linkList ){
			System.out.println(link.getText());
			if(link.getText().equals(OR.getProperty(obj))){
				link.click();
				break A;
			}
		}
		//System.out.println("WinHandle is: " +driver.getWindowHandle());
		//System.out.println("WinHandle is: " +driver.getTitle());
	}
	
	public static boolean ObjPresent(String obj){
		return driver.findElement(By.xpath(OR.getProperty(obj))).isDisplayed();
	}
	
	public static void loadOR(String filepath) throws IOException{
		//Create Object of Properties Class. 
		OR = new Properties(); 
		//Create Object of FileInputStream Class. Pass file path. 
		FileInputStream objfile = new FileInputStream(filepath); 
		//Pass object reference objfile to load method of Properties object. 
		OR.load(objfile);
	}
	
	
	
	public static String RetrieveTestDataScenarioNameMatchingTestMethod(String TestMethodName){
		 //Retrieve TestData Scenario Name Matching TestMethod
		String MethodName = Prop.getProperty(TestMethodName);
		return MethodName;	
	}
	
	public static void loadProp(String filepath) throws IOException{
		//Create Object of Properties Class. 
		Prop = new Properties(); 
		//Create Object of FileInputStream Class. Pass file path. 
		FileInputStream objfile = new FileInputStream(filepath); 
		//Pass object reference objfile to load method of Properties object. 
		Prop.load(objfile);
	}
	
	public static WebElement findElement(String locatorKey){
		WebElement ele = driver.findElement(By.xpath(OR.getProperty(locatorKey)));
		return ele;
		
	}
	
	public static void FindElementsByText(String obj,String eletext){
		libraryFunctions.implicitWait();
		List<WebElement> ObjList = driver.findElements(By.tagName("label"));
		A: for(WebElement ele : ObjList ){
			System.out.println(ele.getText());
			if(ele.getText().equals(eletext)){
				ele.click();
				break A;
			}
		}
		
	}
	
}
