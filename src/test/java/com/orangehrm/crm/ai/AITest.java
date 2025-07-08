package com.orangehrm.crm.ai;

import org.testng.annotations.Test;

import com.orangehrm.crm.framework.AiPage;
import com.orangehrm.crm.framework.ai.commons.AICommons;

import io.restassured.response.Response;

public class AITest {

    @Test
    public void testAIResponse(){
        String prompt ="Given values are 12 ,34,11,36,87,98,93.Store the values in Array and Print second largest number from the above values? Respond using JSON";
        String requestBody = AiPage.requestBodyForAI(prompt);
        System.out.println("Request Body: " + requestBody);
        Response response = AICommons.getResposne(prompt, requestBody);
        System.out.println("Response: " + response.getBody().jsonPath().getString("response"));
    }

}
