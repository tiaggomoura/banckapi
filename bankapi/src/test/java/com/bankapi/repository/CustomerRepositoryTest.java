package com.bankapi.repository;

import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.bankapi.entities.Customer;
import com.bankapi.util.CustomerCreator;

@DataJpaTest
@DisplayName("Tests for Customer Repository")
class CustomerRepositoryTest {

	@Autowired
	private CustomerRepository repository;

	@Test
	@DisplayName("Save customer when successful")
	void save_PersitenceCustomer_WhenSuccessful() {
		Customer customerToBeSaved = CustomerCreator.createCustomerToBeSaved();
		Customer customerSaved = this.repository.save(customerToBeSaved);

		Assertions.assertThat(customerSaved).isNotNull();
		Assertions.assertThat(customerSaved.getId()).isNotNull();
		Assertions.assertThat(customerSaved.getName()).isEqualTo(customerToBeSaved.getName());

	}

	@Test
	@DisplayName("Update customer when successful")
	void update_PersitenceCustomer_WhenSuccessful() {
		Customer customerToBeSaved = CustomerCreator.createCustomerToBeSaved();
		Customer customerSaved = this.repository.save(customerToBeSaved);
		customerSaved.setName("Tiago Moura");
		Customer customerUpdated = this.repository.save(customerSaved);

		Assertions.assertThat(customerUpdated).isNotNull();
		Assertions.assertThat(customerUpdated.getId()).isNotNull();
		Assertions.assertThat(customerUpdated.getName()).isEqualTo(customerSaved.getName());

	}

	@Test
	@DisplayName("Delete customer when successful")
	void delete_PersitenceCustomer_WhenSuccessful() {
		Customer customerToBeSaved = CustomerCreator.createCustomerToBeSaved();
		Customer customerSaved = this.repository.save(customerToBeSaved);

		this.repository.delete(customerSaved);

		Optional<Customer> customerOp = this.repository.findById(customerSaved.getId());

		Assertions.assertThat(customerOp).isEmpty();

	}

	@Test
	@DisplayName("Find By ID customer when successful")
	void findById_PersitenceCustomer_WhenSuccessful() {
		Customer customerToBeSaved = CustomerCreator.createCustomerToBeSaved();
		Customer customerSaved = this.repository.save(customerToBeSaved);

		Optional<Customer> customerOp = this.repository.findById(customerSaved.getId());

		Assertions.assertThat(customerOp).isNotEmpty();

	}

	/**
	 * Testing Exceptions
	 * 
	 */

	@Test
	@DisplayName("Save throw ConstraintViolationException when name is empty")
	void save_ThrowConstraintViolationException_WhenNameIsEmpty() {
		Customer customer = new Customer();

		Assertions.assertThatThrownBy(() -> this.repository.save(customer))
				.isInstanceOf(ConstraintViolationException.class);
	}

}
