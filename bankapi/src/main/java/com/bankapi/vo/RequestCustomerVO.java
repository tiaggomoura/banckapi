package com.bankapi.vo;

import javax.validation.constraints.NotEmpty;

import com.bankapi.entities.Customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestCustomerVO {

	private Long id;
	
	@NotEmpty(message = "Name is Required.")
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
