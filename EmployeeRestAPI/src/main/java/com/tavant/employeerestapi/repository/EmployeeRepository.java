package com.tavant.employeerestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tavant.employeerestapi.model.Employee;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
