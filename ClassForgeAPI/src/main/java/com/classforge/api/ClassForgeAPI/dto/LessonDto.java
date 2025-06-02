package com.classforge.api.ClassForgeAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LessonDto {
    private Integer id;
    private String name;
    private String theme;
    private String homework;
    private Boolean is_done;
    private Boolean is_paid;
    private Integer length;
    private java.sql.Timestamp timestamp;
    private Integer relation_id;
    private UserDto student;


    public LessonDto() {

    }
}
