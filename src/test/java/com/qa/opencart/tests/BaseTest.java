package com.qa.opencart.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.RegistrationPage;

public class BaseTest {
	public WebDriver driver;
	public DriverFactory driverfactory;
	public Properties prop;
	public LoginPage login;
	public AccountPage accountPage;
	public RegisterPage registerPage;
	public RegistrationPage registrationPage;

	@Parameters({"browser","browserversion"})
	
	@BeforeTest
	public void setup(String browserName ,String browserVersion) {
		
		driverfactory = new DriverFactory();
		prop = driverfactory.initProp();
		
		if(browserName!= null)
		{
			prop.setProperty("browser", browserName);
			prop.setProperty("browserversion", browserVersion);
		}
		
		driver = driverfactory.initDriver(prop);
		login = new LoginPage(driver);

	}

	@AfterTest
	public void tearDown() {
		//driver.quit();
	}

}
