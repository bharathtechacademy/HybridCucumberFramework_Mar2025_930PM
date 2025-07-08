package com.orangehrm.crm.framework.ai.commons;

import com.orangehrm.crm.framework.utilities.PropUtil;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Properties;

public class AICommons {

static Properties prop = PropUtil.readData("Config.properties");

//Common method to get the answers from LLM
public static Response getResposne(String prompt,String requestBody) {
    RestAssured.baseURI = prop.getProperty("AI_BASE_URL");
    String endPoint = prop.getProperty("AI_END_POINT");
    Response response = RestAssured.given().headers("Content-Type", "application/json")
            .body(requestBody)
            .when()
            .post(endPoint);
    return response;
}

}
