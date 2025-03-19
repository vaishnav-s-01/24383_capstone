@product
Feature: Product Search and Cart Functionality

Background:
		Given User is on the home page
    When User logs in with valid credentials "Vaishnav@gmail.com" and "Vaishnav"
    Then User should be logged in successfully
    When User navigates to the products page
@remove
  Scenario: add to cart and checkout
    
    When User searches for a product "Blue Top"
    And User adds the product to the cart
    And User proceeds to checkout
    And Reviews the details and clicks place order
    And Enters "vaishnav" "12345677" "232" "11" "24" and clicks pay and confirm order
    Then User verifies order confirmation message "ORDER PLACED!"
    
  Scenario: remove product from cart
  	When User searches for a product "Blue Top"
    And User adds the product to the cart				
    Then User removes the product from the cart
    
	