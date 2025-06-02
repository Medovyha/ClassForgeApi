package com.classforge.api.ClassForgeAPI.Mappers;

import com.classforge.api.ClassForgeAPI.dao.Info;
import com.classforge.api.ClassForgeAPI.dto.InfoDto;

public class InfoMapper {
    public static InfoDto toDto(Info infoDao) {
        if(infoDao == null) {
            return null;
        }
        return new InfoDto(
                infoDao.getName(),
                infoDao.getSurname(),
                infoDao.getPhoto(),
                infoDao.getDescription(),
                infoDao.getUser().getId(),
                infoDao.getYear()
        );
    }

}
