package com.bankapi.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.bankapi.entities.Account;
import com.bankapi.util.AccountCreator;

@DataJpaTest
@DisplayName("Tests for Account Repository")
class AccountRepositoryTest {

	@Autowired
	private AccountRepository accountRepository;

	@Test
	@DisplayName("Save account when successful")
	void save_PersitenceCustomer_WhenSuccessful() {
		Account accountToBeSaved = AccountCreator.createCustomerToBeSaved();
		Account accountSaved = this.accountRepository.save(accountToBeSaved);

		Assertions.assertThat(accountSaved).isNotNull();
		Assertions.assertThat(accountSaved.getId()).isNotNull();
		Assertions.assertThat(accountSaved.getName()).isEqualTo(accountToBeSaved.getName());
	}
	
	//TODO:: Others tests CRUD ....

}
