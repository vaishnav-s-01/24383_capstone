@registration
Feature: User Registration

  Scenario: Successful user registration with valid details from Excel
    Given the user navigates to the registration page
    And enter "example" and "asguard003@gmail.com"
    And clicks signup
    When the user enters registration details from Excel
    And submits the registration form
    Then the user registration should be successful
