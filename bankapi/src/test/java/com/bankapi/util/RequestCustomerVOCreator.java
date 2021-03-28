package com.bankapi.util;

import com.bankapi.vo.RequestCustomerVO;

public class RequestCustomerVOCreator {
	
	public static RequestCustomerVO createRequestCustomerVOValid() {
		return new RequestCustomerVO()
				.builder().
				name("Tiago Rodrigues")
				.email("trmoura@yahoo.com.br")
				.nationalID("123456798912")
				.build();
	}

}
