package com.classforge.api.ClassForgeAPI.services;

import com.classforge.api.ClassForgeAPI.repositories.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LessonService {
    @Autowired
    private LessonRepository lessonRepository;
}
