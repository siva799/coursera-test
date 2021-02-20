package com.tavant.employeerestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tavant.employeerestapi.model.OrderDetails;
@Repository
public interface OrdersDetailsRepository extends JpaRepository<OrderDetails, Integer> {

}
