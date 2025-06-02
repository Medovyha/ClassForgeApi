package com.classforge.api.ClassForgeAPI.repositories;

import com.classforge.api.ClassForgeAPI.dao.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LessonRepository  extends JpaRepository<Lesson, Integer> {
    @Query(value = "SELECT * FROM lessons WHERE timestamp < (SELECT timestamp FROM lessons WHERE id = ?1) ORDER BY timestamp DESC LIMIT 1", nativeQuery = true)
    Lesson findPreviousLesson(Integer id);
}
