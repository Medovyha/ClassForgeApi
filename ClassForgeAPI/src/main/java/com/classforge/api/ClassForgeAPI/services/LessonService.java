package com.classforge.api.ClassForgeAPI.services;

import com.classforge.api.ClassForgeAPI.dao.Lesson;
import com.classforge.api.ClassForgeAPI.repositories.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LessonService {
    @Autowired
    private LessonRepository lessonRepository;

    public Lesson getLessonById(Integer id) {
        return lessonRepository.findById(id).orElse(null);
    }

    public void addLesson(Lesson lesson) {
        lessonRepository.save(lesson);
    }

    public void deleteLesson(Lesson lesson) {
        lessonRepository.delete(lesson);
    }

    public void updateLesson(Lesson lesson) {
        lessonRepository.save(lesson);
    }

    public Lesson getPreviousLesson(Integer id) {
        return lessonRepository.findPreviousLesson(id);
    }

}
