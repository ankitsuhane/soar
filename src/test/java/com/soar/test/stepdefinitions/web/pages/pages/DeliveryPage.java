package com.soar.test.stepdefinitions.web.pages.pages;

import com.soar.framework.web.BaseWebAction;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Lazy
@Component
@Scope("cucumber-glue")
public class DeliveryPage extends BaseWebAction {

    @FindBy(xpath = "//span[text()='Continue']")
    WebElement continueButton;

    @FindBy(id = "mat-radio-43")
    WebElement deliveryOption;

    @FindBy(css = "span.mat-simple-snack-bar-content")
    public WebElement invisiblePopup;

    public void selectDeliveryOption() throws InterruptedException {
        PageFactory.initElements(webDriverFactory.getWebDriver(), this);
        waitForElementInvisible(invisiblePopup);
        Thread.sleep(2000);
        deliveryOption.click();
    }

    public void pressContinue() {
        continueButton.click();
    }
}
