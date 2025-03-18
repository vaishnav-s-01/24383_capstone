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
        driver.findElement(By.xpath("//*[@id='form']/div/div/div[3]/div/form/button")).click();
    }

    public void enterUserDetails(String title, String password, String day, String month, String year,
                                 String firstName, String lastName, String company, String address,
                                 String address2, String country, String state, String city, String zipcode, String mobile) {
        if (title.equalsIgnoreCase("mr")) {
            driver.findElement(By.id("id_gender1")).click();
        } else if (title.equalsIgnoreCase("mrs")) {
            driver.findElement(By.id("id_gender2")).click();
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
        WebElement checkbox = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox);
        if (!checkbox.isSelected()) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
        }
    }

    public void submitForm() {
        WebElement createAccountBtn = driver.findElement(By.xpath("//button[contains(text(),'Create Account')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", createAccountBtn);
        createAccountBtn.click();
    }

    public void verifyRegistrationSuccess() {
        WebElement successMessage = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Account Created!')]")));
        if (successMessage.isDisplayed()) {
            System.out.println("Registration successful: 'Account Created!' message is displayed.");
        } else {
            System.err.println("Registration failed: 'Account Created!' message is not displayed.");
        }
    }

    public void closeBrowser() {
        driver.quit();
    }
}
