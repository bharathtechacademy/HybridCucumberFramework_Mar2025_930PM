package com.orangehrm.crm.application.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.orangehrm.crm.application.elements.ForgotLoginElements;

/**
 * This class provides **step definitions** related to the
 * **"Forgot Login / Reset Password"** feature in the OrangeHRM application.
 * 
 * <p>It extends {@link ForgotLoginElements} and includes high-level
 * actions that abstract away low-level interactions with the Forgot Password page.</p>
 * 
 * <p>It supports functionality such as verifying that the forgot login page is loaded,
 * entering the username, clicking on reset password, and verifying the success message.</p>
 * 
 * <p><b>Key Responsibilities:</b></p>
 * <ul>
 *   <li>Page validation of the Forgot Login screen</li>
 *   <li>Form submission for password reset</li>
 *   <li>Success confirmation after reset action</li>
 * </ul>
 * 
 * <p>Designed to be used in TestNG test classes to ensure test code remains
 * readable, maintainable, and aligned with business flows.</p>
 * 
 * @author Bharath
 * @since 2025-06-15
 */
public class ForgotLoginSteps extends ForgotLoginElements {

    /**
     * Constructor that initializes all Forgot Login page elements using
     * Selenium PageFactory pattern.
     *
     * @param driver WebDriver instance passed from the test layer.
     */
    public ForgotLoginSteps(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /**
     * Verifies that the Forgot Login page is displayed correctly
     * by checking the presence of the header element.
     */
    public void verifyForgotLoginPageIsDisplayed() {
        waitForElement(forgotLoginPageHeader);
    }

    /**
     * Enters the provided username in the Forgot Login form
     * to initiate the password reset process.
     *
     * @param username The application username to reset the password for.
     */
    public void enterUsernameTorestThePassword(String username) {
        waitForElement(usernameTxtb);
        enterText(usernameTxtb, username);
    }

    /**
     * Clicks on the "Reset Password" button to trigger the
     * password reset functionality.
     */
    public void clickResetPasswordButton() {
        waitForElement(resetPasswordButton);
        click(resetPasswordButton);
    }

    /**
     * Verifies that a success message is displayed after the password
     * reset request is submitted successfully.
     */
    public void verifyPasswordResetSuccessMessage() {
        waitForElement(passwordRestSuccessMessage);
    }
}
