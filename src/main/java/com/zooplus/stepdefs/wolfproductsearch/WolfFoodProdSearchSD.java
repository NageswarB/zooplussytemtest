package com.zooplus.stepdefs.wolfproductsearch;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.zooplus.lib.ScumberException;
import com.zooplus.lib.WebDriverActions;
import com.zooplus.pageobjects.wolfproductsearch.WolfFoodProdSearchPage;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class WolfFoodProdSearchSD {

	private final static Logger log = Logger.getLogger(WolfFoodProdSearchSD.class.getName());

	private WebDriver driver;
	private WolfFoodProdSearchPage wfps;

	public WolfFoodProdSearchSD() {
		log.info("Constructor: WolfFoodProdSearchSD");
	}

	@Before
	public void before(final Scenario scenario) throws ScumberException {
		driver = WebDriverActions.openBrowser(scenario);
		wfps = PageFactory.initElements(driver, WolfFoodProdSearchPage.class);
	}

	@Given("^that I launch application in browser$")
	public void launchApplication() {
		String appURL = System.getProperty("base.url");
		log.info("Application Under Test : " + appURL);
		if(driver != null){
			driver.get(appURL);
		}
	}

	@When("^I select country \"([^\"]*)\"$")
	public void selectCountry(String countryName) {
		wfps.selectCountry(countryName);
	}

	@When("^I navigate to app with asset filters \"([^\"]*)\" and \"([^\"]*)\"$")
	public void navigateToURLWithAssetFilterEnabled(String foodCategory, String foodFlavour) {
		wfps.navigateToProductSearchURL(foodCategory, foodFlavour);
	}

	@Then("^I should see the top navigation \"([^\"]*)\" highlighted$")
	public void verifyTopBarHighlighted(String foodCategory) {
		wfps.verifyMainTabHighlighted(foodCategory);
	}

	@Then("^I see the products matching with \"([^\"]*)\" asset filters$")
	public void verifyProdResultsMatchWithFilters(String foodFlavour) {
		wfps.verifyFilterSelected(foodFlavour);
	}
}