package com.orangehrm.crm.application.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.orangehrm.crm.application.elements.HomePageElements;

/**
 * This class contains high-level **step definitions** for interacting with
 * and verifying elements on the **Home Page** of the OrangeHRM application.
 * 
 * <p>It extends {@link HomePageElements} and encapsulates test actions related to:
 * post-login validations, UI element visibility checks, menu navigation, and logout functionality.</p>
 *
 * <p>The methods here are meant to be used in TestNG test classes to improve
 * modularity, maintainability, and readability of the test scripts.</p>
 *
 * <p><b>Responsibilities include:</b></p>
 * <ul>
 *   <li>Verifying login success</li>
 *   <li>Validating presence of dashboard and profile UI components</li>
 *   <li>Checking all menu items on the home page</li>
 *   <li>Performing user logout</li>
 * </ul>
 * 
 * @author Bharath
 * @since 2025-06-15
 */
public class HomePageSteps extends HomePageElements {

    /**
     * Constructor that initializes all elements of the Home Page
     * using Selenium's PageFactory.
     *
     * @param driver The WebDriver instance used for the current session.
     */
    public HomePageSteps(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /**
     * Verifies that login was successful by waiting for and validating
     * the presence of the dashboard header on the home page.
     */
    public void verifyLoginSuccessful() {
        waitForElement(dashboardHeader);
    }

    /**
     * Verifies that the user profile image is displayed on the home page,
     * which serves as an indicator of a valid and complete login session.
     */
    public void verifyUserProfileImageDisplayed() {
        waitForElement(userProfileImage);
    }

    /**
     * Verifies that all primary menu items are present and visible
     * on the home page. This ensures the UI is fully loaded and ready for interaction.
     */
    public void verifyMenuItemsDisplayed() {
        waitForElement(admin);
        waitForElement(pim);
        waitForElement(leave);
        waitForElement(time);
        waitForElement(recruitment);
        waitForElement(myInfo);
        waitForElement(performance);
        waitForElement(dashBoard);
        waitForElement(directory);
        waitForElement(maintenance);
        waitForElement(claim);
        waitForElement(buzz);		
    }
    /**
	 * Verifies that a specific menu item is present and visible on the home page.
	 * This method can be used to check for any menu item by passing its name.
	 *
	 * @param menuItem The name of the menu item to verify.
	 */
    public void verifyMenuItem(String menuItem) {		
		waitForElement(getmenuItem(menuItem));
	}

    /**
     * Performs logout from the application by clicking on the user profile
     * dropdown and selecting the logout option.
     */
    public void logout() {
        waitForElement(userProfileImage);
        userProfileImage.click();
        waitForElement(userProfileOptions);
        userProfileOptions.click();
        waitForElement(logout);
        logout.click();
    }
}
