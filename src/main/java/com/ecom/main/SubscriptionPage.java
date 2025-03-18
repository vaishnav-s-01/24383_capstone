package com.ecom.main;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SubscriptionPage  {

    WebDriver driver ;

    // Locators
    private By subscriptionInput = By.id("susbscribe_email");
    private By subscriptionButton = By.id("subscribe");
    private By successMessage = By.xpath("//div[contains(text(),'You have been successfully subscribed!')]");

    // Navigate to Home Page
    public void navigateToHomePage() {
    	 this.driver = new ChromeDriver();
         driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
         driver.manage().window().maximize();
         driver.get("https://www.automationexercise.com");
    }

    // Enter subscription email
    public void enterSubscriptionEmail(String email) {
        WebElement emailInput = driver.findElement(subscriptionInput);
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    // Click the subscribe button using JavaScript Executor
    public void clickSubscribeButton() {
        WebElement subscribeBtn = driver.findElement(subscriptionButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", subscribeBtn);
    }

    // Get success message with wait
    public String getSuccessMessage() {
        try {
            WebElement successMsg = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(successMessage));
            return successMsg.getText();
        } catch (TimeoutException e) {
            return "";
        }
    }

    // Get error message with wait
 // Get error message using the "validationMessage" attribute
    public String getErrorMessage() {
        WebElement emailField = driver.findElement(subscriptionInput);
        return emailField.getAttribute("validationMessage");
    }

}
