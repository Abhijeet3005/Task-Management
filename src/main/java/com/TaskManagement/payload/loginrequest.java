package com.TaskManagement.payload;

public class loginrequest {
    private String username;
    private String password;

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String s){
        this.password = s;
    }

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String s){
        this.username = s;
    }
}