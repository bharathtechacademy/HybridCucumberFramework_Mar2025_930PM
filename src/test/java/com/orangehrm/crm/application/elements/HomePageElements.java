package com.orangehrm.crm.application.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.orangehrm.crm.framework.web.commons.WebCommons;

public class HomePageElements extends WebCommons {

	@FindBy(xpath = "//h6[text()='Dashboard']")
    protected WebElement dashboardHeader;

    @FindBy(xpath = "//img[@class='oxd-userdropdown-img']")
    protected WebElement userProfileImage;

    @FindBy(xpath = "//i[contains(@class,'oxd-userdropdown-icon')]")
    protected WebElement userProfileOptions;

    @FindBy(xpath = "//a[text()='Logout']")
    protected WebElement logout;

    @FindBy(xpath = "//input[@placeholder='Search']")
    protected WebElement search;

    @FindBy(xpath = "//span[text()='Admin']")
    protected WebElement admin;

    @FindBy(xpath = "//span[text()='PIM']")
    protected WebElement pim;

    @FindBy(xpath = "//span[text()='Leave']")
    protected WebElement leave;

    @FindBy(xpath = "//span[text()='Time']")
    protected WebElement time;

    @FindBy(xpath = "//span[text()='Recruitment']")
    protected WebElement recruitment;

    @FindBy(xpath = "//span[text()='My Info']")
    protected WebElement myInfo;

    @FindBy(xpath = "//span[text()='Performance']")
    protected WebElement performance;

    @FindBy(xpath = "//span[text()='Dashboard']")
    protected WebElement dashBoard;

    @FindBy(xpath = "//span[text()='Directory']")
    protected WebElement directory;

    @FindBy(xpath = "//span[text()='Maintenance']")
    protected WebElement maintenance;

    @FindBy(xpath = "//span[text()='Claim']")
    protected WebElement claim;

    @FindBy(xpath = "//span[text()='Buzz']")
    protected WebElement buzz;
    
    public By getmenuItem(String menuName) {    	    
		return By.xpath("//span[text()='" + menuName + "']");
	}

}
