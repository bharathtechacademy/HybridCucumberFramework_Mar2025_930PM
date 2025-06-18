package com.orangehrm.crm.application.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.orangehrm.crm.application.elements.LoginPageElements;

/**
 * This class defines reusable **step definitions** for actions and validations
 * on the **Login Page** of the OrangeHRM application.
 * 
 * <p>It extends {@link LoginPageElements} to inherit UI locators and provides high-level
 * behavioral methods that abstract WebElement interactions behind meaningful business flows.
 * These methods are meant to be used in test classes to promote **code reusability,
 * readability**, and **test maintainability**.</p>
 *
 * <p>Initialization of web elements is handled using Selenium’s PageFactory model.
 * Assertions are done using TestNG's Assert class.</p>
 * 
 * <p><b>Responsibilities include:</b></p>
 * <ul>
 *   <li>Launching the application</li>
 *   <li>Interacting with login page elements (username, password, login button)</li>
 *   <li>Validating login page header/logo</li>
 *   <li>Verifying login success/failure and logout behavior</li>
 *   <li>Navigating to Forgot Password flow</li>
 * </ul>
 * 
 * @author Bharath
 * @since 2025-06-15
 */
public class LoginPageSteps extends LoginPageElements {

    /**
     * Constructor initializes all elements on the Login Page
     * using Selenium PageFactory pattern.
     *
     * @param driver WebDriver instance passed from BaseTest
     */
    public LoginPageSteps(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /**
     * Launches the application and verifies the page title
     * against the expected title from properties.
     */
    public void launchTheApplication() {
        launchApplication();
        Assert.assertEquals(getTitle(), prop.getProperty("APP_TITLE"), 
            "Application title mismatch after launch.");
    }

    /**
     * Validates the login page header against the expected text.
     *
     * @param expHeader Expected header text passed from test data
     */
    public void verifyLoginPageHeader(String expHeader) {
        waitForElement(header);
        Assert.assertEquals(getElementText(header), expHeader, 
            "Login page header text mismatch.");
    }

    /**
     * Verifies if the login page logo is present and visible.
     */
    public void verifyLoginPageLogo() {
        waitForElement(logo);
        Assert.assertTrue(isElementDisplayed(logo), 
            "Login page logo is not visible.");
    }

    /**
     * Enters the provided username and password into respective fields.
     *
     * @param username Username to be entered
     * @param password Password to be entered
     */
    public void enterCredentials(String username, String password) {
        waitForElement(usernameTxtb);
        enterText(usernameTxtb, username);

        waitForElement(passwordTxtb);
        enterText(passwordTxtb, password);
    }

    /**
     * Clicks on the login button to initiate the login process.
     */
    public void clickOnLoginButton() {
        waitForElement(loginBtn);
        click(loginBtn);
    }

    /**
     * Verifies if the login error message is displayed after
     * an unsuccessful login attempt.
     */
    public void verifyLoginErrorMessage() {
        waitForElement(loginErrorMessage);
        Assert.assertTrue(isElementDisplayed(loginErrorMessage),
            "Login error message not displayed on invalid login.");
    }

    /**
     * Navigates to the "Forgot Password" page by clicking the link.
     */
    public void clickOnForgotPasswordLink() {
        waitForElement(forgotPassLink);
        click(forgotPassLink);
    }

    /**
     * Verifies successful logout by checking that the login page
     * header is displayed again.
     */
    public void verifyLogoutSuccessful() {
        waitForElement(header);
        Assert.assertTrue(isElementDisplayed(header),
            "Login page header not displayed after logout.");
    }
}
