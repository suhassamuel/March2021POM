package com.qa.opencart.utils;

import java.util.Arrays;
import java.util.List;

public class Constants {

	public final static String LOGIN_PAGE_TITLE = "Account Login";
	public final static String ACCOUNT_PAGE_TITLE = "My Account";
	public final static String ACCOUNT_PAGE_HEADER = "Your Store";
	public final static String ACCOUNT_SUCCESS_HEADER = "Your Account Has Been Created!";
	public final static String ACCOUNT_PAGELINKS_SHEET_NAME = "pagelinks";
	
	public final static String REGISTER_SUCCESS_MESSG = "Your Account Has Been Created!";
	
	
	public final static String REGISTER_SHEET_NAME = "register";
	
	public  static List<String> getExpectedAccSecList()
	{
		return Arrays.asList("My Account","My Orders","My Affiliate Account","Newsletter");
	}
	
	public final static String REGISTER_PAGE_TITLE = "Register Account";
	public final static String REGISTER_PAGE_FIRSTNAME = "test firstname";
	public final static String REGISTER_PAGE_lASTNAME = "test lastname";
	public final static String REGISTER_PAGE_EMAIL = "test7e8e65r@test.com";
	public final static String REGISTER_PAGE_TELEPHONE = "2329454545";
	public final static String REGISTER_PAGE_PASSWORD = "test123";
	public final static String REGISTER_PAGE_CONFIRMPASSWORD = "test123";
	
}
