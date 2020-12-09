@auth @regression @smoke
Feature: Testing Log in functionality

  Background: Navigating to Web application
    Given User navigates to "URL" application
    Then Verify User successfully navigated to this app

  @TEC-0001
  Scenario: Log in functionality with valid credentials
    Given User log in with "valid" credentials
    Then Verifying User successfully logged in

  @TEC-0002
  Scenario: Reset password functionality
    Given User log in with "invalid" credentials
    When User click on reset password button and provide "valid" email
    Then User can reset password "newPassword"

  @TEC-0003
  Scenario: Verify error states for incorrect login attempts
    Given User provide "invalid" credentials 10 times
    Then User see error message for maximum incorrect login attempts