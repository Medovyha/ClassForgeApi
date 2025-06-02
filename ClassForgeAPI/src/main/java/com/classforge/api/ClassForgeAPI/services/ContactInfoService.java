package com.classforge.api.ClassForgeAPI.services;

import com.classforge.api.ClassForgeAPI.dao.ContactInfo;
import com.classforge.api.ClassForgeAPI.repositories.ContactInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactInfoService {

    private ContactInfoRepository contactInfoRepository;

    ContactInfoService(ContactInfoRepository contactInfoRepository){
        this.contactInfoRepository = contactInfoRepository;
    }
    public void addContactInfo(ContactInfo contactInfo){
        contactInfoRepository.save(contactInfo);
    }

    public ContactInfo getContactInfoByUserId(Integer userId){
        return contactInfoRepository.getContactInfoByUserId(userId);
    }

    public List<ContactInfo> getContactInfosByUserId(Integer userId){
        return contactInfoRepository.getContactInfosByUserId(userId);
    }

    public void deleteContactInfo(ContactInfo contactInfo){
        contactInfoRepository.delete(contactInfo);
    }

    public ContactInfo getContactInfoById(Integer id){
        return contactInfoRepository.findById(id).orElse(null);
    }
}
