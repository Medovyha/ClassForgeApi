package com.classforge.api.ClassForgeAPI.Mappers;

import com.classforge.api.ClassForgeAPI.dao.ContactInfo;
import com.classforge.api.ClassForgeAPI.dto.ContactInfoDto;

public class ContactInfoMapper {
    public static ContactInfoDto toDto(ContactInfo contactInfo) {
        if (contactInfo == null) {
            return null;
        }
        return new ContactInfoDto(
                contactInfo.getType(),
                contactInfo.getValue()
        );
    }

}
