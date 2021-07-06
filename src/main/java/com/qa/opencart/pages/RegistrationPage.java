package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegistrationPage {

	private ElementUtil elementUtil;

	// 1. locators
	/*private By firstname = By.xpath("//input[@id='input-firstname']");
	private By lastname = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPassword = By.id("input-confirm");
	private By subscribe_Yes = By.xpath("(//label[@class='radio-inline'])[position()='1']/input");
	private By subscribe_No = By.xpath("(//label[@class='radio-inline'])[position()='2']/input");
//	private By privacy_Policy = By.xpath("//input[@type='checkbox' and @name='agree']");
	
	private By agreeCheckBox = By.name("agree");
	
	private By continue_btn = By.xpath("//input[@type='submit' and @value='Continue']");
	private By SuccessMsg = By.cssSelector("div#content h1");
	private By logOutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");*/
		
	
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");

	private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input[@type='radio']");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input[@type='radio']");

	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");
	private By sucessMessg = By.cssSelector("div#content h1");

	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	// 2. constructor
	public RegistrationPage(WebDriver driver) {

		elementUtil = new ElementUtil(driver);
	}

	public boolean accountRegistration(String firstName, String lastName, String email, String telephone,
			String password, String subscribe) {

		elementUtil.doSendKeys(this.firstName, firstName);
		elementUtil.doSendKeys(this.lastName, lastName);
		elementUtil.doSendKeys(this.email, email);
		elementUtil.doSendKeys(this.telephone, telephone);
		elementUtil.doSendKeys(this.password, password);
		elementUtil.doSendKeys(this.confirmpassword, password);

		if (subscribe.equals("yes")) {
			elementUtil.doClick(subscribeYes);
		} else {
			elementUtil.doClick(subscribeNo);
		}

		elementUtil.doClick(agreeCheckBox);
		elementUtil.doClick(continueButton);

		String mesg = elementUtil.isElementVisible(sucessMessg, 5).getText();

		if (mesg.contains(Constants.REGISTER_SUCCESS_MESSG)) {
			
			System.out.println(Constants.REGISTER_SUCCESS_MESSG);
			elementUtil.doClick(logoutLink);
			elementUtil.doClick(registerLink);
			return true;
		} 
			return false;

	}
}
