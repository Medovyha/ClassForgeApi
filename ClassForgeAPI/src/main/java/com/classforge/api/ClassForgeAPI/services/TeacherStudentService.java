package com.classforge.api.ClassForgeAPI.services;

import com.classforge.api.ClassForgeAPI.dao.TeacherStudent;
import com.classforge.api.ClassForgeAPI.dao.User;
import com.classforge.api.ClassForgeAPI.repositories.TeacherStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class TeacherStudentService {
    @Autowired
    private TeacherStudentRepository teacherStudentRepository;

    public void addStudent(User teacher, User student) {
        TeacherStudent teacherStudent = new     TeacherStudent(
                teacher,
                student
        );
        teacherStudentRepository. save(teacherStudent);
    }
}
