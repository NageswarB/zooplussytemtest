package com.zooplus.pageobjects.wolfproductsearch;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class WolfFoodProdSearchPageLocators {
	
	@Autowired
	public WolfFoodProdSearchPageLocators(final WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "div.product-listing.product-grid > div")
	public List<WebElement> productsDisplayed;
	
}
