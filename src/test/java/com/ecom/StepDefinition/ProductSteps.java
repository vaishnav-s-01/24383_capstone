package com.ecom.StepDefinition;

import com.ecom.main.ProductPage;
import io.cucumber.java.en.*;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

public class ProductSteps {

    ProductPage productPage;

    @Given("User is on the home page")
    public void user_is_on_the_home_page() {
        productPage = new ProductPage();
        String expectedTitle = "Automation Exercise";
        String actualTitle =  productPage.homepage();
        assertEquals(actualTitle, expectedTitle, "Home page title mismatch");
        System.out.println("Home page opened successfully with title: " + actualTitle);
    }

    @When("User logs in with valid credentials {string} and {string}")
    public void user_logs_in_with_valid_credentials_and(String email, String password) {
        productPage.login(email, password);
        boolean loginSuccess = productPage.isUserLoggedIn();
        assertEquals(loginSuccess, true, "Login failed - user not logged in.");
        System.out.println("User logged in successfully as: " + email);
    }


    @Then("User should be logged in successfully")
    public void user_should_be_logged_in_successfully() {
        assertTrue(productPage.isUserLoggedIn(), "User login failed");
        System.out.println("User logged in successfully");
    }

    @When("User navigates to the products page")
    public void user_navigates_to_the_products_page() {
        productPage.goToProductsPage();
        System.out.println("User navigated to the products page");
    }

    @When("User searches for a product {string}")
    public void user_searches_for_a_product(String productName) {
        productPage.searchProduct(productName);
        System.out.println("Searched for product: " + productName);
    }

    @When("User adds the product to the cart")
    public void user_adds_the_product_to_the_cart() {
        productPage.addProductToCart();
        System.out.println("Product added to cart");
    }

    @When("User proceeds to checkout")
    public void user_proceeds_to_checkout() {
        productPage.goToCart();
        productPage.proceedToCheckout();
        System.out.println("Proceeded to checkout");
    }

    @When("Reviews the details and clicks place order")
    public void reviews_the_details_and_clicks_place_order() {
        productPage.reviewAndPlaceOrder();
        System.out.println("Reviewed order and placed it");
    }

    @When("Enters {string} {string} {string} {string} {string} and clicks pay and confirm order")
    public void enters_and_clicks_pay_and_confirm_order(String name, String cardNumber, String cvc, String expiryMonth, String expiryYear) {
        productPage.enterPaymentDetailsAndConfirm(name, cardNumber, cvc, expiryMonth, expiryYear);
        System.out.println("Entered payment details and confirmed order");
    }

    @Then("User verifies order confirmation message {string}")
    public void user_verifies_order_confirmation_message(String expectedMessage) {
        String actualMessage = productPage.getOrderConfirmationMessage();
        assertEquals(actualMessage, expectedMessage, "Order confirmation message mismatch");
        System.out.println("Verified order confirmation message: " + actualMessage);
        productPage.closeBrowser();
    }

    @When("User removes the product from the cart")
    public void user_removes_the_product_from_the_cart() {
        productPage.goToCart();
        productPage.removeProductFromCart();
        System.out.println("Product removed from cart");
        productPage.closeBrowser();
    }
}
