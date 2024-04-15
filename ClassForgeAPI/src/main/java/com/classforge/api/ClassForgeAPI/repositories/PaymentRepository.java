package com.classforge.api.ClassForgeAPI.repositories;

import com.classforge.api.ClassForgeAPI.dao.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
