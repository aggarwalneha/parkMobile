package com.company.appl.PageClasses;

import com.company.appl.Config.libraryFunctions;

public class StartParking {
	
	public static void clickStartParking(){
		//Retrieve startParking link
		
		libraryFunctions.clickLink("StartParkingLink"); //"StartParkingLink" is OR property name
	}
	
	public static void UserOnVerifyStartParkingPage(){
		libraryFunctions.explicitwait("StartParkingPageVerify_Span", 20);//"StartParkingPageVerify_Span" is OR property name
	}
	
	public static void VerifyUserTab(int TimeOut){
		libraryFunctions.explicitwait("UserTabVerify", TimeOut);//"UserTabVerify" is OR property name
	}
		
	public static void StartParking_Hours(double waittimeout,String LPN_Value, String Zone_Value,String location,String Hours){
		clickStartParking();
		//UserOnVerifyStartParkingPage();
		//VerifyUserTab(waittimeout);
		libraryFunctions.clickLink("User_Tab");//"User_Tab" is OR property name
		//VerifyUserTab(waittimeout);
		libraryFunctions.selectDropdown("LPN", LPN_Value);//"StartParkingLink" is OR property name
		libraryFunctions.enterText("Zone", Zone_Value);//"Zone" is OR property name
		libraryFunctions.click("StartParkingButton");
		libraryFunctions.FindElementsByText("Location", location);//"Location" is OR property name
		libraryFunctions.click("StartParkingButton");
		/*if(libraryFunctions.ObjPresent("Custom")){//"Custom" is OR property name
			libraryFunctions.click("Custom");
		}*/
		libraryFunctions.explicitwait("Hours", (int)waittimeout);//"Hours" is OR property name
		//libraryFunctions.SetTextInInputElement("Hours", Hours);
		libraryFunctions.enterText("Hours", Hours);
		libraryFunctions.click("StartParkingButton");
		libraryFunctions.explicitwait("AgreeStartParkingConditions", (int)waittimeout);
		//libraryFunctions.selectCheckbox("AgreeStartParkingConditions");
		libraryFunctions.click("AgreeStartParkingConditions");
		libraryFunctions.click("StartParkingButton");
		
	}

	public static void StartParking_Minutes(double waittimeout, String LPN_Value, String Zone_Value, String location,
			String minutes) {
		// TODO Auto-generated method stub
		clickStartParking();
		//UserOnVerifyStartParkingPage();
		//VerifyUserTab(waittimeout);
		libraryFunctions.clickLink("User_Tab");//"User_Tab" is OR property name
		//VerifyUserTab(waittimeout);
		libraryFunctions.selectDropdown("LPN", LPN_Value);//"StartParkingLink" is OR property name
		libraryFunctions.enterText("Zone", Zone_Value);//"Zone" is OR property name
		libraryFunctions.click("StartParkingButton");
		libraryFunctions.FindElementsByText("Location", location);//"Location" is OR property name
		libraryFunctions.click("StartParkingButton"); 
		//if(libraryFunctions.ObjPresent("Custom")){//"Custom" is OR property name
			libraryFunctions.click("Custom");
		//}
		libraryFunctions.explicitwait("Minutes", (int)waittimeout);//"Minutes" is OR property name
		//libraryFunctions.SetTextInInputElement("Hours", Hours);
		libraryFunctions.enterText("Minutes", minutes);
		libraryFunctions.click("StartParkingButton");
		libraryFunctions.explicitwait("AgreeStartParkingConditions", (int)waittimeout);
		//libraryFunctions.selectCheckbox("AgreeStartParkingConditions");
		libraryFunctions.click("AgreeStartParkingConditions");
		libraryFunctions.click("StartParkingButton");
	}
	

}
