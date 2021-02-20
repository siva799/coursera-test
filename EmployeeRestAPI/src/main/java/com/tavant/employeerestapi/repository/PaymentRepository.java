package com.tavant.employeerestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tavant.employeerestapi.model.Payments;
@Repository
public interface PaymentRepository extends JpaRepository<Payments, Integer> {

}
