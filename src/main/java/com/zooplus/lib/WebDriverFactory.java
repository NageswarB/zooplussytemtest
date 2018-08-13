package com.zooplus.lib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public final class WebDriverFactory {
	private final static Logger Log = Logger.getLogger(WebDriverFactory.class.getName());

	private static String hubUrl = "http://localhost:4444/wd/hub";

	private WebDriverFactory() {
		// Should not be invoked directly.
	}

	public static WebDriver createWebDriver()
			throws ScumberException {
		final Properties props = loadBrowserProps();
		final String browser = props.getProperty("browser");
	
		return createWebDriver(browser);
	}

	public static WebDriver createWebDriver(final String browser)
			throws ScumberException {
		Log.info("Creating browser instance: " + browser);

		switch (browser) {

		case "chrome":
			return createChromeWebDriver();
			
		case "ie":
			// to do

		case "firefox":
			// to do
		}

		throw new ScumberException("Unknown browser type: " + browser);
	}

	private static WebDriver createChromeWebDriver() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/webdriver/chromedriver.exe");

		RemoteWebDriver remoteChromeDriver = null;
		DesiredCapabilities cap = null;

		cap = DesiredCapabilities.chrome();
		cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
		try {
			remoteChromeDriver = new RemoteWebDriver(new URL(hubUrl), cap);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return remoteChromeDriver;
	}
	
	public static Properties loadBrowserProps(){
		Properties browserProperties = new Properties();
		
		File f = new File(ResourcesLookup.getTestResoucresDirPath() + "/browser.properties");

		if( f.exists()){
			try {
				browserProperties.load(new FileInputStream(f));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			Log.log(Level.WARNING, "File not found!");
		}
		return browserProperties;
	}
}
