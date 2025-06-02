package com.classforge.api.ClassForgeAPI.web.controllers;

import com.classforge.api.ClassForgeAPI.Mappers.LessonMapper;
import com.classforge.api.ClassForgeAPI.dao.Lesson;
import com.classforge.api.ClassForgeAPI.dao.TeacherStudent;
import com.classforge.api.ClassForgeAPI.dao.User;
import com.classforge.api.ClassForgeAPI.dto.LessonDto;
import com.classforge.api.ClassForgeAPI.services.LessonService;
import com.classforge.api.ClassForgeAPI.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("api/lesson")
@RestController
public class LessonController {
    @Autowired
    private LessonService lessonService;
    @Autowired
    private UserService userService;
    @GetMapping("{id:\\d+}")
    public ResponseEntity<LessonDto> getLesson(@PathVariable Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Lesson lesson = lessonService.getLessonById(id);
        if (lesson == null) {
            return ResponseEntity.notFound().build();
        }
        User user = userService.getUserByUsername(authentication.getName());
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        if (lesson.getRelation().getTeacher().getId().equals(user.getId()) || lesson.getRelation().getStudent().getId().equals(user.getId())) {
            return ResponseEntity.ok(LessonMapper.toDto(lesson));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/student/{id:\\d+}")
    public ResponseEntity<List<LessonDto>> getStudentLessons(@PathVariable Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByUsername(authentication.getName());
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        TeacherStudent relationship = user.getStudents()
                .stream().filter(
                        student_ -> student_
                                .getStudent()
                                .getId()
                                .equals(id))
                .findFirst()
                .orElse(null);
        if (relationship == null) {
            return ResponseEntity.notFound().build();
        }
        List<Lesson> lessons = relationship.getLessons();
        return ResponseEntity.ok(lessons.stream().map(LessonMapper::toDto).collect(Collectors.toList()) );

    }

    @PostMapping("/student/{studentId:\\d+}")
    public ResponseEntity<LessonDto> addLesson(@RequestBody LessonDto lessonDto,
                                               @PathVariable int studentId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByUsername(authentication.getName());
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        TeacherStudent relationship = user.getStudents()
                .stream().filter(
                        student_ -> student_
                                .getStudent()
                                .getId()
                                .equals(studentId))
                .findFirst()
                .orElse(null);
        Lesson lesson = LessonMapper.toDao(lessonDto);
        lesson.setRelation(relationship);
        lessonService.addLesson(lesson);
        return ResponseEntity.ok(LessonMapper.toDto(lesson));
    }

    @PostMapping("/{id:\\d+}")
public ResponseEntity<LessonDto> updateLesson(@RequestBody LessonDto lessonDto,
                                                  @PathVariable Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByUsername(authentication.getName());
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        Lesson lesson = lessonService.getLessonById(id);
        if (lesson == null) {
            return ResponseEntity.notFound().build();
        }
        if (lesson.getRelation().getTeacher().getId().equals(user.getId())) {
            if (lessonDto.getName() != null) {
                lesson.setName(lessonDto.getName());
            }
            if (lessonDto.getTheme() != null) {
                lesson.setTheme(lessonDto.getTheme());
            }
            if (lessonDto.getHomework() != null) {
                lesson.setHomework(lessonDto.getHomework());
            }
            if (lessonDto.getIs_done() != null) {
                lesson.setIs_done(lessonDto.getIs_done());
            }
            if (lessonDto.getIs_paid() != null) {
                lesson.setIs_paid(lessonDto.getIs_paid());
            }
            if (lessonDto.getLength() != null) {
                lesson.setLength(lessonDto.getLength());
            }
            if (lessonDto.getTimestamp() != null) {
                lesson.setTimestamp(lessonDto.getTimestamp());
            }
            lessonService.updateLesson(lesson);
            return ResponseEntity.ok(LessonMapper.toDto(lesson));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id:\\d+}")
    public ResponseEntity<LessonDto> deleteLesson(@PathVariable Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByUsername(authentication.getName());
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        Lesson lesson = lessonService.getLessonById(id);
        if (lesson == null) {
            return ResponseEntity.notFound().build();
        }
        if (lesson.getRelation().getTeacher().getId().equals(user.getId())) {
            lessonService.deleteLesson(lesson);
            return ResponseEntity.ok(LessonMapper.toDto(lesson));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/teacher")
    public ResponseEntity<List<LessonDto>> getTeacherLessons() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByUsername(authentication.getName());
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        List<TeacherStudent> relationship = user.getStudents();

        if (relationship == null) {
            return ResponseEntity.notFound().build();
        }
        List<Lesson> lessons = relationship.stream()
                .map(
                        TeacherStudent::getLessons).flatMap(List::stream)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lessons.stream().map(LessonMapper::toDto)
                .sorted((o1, o2) -> o1.getTimestamp().compareTo(o2.getTimestamp()))
                .collect(Collectors.toList()) );

    }

    @GetMapping("/teacher/week")
    public ResponseEntity<List<LessonDto>> getTeacherWeekLessons(@RequestParam String week, @RequestParam String year) {
        System.out.println(week);
        System.out.println(year);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByUsername(authentication.getName());
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        List<TeacherStudent> relationship = user.getStudents();
        if (relationship == null) {
            return ResponseEntity.notFound().build();
        }
        Calendar calendar = Calendar.getInstance();

        List<Lesson> lessons = relationship.stream()
                .map(
                        TeacherStudent::getLessons).flatMap(List::stream)
                .filter(lesson ->{
                    calendar.setTime(lesson.getTimestamp());
                    return calendar.get(Calendar.YEAR) == Integer.parseInt(year)
                                && calendar.get(Calendar.WEEK_OF_YEAR) == Integer.parseInt(week);
                })
                .   toList();

        return ResponseEntity.ok(lessons.stream().map(LessonMapper::toDto)
                .sorted((o1, o2) -> o1.getTimestamp().compareTo(o2.getTimestamp()))
                .collect(Collectors.toList()) );

    }

        @PostMapping("teacher/check/{id:\\d+}")
    public ResponseEntity<LessonDto> checkLesson(@PathVariable Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByUsername(authentication.getName());

        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        Lesson lesson = lessonService.getLessonById(id);
        System.out.println(lesson);
        if (lesson == null) {
            return ResponseEntity.notFound().build();
        }
        if (lesson.getRelation().getTeacher().getId().equals(user.getId())) {
            lesson.setIs_done(!lesson.getIs_done());
            lessonService.updateLesson(lesson);
            return ResponseEntity.ok(LessonMapper.toDto(lesson));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("previous")
    public ResponseEntity<LessonDto> getPreviousLessons(@RequestParam Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByUsername(authentication.getName());
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        if (!lessonService.getLessonById(id).getRelation().getTeacher().getId().equals(user.getId())) {
            return ResponseEntity.notFound().build();
        }
        Lesson lessons = lessonService.getPreviousLesson(id);
        if (lessons == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(LessonMapper.toDto(lessons));
    }

    @PostMapping("teacher/payed/{id:\\d+}")
    public ResponseEntity<LessonDto> payedLesson(@PathVariable Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByUsername(authentication.getName());
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        Lesson lesson = lessonService.getLessonById(id);
        if (lesson == null) {
            return ResponseEntity.notFound().build();
        }
        if (lesson.getRelation().getTeacher().getId().equals(user.getId())) {
            lesson.setIs_paid(!lesson.getIs_paid());
            lessonService.updateLesson(lesson);
            return ResponseEntity.ok(LessonMapper.toDto(lesson));
        }
        return ResponseEntity.notFound().build();
    }
}