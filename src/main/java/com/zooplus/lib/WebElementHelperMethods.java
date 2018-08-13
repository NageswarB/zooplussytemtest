package com.zooplus.lib;

import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public abstract class WebElementHelperMethods extends WebElementCheck {
	
	protected static final int defaultWaitInSecs = 3;
	
	public static enum How {
		className, css, id, linkText, name, partialLinkText, tagName, xPath, accessibility
	};

	private final static Logger Log = Logger.getLogger(WebElementHelperMethods.class.getName());

	protected WebElementHelperMethods(final WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	

	/**
	 * Type text value in textbox element
	 * 
	 * @param editBox
	 * @param valueToType
	 */
	protected void typeEditBox(final WebElement editBox, final String valueToType) {
		waitForVisible(editBox);
		editBox.clear();
		editBox.sendKeys(valueToType);
	}

	/**
	 * Select list option by visible option text in a drop down list
	 * 
	 * @param listDropDown
	 * @param optionText
	 */
	protected void selectListByVisibleText(final WebElement listElement, final String optionText) {
		waitForVisible(listElement);
		final Select selectList = new Select(listElement);
		Log.info("Select list option: " + optionText);
		selectList.selectByVisibleText(optionText);
	}

	/**
	 * Click on webelement
	 * 
	 * @param webElement
	 */
	protected void clickElement(final WebElement webElement) {
		waitForVisible(webElement);
		webElement.click();
	}

	/**
	 * Get "value" attribute of an element
	 * 
	 * @param webElement
	 * @param attrName
	 * @return
	 */
	protected String getAttributeValue(final WebElement webElement, String attrName) {
		return webElement.getAttribute(attrName);
	}

	/**
	 * Get element text or attribute value
	 * 
	 * @param element
	 */
	protected String getElementText(final WebElement element) {
		waitForVisible(element);
		final String buttonText = element.getAttribute("value");
		return !buttonText.equals("") ? buttonText : element.getText();
	}

	/**
	 * Returns the value of the color attribute for that element
	 * 
	 * @param colorAttribute
	 *            = "color" or "background-color"
	 */
	protected String getElementColor(final WebElement element, final String colorAttribute) {
		waitForVisible(element);
		return element.getCssValue(colorAttribute);
	}

	/**
	 * Returns the value of the attribute for that element
	 * 
	 * @param attribute
	 *            = "class", "id" etc.
	 */
	protected String getElementAttribute(final WebElement element, final String attribute) {
		return element.getAttribute(attribute);
	}

	/**
	 * selects from dropdown by reference value
	 * 
	 * @param listElement
	 * @param optionText
	 *
	 */
	protected void selectListByValueOptions(final WebElement listElement, final String optionText) {
		waitForVisible(listElement);
		final Select selectList = new Select(listElement);
		selectList.selectByValue(optionText);
	}

	
	/**
	 * Click on webElement after scrolling to view so that element becomes
	 * visible
	 * 
	 * @param webElement
	 *            - element on which click needs to be performed
	 */
	protected void clickElementAfterScroll(final WebElement webElement) {
		if (webElement != null) {
			JavascriptExecutor jse = (JavascriptExecutor) itsDriver;
			jse.executeScript("arguments[0].scrollIntoView(false)", webElement);
		}
		waitForSecs(2);
		clickElement(webElement);
	}

	public boolean isElementPresent(By by) {
		try {
			itsDriver.findElement(by);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}
	
}