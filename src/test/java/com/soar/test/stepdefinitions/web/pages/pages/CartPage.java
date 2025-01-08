package com.soar.test.stepdefinitions.web.pages.pages;

import com.soar.framework.web.BaseWebAction;
import org.openqa.selenium.By;
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
public class CartPage extends BaseWebAction {

    @FindAll({@FindBy(css = ".mat-cell.cdk-cell.cdk-column-product.mat-column-product.ng-star-inserted")})
    private List<WebElement> listProductsCell;

    @FindAll({@FindBy(css = "svg[data-icon='plus-square']")})
    private List<WebElement> listPlusIcons;

    @FindAll({@FindBy(css = "svg[data-icon='trash-alt']")})
    private List<WebElement> listDeleteIcons;

    @FindBy(id = "price")
    private WebElement price;

    @FindBy(css = "h1")
    private WebElement yourBasketHeading;

    @FindBy(css = "svg.svg-inline--fa.fa-cart-arrow-down.fa-w-18")
    private WebElement checkoutButton;

    @FindBy(css = "span.mat-simple-snack-bar-content")
    public WebElement invisiblePopup;

    public int verifyProductCount(int numberOfProducts){
        PageFactory.initElements(webDriverFactory.getWebDriver(), this);
        waitForElementToBeClickable(checkoutButton);
        waitForListElementsVisible(By.
                cssSelector(".mat-cell.cdk-cell.cdk-column-product.mat-column-product.ng-star-inserted"),numberOfProducts);
        return listProductsCell.size();
    }

    public boolean amendProductsVerifyPriceChange() throws InterruptedException {
        String beforePrice = price.getText();
        Random random = new Random();
        int productIncrement = random.nextInt(5);
        listPlusIcons.get(productIncrement).click();
        listDeleteIcons.get(productIncrement).click();
        waitForElementInvisible(invisiblePopup);
        Thread.sleep(2000);
        return !price.getText().equals(beforePrice);
    }
    public void submitCheckout(){
        checkoutButton.click();
    }
}
