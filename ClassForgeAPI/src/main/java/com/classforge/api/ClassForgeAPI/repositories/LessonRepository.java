package com.classforge.api.ClassForgeAPI.repositories;

import com.classforge.api.ClassForgeAPI.dao.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository  extends JpaRepository<Lesson, Integer> {
}
