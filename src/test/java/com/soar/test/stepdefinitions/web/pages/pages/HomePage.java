package com.soar.test.stepdefinitions.web.pages.pages;

import com.soar.framework.web.BaseWebAction;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;

@Lazy
@Component
@Scope("cucumber-glue")
public class HomePage extends BaseWebAction {

    @Value("${webui}")
    private String webUI;

    @FindBy(css = ".mat-select-arrow.ng-tns-c30-9")
    public WebElement itemsPerPageArrow;

    @FindAll({@FindBy(css= ".mat-option-text")})
    public List<WebElement> itemsPerPageValues;

    @FindAll({@FindBy(css= "div [class=item-name]")})
    public List<WebElement> itemsNames;

    @FindBy(css = ".cc-compliance a")
    public WebElement meWantItButton;

    @FindBy(xpath = "//span[text()='Dismiss']")
    public WebElement dismissButton;

    @FindBy(xpath = "//img[@class='img-thumbnail']")
    public WebElement getPopUpImage;

    @FindBy(xpath = "//span[contains(text(), 'Reviews')]")
    public WebElement reviewDropDown;

    @FindBy(xpath = "//span[contains(text(), 'Close')]")
    public WebElement closeButton;

    @FindBy(xpath = "//span[contains(text(), 'Account')]")
    public WebElement accountLink;

    @FindBy(id = "navbarLoginButton")
    public WebElement login;

    @FindBy(css = ".heading.mat-elevation-z6")
    public WebElement allProductHeading;

    @FindBy(css = "span.mat-simple-snack-bar-content")
    public WebElement invisiblePopup;

    @FindAll({@FindBy(xpath = "//span[text()='Add to Basket']")})
    public List<WebElement> listAddToBasket;

    @FindBy(xpath = "//mat-icon[contains(text(),'shopping_cart')]")
    public WebElement shoppingCart;

    public void accessFirstTimeHomePage(){
        webDriverFactory.getWebDriver().get(webUI);
        PageFactory.initElements(webDriverFactory.getWebDriver(), this);
        waitForElementToBeClickable(meWantItButton).click();
        waitForElementToBeClickable(dismissButton).click();
        waitForElementInvisible(invisiblePopup);
    }

    public String getMaxValueItemDropDown(){
        waitForElementToBeClickable(itemsPerPageArrow).click();
        return itemsPerPageValues.get(itemsPerPageValues.size()-1).getText();
    }

    public void changeMaxItems(){
        WebElement maxItemsPerPageValue = itemsPerPageValues.get(itemsPerPageValues.size()-1);
        maxItemsPerPageValue.click();
    }

    public int getMaxItemsInPage(){
        return itemsNames.size();
    }

    public void clickFirstProduct() {
        waitForElementToBeClickable(itemsNames.get(0)).click();
    }

    public String getImage() {
        return waitForElementToBeClickable(getPopUpImage).getDomAttribute("src");
    }

    public void expandReviewButton() {
        waitForElementToBeClickable(reviewDropDown).click();
    }

    public void clickCloseButton() {
        waitForElementToBeClickable(closeButton).click();
    }

    public void openLoginPage() {
        accessFirstTimeHomePage();
        waitForElementToBeClickable(accountLink).click();
        waitForElementToBeClickable(login).click();
    }

    public boolean checkAllProductHeadingPresent() {
        return waitForVisibilityOfElement(allProductHeading).isDisplayed();
    }

    public void addProductsCount(int count) {
        waitForElementInvisible(invisiblePopup);
        waitForVisibilityOfElement(allProductHeading);
        for (int j = 0; j < count; j++) {
            listAddToBasket.get(j).click();
            if(waitForVisibilityOfElement(invisiblePopup).getText().contains("We are out of stock! Sorry for the inconvenience."))
                count++;
            waitForElementInvisible(invisiblePopup);
        }
        shoppingCart.click();
    }
    
}
