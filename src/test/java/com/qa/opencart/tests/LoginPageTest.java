package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class LoginPageTest extends BaseTest {

	@Test
	public void loginPageTitleTest() {
		String title = login.getLoginPageTitle();
		System.out.println("loing page title is :" + title);

		// Assert.assertEquals(title, "Account Login");
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}

	@Test
	public void forgotpwdlinkTest() {

		Assert.assertTrue(login.isForgotPwdExist());

	}
	
	@Test
	public void loginTest()
	{
		accountPage = login.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		
		Assert.assertEquals(accountPage.getAccountPageTitle(), Constants.ACCOUNT_PAGE_TITLE);
	}

}
