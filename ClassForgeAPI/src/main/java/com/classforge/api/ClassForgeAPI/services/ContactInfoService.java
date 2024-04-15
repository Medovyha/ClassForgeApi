package com.classforge.api.ClassForgeAPI.services;

import com.classforge.api.ClassForgeAPI.repositories.ContactInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactInfoService {
    @Autowired
    private ContactInfoRepository contactInfoRepository;
}
