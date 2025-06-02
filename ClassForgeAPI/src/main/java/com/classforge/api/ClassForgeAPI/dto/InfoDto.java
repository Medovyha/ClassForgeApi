package com.classforge.api.ClassForgeAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InfoDto {
private String name;
    private String surname;
    private String photo;
    private String description;
    private Integer userId;
    private Integer year;
}
