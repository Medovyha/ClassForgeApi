package com.classforge.api.ClassForgeAPI.services;

import com.classforge.api.ClassForgeAPI.repositories.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceService {
    @Autowired
    private PriceRepository priceRepository;
}
