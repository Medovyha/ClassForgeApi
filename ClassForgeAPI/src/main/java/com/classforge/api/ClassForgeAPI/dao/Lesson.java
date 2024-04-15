package com.classforge.api.ClassForgeAPI.dao;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "lessons")
public class Lesson {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "theme")
    private String theme;

    @Column(name = "homework")
    private String homework;

    @Column(name = "is_done")
    private Boolean is_done;

    @Column(name = "is_paid")
    private Boolean is_paid;

    @Column(name = "length")
    private Integer length;

    @Column(name = "timestamp")
    private java.sql.Timestamp timestamp;

    @ManyToOne
    @JoinColumn(name="relation_id")
    private teacher_student relation;
}
