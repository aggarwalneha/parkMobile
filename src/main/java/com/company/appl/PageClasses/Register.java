package com.company.appl.PageClasses;

import com.company.appl.Config.libraryFunctions;

public class Register {
	
	public static void UserNavigateToSignUpScreen(){
		libraryFunctions.click("SignUp"); // Here SignUp is OR property name.
	}
	
	public static void BasicMemberShip_UserRegister(){
		UserNavigateToSignUpScreen();
		libraryFunctions.click("BasicMembership");
		libraryFunctions.click("NextBtn");
	}
	
	
}
