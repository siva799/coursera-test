package com.tavant.employeerestapi.model;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Orders {
	@Id
	private Integer orderNumber;
	private String orderDate;
	private String requiredDate;
	private String shippedDate;
	private String status;
	private String comments;
	//@ManyToOne
	//@JoinColumn(name = "customerNumber")
	private String customerNumber;
	
}
