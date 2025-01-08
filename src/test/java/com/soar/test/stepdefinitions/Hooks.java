package com.soar.test.stepdefinitions;

import com.soar.framework.web.WebDriverFactory;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Hooks {

        private static final Logger logger = LoggerFactory.getLogger(Hooks.class);

        @Autowired
        WebDriverFactory webDriverFactory;

        @Before ("@Web")
        public void setupWeb() throws MalformedURLException {
            logger.info("Setting up Web Driver!");
            webDriverFactory.setUpWebDriverLocal();
        }
        @After ("@Web")
        public void tearDownWeb(Scenario scenario) {
            logger.info("Tear down Web Driver!");
            if (scenario.isFailed()) {
                try {

                    File path_screenshot = new File(System.getProperty("user.dir")+"/Screenshots");
                    if (!path_screenshot.exists()) {
                        path_screenshot.mkdirs(); // Create directory if it doesn't exist
                    }
                    scenario.write("Current page title is: " +
                            webDriverFactory.getWebDriver().getTitle());
                    File screenshot = ((TakesScreenshot) webDriverFactory.getWebDriver()).
                            getScreenshotAs(OutputType.FILE);
                    String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                    String filename = timestamp + ".jpg";
                    File targetFile = new File(path_screenshot + File.separator + filename);
                    FileUtils.copyFile(screenshot, targetFile);
                } catch (Exception somePlatformsDontSupportScreenShots) {
                    logger.error(somePlatformsDontSupportScreenShots.getMessage());
                }
            }
            webDriverFactory.getWebDriver().manage().deleteAllCookies();
            webDriverFactory.getWebDriver().close();
            webDriverFactory.getWebDriver().quit();
        }
}
