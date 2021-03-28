package com.bankapi.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import com.bankapi.vo.RequestCustomerVO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements GenericEntity<Customer, RequestCustomerVO> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private Long id;

	@NotEmpty(message = "Name is Required.")
	private String name;
	private String nationalID;
	private String email;

	@JsonIgnore
	@OneToMany(orphanRemoval = true)	
	@JoinColumn(name = "customer_id")
	private Set<Account> accounts = new HashSet<>();

	public Customer(RequestCustomerVO vo) {
		this.id = vo.getId();
		this.name = vo.getName();
		this.nationalID = vo.getNationalID();
		this.email = vo.getEmail();
	}

	@Override
	public void update(RequestCustomerVO source) {
		this.name = source.getName();
		this.email = source.getEmail();
	}

	@Override
	public Customer createNewInstance(Customer customer, RequestCustomerVO source) {
		return new Customer(source);
	}

}
