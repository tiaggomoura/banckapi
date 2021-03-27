package com.bankapi.repository;

import org.springframework.stereotype.Repository;

import com.bankapi.entities.Customer;
import com.bankapi.vo.RequestCustomerVO;

@Repository
public interface CustomerRepository extends GenericRepository<Customer, RequestCustomerVO> {

}
