package com.orangehrm.crm.api.stepDefinitions;

import com.orangehrm.crm.framework.api.commons.JMeterCommons;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
//import org.apache.jmeter.report.config.ConfigurationException;
//import org.apache.jmeter.report.dashboard.GenerationException;

import java.io.IOException;

public class JMeterStepDefinitions {

//    When The user load the Jmeter file "GitApiTest.jmx"
//    Then The user should see the performance results in the Jmeter report

    static String jmeterFileName ;

    @When("The user load the Jmeter file {string}")
    public void theUserLoadTheJmeterFile(String jmeterFileName) {
        JMeterStepDefinitions.jmeterFileName = jmeterFileName;
        System.out.println("Loading JMeter file to Run: " + jmeterFileName);
    }

//    @Then("The user should see the performance results in the Jmeter report")
//    public void theUserShouldSeeThePerformanceResultsInTheJmeterReport() throws ConfigurationException, GenerationException, IOException {
//        JMeterCommons.runJMeterScript(jmeterFileName);
//    }
}
