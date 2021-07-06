package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.qa.opencart.utils.Constants;


public class RegisterPageTest extends BaseTest {

	@BeforeClass
	public void registerPageSetup() {
		registerPage = login.registerLinkClick();
	}

	@Test(priority = 1)
	public void registerPageTest() {

		String title = registerPage.registerPageTitleDisplayed();
		Assert.assertEquals(title, Constants.REGISTER_PAGE_TITLE);
	}

	@Test(priority = 2)
	public void registerTitleTest() {
		String title = registerPage.registerPageTitleDisplayed();

		Assert.assertEquals(title, Constants.REGISTER_PAGE_TITLE);
	}

	@Test(priority = 3)
	public void registerDataInsertTest() {

		accountPage =  registerPage.registerInfoInsert();
				
		String title = accountPage.success_msg();
		
		Assert.assertEquals(title, Constants.ACCOUNT_SUCCESS_HEADER);
		

	}

}
