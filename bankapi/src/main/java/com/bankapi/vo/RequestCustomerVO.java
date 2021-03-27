package com.bankapi.vo;

import com.bankapi.entities.Customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestCustomerVO {

	private Long id;
	private String name;
	private String nationalID;
	private String email;

	public RequestCustomerVO(Customer customer) {
		this.id = customer.getId();
		this.name = customer.getName();
		this.nationalID = customer.getNationalID();
		this.email = customer.getEmail();
	}

	public Customer toEntity() {
		return new Customer(this);
	}
}
