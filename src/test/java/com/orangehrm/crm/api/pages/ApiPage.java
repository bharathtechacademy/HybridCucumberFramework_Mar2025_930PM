package com.orangehrm.crm.api.pages;

import org.json.JSONObject;

public class ApiPage {
    public static String requestBodyToCreateRepo(String name, String description, boolean isPrivate) {
        JSONObject jo = new JSONObject();
        jo.put("name", name);
        jo.put("description", description);
        jo.put("private", isPrivate);
        return jo.toString();
    }

    public static String requestBodyToUpdateRepoVisibility(boolean visibility) {
        JSONObject jo = new JSONObject();
        jo.put("private", visibility);
        return jo.toString();
    }
}
