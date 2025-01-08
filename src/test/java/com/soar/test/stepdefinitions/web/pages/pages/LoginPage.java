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
public class LoginPage extends BaseWebAction {

    @FindBy(css = "div#newCustomerLink")
    public WebElement newCustomerLink;

    @FindBy(css = "h1")
    public WebElement loginHeading;

    @FindBy(id = "email")
    public WebElement email;

    @FindBy(id = "password")
    public WebElement password;

    @FindBy(id = "loginButton")
    public WebElement loginButton;

    @FindBy(className = "mat-simple-snack-bar-content")
    public WebElement successfulRegistrationMessage;


    public void newCustomer(){
        PageFactory.initElements(webDriverFactory.getWebDriver(), this);
        waitForVisibilityOfElement(loginHeading).click();
        scrollToBottomPage();
        waitForElementToBeClickable(newCustomerLink).click();
    }
    public void login(String username){
        waitForVisibilityOfElement(successfulRegistrationMessage);
        waitForElementToBeClickable(email).sendKeys(username+"@gmail.com");
        password.sendKeys(username+"123@");
        loginButton.click();
    }

}
