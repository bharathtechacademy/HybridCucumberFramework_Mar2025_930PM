package com.orangehrm.crm.framework.base;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.orangehrm.crm.framework.utilities.PropUtil;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


/**
 * The {@code BasePage} class provides common browser setup and teardown functionality
 * for web-based automated tests. It supports launching Chrome, Firefox, and Edge browsers.
 * <p>
 * This class also contains utility methods to get and set the WebDriver instance.
 * It is intended to be extended or used by other test classes in the framework.
 * </p>
 * 
 * <p><b>Usage Example:</b></p>
 * <pre>
 * {@code
 * public class LoginPageTest extends BasePage {
 *     @Test
 *     public void testLogin() {
 *         WebDriver driver = getDriver();
 *         // test logic here
 *     }
 * }
 * }
 * </pre>
 * 
 * @author Bharath Reddy
 */
public class BasePage{

    /** Singleton instance of the WebDriver. */
    private static WebDriver driver = null;
    public Properties prop = PropUtil.readData("Config.properties");

    /**
     * Launches the specified browser before each test method.
     * This method is invoked automatically by TestNG via the {@code @BeforeMethod} annotation.
     *
     * @param browserName the name of the browser to launch (chrome, firefox, edge)
     * @throws AssertionError if the specified browser is not supported
     */
    @Before
    public void setupBrowser() {
    	String browserName =prop.getProperty("BROWSER");
        if (browserName.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else {
            Assert.fail("Browser is not supported: " + browserName);
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    /**
     * Quits the browser session after each test method.
     * This method is invoked automatically by TestNG via the {@code @AfterMethod} annotation.
     */
    @After(order=0)
    public void teardownBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    @After(order=1)
    public void addScreenshotForFailureTest(Scenario scenario) throws IOException {
		if (scenario.isFailed()) {
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			byte[] screenshotBytes = FileUtils.readFileToByteArray(screenshot);
			scenario.attach(screenshotBytes, "image/png", scenario.getName() + "_screenshot");
		}    	
    }

    /**
     * Returns the current instance of WebDriver.
     *
     * @return the active {@link WebDriver} instance
     */
    public static WebDriver getDriver() {
        return driver;
    }

    /**
     * Sets the WebDriver instance. 
     * This method can be used to override the driver instance if needed.
     *
     * @param driver the {@link WebDriver} instance to set
     */
    public static void setDriver(WebDriver driver) {
        BasePage.driver = driver;
    }
}
