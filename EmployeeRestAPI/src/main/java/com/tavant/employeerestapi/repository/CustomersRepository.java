package com.tavant.employeerestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tavant.employeerestapi.model.Customers;
@Repository
public interface CustomersRepository extends JpaRepository<Customers, Integer> {

}
