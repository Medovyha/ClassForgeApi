package com.classforge.api.ClassForgeAPI.repositories;

import com.classforge.api.ClassForgeAPI.dao.ContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactInfoRepository  extends JpaRepository<ContactInfo, Integer> {
    ContactInfo getContactInfoByUserId(Integer userId);

    List<ContactInfo> getContactInfosByUserId(Integer userId);
}
