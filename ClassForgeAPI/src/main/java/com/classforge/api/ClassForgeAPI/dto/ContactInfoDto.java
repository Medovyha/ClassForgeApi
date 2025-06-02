package com.classforge.api.ClassForgeAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContactInfoDto {
private Integer id;
private String type;
private String value;
private Integer userId;

    public ContactInfoDto() {
    }


    public ContactInfoDto(String type, String value) {
    }
}
