package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {

	private WebDriver driver;
	private ElementUtil elementUtil;

	// 1. page locator

	// private By registerLink = By.linkText("Register");

	private By registerLink = By.xpath("//a[text()='Register' and @class='list-group-item']");
	// private By firstname = By.id("input-firstname");

	private By firstname = By.xpath("//input[@id='input-firstname']");
	private By lastname = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPassword = By.id("input-confirm");
	private By subscribe_No = By.xpath("//input[@type='radio' and @value='0']");
	private By privacy_Policy = By.xpath("//input[@type='checkbox' and @name='agree']");
	private By continue_btn = By.xpath("//input[@type='submit' and @value='Continue']");
	//private By confirm_continue_btn = By.xpath("//a[text()='Continue']");

	// 2. Constructor

	public RegisterPage(WebDriver driver) {

		System.out.println("testing.... register page");
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}

	/*
	 * public boolean registerLinkExist() { // return
	 * driver.findElement(registerLink).isDisplayed();
	 * 
	 * return elementUtil.doIsDisplayed(registerLink); }
	 */

	public void registerLinkClick() {
		if (elementUtil.doIsDisplayed(registerLink)) {
			elementUtil.doClick(registerLink);
		}
	}

	public String registerPageTitleDisplayed() {
		// driver.getTitle();

		return elementUtil.waitForTitleIs(Constants.REGISTER_PAGE_TITLE, 5);
	}

	public AccountPage registerInfoInsert() {

		elementUtil.doSendKeys(firstname, Constants.REGISTER_PAGE_FIRSTNAME);
		elementUtil.doSendKeys(lastname, Constants.REGISTER_PAGE_lASTNAME);
		elementUtil.doSendKeys(email, Constants.REGISTER_PAGE_EMAIL);
		elementUtil.doSendKeys(telephone, Constants.REGISTER_PAGE_TELEPHONE);
		elementUtil.doSendKeys(password, Constants.REGISTER_PAGE_PASSWORD);
		elementUtil.doSendKeys(confirmPassword, Constants.REGISTER_PAGE_CONFIRMPASSWORD);
		elementUtil.doClick(subscribe_No);
		elementUtil.doClick(privacy_Policy);
		elementUtil.doClick(continue_btn);
		//elementUtil.doClick(confirm_continue_btn);

		return new AccountPage(driver);
	}

}
