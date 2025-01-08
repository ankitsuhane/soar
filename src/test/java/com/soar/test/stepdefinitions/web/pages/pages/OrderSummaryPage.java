package com.soar.test.stepdefinitions.web.pages.pages;

import com.github.javafaker.Faker;
import com.soar.framework.web.BaseWebAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Lazy
@Component
@Scope("cucumber-glue")
public class OrderSummaryPage extends BaseWebAction {



    @FindBy(id = "checkoutButton")
    WebElement checkoutButton;

    @FindBy(css = "h1")
    WebElement orderSummaryHeading;

    @FindBy(linkText = "Track Orders")
    WebElement trackOrderLink;

    public String orderSummary() throws InterruptedException {
        PageFactory.initElements(webDriverFactory.getWebDriver(), this);
        Thread.sleep(2000);
        waitForVisibilityOfElement(checkoutButton).click();
        waitForVisibilityOfElement(trackOrderLink);
        return orderSummaryHeading.getText();
    }
}
