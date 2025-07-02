package com.orangehrm.crm.framework.api.commons;

    import com.orangehrm.crm.framework.utilities.PropUtil;
    import io.restassured.RestAssured;
    import io.restassured.response.Response;
    import org.testng.Assert;

    import java.util.Properties;

    import static io.restassured.RestAssured.given;

    /**
     * This class provides common utilities and methods for API automation.
     * It includes functionalities for sending API requests, verifying responses, and handling API-related operations.
     */
    public class ApiCommons {

        /**
         * Stores the response of the API request.
         */
        public static Response response = null;

        /**
         * Loads properties from the `Config.properties` file.
         */
        public static Properties prop = PropUtil.readData("Config.properties");

        /**
         * Sends an API request based on the specified request type and endpoint.
         *
         * <p>This method supports the following HTTP request types:
         * GET, POST, PUT, PATCH, and DELETE. It uses the RestAssured library to send the requests.
         * The base URI and authorization token are retrieved from the `Config.properties` file.</p>
         *
         * <p>For GET and DELETE requests, the method sends the request without a body.
         * For POST, PUT, and PATCH requests, the method includes the provided request body.</p>
         *
         * @param requestType  The type of HTTP request (e.g., GET, POST, PUT, PATCH, DELETE).
         * @param endPoint     The API endpoint to which the request is sent.
         * @param requestBody  The body of the request for POST, PUT, or PATCH requests. Can be null for GET and DELETE requests.
         * @throws IllegalArgumentException If the provided request type is invalid.
         */
        public static void getResponse(String requestType, String endPoint, String requestBody) {
            RestAssured.baseURI = prop.getProperty("API_BASE_URL");
            String token = prop.getProperty("API_TOKEN");

            switch (requestType.toUpperCase()) {
                case "GET":
                    response = given()
                            .headers("Authorization", "Bearer " + token)
                            .headers("Accept", "application/vnd.github+json")
                            .when()
                            .get(endPoint);
                    break;
                case "POST":
                    response = given()
                            .headers("Authorization", "Bearer " + token)
                            .headers("Accept", "application/vnd.github+json")
                            .body(requestBody)
                            .when()
                            .post(endPoint);
                    break;
                case "PUT":
                    response = given()
                            .headers("Authorization", "Bearer " + token)
                            .headers("Accept", "application/vnd.github+json")
                            .body(requestBody)
                            .when()
                            .put(endPoint);
                    break;
                case "PATCH":
                    response = given()
                            .headers("Authorization", "Bearer " + token)
                            .headers("Accept", "application/vnd.github+json")
                            .body(requestBody)
                            .when()
                            .patch(endPoint);
                    break;
                case "DELETE":
                    response = given()
                            .headers("Authorization", "Bearer " + token)
                            .headers("Accept", "application/vnd.github+json")
                            .when()
                            .delete(endPoint);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid request type: " + requestType);
            }
        }

        /**
         * Verifies that the actual response code matches the expected response code.
         *
         * @param expectedCode The expected HTTP status code.
         * @throws AssertionError If the actual status code does not match the expected code.
         */
        public static void verifyResponseCode(int expectedCode) {
            int actualCode = response.getStatusCode();
            Assert.assertEquals(actualCode, expectedCode, "Response code does not match the expected value.");
        }

        /**
         * Verifies that the status line contains the specified status message.
         *
         * @param expectedStatusMessage The expected status message (e.g., OK, Created, No Content).
         * @throws AssertionError If the status line does not contain the expected message.
         */
        public static void verifyStatusLineContains(String expectedStatusMessage) {
            String actualStatusLine = response.getStatusLine();
            Assert.assertTrue(actualStatusLine.contains(expectedStatusMessage),
                    "Status line does not contain the expected message: " + expectedStatusMessage);
        }

        /**
         * Verifies that the response time is less than or equal to the specified maximum time.
         *
         * @param maxResponseTime The maximum allowed response time in milliseconds.
         * @throws AssertionError If the response time exceeds the specified maximum.
         */
        public static void verifyResponseTime(long maxResponseTime) {
            long actualResponseTime = response.getTime();
            Assert.assertTrue(actualResponseTime <= maxResponseTime,
                    "Response time is greater than the expected maximum: " + maxResponseTime + " ms");
        }

        /**
         * Verifies that the response body contains the specified key-value pair.
         *
         * @param key           The key to look for in the response body.
         * @param expectedValue The expected value associated with the key.
         * @throws AssertionError If the key-value pair is not found in the response body.
         */
        public static void verifyResponseBodyContains(String key, String expectedValue) {
            String actualValue = response.getBody().jsonPath().getString(key);
            Assert.assertEquals(actualValue, expectedValue,
                    "Response body does not contain the expected key-value pair: " + key + " = " + expectedValue);
        }

        /**
         * Verifies that the response body matches the expected JSON structure.
         *
         * @param expectedJson The expected JSON structure as a string.
         * @throws AssertionError If the response body does not match the expected JSON structure.
         */
        public static void verifyResponseBodyMatches(String expectedJson) {
            String actualJson = response.getBody().asString();
            Assert.assertEquals(actualJson, expectedJson,
                    "Response body does not match the expected JSON structure.");
        }

        /**
         * Verifies that the response headers contain the specified key-value pair.
         *
         * @param headerName    The name of the header to look for.
         * @param expectedValue The expected value associated with the header.
         * @throws AssertionError If the header does not contain the expected key-value pair.
         */
        public static void verifyResponseHeaderContains(String headerName, String expectedValue) {
            String actualValue = response.getHeader(headerName);
            Assert.assertEquals(actualValue, expectedValue,
                    "Response header does not contain the expected key-value pair: " + headerName + " = " + expectedValue);
        }

        /**
         * Verifies that the response headers contain the specified key.
         *
         * @param headerName The name of the header to look for.
         * @throws AssertionError If the header does not exist in the response.
         */
        public static void verifyResponseHeaderExists(String headerName) {
            String actualValue = response.getHeader(headerName);
            Assert.assertNotNull(actualValue, "Response header does not contain the expected key: " + headerName);
        }

        /**
         * Verifies that the response body contains the specified key.
         *
         * @param key The key to look for in the response body.
         * @throws AssertionError If the key does not exist in the response body.
         */
        public static void verifyResponseBodyContainsKey(String key) {
            boolean containsKey = response.getBody().jsonPath().getMap("$").containsKey(key);
            Assert.assertTrue(containsKey, "Response body does not contain the expected key: " + key);
        }
    }