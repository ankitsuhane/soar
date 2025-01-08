package com.soar.framework.web;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.time.Duration;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public abstract class BaseWebAction {

    @Value("${webdriver.wait.secs}")
    private int secs;

    @Autowired
    protected WebDriverFactory webDriverFactory;

    public boolean waitForPageTitle(String pageTitle) {
        return new WebDriverWait(webDriverFactory.getWebDriver(), Duration.ofSeconds(secs))
                .until(titleIs(pageTitle));
    }

   public WebElement waitForVisibilityOfElement(WebElement elementIdentifier) {
        return new WebDriverWait(webDriverFactory.getWebDriver(), Duration.ofSeconds(secs))
                .until(visibilityOf(elementIdentifier));
    }

    public WebElement waitForElementToBeClickable(WebElement elementIdentifier) {
        return new WebDriverWait(webDriverFactory.getWebDriver(), Duration.ofSeconds(secs))
                .until(elementToBeClickable(elementIdentifier));
    }

    public List<WebElement> waitForListElementsVisible(By elementIdentifier, int numberOfLocators) {
        return new WebDriverWait(webDriverFactory.getWebDriver(), Duration.ofSeconds(secs))
                .until(numberOfElementsToBe(elementIdentifier, numberOfLocators));
    }

    public WebElement waitForElementRefresh(WebElement elementIdentifier) {
        return new WebDriverWait(webDriverFactory.getWebDriver(), Duration.ofSeconds(secs))
                .until(refreshed(ExpectedConditions.visibilityOf(elementIdentifier)));
    }


    public Boolean waitForElementInvisible(WebElement element) {
        return new WebDriverWait(webDriverFactory.getWebDriver(), Duration.ofSeconds(secs))
                .until(ExpectedConditions.invisibilityOf(element));
    }

    public void scrollToBottomPage(){
        webDriverFactory.getWebDriver().findElement(By.tagName("body")).sendKeys(Keys.END);
    }
}