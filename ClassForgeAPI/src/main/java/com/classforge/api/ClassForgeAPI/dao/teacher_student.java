package com.classforge.api.ClassForgeAPI.dao;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "teacher_student")
public class teacher_student {
    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne
    @JoinColumn(name="teacher_id")
    private User teacher;

    @ManyToOne
    @JoinColumn(name="student_id")
    private User student;

    @Column(name = "is_active")
    private Boolean is_active;

    @Column(name = "description")
    private String description;

    @Column(name = "timetable")
    private String timetable;

    @OneToMany(mappedBy = "relation", fetch = FetchType.LAZY)
    private List<Price> prices;
}
