package com.classforge.api.ClassForgeAPI.web.controllers;

import com.classforge.api.ClassForgeAPI.Mappers.ContactInfoMapper;
import com.classforge.api.ClassForgeAPI.Mappers.InfoMapper;
import com.classforge.api.ClassForgeAPI.Mappers.UserMapper;
import com.classforge.api.ClassForgeAPI.dao.TeacherStudent;
import com.classforge.api.ClassForgeAPI.dao.User;
import com.classforge.api.ClassForgeAPI.dto.UserDto;
import com.classforge.api.ClassForgeAPI.services.ContactInfoService;
import com.classforge.api.ClassForgeAPI.services.InfoService;
import com.classforge.api.ClassForgeAPI.services.TeacherStudentService;
import com.classforge.api.ClassForgeAPI.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@RestController
@RequestMapping("api/user/")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private TeacherStudentService teacherStudentService;
    @Autowired
    private InfoService infoService;
    @Autowired
    private ContactInfoService contactService;

    @GetMapping(value = "{id:\\d+}")
    public User getUser(@PathVariable Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.getUserById(id);
    }

    @GetMapping
    public ResponseEntity<UserDto> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByUsername(authentication.getName());
        if (user != null) {
            UserDto userDto = UserMapper.toDto(user);
            return ResponseEntity.ok(userDto);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "teacher/students")
    public ResponseEntity<List<UserDto>> getTeacherStudents() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByUsername(authentication.getName());
        if (user != null) {
            List<UserDto> students = user.getStudents().stream().map(teacherStudent -> UserMapper.toDto(teacherStudent.getStudent())).toList();
            return ResponseEntity.ok(students);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping(value = "student")
    public ResponseEntity<UserDto> addStudent(@RequestBody UserDto userDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByUsername(authentication.getName());
        if (user != null) {
            User student = UserMapper.toDao(userDto);
            userService.addUser(student);
            teacherStudentService.addStudent(user, student);
            return ResponseEntity.ok(UserMapper.toDto(student));
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "student/{id:\\d+}")
    public ResponseEntity<UserDto> getStudent(@PathVariable Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByUsername(authentication.getName());
        if (user != null) {
            TeacherStudent relationhip = user.getStudents().stream().filter(student_ -> student_.getStudent().getId().equals(id)).findFirst().orElse(null);
            if (relationhip != null) {
                return ResponseEntity.ok(UserMapper.toDto(relationhip.getStudent()));

            }

        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "get_full")
    public ResponseEntity<UserDto> getFullUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByUsername(authentication.getName());
        if (user != null) {
            UserDto userDto = UserMapper.toDto(user);
            userDto.setInfo(
                    InfoMapper.toDto(
                            infoService.getInfoByUserId(user.getId())));
            userDto.setContactInfo(ContactInfoMapper.toDto(
                    contactService.getContactInfoByUserId(
                            user.getId()))

            );

            return ResponseEntity.ok(userDto);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "get_full_student/{id:\\d+}")
    public ResponseEntity<UserDto> getFullStudent(@PathVariable Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByUsername(authentication.getName());
        if (user != null) {
            TeacherStudent relationhip = user.getStudents().stream().filter(student_ -> student_.getStudent().getId().equals(id)).findFirst().orElse(null);
            if (relationhip != null) {
                UserDto userDto = UserMapper.toDto(relationhip.getStudent());
                userDto.setInfo(
                        InfoMapper.toDto(
                                infoService.getInfoByUserId(relationhip.getStudent().getId())));
                userDto.setContactInfo(ContactInfoMapper.toDto(
                        contactService.getContactInfoByUserId(
                                relationhip.getStudent().getId()))

                );

                return ResponseEntity.ok(userDto);
            }

        }
        return ResponseEntity.notFound().build();
    }

}
