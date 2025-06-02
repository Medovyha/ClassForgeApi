package com.classforge.api.ClassForgeAPI.dao;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "users")
public class User implements UserDetails {
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
    private boolean isTeacher;

    @OneToOne(mappedBy = "user")
    private Info info;

    @OneToOne(mappedBy = "student", fetch = FetchType.EAGER)
    private TeacherStudent teacher;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "teacher")
    private java.util.List<TeacherStudent> students;

    @OneToMany(mappedBy = "user")
    private java.util.List<ContactInfo> contact_info;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public boolean getIsTeacher() {
        return isTeacher;
    }

    public void setIsTeacher(boolean teacher) {
        isTeacher = teacher;
    }
}
