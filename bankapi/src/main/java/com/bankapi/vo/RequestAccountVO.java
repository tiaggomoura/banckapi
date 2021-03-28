package com.bankapi.vo;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.bankapi.entities.Account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestAccountVO {

	
	@NotNull(message = "Customer Id is required.")
	private Long customerId;
	private String number;
	
	private String name;
	private BigDecimal balance = BigDecimal.ZERO;

	public RequestAccountVO(String number,String name, Long customerId) {
		this.number = number;
		this.name = name;
		this.customerId = customerId;
	}

	public RequestAccountVO(Account account) {
		super();
		this.name = account.getName();
		this.balance = account.getBalance();
		this.number = account.getNumber();
	}

	public Account toEntity() {
		return new Account(this);
	}
}
