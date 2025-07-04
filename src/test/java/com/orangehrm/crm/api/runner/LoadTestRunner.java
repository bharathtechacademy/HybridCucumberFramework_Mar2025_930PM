package com.orangehrm.crm.api.runner;


import com.orangehrm.crm.framework.api.commons.JMeterCommons;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

import java.io.File;

@CucumberOptions(
        features = "PerformanceTestFeatures",
        glue = "com.orangehrm.crm.api.stepDefinitions",
        plugin = {"pretty", "html:Reports/cucumber-load-test-report.html"}
)

public class LoadTestRunner extends AbstractTestNGCucumberTests {

    @Test
    public void runLoadTest(){
        File outputFolder = new File(System.getProperty("user.dir") + "\\report-output");
        File binOutputFolder = new File(System.getProperty("user.dir") + "//src//test//resources//jmeter//bin//report-output");
        JMeterCommons.deleteDirectory(outputFolder);
        JMeterCommons.deleteDirectory(binOutputFolder);
        System.out.println("Running API Performance tests...");
    }

}
