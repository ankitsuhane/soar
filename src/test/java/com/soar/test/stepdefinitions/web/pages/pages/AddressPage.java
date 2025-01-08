package com.soar.test.stepdefinitions.web.pages.pages;

import com.github.javafaker.Faker;
import com.soar.framework.web.BaseWebAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;
import java.util.Random;

@Lazy
@Component
@Scope("cucumber-glue")
public class AddressPage extends BaseWebAction {

    @FindBy(xpath = "//span[contains(text(),'Add New Address')]")
    private WebElement addAddressButton;

    @FindBy(id = "mat-input-9")
    WebElement countryInput;

    @FindBy(id = "mat-input-10")
    WebElement nameInput;

    @FindBy(id = "mat-input-11")
    WebElement mobileInput;

    @FindBy(id = "mat-input-12")
    WebElement zipCodeInput;

    @FindBy(id = "address")
    WebElement addressInput;

    @FindBy(id = "mat-input-14")
    WebElement cityInput;

    @FindBy(id = "mat-input-15")
    WebElement stateInput;

    @FindBy(id = "submitButton")
    WebElement submitButton;

    @FindBy(css = ".mat-radio-button.mat-accent")
    WebElement selectAddressRadioButton;

    @FindBy(xpath = "//span[text()='Continue']")
    WebElement continueButton;

    @FindBy(css = "span.mat-simple-snack-bar-content")
    public WebElement invisiblePopup;

    public void enterCountry(String country) {
        countryInput.sendKeys(country);
    }

    public void enterName(String name) {
        nameInput.sendKeys(name);
    }

    public void enterMobile(String mobile) {
        mobileInput.sendKeys(mobile);
    }

    public void enterZipCode(String zipCode) {
        zipCodeInput.sendKeys(zipCode);
    }

    public void enterAddress(String address) {
        addressInput.sendKeys(address);
    }

    public void enterCity(String city) {
        cityInput.sendKeys(city);
    }

    public void enterState(String state) {
        stateInput.sendKeys(state);
    }

    public void submitForm(String userName) {
        Faker faker = new Faker(Locale.UK);
        enterCountry(String.valueOf(faker.country().name()));
        enterName(userName);
        enterMobile(String.valueOf(faker.phoneNumber().cellPhone()));
        enterZipCode(String.valueOf(faker.address().zipCode()));
        enterAddress(String.valueOf(faker.address().fullAddress()));
        enterCity(String.valueOf(faker.address().city()));
        enterState(String.valueOf(faker.address().state()));
        submitButton.click();
    }

    public void clickNewAddressButton() {
        PageFactory.initElements(webDriverFactory.getWebDriver(), this);
        addAddressButton.click();
    }

    public void confirmAddress() throws InterruptedException {
        waitForElementInvisible(invisiblePopup);
        Thread.sleep(2000);
        selectAddressRadioButton.click();
        continueButton.click();
    }

}
