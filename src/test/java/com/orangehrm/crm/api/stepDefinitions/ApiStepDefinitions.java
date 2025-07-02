package com.orangehrm.crm.api.stepDefinitions;

        import com.orangehrm.crm.api.pages.ApiPage;
        import com.orangehrm.crm.framework.api.commons.ApiCommons;
        import io.cucumber.java.en.*;
        import org.testng.Assert;

        public class ApiStepDefinitions extends ApiCommons {

                private String repositoryName;
                private String repositoryDescription;

                @Given("User sets the repository name as {string} and description as {string}")
                public void userSetsTheRepositoryNameAndDescription(String name, String description) {
                        this.repositoryName = name;
                        this.repositoryDescription = description;
                }

                @When("User sends a {string} request with endpoint {string} to create repository with visibility {string}")
                public void userSendsRequestToCreateRepository(String method, String endpoint, String visibility) {
                        String requestBody = ApiPage.requestBodyToCreateRepo(repositoryName, repositoryDescription, Boolean.parseBoolean(visibility));
                        getResponse(method, endpoint, requestBody);
                }

                @Then("User should get the response code as {int}")
                public void userVerifiesResponseStatusCode(int statusCode) {
                       verifyResponseCode(statusCode);
                }

                @Then("User should get the response message as {string}")
                public void userVerifiesResponseMessage(String expectedMessage) {
                        verifyStatusLineContains(expectedMessage);
                }

                @Then("User should get the response time less than {int} milliseconds")
                public void userVerifiesResponseTimeLessThan(int maxResponseTime) {
                        verifyResponseTime(maxResponseTime);
                }

                @Then("User should get the response body with {string} as {string}")
                public void userVerifiesResponseBodyContains(String key, String expectedValue) {
                        verifyResponseBodyContains(key, expectedValue);
                }

                @When("User sends a {string} request with endpoint {string} to get repository details")
                public void userSendsRequestToGetRepositoryDetails(String method, String endpoint) {
                        getResponse(method, endpoint, null);
                }

                @When("User sends a {string} request with endpoint {string} to update repository visibility to {string}")
                public void userSendsRequestToUpdateRepositoryVisibility(String method, String endpoint, String visibility) {
                        String requestBody = ApiPage.requestBodyToUpdateRepoVisibility(Boolean.parseBoolean(visibility));
                        getResponse(method, endpoint, requestBody);
                }

                @When("User sends a {string} request with endpoint {string} to delete repository")
                public void userSendsRequestToDeleteRepository(String method, String endpoint) {
                        getResponse(method, endpoint, null);
                }

        }