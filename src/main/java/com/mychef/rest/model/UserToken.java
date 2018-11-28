package com.mychef.rest.model;

public class UserToken {
    private String token;
    private Long expery_in;

    public UserToken() {}

    public UserToken(String token, Long expery_in) {
        this.token = token;
        this.expery_in = expery_in;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getExpery_in() {
        return expery_in;
    }

    public void setExpery_in(Long expery_in) {
        this.expery_in = expery_in;
    }
}
