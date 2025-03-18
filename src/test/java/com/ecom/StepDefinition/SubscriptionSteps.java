package com.ecom.StepDefinition;

import org.testng.Assert;
import com.ecom.main.SubscriptionPage;
import io.cucumber.java.en.*;

public class SubscriptionSteps {

    SubscriptionPage subscriptionPage = new SubscriptionPage();

    @Given("User navigates to the home page")
    public void user_navigates_to_the_home_page() {
        subscriptionPage.navigateToHomePage();
        System.out.println("Navigated to the home page successfully.");
    }

    @When("User subscribes with valid email {string}")
    public void user_subscribes_with_valid_email(String email) {
        subscriptionPage.enterSubscriptionEmail(email);
        subscriptionPage.clickSubscribeButton();
        System.out.println("Subscribed with valid email: " + email);
    }

    @Then("Success message {string} is displayed")
    public void success_message_is_displayed(String expectedMessage) {
        String actualMessage = subscriptionPage.getSuccessMessage();
        Assert.assertEquals(actualMessage, expectedMessage);
        System.out.println("Success message displayed: " + actualMessage);
    }

    @When("User subscribes with invalid email {string}")
    public void user_subscribes_with_invalid_email(String email) {
        subscriptionPage.enterSubscriptionEmail(email);
        subscriptionPage.clickSubscribeButton();
        System.out.println("Subscribed with invalid email: " + email);
    }

    @Then("Error message {string} is displayed")
    public void error_message_is_displayed(String expectedMessage) {
        String actualMessage = subscriptionPage.getErrorMessage();
        Assert.assertEquals(actualMessage, expectedMessage);
        System.out.println("Error message displayed: " + actualMessage);
    }
}
