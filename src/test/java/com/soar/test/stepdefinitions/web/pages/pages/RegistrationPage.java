package com.soar.test.stepdefinitions.web.pages.pages;

import com.github.javafaker.Faker;
import com.soar.framework.web.BaseWebAction;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;

@Lazy
@Component
@Scope("cucumber-glue")
public class RegistrationPage extends BaseWebAction {

    @FindBy(id = "emailControl")
    public WebElement email;

    @FindBy(id = "passwordControl")
    public WebElement password;

    @FindBy(id = "repeatPasswordControl")
    public WebElement repeatPassword;

    @FindBy(xpath = "//*[@name='securityQuestion']")
    public WebElement securityQuestion;

    @FindBy(id = "securityAnswerControl")
    public WebElement securityAnswer;

    @FindBy(id = "registerButton")
    public WebElement registerButton;

    @FindBy(css = "span[class='mat-slide-toggle-bar']")
    public WebElement passwordAdvice;

    @FindBy(id = "mat-error-2")
    public WebElement emailError;

    @FindBy(id = "mat-error-3")
    public WebElement passwordError;

    @FindBy(id = "mat-error-4")
    public WebElement repeatPasswordError;

    @FindBy(id = "mat-error-6")
    public WebElement securityAnswerError;

    @FindAll({@FindBy(xpath="//mat-option[contains(@id,'mat-option-')]")})
    public List<WebElement> securityAnswerOptions;

    public String clickCheckEmailError(){
        PageFactory.initElements(webDriverFactory.getWebDriver(), this);
        email.click();
        password.click();
        return waitForVisibilityOfElement(emailError).getText();

    }
    public String clickCheckPasswordError(){
        password.click();
        repeatPassword.click();
        return waitForVisibilityOfElement(passwordError).getText();
    }

    public String clickCheckRepeatPasswordError(){
        repeatPassword.click();
        securityAnswer.click();
        return waitForVisibilityOfElement(repeatPasswordError).getText();
    }

    public String clickCheckAnswerError(){
        securityAnswer.click();
        repeatPassword.click();
        return waitForVisibilityOfElement(securityAnswerError).getText();
    }
    public String submitUserDetails(String securityPassword) throws InterruptedException {
        PageFactory.initElements(webDriverFactory.getWebDriver(), this);
        Faker faker = new Faker(Locale.UK);
        String userName = faker.name().username();
        email.sendKeys(userName+"@gmail.com");
        password.sendKeys(userName+"123@");
        repeatPassword.sendKeys(userName+"123@");
        securityQuestion.click();
        securityAnswerOptions.stream().
                filter(s->s.getText().equals(securityPassword)).findFirst()
                .ifPresent(WebElement::click);
        securityAnswer.sendKeys(userName);
        Thread.sleep(2000);
        passwordAdvice.click();
        registerButton.click();
        return userName;
    }

}
