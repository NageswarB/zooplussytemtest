package com.zooplus.stepdefs.wolfproductsearch;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;

import com.zooplus.lib.AbstractBasePage;
import com.zooplus.lib.WebDriverActions;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {
    private static final Logger log = Logger.getLogger(Hooks.class.getName());

    private WebDriver itsDriver;

    public Hooks() {
        //log.info("Constructor: Hooks");
    }

    @Before
    public void before(final Scenario scenario) {
        itsDriver = WebDriverActions.openBrowser(scenario);
        AbstractBasePage.initGlobalParamMap(scenario);
    }

    @After
    public void after(final Scenario scenario) {
        WebDriverActions.closeBrowser(scenario, itsDriver);
        itsDriver = null;
        AbstractBasePage.resetGlobalParamMap(scenario);
    }
}