package com.TaskManagement.payload;

public class jwtresponse {
    private String token;

    public jwtresponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
