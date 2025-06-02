package com.classforge.api.ClassForgeAPI.Mappers;

import com.classforge.api.ClassForgeAPI.dao.Lesson;
import com.classforge.api.ClassForgeAPI.dto.LessonDto;

public class LessonMapper {
public static LessonDto toDto(Lesson lesson) {
        LessonDto dto = new LessonDto();
        dto.setId(lesson.getId());
        dto.setName(lesson.getName());
        dto.setTheme(lesson.getTheme());
        dto.setHomework(lesson.getHomework());
        dto.setIs_done(lesson.getIs_done());
        dto.setIs_paid(lesson.getIs_paid());
        dto.setLength(lesson.getLength());
        dto.setTimestamp(lesson.getTimestamp());
        dto.setRelation_id(lesson.getRelation().getId());
        dto.setStudent(UserMapper.toDto(lesson.getRelation().getStudent()));
        return dto;
    }

    public static Lesson toDao(LessonDto lessonDto) {
        Lesson lesson = new Lesson();
        lesson.setId(lessonDto.getId());
        lesson.setName(lessonDto.getName());
        lesson.setTheme(lessonDto.getTheme());
        lesson.setHomework(lessonDto.getHomework());
        lesson.setIs_done(lessonDto.getIs_done());
        lesson.setIs_paid(lessonDto.getIs_paid());
        lesson.setLength(lessonDto.getLength());
        lesson.setTimestamp(lessonDto.getTimestamp());
        return lesson;
    }
}
