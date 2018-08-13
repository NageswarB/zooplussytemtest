package com.zooplus.pageobjects.wolfproductsearch;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.zooplus.lib.WebElementCheck;

public class WolfFoodProdSearchPageHelper extends WebElementCheck {

	public WolfFoodProdSearchPageHelper(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	private final static Logger log = Logger.getLogger(WolfFoodProdSearchPageHelper.class.getName());

	// To get country code based on country name
	public String getCountryCode(String countryName) {
		String countryCode = null;
		switch (countryName) {
		case "Niederlande":
			countryCode = "NL";
			break;
		case "Deutschland":
			countryCode = "DE";
			break;
		default:
			log.log(Level.WARNING, "Provided Country "+ countryName + " is not in available options, please try again.");
			break;
		}
		return countryCode;
	}

	// To form URL with product search filters and asset values
	public String composeURL(String filter1, String flavour) {	
		String formattedUrl = filter1 + "?q=%3Arelevance%3Aflavour%3A" + flavour;
		return formattedUrl;
	}

	public String getCountFromSelectVerity(String foodFlavour) {
		String number = null;
		String xpathToSubFilterItems = "//span[contains(text(),'" + foodFlavour + "')]/span";

		String spanText = itsDriver.findElement(By.xpath(xpathToSubFilterItems)).getText();
		Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(spanText);
		while(m.find()) {
			number =  m.group(1);    
		}	
		return number;
	}

}
