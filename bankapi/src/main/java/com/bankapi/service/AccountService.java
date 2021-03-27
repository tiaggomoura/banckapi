package com.bankapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bankapi.entities.Account;
import com.bankapi.entities.Customer;
import com.bankapi.exception.CvcBankApiException;
import com.bankapi.repository.GenericRepository;
import com.bankapi.vo.RequestAccountVO;

@Service
public class AccountService extends GenericService<Account, RequestAccountVO> {

	public AccountService(GenericRepository<Account, RequestAccountVO> repository) {
		super(repository);
	}

	public Account findById(long id) throws CvcBankApiException {
		return this.get(id);
	}

	public List<Account> finddAll() {
		return this.getAll();
	}

	public Account create(RequestAccountVO source, Customer customer) {
		return this.create(new Account().setOwner(customer), source);
	}

}
