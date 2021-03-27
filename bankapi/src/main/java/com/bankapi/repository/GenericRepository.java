package com.bankapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.bankapi.entities.GenericEntity;

@NoRepositoryBean
public interface GenericRepository<T extends GenericEntity<T, VO>, VO> extends JpaRepository<T, Long> {
	
}