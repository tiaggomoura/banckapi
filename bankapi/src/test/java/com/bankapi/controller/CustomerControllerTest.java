package com.bankapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.bankapi.entities.Customer;
import com.bankapi.exception.CvcBankApiException;
import com.bankapi.service.CustomerService;
import com.bankapi.util.CustomerCreator;
import com.bankapi.util.RequestCustomerVOCreator;
import com.bankapi.vo.RequestCustomerVO;

@ExtendWith(SpringExtension.class)
class CustomerControllerTest {

	@InjectMocks
	private CustomerController controller;

	@Mock
	private CustomerService service;

	@BeforeEach
	void setUp() throws CvcBankApiException {

		BDDMockito.when(this.service.findAll()).thenReturn(List.of(CustomerCreator.createCustomerValid()));

		BDDMockito.when(this.service.findById(ArgumentMatchers.anyLong()))
				.thenReturn(CustomerCreator.createCustomerValid());

		BDDMockito
				.when(this.service.create(ArgumentMatchers.any(Customer.class),
						ArgumentMatchers.any(RequestCustomerVO.class)))
				.thenReturn(CustomerCreator.createCustomerValid());

	}

	@Test
	@DisplayName("findById Returns customer when successful")
	void findById_ReturnsCustomer_WhenSuccessful() throws CvcBankApiException {

		Customer customerValid = CustomerCreator.createCustomerValid();
		Object response = this.controller.getInfo(1L).getResponse();

		Assertions.assertThat(response).isNotNull();
		Assertions.assertThat(customerValid.getId()).isNotNull().isEqualTo(1L);

	}

	@Test
	@DisplayName("findAll Returns customer when successful")
	void findAll_ReturnsCustomer_WhenSuccessful() throws CvcBankApiException {

		@SuppressWarnings("unchecked")
		List<Object> customers = (List<Object>) this.controller.getAll().getResponse();
		Assertions.assertThat(customers).isNotNull().isNotEmpty().hasSize(1);

	}
	
	@Test
	@DisplayName("save Returns customer when successful")
	void saved_ReturnsCustomer_WhenSuccessful() throws CvcBankApiException {
	
		Object response = this.controller.create(RequestCustomerVOCreator.createRequestCustomerVOValid()).getResponse();

		Assertions.assertThat(((Customer) response).getId()).isNotNull().isEqualTo(1L);

	}
	
	//TODO CRUD operation tests...

}
