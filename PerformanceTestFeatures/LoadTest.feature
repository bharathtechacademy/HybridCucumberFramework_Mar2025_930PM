Feature: Validate performance of Git Api's
    I want to use the Git API to validate the performance of various operations

  Scenario:  Validate performance of Git API
    When The user load the Jmeter file "GitApiTest.jmx"
    Then The user should see the performance results in the Jmeter report