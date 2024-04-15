package com.classforge.api.ClassForgeAPI.dao;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "contact_info")
public class ContactInfo {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;

    @Column(name="type")
    private String type;

    @Column(name="value")
    private String value;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
