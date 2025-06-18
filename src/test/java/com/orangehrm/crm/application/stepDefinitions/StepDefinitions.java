package com.orangehrm.crm.application.stepDefinitions;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import com.orangehrm.crm.application.steps.ForgotLoginSteps;
import com.orangehrm.crm.application.steps.HomePageSteps;
import com.orangehrm.crm.application.steps.LoginPageSteps;
import com.orangehrm.crm.framework.base.BasePage;
import com.orangehrm.crm.framework.utilities.PropUtil;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions {

	public Properties prop = PropUtil.readData("Config.properties");

	public LoginPageSteps loginPage;
	public HomePageSteps homePage;
	public ForgotLoginSteps forgotLogin;

	@Given("Initialize all page objects")
	public void initializePages() {
		WebDriver driver = BasePage.getDriver();
		loginPage = new LoginPageSteps(driver);
		homePage = new HomePageSteps(driver);
		forgotLogin = new ForgotLoginSteps(driver);
	}

	@Given("Launch the application")
	public void launch_the_application() {
		loginPage.launchApplication();
	}

	@Then("Logo Should be displayed in the Login Page")
	public void verifyLogo() {
		loginPage.verifyLoginPageLogo();
	}

	@Then("Login page header should be displayed as {string}")
	public void verifyloginPageHeader(String expValue) {
		loginPage.verifyLoginPageHeader(expValue);
	}

	@When("^I Enter (.*) and (.*)$")
	public void enterUsernameAndPassword(String username, String password) {
		loginPage.enterCredentials(username, password);
	}

	@When("I Click on Login button")
	public void clickOnLoginButton() {
		loginPage.clickOnLoginButton();
	}

	@Then("^login should be (.*)$")
	public void verifyLogin(String status) {
		if (status.equalsIgnoreCase("fail")) {
			loginPage.verifyLoginErrorMessage();
		} else if (status.equalsIgnoreCase("success")) {
			homePage.verifyLoginSuccessful();
		}
	}	

	@When("User click on the forgot password link")
	public void clickOnForgotPasswordLink() {
		loginPage.clickOnForgotPasswordLink();
	}

	@Then("Forgot password page should be launched")
	public void verifyForgotPassPage() {
		forgotLogin.verifyForgotLoginPageIsDisplayed();
	}

	@When("User enter the username as {string} to reset the password")
	public void enterUsernameToResetPass(String username) {
		forgotLogin.enterUsernameTorestThePassword(username);
	}

	@When("Click on the Reset password button")
	public void clickOnresetPasswordBtn() {
		forgotLogin.clickResetPasswordButton();
	}

	@Then("Password reset successful message should be displayed")
	public void verifyPasswordReset() {
		forgotLogin.verifyPasswordResetSuccessMessage();
	}

	@When("User Enter the Credentials")
	public void enterCredentials(DataTable dataTable) {
		List<String> credentials = dataTable.row(1);
		loginPage.enterCredentials(credentials.get(0), credentials.get(1));
	}

	@When("Click on the login button")
	public void clickOnLoginBtn() {
		loginPage.clickOnLoginButton();
	}

	@Then("Login should be successful")
	public void verifyLoginIsSuccessful() {
		homePage.verifyLoginSuccessful();
	}

	@Then("Profile image should be displayed")
	public void verifyProfileImage() {
		homePage.verifyUserProfileImageDisplayed();
	}

	@When("User click on logout button")
	public void clickOnLogout() {
		homePage.logout();
	}

	@Then("Logout should be successful")
	public void verifyLogoutIsSuccessful() {
		loginPage.verifyLogoutSuccessful();
	}

	@Then("Home page should be displayed with below menus")
	public void verifyAllMenus(DataTable dataTable) {
		List<String> value = dataTable.row(1);
		String [] expectedMenus = value.get(0).split(",");
		for(String menu : expectedMenus) {
			homePage.verifyMenuItem(menu.trim());
		}
	}

}
