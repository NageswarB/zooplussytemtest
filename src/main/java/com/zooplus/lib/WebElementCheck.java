package com.zooplus.lib;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebElementCheck extends WebElementWait {
	private final static Logger Log = Logger.getLogger(WebElementCheck.class.getName());

	public WebElementCheck(final WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	/**
	 * This method will assert/check for true output of a condition
	 *
	 * @param message
	 *            - message when the output of condition is false
	 * @param conditionToCheck
	 *            - condition to evaluate
	 */
	protected void assertCheckIfTrue(final String message, final boolean conditionToCheck) {
		assertTrue(message, conditionToCheck);
	}

	/**
	 * This method will assert/check for false output of a condition
	 *
	 * @param message
	 *            - message when the output of condition is true
	 * @param conditionToCheck
	 *            - condition to evaluate
	 */
	public void assertCheckIfNotNull(final String message, final boolean conditionToCheck) {
		assertNotNull(message, conditionToCheck);
	}

	/**
	 * This method will assert/check for false output of a condition
	 *
	 * @param message
	 *            - message when the output of condition is true
	 * @param conditionToCheck
	 *            - condition to evaluate
	 */
	protected void assertCheckIfFalse(final String message, final boolean conditionToCheck) {
		assertFalse(message, conditionToCheck);
	}

	/**
	 * This method checks if the object is not null
	 *
	 * @param message
	 *            - message if the object is NULL
	 * @param objectToCheckNotNull
	 *            - an Object to check
	 */
	protected void assertCheckIfNotNull(final String message, final Object objectToCheckNotNull) {
		assertNotNull(message, objectToCheckNotNull);
	}

	

	public void waitForSecs(final int waitSecs) {
		try {
			TimeUnit.SECONDS.sleep((long) waitSecs);
		} catch (InterruptedException var2) {
			var2.printStackTrace();
		}
	}

	/**
	 * This function is used to wait for loading of a page by passing any
	 * particular element of that page, if element is not loaded then testcase
	 * will fail.
	 * 
	 * @param driver
	 * @param element
	 */
	public static void waitForPageLoad(WebDriver driver, WebElement element) {
		String objectWaitTime = "10";
		Long ObjectWaitTime = Long.parseLong(objectWaitTime);
		String callingClassName = getCallerClassName();
		String currentPageName = callingClassName.substring(callingClassName.lastIndexOf('.') + 1);
		Log.info("Started waiting for '" + currentPageName + "' to load. Wait upto " + ObjectWaitTime + " seconds.");

		WebDriverWait wait = new WebDriverWait(driver, 30);

		try {
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (StaleElementReferenceException e) {
			Log.info("StaleElementReferenceException occured, so trying again...");

			try {
				wait.until(ExpectedConditions.visibilityOf(element));
			} catch (Exception exc) {
				Assert.fail("Even after second try, element is not loaded, so exiting.");
			}
		} catch (TimeoutException e) {
			Assert.fail(
					"'" + currentPageName + "' NOT loaded even after :- " + objectWaitTime + " seconds. Exiting...");
		}
	}

	private static String getCallerClassName() {
		StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
		for (int i = 1; i < stElements.length; i++) {
			StackTraceElement ste = stElements[i];
			if (!ste.getClassName().equals(WebElementCheck.class.getName()) && !ste.getClassName().contains("helper")
					&& ste.getClassName().indexOf("java" + ".lang.Thread") != 0) {
				return ste.getClassName();
			}
		}
		return null;
	}

	public static void compareByteArray(byte[] array1, byte[] array2) {
		Assert.assertTrue(Arrays.equals(array1, array2));
	}

	/**
	 * Check if element is displayed or not
	 */
	protected void assertElementIsDisplayed(final WebElement webElement, final String message) {
		boolean enabled = isElementDisplayed(webElement);
		assertTrue(message, enabled);
	}

	/**
	 * Check if element is displayed or not
	 */
	protected void assertElementIsNotDisplayed(final WebElement webElement, final String message) {
		boolean enabled = isElementDisplayed(webElement);
		assertFalse(message, enabled);
	}

	protected static Boolean isElementDisplayed(WebElement element) {
		Boolean visible = true;
		if (element == null)
			return false;
		try {
			visible = element.isDisplayed();
		} catch (NoSuchElementException e) {
			visible = false;
		}
		return visible;
	}
}
