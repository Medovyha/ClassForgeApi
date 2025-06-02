package com.classforge.api.ClassForgeAPI.services;

import com.classforge.api.ClassForgeAPI.dao.Info;
import com.classforge.api.ClassForgeAPI.repositories.InfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class InfoService {
    @Autowired
    private InfoRepository infoRepository;

    public void addInfo(Info info){
        infoRepository.save(info);
    }

    public Info getInfoByUserId(Integer userId){
        return infoRepository.getInfoByUserId(userId);
    }

    public void saveInfo(Info infoDao) {
        infoRepository.save(infoDao);
    }
}
