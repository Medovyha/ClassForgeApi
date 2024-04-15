package com.classforge.api.ClassForgeAPI.repositories;

import com.classforge.api.ClassForgeAPI.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User getOneByUsername(String username);
    User getOneByMail(String email);
}
