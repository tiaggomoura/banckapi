package com.bankapi.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import com.bankapi.entities.GenericEntity;
import com.bankapi.exception.CvcBankApiException;
import com.bankapi.repository.GenericRepository;

public abstract class GenericService<T extends GenericEntity<T, VO>, VO> {

	private final GenericRepository<T, VO> repository;

	public GenericService(GenericRepository<T, VO> repository) {
		this.repository = repository;
	}

	public Page<T> getPage(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public T get(Long id) throws CvcBankApiException {
		return repository.findById(id)
				.orElseThrow(() -> new CvcBankApiException("Resource not found for ID: " + id, HttpStatus.NOT_FOUND));
	}

	public List<T> getAll() {
		return repository.findAll();
	}

	@Transactional
	public T update(long id, VO source) throws CvcBankApiException {
		T dbDomain = get(id);
		dbDomain.update(source);
		return repository.save(dbDomain);
	}

	@Transactional
	public T create(T newDomain, VO source) {
		T dbDomain = newDomain.createNewInstance(newDomain, source);
		return repository.save(dbDomain);
	}

	@Transactional
	public T delete(Long id) throws CvcBankApiException {
		T deleted = this.get(id);
		repository.deleteById(id);
		return deleted;
	}
}