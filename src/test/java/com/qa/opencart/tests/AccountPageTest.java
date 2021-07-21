package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class AccountPageTest extends BaseTest{
	
	private String TEST_DATA_SHEET = "./src\\test\\resources\\testdata\\Democart_account.xlsx";
	@BeforeClass
	public void AccntPageSetup()
	{
		accountPage = login.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] getLinkdata()
	{
		return new ExcelUtil().getTestData(TEST_DATA_SHEET,Constants.ACCOUNT_PAGELINKS_SHEET_NAME);
	}
	
	@Test (dataProvider = "getLinkdata")
	public void accPageLinkExistTest(String linkName)
	{
		Assert.assertTrue(accountPage.isPageLinkAvaiable(linkName));
	}
	
	@Test
	public void  accPageTitleTest()
	{
		String title =  accountPage.getAccountPageTitle();
		
		System.out.println("Account page title is:"+title);

		Assert.assertEquals(title, Constants.ACCOUNT_PAGE_TITLE);
	}
	@Test
	public void accPageSectionListtest()
	{
		List<String> actualList = accountPage.getAccountSeclist();
		
		System.out.println("Actual List : "+actualList);
		Assert.assertEquals(actualList, Constants.getExpectedAccSecList());
	}
	
	@Test
	
	public void logoutLinkExist()
	{
		Assert.assertTrue(accountPage.isLogoutLinkExist());
	}
	
	
	@Test
	public void accountPageHeaderTest()
	{
		Assert.assertEquals(accountPage.getAccountPageHeadertext(), Constants.ACCOUNT_PAGE_HEADER);
	}

	@Test
	public void accountSearchExist()
	{
		Assert.assertTrue(accountPage.isSearchExist());
	}
}
