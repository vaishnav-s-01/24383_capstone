@login
Feature: User Login
	Background:
    Given the user navigates to Home page
    Then the home page should be visible successfully
    Given the user clicks on "Signup / Login" button	
    
    
  Scenario: Login with valid credentials
    When User enters email "Vaishnav@gmail.com" and password "Vaishnav"
    And Clicks on Login
    Then Verify successful login message and logout


  Scenario: Login with invalid credentials
    When User enters email "invalid@example.com" and password "wrongpass"
    And Clicks on Login
    Then Verify error message "Your email or password is incorrect!"
