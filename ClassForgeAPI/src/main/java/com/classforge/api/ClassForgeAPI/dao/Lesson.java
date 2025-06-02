package com.classforge.api.ClassForgeAPI.dao;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

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
    @ColumnDefault("False")
    private Boolean is_done= false;

    @Column(name = "is_paid")
    @ColumnDefault("False")
    private Boolean is_paid=false;

    @Column(name = "length")
    private Integer length = 60;

    @Column(name = "timestamp")
    private java.sql.Timestamp timestamp;

    @ManyToOne
    @JoinColumn(name="relation_id")
    private TeacherStudent relation;
}
