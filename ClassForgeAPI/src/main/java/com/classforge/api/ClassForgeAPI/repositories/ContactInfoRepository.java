package com.classforge.api.ClassForgeAPI.repositories;

import com.classforge.api.ClassForgeAPI.dao.ContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactInfoRepository  extends JpaRepository<ContactInfo, Integer> {
}
