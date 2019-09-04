package com.prematix_hangouts.ModleClass;

public class RegisterModule {
    private String userId;
    private String name;
    private String email;
    private String password;
    private String mobile;
    private String gender;
    private String userToken;

    public RegisterModule() {
    }

    public RegisterModule(String userId,String email, String password) {
        this.userId = userId;
        this.email = email;
        this.password = password;
    }

    public RegisterModule(String userId,String name, String email, String password, String mobile, String gender,String userToken) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.mobile = mobile;
        this.gender = gender;
        this.userToken = userToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}
