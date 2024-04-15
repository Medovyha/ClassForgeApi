package com.classforge.api.ClassForgeAPI.services;

import com.classforge.api.ClassForgeAPI.repositories.TeacherStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class TeacherStudentService {
    @Autowired
    private TeacherStudentRepository teacherStudentRepository;
}
