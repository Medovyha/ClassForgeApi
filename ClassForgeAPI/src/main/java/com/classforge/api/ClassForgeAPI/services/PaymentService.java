package com.classforge.api.ClassForgeAPI.services;

import com.classforge.api.ClassForgeAPI.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
}
