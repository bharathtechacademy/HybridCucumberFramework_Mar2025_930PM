package com.orangehrm.crm.application.runner;

import org.testng.annotations.Test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "Features",
		glue = {"com.orangehrm.crm.framework.base","com.orangehrm.crm.application.stepDefinitions"},
		plugin = {"pretty", "html:Reports/cucumber-test-report.html"}
	//	,tags = "@smoke"
)

public class ApplicationTestRunner extends AbstractTestNGCucumberTests{	
	
	@Test
	public void runApplicationTests() {
		System.out.println("Running Application UI Tests....!!!");
	}

}
