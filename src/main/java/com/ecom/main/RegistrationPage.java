package com.ecom.main;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

public class RegistrationPage {
    private WebDriver driver;

    // All locators grouped at the top
    private By nameField = By.xpath("//*[@id='form']/div/div/div[3]/div/form/input[2]");
    private By emailField = By.xpath("//*[@id='form']/div/div/div[3]/div/form/input[3]");
    private By signupButton = By.xpath("//*[@id='form']/div/div/div[3]/div/form/button");
    private By genderMr = By.id("id_gender1");
    private By genderMrs = By.id("id_gender2");
    private By passwordField = By.id("password");
    private By dayDropdown = By.id("days");
    private By monthDropdown = By.id("months");
    private By yearDropdown = By.id("years");
    private By newsletterCheckbox = By.id("newsletter");
    private By optinCheckbox = By.id("optin");
    private By firstNameField = By.id("first_name");
    private By lastNameField = By.id("last_name");
    private By companyField = By.id("company");
    private By address1Field = By.id("address1");
    private By address2Field = By.id("address2");
    private By countryDropdown = By.id("country");
    private By stateField = By.id("state");
    private By cityField = By.id("city");
    private By zipcodeField = By.id("zipcode");
    private By mobileField = By.id("mobile_number");
    private By createAccountButton = By.xpath("//button[contains(text(),'Create Account')]");
    private By successMessage = By.xpath("//b[contains(text(), 'Account Created!')]");
    private By continueButton = By.xpath("//a[@data-qa='continue-button']");
    private By deleteAccountButton = By.xpath("//a[contains(text(),'Delete Account')]");
    private By deleteSuccessMessage = By.xpath("//b[contains(text(), 'Account Deleted!')]");

    public void navigateToRegistrationPage() {
        BasePage.initializeBrowser("chrome");
        driver=BasePage.getDriver();
        driver.get("https://www.automationexercise.com/login");
    }

    public void enterInitialDetails(String name, String email) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(emailField).sendKeys(email);
    }

    public void clickSignupAndRedirect() {
        clickUsingJS(driver.findElement(signupButton));
    }

    public void enterUserDetails(String title, String password, String day, String month, String year,
                                 String firstName, String lastName, String company, String address,
                                 String address2, String country, String state, String city, String zipcode, String mobile) {
        if (title.equalsIgnoreCase("mr")) {
            clickUsingJS(driver.findElement(genderMr));
        } else if (title.equalsIgnoreCase("mrs")) {
            clickUsingJS(driver.findElement(genderMrs));
        }

        driver.findElement(passwordField).sendKeys(password);

        new Select(driver.findElement(dayDropdown)).selectByValue(day);
        new Select(driver.findElement(monthDropdown)).selectByVisibleText(month);
        new Select(driver.findElement(yearDropdown)).selectByValue(year);

        checkCheckbox(newsletterCheckbox);
        checkCheckbox(optinCheckbox);

        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(companyField).sendKeys(company);
        driver.findElement(address1Field).sendKeys(address);
        driver.findElement(address2Field).sendKeys(address2);

        new Select(driver.findElement(countryDropdown)).selectByVisibleText(country);

        driver.findElement(stateField).sendKeys(state);
        driver.findElement(cityField).sendKeys(city);
        driver.findElement(zipcodeField).sendKeys(zipcode);
        driver.findElement(mobileField).sendKeys(mobile);
    }

    private void checkCheckbox(By locator) {
        WebElement checkbox = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(locator));
        clickUsingJS(checkbox);
    }

    private void clickUsingJS(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        js.executeScript("arguments[0].click();", element);
    }

    public void submitForm() {
        clickUsingJS(driver.findElement(createAccountButton));
    }

    public String getSuccessMessage() {
        WebElement message = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        return message.getText();
    }

    public String getIntermediateMessage(String buttonText) {
        WebElement message = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(continueButton));
        return message.getText();
    }

    public String deleteAccount() {
        WebElement contButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(continueButton));
        clickUsingJS(contButton);

        WebElement deleteBtn = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(deleteAccountButton));
        clickUsingJS(deleteBtn);

        WebElement confirmMessage = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(deleteSuccessMessage));
        String deleteMessage = confirmMessage.getText();

        WebElement finalContinueButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(continueButton));
        clickUsingJS(finalContinueButton);

        return deleteMessage;
    }

    public void closeBrowser() {
        driver.quit();
    }
}
