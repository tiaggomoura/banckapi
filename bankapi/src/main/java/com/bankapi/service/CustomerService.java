package com.bankapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bankapi.entities.Customer;
import com.bankapi.exception.CvcBankApiException;
import com.bankapi.repository.GenericRepository;
import com.bankapi.vo.RequestCustomerVO;

@Service
public class CustomerService extends GenericService<Customer, RequestCustomerVO> {

	public CustomerService(GenericRepository<Customer, RequestCustomerVO> repository) {
		super(repository);
	}

	public Customer findById(Long id) throws CvcBankApiException {
		return this.get(id);
	}

	public List<Customer> finddAll() {
		return this.getAll();
	}

}
