package com.classforge.api.ClassForgeAPI.repositories;

import com.classforge.api.ClassForgeAPI.dao.teacher_student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherStudentRepository  extends JpaRepository<teacher_student, Integer> {
}
