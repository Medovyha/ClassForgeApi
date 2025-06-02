package com.classforge.api.ClassForgeAPI.dto;

import lombok.Data;

@Data
public class UserDto {
    private Integer id;
    private String username;
    private String mail;
    private boolean isTeacher;
    private ContactInfoDto contactInfo;
    private InfoDto info;

    public boolean getIsTeacher() {
        return isTeacher;
    }

    public void setIsTeacher(boolean isTeacher) {
        this.isTeacher = isTeacher;
    }

    }
