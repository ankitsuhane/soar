package com.soar.test.stepdefinitions.web.stepdef;

import com.soar.test.stepdefinitions.web.pages.pages.*;
import cucumber.api.java8.En;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

public class TestStepDefinition implements En {

	@Autowired
	HomePage homePage;

	@Autowired
	LoginPage loginPage;

	@Autowired
	CartPage cartPage;

	@Autowired
	AddressPage addressPage;

	@Autowired
	PaymentOptionsPage paymentOptionsPage;

	@Autowired
	DeliveryPage deliveryPage;

	@Autowired
	OrderSummaryPage orderSummaryPage;

	@Autowired
	RegistrationPage registrationPage;

	String maxObjectsInPage, userName;

	public TestStepDefinition() {

		Given("Open HomePage", () -> {
			homePage.accessFirstTimeHomePage();
		});

		Given("Scroll down to the end of page", () -> {
			homePage.scrollToBottomPage();
		});

		When("Change items per page to the maximum number", () -> {
			maxObjectsInPage =homePage.getMaxValueItemDropDown();
			homePage.changeMaxItems();
		});

		Then("Home page displays all of items", () -> {
			Assert.assertEquals(Integer.parseInt(maxObjectsInPage), homePage.getMaxItemsInPage());
		});

		When("Click on the first product", () -> {
			homePage.clickFirstProduct();
		});

		Then("Popup appeared and image of product exists", () -> {
			Assert.assertTrue(homePage.getImage().endsWith(".jpg") ||
					homePage.getImage().endsWith(".jpeg")|| homePage.getImage().endsWith(".png"));
		});

		When("Expand the review this review and close the product", () -> {
			homePage.expandReviewButton();
			homePage.clickCloseButton();
		});

		Given("Goto Registration page", () -> {
			homePage.openLoginPage();
			loginPage.newCustomer();
		});

		Then("validate the error messages on clicking the text boxes", () -> {
			Assert.assertEquals("Please provide an email address.", registrationPage.clickCheckEmailError());
			Assert.assertEquals("Please provide a password.", registrationPage.clickCheckPasswordError());
			Assert.assertEquals("Please repeat your password.", registrationPage.clickCheckRepeatPasswordError());
			Assert.assertEquals("Please provide an answer to your security question.", registrationPage.clickCheckAnswerError());
		});

		And("^Fill the registration form and click on show password advice \"([^\"]*)\"$",
				(String securityPassword) -> {
			userName = registrationPage.submitUserDetails(securityPassword);
		});

		Then("registration successful and user logged in", () -> {
			loginPage.login(userName);
			Assert.assertTrue(homePage.checkAllProductHeadingPresent());
		});

		Given("^A user is logged-in whose \"([^\"]*)\"$", (String securityPassword) -> {
			homePage.openLoginPage();
			loginPage.newCustomer();
			userName = registrationPage.submitUserDetails(securityPassword);
			loginPage.login(userName);
		});

		When("add five different product in the cart and checkout", () -> {
			homePage.addProductsCount(5);
			Assert.assertEquals(5, cartPage.verifyProductCount(5));
			Assert.assertTrue(cartPage.amendProductsVerifyPriceChange());
			cartPage.submitCheckout();
		});

		When("Add a new address of the user and select it", () -> {
			addressPage.clickNewAddressButton();
			addressPage.submitForm(userName);
			addressPage.confirmAddress();
		});

		When("Select any delivery option", () -> {
			deliveryPage.selectDeliveryOption();
			deliveryPage.pressContinue();
		});

		When("Add a new credit card when wallet balance is zero", () -> {
			if(paymentOptionsPage.isWalletBalanceZero(3)) {
				paymentOptionsPage.addNewCreditCard();
				paymentOptionsPage.enterCardDetails();
				paymentOptionsPage.pressSubmit();
			}
		});
		Then("^Order should be placed$", () -> {
			Assert.assertEquals("Thank you for your purchase!", orderSummaryPage.orderSummary());
		});
	}
}