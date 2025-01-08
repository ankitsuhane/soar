package com.soar.test.testRunner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "json:target/cucumber-reports/Cucumber2.json"}, 
		features = "src/test/java/com/soar/test/features",
		glue = "com.soar.test.stepdefinitions",
		//dryRun=true,
		 tags = {"@Web"},
		//tags = {"@BrowserStack"},
		monochrome=true)

public class TestRunner {
}
