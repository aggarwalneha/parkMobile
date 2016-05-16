package com.company.appl.Runner;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Properties;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.company.appl.Config.Constants;
import com.company.appl.Config.libraryFunctions;
import com.company.appl.PageClasses.*;
import com.company.appl.Utility.ExcelUtils;

public class TestRunner {
	public static HashMap<Object,Object> testdata;
	//public static Properties prop = null;
	
	@BeforeSuite
	public void beforeSuiteMethod() throws IOException{
		libraryFunctions.loadOR(Constants.Path_OR);
		libraryFunctions.loadProp(Constants.Path_Prop);
		libraryFunctions.IntializeDriver();
		libraryFunctions.navigateToUrl(Constants.url);
		libraryFunctions.implicitWait();
	}
	
	@AfterMethod
	public void AfterTestMethod(){
		tearDown.Do_Logoff();
	}
	
	@Test(dataProvider = "TestDataProvider",enabled = false)
	public void Login_Success(HashMap<Object,Object> map)
	{
		String Email = map.get("loginName").toString();
		String Password = map.get("password").toString();
		Login.Success_Login(Email, Password);
	 
	}
	
	@Test(dataProvider = "TestDataProvider")
	public void StartParkingByHours(HashMap<Object,Object> map){
		
		double waittimeout = ((Double)(map.get("waittimeout"))).doubleValue();
		String LPN_Value = ExcelUtils.ConvertDoubleToIntToString(map.get("LPN_Value"));
		String Zone_Value = ExcelUtils.ConvertDoubleToIntToString(map.get("Zone_Value"));
		String Location = map.get("Location").toString();
		String Hours = ExcelUtils.ConvertDoubleToIntToString(map.get("Hours"));
		//String Minutes = ExcelUtils.ConvertDoubleToIntToString(map.get("Minutes"));
		String Email = map.get("loginName").toString();
		String Password = map.get("password").toString();
		Login.Success_Login(Email, Password);	
		StartParking.StartParking_Hours(waittimeout,LPN_Value,Zone_Value,Location,Hours);
	}
	
	@Test( dataProvider = "TestDataProvider")
	public void StartParkingByMinutes(HashMap<Object,Object> map){
		double waittimeout = ((Double)(map.get("waittimeout"))).doubleValue();
		String LPN_Value = ExcelUtils.ConvertDoubleToIntToString(map.get("LPN_Value"));
		String Zone_Value = ExcelUtils.ConvertDoubleToIntToString(map.get("Zone_Value"));
		String Location = map.get("Location").toString();
		String Minutes = ExcelUtils.ConvertDoubleToIntToString(map.get("Minutes"));
		String Email = map.get("loginName").toString();
		String Password = map.get("password").toString();
		Login.Success_Login(Email, Password);	
		StartParking.StartParking_Minutes(waittimeout,LPN_Value,Zone_Value,Location,Minutes);
	}
	@DataProvider(name = "TestDataProvider")
	public static Object[][] getDataFromDataProvider(Method m){
		String TestMethodName = m.getName();
		String MethodName =libraryFunctions.RetrieveTestDataScenarioNameMatchingTestMethod(TestMethodName);
		testdata = new HashMap<Object,Object>();
		int Row = libraryFunctions.MatchExcelColumnData(Constants.DataSheetScenarioCol, MethodName, Constants.user_testdata_excel, Constants.userDataSheet_excel);
		testdata = libraryFunctions.ReadExcelDataToHashMapForSpecificRow(Row, Constants.user_testdata_excel, Constants.userDataSheet_excel);
		
		return new Object[][]{
			{testdata}};
	}
  
}
