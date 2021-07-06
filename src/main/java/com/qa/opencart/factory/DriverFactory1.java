package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author suhas
 *
 */
public class DriverFactory1 {

	public WebDriver driver;
	public Properties prop;

	/**
	 * This method used to initialize the driver on the basis of given driver name
	 * 
	 * @param browsername
	 * @return
	 */
	public WebDriver init_driver(Properties prop) {
		
		String browsername = prop.getProperty("browser");
		
		System.out.println("Browsername is : " + browsername);
		if (browsername.equals("chrome")) {
			WebDriverManager.chromedriver().setup();

			driver = new ChromeDriver();

		} else if (browsername.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browsername.equals("safari")) {
			driver = new SafariDriver();
		} else {
			System.out.println("Pass the correct browser name");
		}
		driver.get(prop.getProperty("url"));
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		return driver;

	}
	/**
	 * This is use to initialize prop from the config file
	 * @return
	 */

	public Properties init_prop() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return prop;

	}

}
