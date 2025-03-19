package com.ecom.StepDefinition;

import io.cucumber.java.en.*;
import org.testng.Assert;
import com.ecom.main.ContactUsPage;
import com.aventstack.extentreports.Status;
import static com.ecom.StepDefinition.Hooks.test;

public class ContactUsSteps {

    ContactUsPage contactUsPage = new ContactUsPage();

    @Given("User navigates to the Contact Us page")
    public void user_navigates_to_the_contact_us_page() {
        contactUsPage.navigateToContactUsPage();
        test.log(Status.INFO, "Navigated to the Contact Us page");
    }

    @When("User enters valid details and submits the form")
    public void user_enters_valid_details_and_submits_the_form() {
        contactUsPage.fillContactForm("Vaishnav", "Vaishnav@gmail.com", "Feedback", "This is a test message.");
        contactUsPage.submitForm();
        test.log(Status.INFO, "Entered valid contact details and submitted the form");
    }

    @Then("Success message {string} should be displayed")
    public void success_message_should_be_displayed(String message) {
        Assert.assertTrue(contactUsPage.verifySuccessMessage(), "Success message not displayed!");
        test.log(Status.PASS, "Success message displayed: " + message);
    }

    @When("User submits the form without filling the email field")
    public void user_submits_the_form_without_filling_the_email_field() {
        contactUsPage.fillContactForm("Vaishnav", "", "Feedback", "This is a test message.");
        contactUsPage.submitForm();
        test.log(Status.INFO, "Submitted the form without filling the email field");
    }

    @Then("Error message {string} should be displayed for {string}")
    public void error_message_should_be_displayed_for(String expectedMessage, String fieldName) {
        String actualMessage = contactUsPage.verifyEmailErrorMessage();
        Assert.assertEquals(actualMessage, expectedMessage, "Error message mismatch for field: " + fieldName);
        test.log(Status.PASS, "Error message for field '" + fieldName + "' displayed: " + expectedMessage);
    }

}
