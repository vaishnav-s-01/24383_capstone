package com.ecom.StepDefinition;

import com.ecom.main.ProductPage;
import io.cucumber.java.en.*;
import com.aventstack.extentreports.Status;
import static com.ecom.StepDefinition.Hooks.test;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

public class ProductSteps {

    ProductPage productPage = new ProductPage();

    @Given("User is on the home page")
    public void user_is_on_the_home_page() {
        String actualTitle = productPage.homepage();
        assertEquals(actualTitle, "Automation Exercise", "Home page title mismatch");
        test.log(Status.PASS, "Home page opened successfully with title: " + actualTitle);
    }

    @When("User logs in with valid credentials {string} and {string}")
    public void user_logs_in_with_valid_credentials(String email, String password) {
        productPage.login(email, password);
        test.log(Status.INFO, "Logged in with valid credentials: " + email);
    }

    @Then("User should be logged in successfully")
    public void user_should_be_logged_in_successfully() {
        assertTrue(productPage.isUserLoggedIn(), "User login failed");
        test.log(Status.PASS, "User logged in successfully");
    }

    @When("User navigates to the products page")
    public void user_navigates_to_the_products_page() {
        productPage.goToProductsPage();
        test.log(Status.INFO, "Navigated to the products page");
    }

    @When("User searches for a product {string}")
    public void user_searches_for_a_product(String productName) {
        productPage.searchProduct(productName);
        test.log(Status.INFO, "Searched for product: " + productName);
    }

    @When("User adds the product to the cart")
    public void user_adds_the_product_to_the_cart() {
        productPage.addProductToCart();
        test.log(Status.INFO, "Product added to cart");
    }

    @When("User proceeds to checkout")
    public void user_proceeds_to_checkout() {
        productPage.proceedToCheckout();
        test.log(Status.INFO, "Proceeded to checkout");
    }

    @When("Reviews the details and clicks place order")
    public void reviews_the_details_and_clicks_place_order() {
        productPage.reviewAndPlaceOrder();
        test.log(Status.INFO, "Reviewed order details and placed order");
    }

    @When("Enters {string} {string} {string} {string} {string} and clicks pay and confirm order")
    public void enters_payment_details_and_clicks_pay_and_confirm_order(String name, String cardNumber, String cvc, String expiryMonth, String expiryYear) {
        productPage.enterPaymentDetailsAndConfirm(name, cardNumber, cvc, expiryMonth, expiryYear);
        test.log(Status.INFO, "Entered payment details and confirmed the order");
    }

    @Then("User verifies order confirmation message {string}")
    public void user_verifies_order_confirmation_message(String message) {
        String confirmationMessage = productPage.getOrderConfirmationMessage();
        assertEquals(confirmationMessage, message, "Order confirmation message mismatch");
        test.log(Status.PASS, "Order confirmation message displayed: " + confirmationMessage);
    }

    @Then("User removes the product from the cart")
    public void user_removes_the_product_from_the_cart() {
        productPage.removeProductFromCart();
        test.log(Status.INFO, "Product removed from cart");
    }
}
