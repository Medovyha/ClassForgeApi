package com.classforge.api.ClassForgeAPI.repositories;

import com.classforge.api.ClassForgeAPI.dao.User;
import com.classforge.api.ClassForgeAPI.dao.TeacherStudent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherStudentRepository  extends JpaRepository<TeacherStudent, Integer> {
}
