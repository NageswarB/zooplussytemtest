package com.zooplus.tests;

import org.junit.runner.RunWith;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import cucumber.api.CucumberOptions;
import cucumber.api.Scenario;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
	monochrome = true, 
	features = { "classpath:feature_files/" }, 
	glue = {"com.zooplus.stepdefs/" }, 
	tags = "@system-test",
	plugin = {"json:target/cucumber.json", "html:target/cucumber"})
public class MasterTest {
		
	public static void embedScreenshot(Scenario scenario, WebDriver driver) {
		try {
			byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.embed(screenshot, "image/png");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
