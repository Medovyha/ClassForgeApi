package com.classforge.api.ClassForgeAPI.web.controllers;

import com.classforge.api.ClassForgeAPI.dao.ContactInfo;
import com.classforge.api.ClassForgeAPI.dto.ContactInfoDto;
import com.classforge.api.ClassForgeAPI.services.ContactInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/contact-info")
public class ContactInfoController {
    @Autowired
    ContactInfoService contactInfoService;

    @GetMapping("/user/{id:\\d+}")
    public ResponseEntity<List<ContactInfoDto>> getContactInfoByUserId(@PathVariable Integer id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
        List<ContactInfo> contactInfos = contactInfoService.getContactInfosByUserId(id);
        if(contactInfos != null){
            return ResponseEntity.ok(contactInfos.stream().map(contactInfo -> new ContactInfoDto(
                contactInfo.getId(),
                contactInfo.getType(),
                contactInfo.getValue(),
                contactInfo.getUser().getId()
            )).collect(Collectors.toList()));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id:\\d+}")
    public ResponseEntity<Void> deleteContactInfoByUserId(@PathVariable Integer id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ContactInfo contactInfo = contactInfoService.getContactInfoById(id);
        if(contactInfo != null){
            contactInfoService.deleteContactInfo(contactInfo);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id:\\d+}")
    public ResponseEntity<ContactInfoDto> updateContactInfo(@PathVariable Integer id, @RequestBody ContactInfoDto contactInfoDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ContactInfo contactInfo = contactInfoService.getContactInfoById(id);
        if(contactInfo != null){
            contactInfo.setType(contactInfoDto.getType());
            contactInfo.setValue(contactInfoDto.getValue());
            contactInfoService.addContactInfo(contactInfo);
            return ResponseEntity.ok(new ContactInfoDto(
                contactInfo.getId(),
                contactInfo.getType(),
                contactInfo.getValue(),
                contactInfo.getUser().getId()
            ));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/user/{id:\\d+}")
    public ResponseEntity<ContactInfoDto> addContactInfo(@PathVariable Integer id, @RequestBody ContactInfoDto contactInfoDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setType(contactInfoDto.getType());
        contactInfo.setValue(contactInfoDto.getValue());
        contactInfoService.addContactInfo(contactInfo);
        return ResponseEntity.ok(new ContactInfoDto(
            contactInfo.getId(),
            contactInfo.getType(),
            contactInfo.getValue(),
            contactInfo.getUser().getId()
        ));
    }
}
