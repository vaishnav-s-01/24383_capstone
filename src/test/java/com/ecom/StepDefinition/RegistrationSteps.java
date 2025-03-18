package com.ecom.StepDefinition;


import com.ecom.main.RegistrationPage;
import com.ecom.util.ExcelUtils;
import io.cucumber.java.en.*;

public class RegistrationSteps {

    RegistrationPage registrationPage;

    @Given("the user navigates to the registration page")
    public void userNavigatesToRegistrationPage() {

        registrationPage = new RegistrationPage();
        registrationPage.navigateToRegistrationPage();
    }
    @Given("enter {string} and {string}")
    public void enter_and(String string, String string2) {
    	registrationPage.enterInitialDetails(string, string2);
    }
    @Given("clicks signup")
    public void clicks_signup() {
        registrationPage.clickSignupAndRedirect();
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
    }

    @And("submits the registration form")
    public void submitsRegistrationForm() {
        registrationPage.submitForm();
    }

    @Then("the user registration should be successful")
    public void verifyUserRegistrationSuccess() {
        registrationPage.verifyRegistrationSuccess();
        registrationPage.closeBrowser();

    }
}
