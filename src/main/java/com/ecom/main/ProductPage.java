package com.ecom.main;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;


public class ProductPage {
	private WebDriver driver;

	// Locators
	private By loginLink = By.xpath("//a[contains(text(),'Signup / Login')]");
	private By emailField = By.xpath("//input[@data-qa='login-email']");
	private By passwordField = By.xpath("//input[@data-qa='login-password']");
	private By submitButton = By.xpath("//button[@data-qa='login-button']");
	private By loggedInUser = By.xpath("//a[contains(text(),'Logged in as')]");
	private By productMenu = By.xpath("//a[contains(text(),'Products')]");
	private By searchBox = By.id("search_product");
	private By searchButton = By.id("submit_search");
	private By productName = By.xpath("//div[@class='productinfo text-center']/p");
	private By addToCartButton = By.xpath("//a[@class='btn btn-default add-to-cart']");
	private By continueShoppingButton = By.xpath("//button[contains(text(),'Continue Shopping')]");
	private By viewCartButton = By.xpath("//a[contains(text(),'Cart')]");
	private By cartProductName = By.xpath("//td[@class='cart_description']/h4/a");
	private By cartProductPrice = By.xpath("//td[@class='cart_price']/p");
	private By cartProductQuantity = By.xpath("//td[@class='cart_quantity']/button");
	private By proceedToCheckoutButton = By.xpath("//a[@class='btn btn-default check_out']");
	private By removeProductButton = By.xpath("//a[@class='cart_quantity_delete']");
	private By placeOrderButton = By.xpath("//a[contains(text(),'Place Order')]");
	private By orderConfirmationMessage = By.xpath("//h2[@data-qa='order-placed']");


	
	// Open Home Page
	public String homepage() {
	    driver = new ChromeDriver();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    driver.manage().window().maximize();
	    driver.get("https://www.automationexercise.com");
	    String titleString = driver.getTitle();
		return titleString;
	}

	// User Login
	public void login(String email, String password) {
	    driver.findElement(loginLink).click();
	    driver.findElement(emailField).sendKeys(email);
	    driver.findElement(passwordField).sendKeys(password);
	    driver.findElement(submitButton).click();
	}


	// Verify User Logged In
	public boolean isUserLoggedIn() {
		return driver.findElement(loggedInUser).isDisplayed();
	}

	// Go to Products Page
	public void goToProductsPage() {
		driver.findElement(productMenu).click();
	}

	// Search for a Product
	public void searchProduct(String productName) {
		driver.findElement(searchBox).sendKeys(productName);
		driver.findElement(searchButton).click();
	}

	// Get Search Result Product Name
	public String getSearchResultProductName() {
		return driver.findElement(this.productName).getText();
	}
	// Add Product to Cart
	public void addProductToCart() {
	    try {
	        // Locate the "Add to Cart" button
	        WebElement addToCartBtn = driver.findElement(addToCartButton);

	        // Click the "Add to Cart" button using JavaScript Executor
	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCartBtn);
	        System.out.println("Product added to cart successfully.");

	        // Click the "Continue Shopping" button using JavaScript Executor
	        WebElement continueShoppingBtn = driver.findElement(continueShoppingButton);
	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueShoppingBtn);
	        System.out.println("Continued shopping after adding the product.");
	    } catch (NoSuchElementException e) {
	        System.err.println("Error: Unable to find the required elements. " + e.getMessage());
	    } catch (Exception e) {
	        System.err.println("Unexpected error occurred while adding product to cart: " + e.getMessage());
	    }
	}

	// Go to Cart
	public void goToCart() {
		driver.findElement(viewCartButton).click();
	}

	// Get Cart Product Name
	public String getCartProductName() {
		return driver.findElement(cartProductName).getText();
	}

	// Get Cart Product Price
	public String getCartProductPrice() {
		return driver.findElement(cartProductPrice).getText();
	}

	// Get Cart Product Quantity
	public String getCartProductQuantity() {
		return driver.findElement(cartProductQuantity).getText();
	}

	// Proceed to Checkout
	public void proceedToCheckout() {
		driver.findElement(proceedToCheckoutButton).click();
	}

	// Review and Place Order
	public void reviewAndPlaceOrder() {
	    WebElement placeOrderBtn = driver.findElement(placeOrderButton);
	    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", placeOrderBtn);
	    System.out.println("Successfully clicked on 'Place Order' button.");
	}


	// Enter Payment Details and Confirm Order
	public void enterPaymentDetailsAndConfirm(String name, String cardNumber, String cvc, String expiryMonth,
			String expiryYear) {
		driver.findElement(By.name("name_on_card")).sendKeys(name);
		driver.findElement(By.name("card_number")).sendKeys(cardNumber);
		driver.findElement(By.name("cvc")).sendKeys(cvc);
		driver.findElement(By.name("expiry_month")).sendKeys(expiryMonth);
		driver.findElement(By.name("expiry_year")).sendKeys(expiryYear);
		driver.findElement(By.id("submit")).click();
	}

	// Get Order Confirmation Message
	public String getOrderConfirmationMessage() {
		return driver.findElement(orderConfirmationMessage).getText();
	}

	// Remove Product from Cart
	public void removeProductFromCart() {
		driver.findElement(removeProductButton).click();
	}

	// Close the Browser
	public void closeBrowser() {
		driver.quit();
	}
}
