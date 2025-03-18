package com.ecom.main;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

public class RegistrationPage {
    private WebDriver driver;

    public void navigateToRegistrationPage() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.automationexercise.com/login");
    }

    public void enterInitialDetails(String name, String email) {
        driver.findElement(By.xpath("//*[@id='form']/div/div/div[3]/div/form/input[2]")).sendKeys(name);
        driver.findElement(By.xpath("//*[@id='form']/div/div/div[3]/div/form/input[3]")).sendKeys(email);
    }

    public void clickSignupAndRedirect() {
        WebElement signupButton = driver.findElement(By.xpath("//*[@id='form']/div/div/div[3]/div/form/button"));
        clickUsingJS(signupButton);
    }

    public void enterUserDetails(String title, String password, String day, String month, String year,
                                 String firstName, String lastName, String company, String address,
                                 String address2, String country, String state, String city, String zipcode, String mobile) {
        if (title.equalsIgnoreCase("mr")) {
            clickUsingJS(driver.findElement(By.id("id_gender1")));
        } else if (title.equalsIgnoreCase("mrs")) {
            clickUsingJS(driver.findElement(By.id("id_gender2")));
        }

        driver.findElement(By.id("password")).sendKeys(password);

        new Select(driver.findElement(By.id("days"))).selectByValue(day);
        new Select(driver.findElement(By.id("months"))).selectByVisibleText(month);
        new Select(driver.findElement(By.id("years"))).selectByValue(year);

        checkCheckbox(By.id("newsletter"));
        checkCheckbox(By.id("optin"));

        driver.findElement(By.id("first_name")).sendKeys(firstName);
        driver.findElement(By.id("last_name")).sendKeys(lastName);
        driver.findElement(By.id("company")).sendKeys(company);
        driver.findElement(By.id("address1")).sendKeys(address);
        driver.findElement(By.id("address2")).sendKeys(address2);

        new Select(driver.findElement(By.id("country"))).selectByVisibleText(country);

        driver.findElement(By.id("state")).sendKeys(state);
        driver.findElement(By.id("city")).sendKeys(city);
        driver.findElement(By.id("zipcode")).sendKeys(zipcode);
        driver.findElement(By.id("mobile_number")).sendKeys(mobile);
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
        WebElement createAccountBtn = driver.findElement(By.xpath("//button[contains(text(),'Create Account')]"));
        clickUsingJS(createAccountBtn);
    }

    public String getSuccessMessage() {
        WebElement successMessage = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//b[contains(text(), 'Account Created!')]")));
        return successMessage.getText();
    }

    public String getIntermediateMessage(String buttonText) {
        WebElement continueButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-qa='continue-button']")));
        return continueButton.getText();
    }

    public String deleteAccount() {

        WebElement continueButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-qa='continue-button']")));
        clickUsingJS(continueButton);

        WebElement deleteBtn = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Delete Account')]")));
        clickUsingJS(deleteBtn);

        WebElement confirmMessage = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//b[contains(text(), 'Account Deleted!')]")));
        String deleteMessage = confirmMessage.getText();

        WebElement finalContinueButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-qa='continue-button']")));
        clickUsingJS(finalContinueButton);

        return deleteMessage;
    }


    public void closeBrowser() {
        driver.quit();
    }
}
