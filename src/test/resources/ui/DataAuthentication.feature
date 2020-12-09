@regression @smoke
Feature: Verify correct data is showing on frontend

  Background: Navigating to Web application
    Given User navigates to "URL" application
    When User log in with "valid" credentials
    Then Verify User successfully navigated to this app

  @TEC-0004
  Scenario: Three columns of images displayed
    Given User is able to see images and tags
    Then Verifying ui data is matching with data base

