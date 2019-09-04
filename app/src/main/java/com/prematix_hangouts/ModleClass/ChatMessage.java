package com.prematix_hangouts.ModleClass;

public class ChatMessage {
    private String userId;
    private String userName;
    private String message;
    private String refToken;
    private String chatWith;

    public ChatMessage() {

    }

    public ChatMessage(String userId, String userName, String message, String refToken, String chatWith) {
        this.userId = userId;
        this.userName = userName;
        this.message = message;
        this.refToken = refToken;
        this.chatWith = chatWith;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRefToken() {
        return refToken;
    }

    public void setRefToken(String refToken) {
        this.refToken = refToken;
    }

    public String getChatWith() {
        return chatWith;
    }

    public void setChatWith(String chatWith) {
        this.chatWith = chatWith;
    }
}
