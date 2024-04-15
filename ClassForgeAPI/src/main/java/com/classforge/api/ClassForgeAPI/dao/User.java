package com.classforge.api.ClassForgeAPI.dao;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "mail")
    private String mail;

    @Column(name = "created_at", columnDefinition="timestamp")
    private java.sql.Timestamp created_at;

    @Column(name = "is_teacher")
    private Boolean is_teacher;

    @OneToOne(mappedBy = "user")
    private Info info;

    @OneToOne(mappedBy = "teacher", optional = true)
    private teacher_student teacher;

    @OneToMany(mappedBy = "student")
    private java.util.List<teacher_student> students;

    @OneToMany(mappedBy = "user")
    private java.util.List<ContactInfo> contact_info;


}
