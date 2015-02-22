package com.ghmulti.appengine.dto;

public class ApiResponse {

    private String content;

    public ApiResponse(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
