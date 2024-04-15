package com.classforge.api.ClassForgeAPI.services;

import com.classforge.api.ClassForgeAPI.dao.User;
import com.classforge.api.ClassForgeAPI.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user){
        return userRepository.save(user);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public void deleteUser(int id){
        userRepository.deleteById(id);
    }

    public User updateUser(Integer id, User userDetails) {
        User user = userRepository.findById(id).get();
        user.setUsername(userDetails.getUsername());

        return userRepository.save(user);
    }

    public User getUserById(int id){
        return userRepository.findById(id).get();
    }

    public User getUserByUsername(String username){
        return userRepository.getOneByUsername(username);
    }

}
