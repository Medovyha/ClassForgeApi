package com.classforge.api.ClassForgeAPI.Mappers;

import com.classforge.api.ClassForgeAPI.dao.User;
import com.classforge.api.ClassForgeAPI.dto.UserDto;

import java.util.stream.Collectors;

public class UserMapper {
    public static UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId()) ;
        dto.setUsername(user.getUsername());
        dto.setMail(user.getMail());
        dto.setIsTeacher(user.getIsTeacher());
        if(user.getInfo() != null){
            dto.setInfo(InfoMapper.toDto(user.getInfo()));
        }
        return dto;
    }

    public static User toDao(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setMail(userDto.getMail());
        user.setIsTeacher(userDto.getIsTeacher());
        return user;
    }
}
