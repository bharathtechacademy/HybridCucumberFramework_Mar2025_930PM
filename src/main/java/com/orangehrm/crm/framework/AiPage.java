package com.orangehrm.crm.framework;

import org.json.JSONObject;

public class AiPage {

public static String requestBodyForAI(String prompt) {
    JSONObject formatProperties = new JSONObject();
    formatProperties.put("type", "integer");

    JSONObject format = new JSONObject();
    format.put("type", "object");
    format.put("properties", new JSONObject().put("secondLargestNumber", formatProperties));
    format.put("required", "secondLargestNumber");

    JSONObject requestBody = new JSONObject();
    requestBody.put("model", "llama3.2:latest");
    requestBody.put("prompt", prompt);
    requestBody.put("stream", false);
    requestBody.put("format", format);

    return requestBody.toString();
}


}
