package com.soar.test.stepdefinitions.web.pages.pages;

import com.github.javafaker.CreditCardType;
import com.github.javafaker.Faker;
import org.apache.jmeter.processor.*;
import com.soar.framework.web.BaseWebAction;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.Locale;
import java.util.stream.Collectors;

@Lazy
@Component
@Scope("cucumber-glue")
public class PaymentOptionsPage extends BaseWebAction {


    @FindBy(css = ".ng-tns-c21-41.ng-star-inserted")
    WebElement cardNameFocus;

    @FindBy(css = ".mat-form-field-infix.ng-tns-c21-41 input")
    WebElement cardName;

    @FindBy(xpath = "//mat-panel-description[contains(text(),'Add a credit or debit card')]")
    WebElement addCardButton;

    @FindBy(css = ".mat-form-field-infix.ng-tns-c21-42 input")
    WebElement cardNumber;

    @FindBy(css = ".mat-form-field-infix.ng-tns-c21-43 select")
    WebElement cardMonth;

    @FindBy(css = ".mat-form-field-infix.ng-tns-c21-44 select")
    WebElement cardYear;

    @FindBy(xpath = "//span[contains(text(),'Submit')]")
    WebElement submitButton;

    @FindBy(css = "span.mat-simple-snack-bar-content")
    WebElement invisiblePopup;

    @FindBy(css = ".mat-radio-container")
    WebElement cardRadioButton;

    @FindBy(xpath = "//span[text()='Continue']")
    WebElement continueButton;

    public boolean isWalletBalanceZero(int numberOfProducts) throws InterruptedException {
        PageFactory.initElements(webDriverFactory.getWebDriver(), this);
        waitForElementInvisible(invisiblePopup);
        List<WebElement> wallet = waitForListElementsVisible(By.cssSelector("span.card-title"),numberOfProducts);
        Thread.sleep(2000);
        String walletBalanceValue = wallet.stream()
                .map(WebElement::getText).collect(Collectors.joining());
        return walletBalanceValue.contains("0.00");
    }

    public void enterCardDetails() throws InterruptedException {
        Faker faker = new Faker(Locale.UK);
        webDriverFactory.getWebDriver().findElement(RelativeLocator.with(By.tagName("input")).below(By.cssSelector("h1"))).click();
        Thread.sleep(2000);
        webDriverFactory.getWebDriver().findElement(RelativeLocator.with(By.tagName("input")).below(By.cssSelector("h1"))).sendKeys(faker.name().name()+Keys.TAB);
        String id =webDriverFactory.getWebDriver().findElement(RelativeLocator.with(By.tagName("input")).below(By.cssSelector("h1"))).getDomAttribute("id");
        id = id.substring(0,id.lastIndexOf("-")+1) +incrementId(id);
        webDriverFactory.getWebDriver().findElement(By.id(id)).sendKeys(faker.finance().creditCard(CreditCardType.LASER)+Keys.TAB);
        id = id.substring(0,id.lastIndexOf("-")+1) +incrementId(id);
        webDriverFactory.getWebDriver().findElement(By.id(id)).sendKeys(String.valueOf(faker.number().numberBetween(1, 12))+Keys.TAB);
        id = id.substring(0,id.lastIndexOf("-")+1) +incrementId(id);
        webDriverFactory.getWebDriver().findElement(By.id(id)).sendKeys(String.valueOf(faker.number().numberBetween(2080,2099))+Keys.TAB);
    }

    public void addNewCreditCard() {
        addCardButton.click();
    }

    public void pressSubmit() throws InterruptedException {
        submitButton.click();
        waitForElementInvisible(invisiblePopup);
        Thread.sleep(3000);
        cardRadioButton.click();
        continueButton.click();
    }

    private String incrementId(String id) {
        int lastIndex = id.lastIndexOf("-");
        String numPart = id.substring(lastIndex + 1);
        int num = Integer.parseInt(numPart);
        num++;
        return String.valueOf(num);
    }
}