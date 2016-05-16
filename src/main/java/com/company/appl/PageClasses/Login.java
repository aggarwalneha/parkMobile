package com.company.appl.PageClasses;

import com.company.appl.Config.*;


public class Login {
	public static void Success_Login(String Email, String Password){
		libraryFunctions.clearText("Email");
		//enterText(OR_VariableName,Value)
		libraryFunctions.enterText("Email",Email);
		libraryFunctions.clearText("Password");
		libraryFunctions.enterText("Password",Password);
		libraryFunctions.click("loginOn");
	}

}
