package com.zooplus.pageobjects.wolfproductsearch;

import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zooplus.lib.AbstractBasePage;

public class WolfFoodProdSearchPage extends AbstractBasePage {

	private final static Logger log = Logger.getLogger(WolfFoodProdSearchPage.class.getName());

	final WolfFoodProdSearchPageLocators wfpsPageLocators;
	final WolfFoodProdSearchPageHelper wfpsPageHelper;

	@Autowired
	public WolfFoodProdSearchPage(final WebDriver driver) {
		super(driver);
		wfpsPageLocators = PageFactory.initElements(driver, WolfFoodProdSearchPageLocators.class);
		wfpsPageHelper = PageFactory.initElements(driver, WolfFoodProdSearchPageHelper.class);
	}
	
	public void selectCountry(String countryName) {
		log.info("Select country : " + countryName);
				
		waitForPageToBeStable();
		List<WebElement> dropDown = itsDriver.findElements(By.cssSelector("select[name='code']"));
		
		selectListByValueOptions(dropDown.get(2), wfpsPageHelper.getCountryCode(countryName));
		
		waitForSecs(5);
	}
	
	public void navigateToProductSearchURL(String foodCategory, String foodFlavour) {
		log.info("Navigating to product search page with asset filters");
		
		String cssSelectorValue = "a[title='" + foodCategory + "']";
		String filter1 = getAttributeValue(itsDriver.findElement(By.cssSelector(cssSelectorValue)), "href");
		String prodSearchURL = wfpsPageHelper.composeURL(filter1, foodFlavour);
	
		itsDriver.get(prodSearchURL);
	}
	
	public void verifyMainTabHighlighted(String foodCategory) {
		log.info("Verify main food category tab is highlighted");
		
		String cssSelectorValue = "a[title='" + foodCategory + "']";
		String classAttrValue = getAttributeValue(itsDriver.findElement(By.cssSelector(cssSelectorValue)), "class");
		
		assertCheckIfTrue("Main tab not highlighted in prod search", classAttrValue.equalsIgnoreCase("active"));
	}
	
	public void verifyFilterSelected(String foodFlavour) {
		log.info("Verify sub filter selected under food flavour section");
		
		boolean isProductCountEqual = false;
		
		String xpathToSubFilterText = "//span[contains(text(),'" + foodFlavour + "')]/ancestor::span/preceding-sibling::input";
		String productCountInFilter = wfpsPageHelper.getCountFromSelectVerity(foodFlavour);
		
		log.info("Product count from selected filter under Select Variety : " + productCountInFilter);
		
		int productsDisplayed = wfpsPageLocators.productsDisplayed.size();
		log.info("Product count from selected filter under Products Found : " + productsDisplayed);
		
		boolean isChecked = itsDriver.findElement(By.xpath(xpathToSubFilterText)).isSelected();
		if (productsDisplayed == Integer.parseInt(productCountInFilter) ) {
			isProductCountEqual = true;
		}
		assertCheckIfTrue("Food flavour in sub filter was not selected by default", isChecked && isProductCountEqual);
	}
}
