package com.classforge.api.ClassForgeAPI.dao;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "info")
public class Info {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="surname")
    private String surname;

    @Column(name="photo")
    private String photo;

    @Column(name="description")
    private String description;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;
}
