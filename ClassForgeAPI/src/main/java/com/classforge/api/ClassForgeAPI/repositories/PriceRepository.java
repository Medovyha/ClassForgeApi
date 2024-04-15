package com.classforge.api.ClassForgeAPI.repositories;

import com.classforge.api.ClassForgeAPI.dao.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Integer> {
}
