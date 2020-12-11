@regression @smoke
Feature: Verify correct data is showing on frontend

  Background: Navigating to Web application
    Given User navigates to "URL" application
    When User log in with "valid" credentials
    Then Verify User successfully navigated to this app

  @TEC-0005
  Scenario: Verify users can successfully sort by date in ui
    Given User is able to sort by date
    Then Verifying sorted date is matching with data base

  @TEC-0006
  Scenario: Verify users can successfully search by date picker in ui
    Given User is able to search by date picker
    Then Verifying searched data by date picker show on ui

  @TEC-0007
  Scenario: Verify users can successfully search by team name in ui
    Given User is able to search by team name
    Then Verifying searched data by team name show on ui

  @TEC-0008
  Scenario: Verify users can download csv file
    Given User is able to download csv file
    Then Verifying downloaded data equals to data on ui
