Feature: Github API Features
      As a user of the Github API
      I want to be able to interact with the Github API
      And validate the responses

      Scenario: Request to create a repository for authenticated user with duplicate repo name
        Given User sets the repository name as "CreatioRepo" and description as "Sample Repository Description"
        When User sends a "POST" request with endpoint "/user/repos" to create repository with visibility "true"
        Then User should get the response code as 422
        And User should get the response message as "Unprocessable Entity"
        And User should get the response time less than 2000 milliseconds
        And User should get the response body with "message" as "Repository creation failed."

      Scenario: Request to create a repository for authenticated user with valid data
        Given User sets the repository name as "RestAssuredRepo" and description as "Sample Repository Description"
        When User sends a "POST" request with endpoint "/user/repos" to create repository with visibility "true"
        Then User should get the response code as 201
        And User should get the response message as "Created"
        And User should get the response time less than 2000 milliseconds
        And User should get the response body with "name" as "RestAssuredRepo"
        And User should get the response body with "description" as "Sample Repository Description"

      Scenario: Request to get an existing repository for authenticated user with valid data
        When User sends a "GET" request with endpoint "/repos/bharathtechacademy5/RestAssuredRepo" to get repository details
        Then User should get the response code as 200
        And User should get the response message as "OK"
        And User should get the response time less than 2000 milliseconds
        And User should get the response body with "name" as "RestAssuredRepo"
        And User should get the response body with "description" as "Sample Repository Description"

      Scenario: Request to update an existing repository visibility for authenticated user with valid data
        When User sends a "PATCH" request with endpoint "/repos/bharathtechacademy5/RestAssuredRepo" to update repository visibility to "false"
        Then User should get the response code as 200
        And User should get the response message as "OK"
        And User should get the response time less than 2000 milliseconds
        And User should get the response body with "name" as "RestAssuredRepo"
        And User should get the response body with "description" as "Sample Repository Description"
        And User should get the response body with "private" as "false"

      Scenario: Request to delete an existing repository for authenticated user with valid data
        When User sends a "DELETE" request with endpoint "/repos/bharathtechacademy5/RestAssuredRepo" to delete repository
        Then User should get the response code as 204
        And User should get the response message as "No Content"
        And User should get the response time less than 2000 milliseconds