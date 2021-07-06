package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountPage {

	private WebDriver driver;
	private ElementUtil elementUtil;

	// 1. locator
	private By header = By.cssSelector("div#logo h1");
	// //a[text()='Your Store']
	private By accSection = By.cssSelector("div #content h2");
	private By searchbar = By.name("search");
	private By logoutLink = By.linkText("Logout");
	

	// 2. constructor
	public AccountPage(WebDriver driver) {
		this.driver = driver;

		elementUtil = new ElementUtil(this.driver);
	}

	// 3. page actions
	public String getAccountPageTitle() {
		// driver.getTitle();
		return elementUtil.waitForTitleIs(Constants.ACCOUNT_PAGE_TITLE, 5);
	}

	public String getAccountPageHeadertext() {
		// driver.findElement(header).isDisplayed();
		return elementUtil.doGetText(header);
	}

	public boolean isLogoutLinkExist() {
		return elementUtil.doIsDisplayed(logoutLink);
	}

	public void logout() {
		if (isLogoutLinkExist()) {
			elementUtil.doClick(logoutLink);
		}

	}
	
	public String success_msg()
	{
		return elementUtil.waitForTitleIs(Constants.ACCOUNT_SUCCESS_HEADER, 5);
		
		
	}
	
	
	

	public List<String> getAccountSeclist() {

		List<WebElement> accSecList = elementUtil.waitForElementsToBeVisible(accSection, 5);
		List<String> accSecValList = new ArrayList<String>();

		for (WebElement ele : accSecList) {
			accSecValList.add(ele.getText());
		}

		return accSecValList;

	}

	public boolean isSearchExist() {
		return elementUtil.doIsDisplayed(searchbar);
	}

}
