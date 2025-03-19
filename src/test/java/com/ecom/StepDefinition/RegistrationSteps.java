package com.ecom.StepDefinition;

import com.ecom.main.RegistrationPage;
import com.ecom.util.ExcelUtils;
import io.cucumber.java.en.*;
import com.aventstack.extentreports.Status;
import static com.ecom.StepDefinition.Hooks.test;
import static org.testng.Assert.assertEquals;

public class RegistrationSteps {

    RegistrationPage registrationPage = new RegistrationPage();

    @Given("the user navigates to the registration page")
    public void userNavigatesToRegistrationPage() {
        registrationPage.navigateToRegistrationPage();
        test.log(Status.INFO, "Navigated to registration page");
    }

    @And("enter {string} and {string}")
    public void enterNameAndEmail(String name, String email) {
        registrationPage.enterInitialDetails(name, email);
        test.log(Status.INFO, "Entered Name: " + name + " and Email: " + email);
    }

    @And("clicks signup")
    public void clickSignup() {
        registrationPage.clickSignupAndRedirect();
        test.log(Status.INFO, "Clicked on Signup button");
    }

    @When("the user enters registration details from Excel")
    public void userEntersDetailsFromExcel() {
        ExcelUtils.loadExcelFile("src/test/resources/TestData/SignupData.xlsx", "Sheet1");
        String title = ExcelUtils.getCellData(1, 2);
        String password = ExcelUtils.getCellData(1, 3);
        String day = ExcelUtils.getCellData(1, 4);
        String month = ExcelUtils.getCellData(1, 5);
        String year = ExcelUtils.getCellData(1, 6);
        String firstName = ExcelUtils.getCellData(1, 7);
        String lastName = ExcelUtils.getCellData(1, 8);
        String company = ExcelUtils.getCellData(1, 9);
        String address = ExcelUtils.getCellData(1, 10);
        String address2 = ExcelUtils.getCellData(1, 11);
        String country = ExcelUtils.getCellData(1, 12);
        String state = ExcelUtils.getCellData(1, 13);
        String city = ExcelUtils.getCellData(1, 14);
        String zipcode = ExcelUtils.getCellData(1, 15);
        String mobile = ExcelUtils.getCellData(1, 16);

        registrationPage.enterUserDetails(title, password, day, month, year, firstName, lastName, company, address, address2, country, state, city, zipcode, mobile);
        ExcelUtils.closeWorkbook();
        test.log(Status.INFO, "Entered registration details from Excel");
    }


    @And("submits the registration form")
    public void submitRegistrationForm() {
        registrationPage.submitForm();
        test.log(Status.INFO, "Submitted the registration form");
    }

    
    @Then("the user registration should be successful")
    public void verifyUserRegistrationSuccess() {
        String actualMessage = registrationPage.getSuccessMessage();
        assertEquals(actualMessage, "ACCOUNT CREATED!", "Registration success message mismatch.");

        String intermediateMessage = registrationPage.getIntermediateMessage("Continue");
        assertEquals(intermediateMessage, "Continue", "Intermediate message mismatch.");

        String deleteMessage = registrationPage.deleteAccount();
        assertEquals(deleteMessage, "ACCOUNT DELETED!", "Account deletion message mismatch.");
        test.log(Status.PASS, "Registration success message displayed: " + actualMessage);
        registrationPage.closeBrowser();
    }
}


