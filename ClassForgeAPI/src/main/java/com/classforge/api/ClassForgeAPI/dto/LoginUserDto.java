package com.classforge.api.ClassForgeAPI.dto;

import lombok.Getter;

@Getter
public class LoginUserDto {
    private String mail;

    private String password;

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
