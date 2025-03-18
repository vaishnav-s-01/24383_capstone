package com.ecom.StepDefinition;

import io.cucumber.java.en.*;
import org.testng.Assert;
import com.ecom.main.LoginPage;

public class LoginSteps {
	LoginPage loginPage;

	@Given("the user navigates to Home page")
	public void the_user_navigates_to_home_page(){
		loginPage = new LoginPage();
		loginPage.openLoginPage();
	} 

	@Then("the home page should be visible successfully")
	public void the_home_page_should_be_visible_successfully() {
		String titleString = loginPage.page_verification();
		Assert.assertEquals(titleString,"Automation Exercise");
	}

	@Given("the user clicks on {string} button")
	public void the_user_clicks_on_button(String string) {
		loginPage.navigation();
	}

	@When("User enters email {string} and password {string}")
	public void user_enters_email_and_password(String email, String password) {
		 loginPage.enterCredentials(email, password);
	}

	@When("Clicks on Login")
	public void clicks_on_login() {
		 loginPage.clickLogin();
	}

	@Then("Verify error message {string}")
	public void verify_error_message(String string) {
		 Assert.assertTrue(loginPage.verifyErrorMessage(), "Error message not displayed");
		 loginPage.closeBrowser();
	}

	@Then("Verify successful login message and logout")
	public void verify_successful_login_message_and_logout() {
		 Assert.assertTrue(loginPage.verifyLoginSuccess(), "Login was not successful");
       loginPage.closeBrowser();
	}
}
