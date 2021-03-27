package com.bankapi.repository;

import org.springframework.stereotype.Repository;

import com.bankapi.entities.Account;
import com.bankapi.vo.RequestAccountVO;

@Repository
public interface AccountRepository extends GenericRepository<Account, RequestAccountVO> {

}
