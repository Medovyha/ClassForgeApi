package com.classforge.api.ClassForgeAPI.services;

import com.classforge.api.ClassForgeAPI.repositories.InfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class InfoService {
    @Autowired
    private InfoRepository infoRepository;
}
