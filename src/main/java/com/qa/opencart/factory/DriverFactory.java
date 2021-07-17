package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
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

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	/**
	 * This method is use to initialize the driver on the basis of given browser
	 * name
	 * 
	 * @param browserName
	 * @return
	 */
	public WebDriver initDriver(Properties prop) {

		String browserName = prop.getProperty("browser").trim();
		String browserVersion = prop.getProperty("browserversion").trim();

		System.out.println("Browser name : " + browserName);
		optionsManager = new OptionsManager(prop);
		highlight = prop.getProperty("highlight");

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();

			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteDriver("chrome", browserVersion);
			} else {
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			}
			// driver = new ChromeDriver(optionsManager.getChromeOptions());
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			// driver = new FirefoxDriver(optionsManager.getFireFoxOptions());

			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteDriver("firefox", browserVersion);
			} else {
				tlDriver.set(new FirefoxDriver(optionsManager.getFireFoxOptions()));
			}

		} else if (browserName.equalsIgnoreCase("safari")) {
			// driver = new SafariDriver();

			tlDriver.set(new SafariDriver());
		}

		else {
			System.out.println("pass the correct browsername : " + browserName);
		}

		/*
		 * driver.get(prop.getProperty("url")); driver.manage().deleteAllCookies();
		 * driver.manage().window().maximize();
		 */
		getDriver().get(prop.getProperty("url"));
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();

		// return driver;
		return getDriver();
	}

	private void init_remoteDriver(String browser, String browserVersion) {
		System.out.println("Running test on groid server" + browser + "Version :" + browserVersion);

		if (browser.equals("chrome")) {
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability("browserName", "chrome");
			cap.setCapability("browserVersion", browserVersion);
			cap.setCapability("enableVNC", true);

			cap.setCapability(ChromeOptions.CAPABILITY, optionsManager.getChromeOptions());
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), cap));
			} catch (MalformedURLException e) {

				e.printStackTrace();
			}
		} else if (browser.equals("firefox")) {
			DesiredCapabilities cap = DesiredCapabilities.firefox();
			cap.setCapability("browserName", "firefox");
			cap.setCapability("browserVersion", browserVersion);
			cap.setCapability("enableVNC", true);
			cap.setCapability(FirefoxOptions.FIREFOX_OPTIONS, optionsManager.getFireFoxOptions());
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), cap));
			} catch (MalformedURLException e) {

				e.printStackTrace();
			}
		}

	}

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * This method is used to initialize the prop from config file envName =
	 * qa/stage/dev e.g. C:\Users\suhas\eclipse-workspace\MyPOMSeries>mvn clean
	 * install -Denv="qa" default : C:\Users\suhas\eclipse-workspace\MyPOMSeries>mvn
	 * clean install
	 * 
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

			System.out.println("Running on Environment: " + envName);
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

	/**
	 * This method is used to take screenshot
	 * 
	 * @return
	 */
	public String getScreenshot() {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);

		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";

		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
}
