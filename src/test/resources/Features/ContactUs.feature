@contact
Feature: Contact Us Form

  Scenario: Submit Contact Form with Valid Details
    Given User navigates to the Contact Us page
    When User enters valid details and submits the form
    Then Success message "Success! Your details have been submitted successfully." should be displayed

  Scenario: Submit Contact Form with Missing Fields
    Given User navigates to the Contact Us page
   	When User submits the form without filling the email field
    Then Error message "Please fill out this field." should be displayed for "email"
