package com.orangehrm.crm.application.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.orangehrm.crm.framework.web.commons.WebCommons;

public class ForgotLoginElements extends WebCommons {
	
	@FindBy(xpath = "//h6[text()='Reset Password']")
    protected WebElement forgotLoginPageHeader;
	
	@FindBy(xpath = "//h6[text()='Reset Password']")
    protected WebElement usernameTxtb;
	
	@FindBy(xpath = "//h6[text()='Reset Password']")
    protected WebElement cancelButton;
	
	@FindBy(xpath = "//h6[text()='Reset Password']")
    protected WebElement resetPasswordButton;
	
	@FindBy(xpath = "//h6[text()='Reset Password link sent successfully']")
    protected WebElement passwordRestSuccessMessage;

}
