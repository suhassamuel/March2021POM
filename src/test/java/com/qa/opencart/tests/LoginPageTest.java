package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.opencart.utils.Constants;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic - 100: design opencart application")
@Story("US - 101: design login page with different features")
public class LoginPageTest extends BaseTest {

	@Description("Login page title test..")
	@Severity(SeverityLevel.TRIVIAL)
	@Test
	public void loginPageTitleTest() {
		String title = login.getLoginPageTitle();
		System.out.println("loing page title is :" + title);

		// Assert.assertEquals(title, "Account Login");
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}

	@Description("forgot password link test..")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void forgotpwdlinkTest() {

		Assert.assertTrue(login.isForgotPwdExist());

	}
	
	@Description("Application login test with correct username and password..")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void loginTest()
	{
		accountPage = login.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		
		Assert.assertEquals(accountPage.getAccountPageTitle(), Constants.ACCOUNT_PAGE_TITLE);
	}

}
