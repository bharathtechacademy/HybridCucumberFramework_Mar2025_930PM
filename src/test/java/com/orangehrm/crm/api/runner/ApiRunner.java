package com.orangehrm.crm.api.runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        features = "APIFeatures",
        glue = "com.orangehrm.crm.api.stepDefinitions",
        plugin = {"pretty", "html:Reports/cucumber-api-test-report.html"}
)

public class ApiRunner extends AbstractTestNGCucumberTests {

    public void runApiTest(){
        System.out.println("Running API tests...");
    }



}
