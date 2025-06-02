package com.classforge.api.ClassForgeAPI.web.controllers;

import com.classforge.api.ClassForgeAPI.dao.Info;
import com.classforge.api.ClassForgeAPI.dao.User;
import com.classforge.api.ClassForgeAPI.dto.InfoDto;
import com.classforge.api.ClassForgeAPI.services.InfoService;
import com.classforge.api.ClassForgeAPI.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/info")
public class InfoController {
    @Autowired
    private UserService userService;
    @Autowired
    private InfoService infoService;

    @GetMapping("/user/{id:\\d+}")
    public ResponseEntity<InfoDto> getUserInfo(@PathVariable Integer id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByUsername(authentication.getName());
        if(user != null){
            Info infoDao = infoService.getInfoByUserId(id);
            System.out.println(infoDao);
            InfoDto infoDto;
            if(infoDao == null){
                infoDto= new InfoDto(
                    "",
                    "",
                    "",
                    "",
                    user.getId(),
                        0
                );
            }
            else{
            infoDto = new InfoDto(
                infoDao.getName(),
                infoDao.getSurname(),
                infoDao.getPhoto(),
                infoDao.getDescription(),
                infoDao.getUser().getId(),
                    infoDao.getYear()
            );}
            return ResponseEntity.ok(infoDto);

        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/user/{id:\\d+}")
    public ResponseEntity<InfoDto> updateUserInfo(@PathVariable Integer id, @RequestBody InfoDto infoDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByUsername(authentication.getName());
        if(user != null){
            Info infoDao = infoService.getInfoByUserId(id);
            if(infoDao == null){
                infoDao = new Info();
                infoDao.setUser(user);
            }
            infoDao.setName(infoDto.getName());
            infoDao.setSurname(infoDto.getSurname());
            infoDao.setPhoto(infoDto.getPhoto());
            infoDao.setDescription(infoDto.getDescription());
            infoService.saveInfo(infoDao);
            return ResponseEntity.ok(infoDto);
        }
        return ResponseEntity.notFound().build();
    }
}
