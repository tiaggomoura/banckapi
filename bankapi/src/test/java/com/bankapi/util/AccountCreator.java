package com.bankapi.util;

import java.math.BigDecimal;

import org.apache.commons.lang3.RandomStringUtils;

import com.bankapi.entities.Account;

public class AccountCreator {

	public static Account createCustomerToBeSaved() {
		return new Account().builder()
				.id(1L)
				.name("Caixa Econômica")
				.balance(new BigDecimal(100.0))
				.customer(CustomerCreator.createCustomerToBeSaved())
				.number(RandomStringUtils.randomNumeric(6))
				.build();
	}
	
}
