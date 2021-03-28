package com.bankapi.util;

import com.bankapi.entities.Customer;

public class CustomerCreator {

	public static Customer createCustomerToBeSaved() {
		return new Customer().builder().name("Tiago Rodrigues").email("trmoura@yahoo.com.br").nationalID("123456798912")
				.build();
	}

	public static Customer createCustomerValid() {
		return new Customer().builder().id(1L).name("Tiago Rodrigues").email("trmoura@yahoo.com.br")
				.nationalID("123456798912").build();
	}

	public static Customer createCustomeUpdatedr() {
		return new Customer().builder().id(1L).name("Tiago Moura Rodrigues").email("trmoura@yahoo.com.br")
				.nationalID("123456798912").build();
	}

}
