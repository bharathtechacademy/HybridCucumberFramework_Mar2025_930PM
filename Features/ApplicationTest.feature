#Author: Bharath Reddy (BharathTechAcademy@gmail.com)
Feature: User Authentication and Home Page Functionalities
  This feature covers core functionalities of the OrangeHRM web application such as 
  verifying login/logout flows, logo and header validations, forgot password process, 
  dynamic login credential testing using scenario outlines, user profile presence, 
  and validating the home page menu components for different user scenarios.
  
  
  Background: Initialize all Pages
  Given Initialize all page objects

  @Regression @Sanity
  Scenario: Verify Login Page Logo and Login Page Header
    Given Launch the application
    Then Logo Should be displayed in the Login Page
    And Login page header should be displayed as "Login"

  @Regression
  Scenario Outline: Verify Application Login With <scenario> Credentials
    Given Launch the application
    When I Enter <username> and <password>
    And I Click on Login button
    Then login should be <status>

    Examples: 
      | scenario | username | password   | status  |
      | Valid    | Admin    | admin123   | success |
      | Invalid  | Bharath  | bharath123 | fail    |

  @Sanity
  Scenario: Verify Forgot Login Feature
    Given Launch the application
    When User click on the forgot password link
    Then Forgot password page should be launched
    When User enter the username as "Admin" to reset the password
    And Click on the Reset password button
    Then Password reset successful message should be displayed

  @Sanity
  Scenario: Verify the presence of the user profile image on the home page
    Given Launch the application
    When User Enter the Credentials
      | username | password |
      | Admin    | admin123 |
    And Click on the login button
    Then Login should be successful
    And Profile image should be displayed
    When User click on logout button
    Then Logout should be successful

  @Sanity
  Scenario: Verify Home Page Menus
    Given Launch the application
    When User Enter the Credentials
      | username | password |
      | Admin    | admin123 |
    And Click on the login button
    Then Login should be successful
    And Home page should be displayed with below menus
      | Menus                                                                                                      |
      | Admin, PIM, Leave, Time, Recruitment, My Info, Performance, Dashboard, Directory, Maintenance, Claim, Buzz |
    When User click on logout button
    Then Logout should be successful
