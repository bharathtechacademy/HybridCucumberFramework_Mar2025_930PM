package com.orangehrm.crm.application.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.orangehrm.crm.framework.web.commons.WebCommons;

public class LoginPageElements extends WebCommons{
	
    @FindBy(xpath = "//img[@alt='company-branding']")
    protected WebElement logo;

    @FindBy(xpath = "//h5")
    protected WebElement header;

    @FindBy(xpath = "//input[@name='username']")
    protected WebElement usernameTxtb;

    @FindBy(xpath = "//input[@name='password']")
    protected WebElement passwordTxtb;

    @FindBy(xpath = "//button[@type='submit']")
    protected WebElement loginBtn;

    @FindBy(xpath = "//p[text()='Forgot your password? ']")
    protected WebElement forgotPassLink;
    
    @FindBy(xpath = "//p[text()='Invalid credentials']")
    protected WebElement loginErrorMessage;

}
