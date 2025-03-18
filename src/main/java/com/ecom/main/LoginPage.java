package com.ecom.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

import javax.sound.midi.VoiceStatus;

public class LoginPage {
	private WebDriver driver;

	private By loginButton = By.xpath("//a[contains(text(),'Signup / Login')]");
	private By emailField = By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/form/input[2]");
	private By passwordField = By.name("password");
	private By submitButton = By.xpath("//button[contains(text(),'Login')]");
	private By errorMessage = By.xpath("//p[contains(text(),'Your email or password is incorrect!')]");
	private By logoutButton = By.xpath("//a[contains(text(),'Logout')]");

	public void openLoginPage() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://www.automationexercise.com");

	}

	public void navigation() {
		driver.findElement(loginButton).click();
	}

	public String page_verification() {
		String titleString = driver.getTitle();
		return titleString;
	}

	public void enterCredentials(String email, String password) {
		driver.findElement(emailField).sendKeys(email);
		driver.findElement(passwordField).sendKeys(password);
	}

	public void clickLogin() {
		driver.findElement(submitButton).click();
	}

	public void logout() {
		driver.findElement(logoutButton).click();
	}

	public void closeBrowser() {
		driver.quit();
	}

	public boolean verifyErrorMessage() {
		if (driver.findElement(errorMessage).isDisplayed()) {
			return true;
		}else {
			return false;
		}
	}

	public boolean verifyLoginSuccess() {
		if (driver.findElement(logoutButton).isDisplayed()) {
			return true;
		}else {
			return false;
		}
	}
}
