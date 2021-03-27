package com.bankapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankapi.entities.Account;
import com.bankapi.entities.Transfer;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {
	
	List<Transfer> findByOringinOrderByScheduleDateDesc(Account oringin);

}
