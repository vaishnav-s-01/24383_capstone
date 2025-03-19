package com.ecom.StepDefinition;

import io.cucumber.java.en.*;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.ecom.main.LoginPage;

import static com.ecom.StepDefinition.Hooks.test;

public class LoginSteps {
	LoginPage loginPage;

	@Given("the user navigates to Home page")
	public void the_user_navigates_to_home_page() {
		loginPage = new LoginPage();
		loginPage.openLoginPage();
		test.log(Status.INFO, "Navigated to Home page");
	}

	@Then("the home page should be visible successfully")
	public void the_home_page_should_be_visible_successfully() {
		String titleString = loginPage.page_verification();
		Assert.assertEquals(titleString, "Automation Exercise");
		test.log(Status.PASS, "Home page verified successfully: " + titleString);
	}

	@Given("the user clicks on {string} button")
	public void the_user_clicks_on_button(String buttonName) {
		try {
			loginPage.navigation();
			test.log(Status.INFO, "Clicked on '" + buttonName + "' button");
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed to click on '" + buttonName + "' button: " + e.getMessage());
			throw e;
		}
	}

	@When("User enters email {string} and password {string}")
	public void user_enters_email_and_password(String email, String password) {
		loginPage.enterCredentials(email, password);
		test.log(Status.INFO, "Entered email: " + email + " and password");
	}

	@When("Clicks on Login")
	public void clicks_on_login() {
		loginPage.clickLogin();
		test.log(Status.INFO, "Clicked on Login button");
	}

	@Then("Verify error message {string}")
	public void verify_error_message(String expectedMessage) {
		Assert.assertTrue(loginPage.verifyErrorMessage(), "Error message not displayed");
		test.log(Status.PASS, "Verified error message: " + expectedMessage);
		loginPage.closeBrowser();
	}

	@Then("Verify successful login message and logout")
	public void verify_successful_login_message_and_logout() {
		Assert.assertTrue(loginPage.verifyLoginSuccess(), "Login was not successful");
		test.log(Status.PASS, "Login successful");
		loginPage.closeBrowser();
	}
}
