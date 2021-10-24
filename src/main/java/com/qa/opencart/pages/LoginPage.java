package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil elementUtil;

	// 1. By locator
	private By email = By.id("input-email");
	private By password = By.id("input-password");
	private By loginbtn = By.xpath("//input[@value='Login']");
	private By forgotpwd = By.linkText("Forgotten Password");

	private By registerLink = By.xpath("//a[text()='Register' and @class='list-group-item']");

	private By registerLink1 = By.linkText("Register");

	// 2. constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}

	// 3. Page action

	@Step("getting login page title")
	public String getLoginPageTitle() {
		// return driver.getTitle();
		return elementUtil.waitForTitleIs(Constants.LOGIN_PAGE_TITLE, 5);
	}

	@Step("getting forgot password link exist or not")
	public boolean isForgotPwdExist() {
		// return driver.findElement(forgotpwd).isDisplayed();
		return elementUtil.doIsDisplayed(forgotpwd);
	}

	@Step("login with username: {0} and password {1}")
	public AccountPage doLogin(String un, String pwd) {
		System.out.println("login with: username" +un + "Password: " + pwd);

		/*
		 * driver.findElement(email).sendKeys(un);
		 * driver.findElement(password).sendKeys(pwd);
		 * driver.findElement(loginbtn).click();
		 */
		elementUtil.doSendKeys(email, un);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginbtn);

		return new AccountPage(driver);

	}

	@Step("getting register link exist or not")
	public boolean registerLinkExist() {
		return elementUtil.doIsDisplayed(registerLink);
	}

	@Step("getting register link clicked  or not")
	public RegisterPage registerLinkClick() {
		if (elementUtil.doIsDisplayed(registerLink)) {
			elementUtil.doClick(registerLink);

		}
		return new RegisterPage(driver);
	}

	@Step("navigating to register page")
	public RegistrationPage navigateToRegisterPage() {
		elementUtil.doClick(registerLink1);

		return new RegistrationPage(driver);
	}
}
