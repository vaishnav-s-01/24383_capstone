@subscription
Feature: Subscription Functionality

  Scenario: Subscribe with Valid Email
    Given User navigates to the home page
    When User subscribes with valid email "Vaishnav@gmail.com"
    Then Success message "You have been successfully subscribed!" is displayed

  Scenario: Subscribe with Missing '@' Symbol
  Given User navigates to the home page
  When User subscribes with invalid email "invalidemail"
  Then Error message "Please include an '@' in the email address. 'invalidemail' is missing an '@'." is displayed

	Scenario: Subscribe with Missing Domain
  Given User navigates to the home page
  When User subscribes with invalid email "user@"
  Then Error message "Please enter a part following '@'. 'user@' is incomplete." is displayed

