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
 * @author Suhas
 *
 */
public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;
	public static String highlight;

	/**
	 * This method is use to initialize the driver on the basis of given browser
	 * name
	 * 
	 * @param browserName
	 * @return
	 */
	public WebDriver initDriver(Properties prop) {

		String browserName = prop.getProperty("browser");

		System.out.println("Browser name : " + browserName);
		optionsManager = new OptionsManager(prop);
		highlight = prop.getProperty("highlight");

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();

			driver = new ChromeDriver(optionsManager.getChromeOptions());
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver(optionsManager.getFireFoxOptions());
		} else if (browserName.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
		}

		else {
			System.out.println("pass the correct browsername : " + browserName);
		}

		driver.get(prop.getProperty("url"));
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();

		return driver;
	}

	/**
	 * This method is used to initialize the prop from config file envName =
	 * qa/stage/dev
	 * e.g. C:\Users\suhas\eclipse-workspace\MyPOMSeries>mvn clean install -Denv="qa"
	 * default :  C:\Users\suhas\eclipse-workspace\MyPOMSeries>mvn clean install
	 * @return
	 */

	public Properties initProp() {
		prop = new Properties();
		FileInputStream ip = null;

		String envName = System.getProperty("env");

		if (envName == null) {
			System.out.println("Running on prod environment");
			try {
				ip = new FileInputStream("./src\\test\\resources\\config\\config.properties");

				// prop.load(ip);
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}

		} else {

			System.out.println("Running on Environment: "+envName);
			try {

				switch (envName) {
				case "qa":
					ip = new FileInputStream("./src\\test\\resources\\config\\qa.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src\\test\\resources\\config\\stage.config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src\\test\\resources\\config\\dev.config.properties");
					break;

				default:
					break;
				}
			} catch (FileNotFoundException ex) {
				ex.printStackTrace();
			}
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return prop;
	}

}
