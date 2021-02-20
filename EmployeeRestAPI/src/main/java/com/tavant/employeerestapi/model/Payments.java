package com.tavant.employeerestapi.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payments")
public class Payments{
	@Id
	private Integer paymentId;
	//@ManyToOne
	//@JoinColumn(name = "customerNumber")
	private Integer customerNumber;
	private String checkNumber;
	private LocalDate paymentDate;
	private Double amount;
	

}