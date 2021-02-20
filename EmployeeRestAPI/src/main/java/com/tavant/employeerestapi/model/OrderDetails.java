package com.tavant.employeerestapi.model;
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
@Table(name = "orderDetails")
public class OrderDetails {
	@Id
//	@ManyToOne
//	@JoinColumn(name = "orderNumber")
	private Integer orderNumber;
	private String productCode;
	private String quantityOrdered;
	private String priceEach;
	private Integer orderLineNumber;
	public static boolean addOrderDetail(OrderDetails orders) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
