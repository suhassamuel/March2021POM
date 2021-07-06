package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegistrationPageTest extends BaseTest {

	@BeforeClass
	private void setUpRgistration() {
		registrationPage = login.navigateToRegisterPage();
	}

	
	public String getRandomEmail()
	{
		Random raandomGenerator = new Random();
		String email = "qaautomationtest"+raandomGenerator.nextInt(100)+"@gmail.com";
		return email;
		
	}
	
	@DataProvider
	public Object[][] getRegisterData()
	{
		return  ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
	}
	@Test(dataProvider="getRegisterData")
	public void userRegistrationtest ( String firstName,
			String lastName, String telephone,String password ,String subscribe) {
		
		Assert.assertTrue(registrationPage.accountRegistration( firstName,
				 lastName, getRandomEmail(), password ,  telephone, subscribe));
/*
		Assert.assertTrue(registrationPage.accountRegistration("suhas", "samuel", "tl854623s@test.com", "456545646",
				"Test@123673", "yes"));
		
		
		Assert.assertTrue(registrationPage.accountRegistration("suhas", "samuel", getRandomEmail(), "456545646",
				"Test@123673", "yes"));*/
	
	

	}

}
