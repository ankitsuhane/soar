Feature: Soar Tests

  @Web
  Scenario: As a anonymous user, I want to the total number of products displaying on homepage
    Given Open HomePage
    And Scroll down to the end of page
    When Change items per page to the maximum number
    Then Home page displays all of items

  @Web
  Scenario: As a anonymous user, I want to check the first product
    Given Open HomePage
    When Click on the first product
    Then Popup appeared and image of product exists
    When Expand the review this review and close the product

  @Web
  Scenario Outline: As a user, I want to Register myself
    Given Goto Registration page
    When validate the error messages on clicking the text boxes
    And Fill the registration form and click on show password advice "<SecurityPassword>"
    Then registration successful and user logged in
    Examples:
      | SecurityPassword                  |
      | Your eldest siblings middle name? |


  @Web
  Scenario Outline: As a user, I am unable to Buy the products due to insufficient money
    Given A user is logged-in whose "<SecurityPassword>"
    When add five different product in the cart and checkout
    And Add a new address of the user and select it
    And Select any delivery option
    And Add a new credit card when wallet balance is zero
    Then Order should be placed
    Examples:
      | SecurityPassword                  |
      | Your eldest siblings middle name? |