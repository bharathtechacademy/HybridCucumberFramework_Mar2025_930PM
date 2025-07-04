package com.orangehrm.crm.framework.web.commons;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import com.orangehrm.crm.framework.base.BasePage;
import com.orangehrm.crm.framework.constants.Constants;
import com.orangehrm.crm.framework.utilities.PropUtil;

/**
 * The {@code WebCommons} class contains reusable utility methods for web automation
 * using Selenium WebDriver. These methods abstract common actions such as clicking,
 * typing, scrolling, selecting from dropdowns, waiting for elements, and more.
 * <p>
 * This class follows Page Object Model design principles and should be used across
 * all test scripts to ensure code reusability and maintainability.
 * </p>
 * 
 * @author Bharath Reddy
 * @Usage Extend or use this class in page objects to perform standard WebDriver operations</p>
 */
public class WebCommons {
    
    public WebDriver driver = BasePage.getDriver();
    public Properties prop = PropUtil.readData("Config.properties");

    /**
     * Launches the application using URL from Config.properties.
     */
    public void launchApplication() {
        driver.get(prop.getProperty("APP_URL"));
    }

    /**
     * Scrolls the web page until the specified element is in view.
     * 
     * @param element the WebElement to scroll to
     */
    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Clicks on the specified element after scrolling it into view.
     * 
     * @param element the WebElement to click
     */
    public void click(WebElement element) {
        scrollToElement(element);
        element.click();
    }

    /**
     * Performs JavaScript click on an element that may not be interactable through normal means.
     * 
     * @param element the WebElement to be clicked using JS
     */
    public void jsClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    /**
     * Performs a double-click on the specified element.
     * 
     * @param element the WebElement to double-click
     */
    public void doubleClick(WebElement element) {
        scrollToElement(element);
        new Actions(driver).doubleClick(element).perform();
    }

    /**
     * Performs a right-click (context click) on the specified element.
     * 
     * @param element the WebElement to right-click
     */
    public void rightClick(WebElement element) {
        scrollToElement(element);
        new Actions(driver).contextClick(element).perform();
    }

    /**
     * Clears the input field and enters the specified text.
     * 
     * @param element the input field WebElement
     * @param text the text to enter
     */
    public void enterText(WebElement element, String text) {
        scrollToElement(element);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Enters the specified text using Actions class, useful for special characters or capital letters.
     * 
     * @param element the input WebElement
     * @param text the text to enter
     */
    public void enterTextUsingActions(WebElement element, String text) {
        scrollToElement(element);
        new Actions(driver).sendKeys(element, text).perform();
    }

    /**
     * Selects or deselects a checkbox based on the desired status.
     * 
     * @param checkbox the checkbox WebElement
     * @param status true to select, false to deselect
     */
    public void selectCheckbox(WebElement checkbox, boolean status) {
        scrollToElement(checkbox);
        if (checkbox.isSelected() != status) {
            checkbox.click();
        }
    }

    /**
     * Selects an option from a dropdown using the specified method.
     * 
     * @param dropdown the dropdown WebElement
     * @param option the value/text/index to select
     * @param selectBy the method to select: "value", "visibleText", or "index"
     */
    public void selectDropdownOption(WebElement dropdown, String option, String selectBy) {
        scrollToElement(dropdown);
        Select select = new Select(dropdown);
        switch (selectBy.toLowerCase()) {
            case "value":
                select.selectByValue(option);
                break;
            case "visibletext":
                select.selectByVisibleText(option);
                break;
            case "index":
                select.selectByIndex(Integer.parseInt(option));
                break;
            default:
                throw new IllegalArgumentException("Invalid selection method: " + selectBy);
        }
    }

    /**
     * Introduces a hard wait for the specified number of seconds.
     * 
     * @param seconds number of seconds to wait
     */
    public void wait(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Sets implicit wait for locating web elements.
     * 
     * @param seconds duration of implicit wait in seconds
     */
    public void implicitWait(long seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }

    /**
     * Waits until the specified element is visible on the page.
     * 
     * @param element WebElement to wait for
     * @param seconds timeout duration in seconds
     */
    public void waitForElement(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(Constants.WAIT_TIME))
            .until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits until the specified locator finds at least one matching element.
     * 
     * @param locator By locator to find element(s)
     * @param seconds timeout duration in seconds
     */
    public void waitForElement(By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(Constants.WAIT_TIME))
            .until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, 0));
    }

    /**
     * Waits until an alert is present on the page.
     * 
     * @param seconds timeout duration in seconds
     */
    public void waitForAlert() {
        new WebDriverWait(driver, Duration.ofSeconds(Constants.WAIT_TIME))
            .until(ExpectedConditions.alertIsPresent());
    }

    /**
     * Uploads a file by sending the file path to the input field.
     * 
     * @param element the file input WebElement
     * @param filePath the full path of the file to upload
     */
    public void uploadFile(WebElement element, String filePath) {
        scrollToElement(element);
        element.sendKeys(filePath);
    }

    /**
     * Captures a screenshot of the entire browser window.
     * 
     * @param driver WebDriver instance
     * @param fileName name of the screenshot file (without extension)
     * @return the full path of the saved screenshot
     * @throws IOException if an error occurs while saving the file
     */
    public static String windowScreenshot(WebDriver driver, String fileName) throws IOException {
        String screenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" + fileName + ".png";
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotFile, new File(screenshotPath));
        return screenshotPath;
    }

    /**
     * Captures a screenshot of a specific web element.
     * 
     * @param element the WebElement to capture
     * @param fileName name of the screenshot file (without extension)
     * @return the full path of the saved screenshot
     * @throws IOException if an error occurs while saving the file
     */
    public static String elementScreenshot(WebElement element, String fileName) throws IOException {
        String screenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" + fileName + ".png";
        File screenshotFile = element.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotFile, new File(screenshotPath));
        return screenshotPath;
    }

    /**
     * Switches context to a frame using the frame WebElement.
     * 
     * @param frameElement the frame WebElement
     */
    public void switchToFrame(WebElement frameElement) {
        driver.switchTo().frame(frameElement);
    }

    /**
     * Switches context to a frame using its name or ID.
     * 
     * @param frameNameOrId the name or ID of the frame
     */
    public void switchToFrame(String frameNameOrId) {
        driver.switchTo().frame(frameNameOrId);
    }

    /**
     * Generates a unique identifier string based on the current date and time.
     * 
     * @param format the date-time format to use (e.g., "yyyyMMddHHmmss")
     * @return the formatted unique ID string
     */
    public static String uniqueId(String format) {
        return new SimpleDateFormat(format).format(Calendar.getInstance().getTime());
    }

    /**
     * Checks whether the specified element is enabled.
     * 
     * @param element the WebElement to check
     * @return true if the element is enabled, false otherwise
     */
    public boolean isElementEnabled(WebElement element) {
        return element.isEnabled();
    }

    /**
     * Returns the visible text of the specified element.
     * 
     * @param element the WebElement to read
     * @return the text content of the element
     */
    public String getElementText(WebElement element) {
        return element.getText();
    }

    /**
     * Returns the value of the specified attribute of a WebElement.
     * 
     * @param element the WebElement
     * @param attribute the name of the attribute
     * @return the value of the attribute
     */
    public String getAttributeValue(WebElement element, String attribute) {
        return element.getAttribute(attribute);
    }

    /**
     * Gets the title of the current browser window.
     * 
     * @return the window title
     */
    public String getTitle() {
        return driver.getTitle();
    }

    /**
     * Checks whether the specified element is displayed on the page.
     * 
     * @param element the WebElement to check
     * @return true if displayed, false otherwise
     */
    public boolean isElementDisplayed(WebElement element) {
        return element.isDisplayed();
    }
    
    /**
     * Returns the handle ID of the current browser window.
     *
     * @return the window handle as a String
     */
    public String getCurrentWindowHandle() {
        return driver.getWindowHandle();
    }

    /**
     * Returns a set of all window handles opened by the WebDriver session.
     *
     * @return Set of window handles
     */
    public Set<String> getAllWindowHandles() {
        return driver.getWindowHandles();
    }

    /**
     * Switches the driver context to a new window that is not the current one.
     */
    public void switchToNewWindow() {
        String currentWindow = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(currentWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }
    }

    /**
     * Switches the driver context to a window based on its title.
     *
     * @param title the title of the target window
     */
    public void switchToWindowByTitle(String title) {
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
            if (driver.getTitle().equalsIgnoreCase(title)) {
                break;
            }
        }
    }

    /**
     * Switches the driver context to a specific window handle.
     *
     * @param windowHandle the window handle to switch to
     */
    public void switchToWindow(String windowHandle) {
        driver.switchTo().window(windowHandle);
    }

    /**
     * Closes all windows except the original one.
     */
    public void closeAllOtherWindows() {
        String mainWindow = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(mainWindow)) {
                driver.switchTo().window(handle);
                driver.close();
            }
        }
        driver.switchTo().window(mainWindow);
    }

    /**
     * Accepts the alert if present.
     */
    public void acceptAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    /**
     * Dismisses the alert if present.
     */
    public void dismissAlert() {
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }

    /**
     * Gets the text message from the alert.
     *
     * @return the alert message as String
     */
    public String getAlertText() {
        return driver.switchTo().alert().getText();
    }

    /**
     * Sends input text to the alert prompt.
     *
     * @param text the input text for the alert
     */
    public void sendTextToAlert(String text) {
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(text);
    }

    /**
     * Navigates the browser to the previous page.
     */
    public void navigateBack() {
        driver.navigate().back();
    }

    /**
     * Navigates the browser to the next page.
     */
    public void navigateForward() {
        driver.navigate().forward();
    }

    /**
     * Refreshes the current browser page.
     */
    public void refreshPage() {
        driver.navigate().refresh();
    }

    /**
     * Checks whether the element exists and is displayed on the page.
     *
     * @param element the WebElement to verify
     * @return true if element exists and is displayed, false otherwise
     */
    public boolean isElementPresent(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Performs mouse hover over a specified element.
     *
     * @param element the WebElement to hover over
     */
    public void mouseHover(WebElement element) {
        scrollToElement(element);
        new Actions(driver).moveToElement(element).perform();
    }

    /**
     * Scrolls down to the bottom of the page.
     */
    public void scrollToBottom() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    /**
     * Scrolls to the top of the page.
     */
    public void scrollToTop() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");
    }
    

}
