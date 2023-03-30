package com.example.RegisterLogin.payload.response;

public class LoginMessage {
    String Message;
    Boolean status;

    public LoginMessage(String message, Boolean status) {
        Message = message;
        this.status = status;
    }

    public LoginMessage() {
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "LoginMessage{" +
                "Message='" + Message + '\'' +
                ", status=" + status +
                '}';
    }
}
