package com.prematix_hangouts.ModleClass.MessageSave;

public class SendMessageStore {
    private String from_token;
    private String title;
    private String message;
    private String type;
    private String to_token;
    private String f_user;
    private String t_user;

    public SendMessageStore(String from_token, String title, String message, String type,String to_token,String f_user,String t_user) {
        this.from_token = from_token;
        this.title = title;
        this.message = message;
        this.type = type;
        this.to_token = to_token;
        this.f_user = f_user;
        this.t_user = t_user;
    }

    public String getFrom_token() {
        return from_token;
    }

    public void setFrom_token(String from_token) {
        this.from_token = from_token;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTo_token() {
        return to_token;
    }

    public void setTo_token(String to_token) {
        this.to_token = to_token;
    }

    public String getF_user() {
        return f_user;
    }

    public void setF_user(String f_user) {
        this.f_user = f_user;
    }

    public String getT_user() {
        return t_user;
    }

    public void setT_user(String t_user) {
        this.t_user = t_user;
    }
}

