package com.ecom.main;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;

public class ContactUsPage {

    WebDriver driver;

    // Constructor to initialize WebDriver
    public ContactUsPage() {
        this.driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://www.automationexercise.com");
    }

    // Locators
    private By contactUsLink = By.xpath("//a[contains(text(),'Contact us')]");
    private By nameField = By.xpath("//input[@data-qa='name']");
    private By emailField = By.xpath("//input[@data-qa='email']");
    private By subjectField = By.xpath("//input[@data-qa='subject']");
    private By messageField = By.xpath("//textarea[@data-qa='message']");
    private By submitButton = By.xpath("//input[@data-qa='submit-button']");
    private By successMessage = By.xpath("//div[contains(text(),'Success! Your details have been submitted successfully.')]");
    private By errorMessage = By.xpath("//div[contains(text(),'Error')]");

    // Navigate to Contact Us page
    public void navigateToContactUsPage() {
        driver.findElement(contactUsLink).click();
    }

    // Fill the Contact Us form
    public void fillContactForm(String name, String email, String subject, String message) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(subjectField).sendKeys(subject);
        driver.findElement(messageField).sendKeys(message);
        
        
    }

    // Submit the form using JavaScript Executor
    public void submitForm() {
        WebElement submitBtn = driver.findElement(submitButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitBtn);
        driver.switchTo().alert().accept();
    }

    // Verify success message
    public boolean verifySuccessMessage(String message) {
        WebElement successMsg = driver.findElement(successMessage);
        return successMsg.isDisplayed();
    }

    // Verify error message
    public boolean verifyErrorMessage() {
        WebElement errorMsg = driver.findElement(errorMessage);
        return errorMsg.isDisplayed();
    }
}

