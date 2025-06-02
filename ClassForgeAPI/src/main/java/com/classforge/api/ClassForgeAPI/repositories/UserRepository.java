package com.classforge.api.ClassForgeAPI.repositories;

import com.classforge.api.ClassForgeAPI.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    User getOneByUsername(String username);
    User getOneByMail(String email);

    Optional<User> findByMail(String mail);

    Optional<User> findByUsername(String username);

}
