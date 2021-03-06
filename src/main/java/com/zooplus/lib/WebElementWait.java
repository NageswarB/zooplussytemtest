package com.zooplus.lib;


import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class WebElementWait {
    final static int WAIT_TIMEOUT_DEFAULT = 40;
    protected final WebDriver itsDriver;
    protected final WebDriverWait itsWait;

    public WebElementWait(final WebDriver driver) {
        itsDriver = driver;
        PageFactory.initElements(driver, this);
        itsWait = createWebWaitDriver(WAIT_TIMEOUT_DEFAULT);
    }

    public WebDriver getDriver() {
        return itsDriver;
    }

    /**
     * @param message
     * @param element
     */
    private void assertCheckIfNotNull(final String message, final WebElement element) {
        Assert.assertNotNull(message, element);
    }

    /**
     * This method will wait till element is Clickable
     * @param element
     */
    protected WebElement waitUntilElementIsClickable(final WebElement element) {
        final String message = String.format("FAIL : Element with these details is NULL : '%s'", element);
        assertCheckIfNotNull(message, element);
        return itsWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * This method creates a WebDriverWait
     * @param timeOutInSeconds
     */
    protected WebDriverWait createWebWaitDriver(final long timeOutInSeconds) {
        return new WebDriverWait(itsDriver, timeOutInSeconds);
    }

    /**
     * This method waits until the element is visible
     * @param element - WebElement
     */
    protected WebElement waitUntilElementIsVisible(final WebElement element) {
        final String message = String.format("FAIL : Element with these details is NULL : '%s'", element);
        assertCheckIfNotNull(message, element);
        return itsWait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits for given amount of time, Since it affects state in the driver we should undo it using a finally.
     * @param time
     */
    public void implicitWait(final int time) {
        itsDriver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
    }

    /**
     * Waits for element to be visible within 30 sec
     * @param webElement
     */
    public void waitForVisible(final WebElement webElement) {
        itsWait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public void waitForUrlContain(final String urlContain) {
        itsWait.until(ExpectedConditions.urlContains(urlContain));
    }

    public void waitForInVisible(By locator) {
        itsWait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void waitForPageToBeStable() {
        itsWait.until((WebDriver webDriver) ->
                (((JavascriptExecutor)webDriver).executeScript("return document.readyState").equals("complete")));
    }

    protected WebElement waitUntilElementIsPresent(final By locator) {
        final String message = String.format("FAIL : Element with locator details is NULL : '%s'", locator);
        return itsWait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}
