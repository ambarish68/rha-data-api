package com.rha.dataapi.auth.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class JwtRequest implements Serializable {
    private static final long serialVersionUID = 5926468583005150707L;
    private String emailId;
    private String password;

    //need default constructor for JSON Parsing
    public JwtRequest() {
    }

    public JwtRequest(String emailId, String password) {
        this.setEmailId(emailId);
        this.setPassword(password);
    }
}