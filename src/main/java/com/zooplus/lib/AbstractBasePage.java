package com.zooplus.lib;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import cucumber.api.Scenario;

public abstract class AbstractBasePage extends WebElementHelperMethods {
	
	private final static Logger log = Logger.getLogger(AbstractBasePage.class.getName());
	public Properties msgProp = null;

	public static Map<String, String> globalParamMap = null;

	protected AbstractBasePage(final WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public static void initGlobalParamMap(final Scenario scenario) {
		try {
			log.info("Initializing global parameter map");
			globalParamMap = new HashMap<String, String>();
		} catch (Exception e) {
			log.warning(e.getMessage());
		}
	}
	
	public static void resetGlobalParamMap(final Scenario scenario) {
		try {
			log.info("Resetting global parameter map value to null");
			globalParamMap = null;
		} catch (Exception e) {
			log.warning(e.getMessage());
		}
	}

}
